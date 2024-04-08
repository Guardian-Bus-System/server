package com.gachi.gb.common.response

class CommonResponse<T> (
  status: Int,
  message: String,
  val data: T
): BasicResponse(status, message) {
  constructor(data: T) : this(200, "요청에 성공하셨습니다.", data)

  companion object {
    fun <T> ok(data: T): CommonResponse<T> {
      return CommonResponse(data)
    }

    fun ok(): CommonResponse<String> {
      return CommonResponse("")
    }
  }
}