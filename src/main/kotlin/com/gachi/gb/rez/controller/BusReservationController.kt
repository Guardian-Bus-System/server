package com.gachi.gb.rez.controller

import com.gachi.gb.common.annotation.AuthUserId
import com.gachi.gb.common.response.CommonResponse
import com.gachi.gb.common.response.CommonResponse.Companion.ok
import com.gachi.gb.rez.dto.BusReservationDto
import com.gachi.gb.rez.service.BusReservationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@Tag(name = "사용자 버스 예약", description = "사용자용 버스 예약 API")
@RestController
@RequestMapping("/reservations")
class BusReservationController (
  private val busReservationsService: BusReservationService
) {
  @Operation(summary = "사용자 버스 목록 조회")
  @GetMapping("/me")
  fun getBusReservation(
    @AuthUserId userId: String
  ): CommonResponse<BusReservationDto.Get> {
    return ok(busReservationsService.getBusReservation(userId))
  }

  @Operation(summary = "예약 추가")
  @PostMapping
  fun addBusReservation(
    @AuthUserId userId: String,
    @RequestBody dto: BusReservationDto.Add
  ): CommonResponse<String> {
    return ok(busReservationsService.addBusReservation(userId, dto))
  }

  @Operation(summary = "예약 수정")
  @PutMapping
  fun updateBusReservation(
    @AuthUserId userId: String,
    @RequestBody dto: BusReservationDto.Update
  ): CommonResponse<String> {
    return ok(busReservationsService.updateBusReservation(userId, dto))
  }

  @PutMapping("/{reservationId}")
  fun updateOnReservation(
    @AuthUserId userId: String,
    @RequestBody dto: BusReservationDto.OnUpdate,
    @PathVariable reservationId: UUID
  ): CommonResponse<String> {
    return ok(busReservationsService.updateOnReservation(userId, dto, reservationId))
  }

  @Operation(summary = "사용자 버스 목록 삭제")
  @DeleteMapping("/{reservationId}")
  fun deleteBusReservation(
    @AuthUserId userId: String,
    @PathVariable reservationId: UUID
  ): CommonResponse<String> {
    return ok(busReservationsService.deleteBusReservation(userId, reservationId))
  }
}