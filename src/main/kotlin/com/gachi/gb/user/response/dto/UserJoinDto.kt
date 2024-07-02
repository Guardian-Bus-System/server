package com.gachi.gb.user.response.dto

class UserJoinDto (
  var loginId: String,
  var pw: String,
  //이름 ex)정태양
  var name: String,
  //전화번호 ex) 010-7228-6054
  var phoneNumber: String,

  //학급 정보 ex) 고3학년 1반
  var gradeClass: String,
  //학급 번호 ex) 14
  var number: Int
) {
}