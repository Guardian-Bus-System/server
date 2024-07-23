package com.gachi.gb.rez.service.impl

import com.gachi.gb.bus.repository.BusRepository
import com.gachi.gb.rez.domain.BusReservation
import com.gachi.gb.rez.dto.BusReservationDto
import com.gachi.gb.rez.repository.BusReservationRepository
import com.gachi.gb.rez.service.BusReservationAdminService
import com.gachi.gb.user.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class BusReservationAdminServiceImpl (
  private val busReservationsRepository: BusReservationRepository,
  private val busRepository: BusRepository,
  private val userRepository: UserRepository
): BusReservationAdminService {
  override fun getBusReservation(reservationsId: UUID): BusReservationDto.Get {
    val busReservation = busReservationsRepository.findById(reservationsId).orElseThrow {
      IllegalArgumentException("해당 예약목록이 존재하지 않습니다.")
    }

    return BusReservationDto.Get(
      busReservation.id,
      busReservation.user,
      busReservation.bus,
      busReservation.endCity,
      busReservation.onCk,
      busReservation.createAt,
      busReservation.updateAt
    )
  }

  override fun getBusReservations(): List<BusReservationDto.GetList> {
    return busReservationsRepository.findAll().map {
        BusReservationDto.GetList(
          it.id,
          it.user,
          it.bus,
          it.endCity,
          it.onCk,
          it.createAt,
          it.updateAt
        )
    }
  }

  override fun getBusReservationsByBusId(busId: Int): List<BusReservationDto.GetList> {
    val bus = busRepository.findById(busId).orElseThrow {
      IllegalArgumentException("해당 버스가 존재하지 않습니다.")
    }

    return busReservationsRepository.findAllByBus(bus).map {
      BusReservationDto.GetList(
        it.id,
        it.user,
        it.bus,
        it.endCity,
        it.onCk,
        it.createAt,
        it.updateAt
      )
    }
  }

  override fun addBusReservation(userId: String, dto: BusReservationDto.Add): String {
    val user = userRepository.findByLoginId(userId).orElseThrow {
      IllegalArgumentException("해당 유저가 존재하지 않습니다.")
    }

    val bus = busRepository.findByBusNumber(dto.busNumber).orElseThrow {
      IllegalArgumentException("해당 버스가 존재하지 않습니다.")
    }

    if(busReservationsRepository.existsByUser(user)) {
      throw IllegalArgumentException("이미 버스 예약 목록이 존재합니다.")
    }

    if(bus.maxTable != 0) {
      bus.maxTable -= 1;
    } else {
      throw IllegalArgumentException("버스에 탑승인원이 초과하였습니다.")
    }

    busRepository.save(bus)

    val busReservation = BusReservation(
      null,
      bus,
      dto.endCity,
      true,
      LocalDateTime.now(),
      null,
      user
    )

    busReservationsRepository.save(busReservation)

    return "버스 예약이 완료되었습니다."
  }

  override fun updateBusReservation(userId: String, dto: BusReservationDto.Update): String {
    val user = userRepository.findByLoginId(userId).orElseThrow{
      IllegalArgumentException("해당 유저가 존재하지 않습니다.")
    }

    val bus = busRepository.findByBusNumber(dto.busNumber).orElseThrow {
      IllegalArgumentException("해당 버스가 존재하지 않습니다.")
    }

    val busReservation = busReservationsRepository.findByUser(user).orElseThrow {
      IllegalArgumentException("해당 버스 예약 목록이 존재하지 않습니다.")
    }

    val lastBus = busRepository.findById(busReservation.bus.id!!).orElseThrow {
      IllegalArgumentException("이전 호차가 존재하지 않습니다.")
    }

    lastBus.maxTable += 1
    busRepository.save(lastBus)

    if(bus.maxTable != 0) {
      bus.maxTable -= 1;
    } else {
      throw IllegalArgumentException("버스에 탑승인원이 초과하였습니다.")
    }

    busRepository.save(bus)

    val updateBusReservation = BusReservation(
      busReservation.id,
      bus,
      dto.endCity,
      true,
      busReservation.createAt,
      LocalDateTime.now(),
      user,
    )

    busReservationsRepository.save(updateBusReservation)

    return "버스 예약 정보가 수정 되었습니다."
  }

  override fun updateOnReservation(dto: BusReservationDto.OnUpdate, reservationId: UUID): String {
    val busReservation = busReservationsRepository.findById(reservationId).orElseThrow {
      IllegalArgumentException("해당 버스 예약 목록이 존재하지 않습니다.")
    }

    busReservation.onCk = dto.onCk

    busReservationsRepository.save(busReservation)

    return "탑승 여부 수정이 완료되었습니다."
  }

  override fun deleteBusReservation(userId: String, reservationId: UUID): String {
    userRepository.findByLoginId(userId).orElseThrow {
      IllegalArgumentException("해당 유저가 존재하지 않습니다.")
    }

    val busReservation = busReservationsRepository.findById(reservationId).orElseThrow {
      IllegalArgumentException("해당 버스 예약 목록이 존재하지 않습니다.")
    }

    val bus = busReservation.bus

    bus.maxTable += 1

    busRepository.save(bus)

    busReservationsRepository.delete(busReservation)

    return "버스 예약 정보가 초기화 되었습니다."
  }

  override fun markAsNotBoarded(userId: String): String {
    val user = userRepository.findByLoginId(userId).orElseThrow {
      IllegalArgumentException("유저가 존재하지 않습니다.")
    }

    val busReservation = busReservationsRepository.findByUser(user).orElseThrow {
      IllegalArgumentException("해당 유저의 예약 목록이 존재하지 않습니다.")
    }

    busReservation.onCk = false

    busReservationsRepository.save(busReservation)

    return "\"탑승하지 않음\"으로 설정 되었습니다."
  }
}