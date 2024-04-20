package com.gachi.gb.auth.security

import com.gachi.gb.auth.security.authentication.jwt.JwtProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.security.Key
import java.util.Base64
import java.util.Date
import java.util.stream.Collector
import java.util.stream.Collectors

@Component
class JwtUtils (
  private val jwtProperties: JwtProperties
) {
  //decode된 Secret Key를 담는 객체
  var key: Key? = null

  //
  @PostConstruct
  fun init() {
    val bytes: ByteArray = Base64.getDecoder().decode(jwtProperties.secretKey)
    key = Keys.hmacShaKeyFor(bytes)
  }

  //Access Token
  fun generateAccessToken(authentication: Authentication): String {
    //authentication == userDetails 인터페이스 구현한 객체 이기에 사용자 정보를 담은 userDeails로 형변환하여 다양한 메서드를 처리할 수 있다.
    val userDetails = authentication.principal as UserDetails


    return generateToken(userDetails, jwtProperties.jwtExpirationMs!!)
  }

  //Refresh Token
  fun generateRefreshToken(authentication: Authentication): String {
    val principal = authentication.principal as UserDetails

    return generateToken(principal, jwtProperties.jwtRefreshExpirationMs!!)
  }


  fun generateToken(userPrincipal: UserDetails, expirationTime: Int): String {
    val now = Date()
    val expiryDate = Date(now.time + expirationTime)

    val authorities = userPrincipal.authorities.stream()
      .map(GrantedAuthority:: getAuthority)
      .toList()

    val claims = Jwts
      .claims()
      .setSubject(userPrincipal.username)
    claims["roles"] = authorities

    return Jwts.builder()
      .setIssuedAt(now)
      .setExpiration(expiryDate)
      .setClaims(claims)
      .signWith(SignatureAlgorithm.HS512, key)
      .compact()
  }

  fun validationToken(token: String): Boolean {
    return try {
      Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
      true
    } catch (e: Exception) {
      false
    }
  }

  fun getAuthorities(token: String): List<GrantedAuthority> {
    val claims = getClaims(token)
    val authorities = claims["roles"] as List<String>
    //val authorities = claims.get("roles")
    return authorities.stream()
      .map { SimpleGrantedAuthority(it) }
      .collect(Collectors.toList())


  }

  fun getClaims(token: String): Claims {
    return Jwts.parserBuilder()
      .setSigningKey(key)
      .build()
      .parseClaimsJws(token)
      .body //claims를 전체 반환하기 위해
  }

  fun getSubject(token: String): String {
    return getClaims(token).subject
  }
}