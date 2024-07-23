package com.gachi.gb.rez.service

import com.gachi.gb.rez.dto.BusReservationDto
import java.util.UUID

interface BusReservationService {
  fun getBusReservation(userId: String): BusReservationDto.Get

  fun updateBusReservation(userId: String, dto: BusReservationDto.Update): String

  fun updateOnReservation(dto: BusReservationDto.OnUpdate, reservationId: UUID): String

  fun deleteBusReservation(userId: String, reservationId: UUID): String

  fun markAsNotBoarded(userId: String): String

}