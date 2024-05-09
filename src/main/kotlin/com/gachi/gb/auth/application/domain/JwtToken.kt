package com.gachi.gb.auth.application.domain

class JwtToken (
  val accessToken: String,
  val refreshToken: String
) {
}