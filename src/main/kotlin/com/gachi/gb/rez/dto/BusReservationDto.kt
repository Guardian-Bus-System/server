package com.gachi.gb.rez.dto

import com.gachi.gb.bus.domain.Bus
import org.apache.catalina.User
import java.time.LocalDateTime
import java.util.UUID

class BusReservationDto {
  class Get (
    var id: UUID?,
    var busInfo: Bus,
    var endCity: String,
    var onCk: Boolean?,
    var createAt: LocalDateTime,
    var updateAt: LocalDateTime?,
  )

  class GetList (
    var id: UUID?,
    var busInfo: Bus,
    var endCity: String,
    var onCk: Boolean?,
    var createAt: LocalDateTime,
    var updateAt: LocalDateTime?,
  )

  class Add (
    var busNumber: Int,
    var endCity: String
  )

  class Update (
    var busNumber: Int,
    var endCity: String
  )

  class OnUpdate (
    var onCk: Boolean
  )

}