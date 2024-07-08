//package com.gachi.gb.auth.application.service
//import net.minidev.json.JSONObject
//import org.aspectj.bridge.Message
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.stereotype.Service
//import java.util.Random
//
//@Service
//class MessageService {
//
//  @Value("\${coolsms.apikey}")
//  private lateinit var apiKey: String
//
//  @Value("\${coolsms.apisecret}")
//  private lateinit var apiSecret: String
//
//  @Value("\${coolsms.fromnumber}")
//  private lateinit var fromNumber: String
//
//  private fun createRandomNumber(): String {
//    val rand = Random()
//    return (1..4)
//      .map { rand.nextInt(10).toString() }
//      .joinToString("")
//  }
//
//  private fun makeParams(to: String, randomNum: String): HashMap<String, String> {
//    return hashMapOf(
//      "from" to fromNumber,
//      "type" to "SMS",
//      "app_version" to "test app 1.2",
//      "to" to to,
//      "text" to randomNum
//    )
//  }
//
//  // 인증번호 전송하기
//  fun sendSMS(phonNumber: String): String {
//    val coolsms = Message(apiKey, apiSecret)
//
//    // 랜덤한 인증 번호 생성
//    val randomNum = createRandomNumber()
//    println(randomNum)
//
//    // 발신 정보 설정
//    val params = makeParams(phonNumber, randomNum)
//
//    try {
//      val obj = coolsms.send(params) as JSONObject
//      println(obj.toString())
//    } catch (e: CoolsmsException) {
//      println(e.message)
//      println(e.code)
//    }
//
//    return "문자 전송이 완료되었습니다."
//  }
//}