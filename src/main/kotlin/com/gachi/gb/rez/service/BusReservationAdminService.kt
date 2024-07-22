package com.gachi.gb.rez.service

import com.gachi.gb.rez.dto.BusReservationDto
import java.util.UUID

interface BusReservationAdminService {
  fun getBusReservation(reservationsId: UUID): BusReservationDto.Get

  fun getBusReservations(): List<BusReservationDto.GetList>

  fun getBusReservationsByBusId(busId: Int): List<BusReservationDto.GetList>

  fun addBusReservation(userId: String, dto: BusReservationDto.Add): String

  fun updateBusReservation(userId: String, dto: BusReservationDto.Update): String

  fun updateOnReservation(dto: BusReservationDto.OnUpdate, reservationId: UUID): String

  fun deleteBusReservation(userId: String, reservationId: UUID): String

  fun markAsNotBoarded(userId: String): String

}