//package com.gachi.gb.auth.security.token.cookie
//
//import jakarta.servlet.http.Cookie
//import jakarta.servlet.http.HttpServletResponse
//import org.springframework.stereotype.Component
//
//@Component
//class ClearCookies {
//  companion object {
//    fun clear(response: HttpServletResponse) {
//      val accessTokenCookie = Cookie("AccessToken", null)
//      accessTokenCookie.maxAge = 0
//      accessTokenCookie.path = "/"
//      accessTokenCookie.isHttpOnly = true
//
//      val refreshTokenCookie = Cookie("RefreshToken", null)
//      refreshTokenCookie.maxAge = 0
//      refreshTokenCookie.path = "/"
//      refreshTokenCookie.isHttpOnly = true
//
//      response.addCookie(accessTokenCookie)
//      response.addCookie(refreshTokenCookie)
//    }
//  }
//}