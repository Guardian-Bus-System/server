package com.gachi.gb.common.advice.exception

import com.gachi.gb.common.response.ErrorResponse
import io.jsonwebtoken.*
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.security.SignatureException

/**
 * RestController에서 발생하는 JWT와 관련된 예외를 처리하는 Advice 입니다.
 *
 * @author KDY
 */
@RestControllerAdvice(annotations = [RestController::class])
@Order(Ordered.HIGHEST_PRECEDENCE)
class JwtExceptionAdvice : ExceptionAdvice() {
  @ExceptionHandler(value = [MalformedJwtException::class, ExpiredJwtException::class, UnsupportedJwtException::class, SignatureException::class])
  @ResponseStatus(
    HttpStatus.BAD_REQUEST
  )
  fun handleExpiredJwtException(): ErrorResponse {
    return unauthorized(JWT_ERROR_MESSAGE)
  }

  companion object {
    const val JWT_ERROR_MESSAGE = "토큰이 손상되었거나 만료되었습니다."
  }
}