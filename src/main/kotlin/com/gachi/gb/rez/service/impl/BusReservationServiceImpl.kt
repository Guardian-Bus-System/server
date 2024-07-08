package com.gachi.gb.rez.service.impl

import com.gachi.gb.bus.repository.BusRepository
import com.gachi.gb.rez.domain.BusReservation
import com.gachi.gb.rez.dto.BusReservationDto
import com.gachi.gb.rez.repository.BusReservationRepository
import com.gachi.gb.rez.service.BusReservationService
import com.gachi.gb.user.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class BusReservationServiceImpl (
  private val busReservationsRepository: BusReservationRepository,
  private val busRepository: BusRepository,
  private val userRepository: UserRepository
): BusReservationService {
  override fun getBusReservation(userId: String): BusReservationDto.Get {
    val user = userRepository.findByLoginId(userId).orElseThrow {
      IllegalArgumentException("해당 유저가 존재하지 않습니다.")
    }

    val busReservation = busReservationsRepository.findByUser(user).orElseThrow {
      IllegalArgumentException("버스 예약 목록이 존재하지 않습니다.")
    }

    return BusReservationDto.Get(
      busReservation.id,
      busReservation.bus,
      busReservation.endCity,
      busReservation.onCk,
      busReservation.createAt,
      busReservation.updateAt
    )
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

  override fun updateOnReservation(userId: String, dto: BusReservationDto.OnUpdate, reservationId: UUID): String {
    userRepository.findByLoginId(userId).orElseThrow {
      IllegalArgumentException("해당 유저가 존재하지 않습니다.")
    }

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