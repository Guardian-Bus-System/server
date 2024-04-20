//package com.gachi.gb.auth.security
//
//import org.slf4j.LoggerFactory
//import java.security.NoSuchAlgorithmException
//import java.lang.IllegalStateException
//import java.security.MessageDigest
//import java.util.*
//
//class UserIdEncoder {
//  private val log = LoggerFactory.getLogger(javaClass)
//
//  fun encode(password: String): String {
//    return try {
//      val md = MessageDigest.getInstance(SHA_512)
//      md.update(password.toByteArray())
//      Base64.getEncoder().withoutPadding().encodeToString(md.digest())
//    } catch (e: NoSuchAlgorithmException) {
//      log.error(e.localizedMessage)
//      throw IllegalStateException("유효하지 않은 암호화 방식입니다.")
//    }
//  }
//
//  companion object {
//    private const val SHA_256 = "SHA-256"
//    private const val SHA_512 = "SHA-512"
//  }
//}