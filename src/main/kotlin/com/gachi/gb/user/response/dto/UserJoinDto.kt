package com.gachi.gb.user.response.dto

import com.gachi.gb.user.domain.ClassInfo
import jakarta.persistence.Column

class UserJoinDto (
  var loginId: String,
  var pw: String,
  //이름 ex)정태양
  var name: String,
  //전화번호 ex) 010-7228-6054
  var call: String,

  //학급 정보 ex) 고3학년 1반
  var gradeClass: String,
  //학급 번호 ex) 14
  var number: Int,

  ////탑승 여부 ex) true
  var usingCk: Boolean,

  //탑승 호차
  var boardingBus: Int,

  //탑승 호차 상세 지역
  var detailsLine: String,

  //변경 호차
  var changeBoardingBus: Int?
) {
}