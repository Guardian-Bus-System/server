package com.gachi.gb.bus.dto

import com.gachi.gb.bus.domain.BusVillage

class BusTownDto {
  class Get(
    var id: Int?,
    var townName: String,
    var busVillages: MutableList<BusVillage>
  )
}