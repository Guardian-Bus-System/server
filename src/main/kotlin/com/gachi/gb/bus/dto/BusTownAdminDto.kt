package com.gachi.gb.bus.dto

import com.gachi.gb.bus.domain.BusVillage

class BusTownAdminDto {
  class Get(
    var id: Int?,
    var townName: String,
    var busVillages: MutableList<BusVillage>
  )
  class Add(
    var name: String
  )

  class Update(
    var name: String
  )

}