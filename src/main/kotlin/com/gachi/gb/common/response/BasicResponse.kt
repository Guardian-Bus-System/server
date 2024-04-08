package com.gachi.gb.common.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.sql.Timestamp

// abstract = 추상 클레스: interface의 기능도 하면서 class의 기능을 가지고 있는 돌연변이 class 입니다.
abstract class BasicResponse {
  val status: Int
  val message: String

  @JsonFormat(shape = JsonFormat.Shape.STRING)
  val timestamp: Timestamp

  constructor(status: Int, message: String) {
    this.status = status
    this.message = message
    timestamp = Timestamp(System.currentTimeMillis())
  }

  constructor(status: Int, message: String, timestamp: Timestamp) {
    this.status = status
    this.message = message
    this.timestamp = timestamp
  }

}