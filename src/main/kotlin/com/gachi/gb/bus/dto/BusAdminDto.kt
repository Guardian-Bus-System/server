package com.gachi.gb.bus.dto

class BusAdminDto {
  class Add (
    var busNumber: Int,
    var busName: String,
    //지역
    var middleCityId: Int,
    var endCityId: Int,

    var maxTable: Int,
  ) {
  }

  class Update(
    var busNumber: Int,
    var busName: String,
    //지역
    var middleCityId: Int,
    var endCityId: Int,

    var maxTable: Int,
  ) {
  }
}