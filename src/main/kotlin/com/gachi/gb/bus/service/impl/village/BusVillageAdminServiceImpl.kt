package com.gachi.gb.bus.service.impl.village

import com.gachi.gb.bus.domain.BusVillage
import com.gachi.gb.bus.dto.BusVillageDto
import com.gachi.gb.bus.repository.BusTownRepository
import com.gachi.gb.bus.repository.BusVillageRepository
import com.gachi.gb.bus.service.BusVillageAdminService
import org.springframework.stereotype.Service

@Service
class BusVillageAdminServiceImpl(
  private val busTownRepository: BusTownRepository,
  private val busVillageRepository: BusVillageRepository
): BusVillageAdminService {
  override fun addVillage(townId: Int, dto: BusVillageDto.Add): String {
    val town = busTownRepository.findById(townId).orElseThrow {
      IllegalArgumentException("해당 마을이 존재하지 않습니다.")
    }

    val village = BusVillage(
      null,
      town,
      dto.name
    )

    busVillageRepository.save(village)

    return "해당 동내 추가가 완료되었습니다.";
  }

  override fun updateVillage(townId: Int, villageId: Int, dto: BusVillageDto.Update): String {
    val town = busTownRepository.findById(townId).orElseThrow {
      IllegalArgumentException("해당 마을이 존재하지 않습니다.")
    }

    val village = busVillageRepository.findById(villageId).orElseThrow {
      IllegalArgumentException("해당 동내가 존재하지 않습니다.")
    }

    if(village.busTown != town) {
      throw IllegalArgumentException("해당 동내와 마을의 관계가 올바르지 않습니다.")
    }

    village.villageName = dto.name

    busVillageRepository.save(village)

    return "해당 동내 수정이 완료되었습니다."
  }

  override fun deleteVillage(townId: Int, villageId: Int): String {
    val town = busTownRepository.findById(townId).orElseThrow {
      IllegalArgumentException("해당 마을이 존재하지 않습니다.")
    }

    val village = busVillageRepository.findById(villageId).orElseThrow {
      IllegalArgumentException("해당 동내가 존재하지 않습니다.")
    }

    if(village.busTown != town) {
      throw IllegalArgumentException("해당 동내와 마을의 관계가 올바르지 않습니다.")
    }

    busVillageRepository.delete(village)

    return "해당 동내 삭제가 완료되었습니다."
  }
}