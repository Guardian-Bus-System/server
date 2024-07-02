package com.gachi.gb.bus.dto

import com.gachi.gb.bus.domain.BusTown

class BusCityAdminDto {
  class GetList (
    var id: Int?,
    var name: String
  ) {
  }

  class Get (
    var id: Int?,
    var name: String,
    var towns: MutableList<BusTown>?
  )

  class Add (
    var name: String
  )

  class Update (
    var name: String
  )
}