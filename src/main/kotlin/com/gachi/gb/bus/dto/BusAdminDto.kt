package com.gachi.gb.bus.dto

class BusAdminDto {
  class Add (
    var busNumber: Int,
    var busName: String,
    //지역
    var titleCity: Int,

    var towns: MutableList<Int>,

    var maxTable: Int,
  ) {
  }

  class Update(
    var busNumber: Int,
    var busName: String,
    //지역
    var titleCity: Int,

    var towns: MutableList<Int>,

    var maxTable: Int,
  ) {
  }
}