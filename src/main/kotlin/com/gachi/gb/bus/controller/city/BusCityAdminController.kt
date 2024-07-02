package com.gachi.gb.bus.controller.city

import com.gachi.gb.bus.dto.BusCityAdminDto
import com.gachi.gb.bus.service.BusCityAdminService
import com.gachi.gb.common.response.CommonResponse
import com.gachi.gb.common.response.CommonResponse.Companion.ok
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "관리자 경로 관리", description = "관리자용 경로 관리 API")
@RestController
@RequestMapping("/admin/cities")
class BusCityAdminController(
  private val busCityService: BusCityAdminService
) {
  @Operation(summary = "경로 목록 조회")
  @GetMapping
  fun getCities(): CommonResponse<List<BusCityAdminDto.GetList>> {
    return ok(busCityService.getBusCities())
  }

  @Operation(summary = "경로 상세 조회")
  @GetMapping("/{city}")
  fun getCity(
    @PathVariable(name = "city") cityId: Int
  ): CommonResponse<BusCityAdminDto.Get> {
    return ok(busCityService.getCity(cityId))
  }

  @Operation(summary = "경로 추가")
  @PostMapping("/city")
  fun addCity(
    @RequestBody dto: BusCityAdminDto.Add
  ): CommonResponse<String> {
    return ok(busCityService.addCity(dto))
  }

  @Operation(summary = "경로 수정")
  @PutMapping("/{city}")
  fun updateCity(
    @PathVariable("city") cityId: Int,
    @RequestBody dto: BusCityAdminDto.Update
  ): CommonResponse<String> {
    return ok(busCityService.updateCity(cityId, dto))
  }

  @Operation(summary = "버스 경로 삭제")
  @DeleteMapping("/{city}")
  fun deleteCity(
    @PathVariable("city") cityId: Int
  ): CommonResponse<String> {
    return ok(busCityService.deleteCity(cityId))
  }

}