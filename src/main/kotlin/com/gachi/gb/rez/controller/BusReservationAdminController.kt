package com.gachi.gb.rez.controller

import com.gachi.gb.common.annotation.AuthUserId
import com.gachi.gb.common.response.CommonResponse
import com.gachi.gb.common.response.CommonResponse.Companion.ok
import com.gachi.gb.rez.dto.BusReservationDto
import com.gachi.gb.rez.service.BusReservationAdminService
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

@Tag(name = "관리자 버스 예약 목록 관리", description = "관리자용 버스 예약 관리")
@RestController
@RequestMapping("/admin/reservations")
class BusReservationAdminController (
  private val busReservationsAdminService: BusReservationAdminService
) {
  @Operation(summary = "사용자 예약 목록 일괄 조회")
  @GetMapping("/{reservationsId}")
  fun getBusReservation(
    @PathVariable reservationsId: UUID
  ): CommonResponse<BusReservationDto.Get> {
    return ok(busReservationsAdminService.getBusReservation(reservationsId))
  }

  @Operation(summary = "모든 사용자 예약 목록 조회")
  @GetMapping
  fun getBusReservations(): CommonResponse<List<BusReservationDto.GetList>> {
    return ok(busReservationsAdminService.getBusReservations())
  }

  @Operation(summary = "호차별 탑승 예약 목록 조회")
  @GetMapping("/reservation/{busId}")
  fun getBusReservationsByBusId(
    @PathVariable busId: Int
  ): CommonResponse<List<BusReservationDto.GetList>> {
    return ok(busReservationsAdminService.getBusReservationsByBusId(busId))
  }

  @Operation(summary = "예약 목록 추가")
  @PostMapping
  fun addBusReservation(
    @AuthUserId userId: String,
    @RequestBody dto: BusReservationDto.Add
  ): CommonResponse<String> {
    return ok(busReservationsAdminService.addBusReservation(userId, dto))
  }

  @Operation(summary = "예약 수정")
  @PutMapping
  fun updateBusReservation(
    @AuthUserId userId: String,
    @RequestBody dto: BusReservationDto.Update
  ): CommonResponse<String> {
    return ok(busReservationsAdminService.updateBusReservation(userId, dto))
  }

  @Operation(summary = "사용자 탑승 여부 수정")
  @PutMapping("/{reservationId}")
  fun updateOnReservation(
    @RequestBody dto: BusReservationDto.OnUpdate,
    @PathVariable reservationId: UUID
  ): CommonResponse<String> {
    return ok(busReservationsAdminService.updateOnReservation(dto, reservationId))
  }

  @Operation(summary = "사용자 버스 목록 삭제")
  @DeleteMapping("/{reservationId}")
  fun deleteBusReservation(
    @AuthUserId userId: String,
    @PathVariable reservationId: UUID
  ): CommonResponse<String> {
    return ok(busReservationsAdminService.deleteBusReservation(userId, reservationId))
  }
}