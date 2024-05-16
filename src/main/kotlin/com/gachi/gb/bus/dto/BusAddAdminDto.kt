package com.gachi.gb.bus.dto

class BusAddAdminDto (
  var busNumber: Int,
  var busName: String,
  //지역
  var areaName: String,
  var maxTable: Int,
) {
}