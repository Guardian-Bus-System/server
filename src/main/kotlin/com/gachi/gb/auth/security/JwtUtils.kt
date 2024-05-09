package com.gachi.gb.auth.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.annotation.PostConstruct
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.security.Key
import java.util.Base64
import java.util.Date
import java.util.stream.Collectors
import javax.crypto.spec.SecretKeySpec

@Component
class JwtUtils (
  private val jwtProperties: JwtProperties
) {
  //decode된 Secret Key를 담는 객체
  var key: Key? = null

  //
  @PostConstruct
  protected fun init() {
    val secretKey = Base64.getEncoder().encodeToString(
      jwtProperties.secretKey!!.toByteArray()
    )
    key = SecretKeySpec(secretKey!!.toByteArray(), SignatureAlgorithm.HS512.jcaName)
  }

  //Access Token
  fun generateAccessToken(authentication: Authentication): String {
    //authentication == userDetails 인터페이스 구현한 객체 이기에 사용자 정보를 담은 userDeails로 형변환하여 다양한 메서드를 처리할 수 있다.
    val principal = authentication.principal as UserDetails


    return generateToken(principal, jwtProperties.jwtExpirationMs!!)
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

  //토큰 유효성 검사
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

  //Token을 이용한 권한 추출
  fun getAuthorities(token: String): List<GrantedAuthority> {
    val claims = getClaims(token)
    val authorities = claims["roles"] as List<*>
    //val authorities = claims.get("roles")
    return authorities.stream()
      .map { SimpleGrantedAuthority(it.toString()) }
      .collect(Collectors.toList())
  }

  //claims 추출
  fun getClaims(token: String): Claims {
    return Jwts.parserBuilder()
      .setSigningKey(key)
      .build()
      .parseClaimsJws(token)
      .body //claims를 전체 반환하기 위해
  }

  //subject로 변환한 claims 추출
  fun getSubject(token: String): String {
    return getClaims(token).subject
  }
}