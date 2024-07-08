package com.gachi.gb.auth.config

import com.gachi.gb.auth.security.JwtUtils
import com.gachi.gb.auth.security.filter.JwtAuthenticationFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.EnvironmentAware
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.util.*

@Configuration
@EnableWebSecurity
class SecurityConfig (
  val authProviders: List<AuthenticationProvider>?
): EnvironmentAware {
  lateinit var env: Environment

  override fun setEnvironment(environment: Environment) {
    this.env = environment
  }

  @Autowired
  fun registerProvider(authenticationManagerBuilder: AuthenticationManagerBuilder) {
    authProviders?.forEach(authenticationManagerBuilder::authenticationProvider)
  }

  @Bean
  fun filterChain(
    authenticationConfiguration: AuthenticationConfiguration,
    http: HttpSecurity,
    jwtUtils: JwtUtils
  ): SecurityFilterChain {
    //동일한 출처의 프레임 허용
    //ClickJacking 공격을 방지하기 위해.
    http.headers {
      it.frameOptions{ frames ->
        frames.disable()
      }
    }
    http.cors {
      it.configurationSource(corsConfigurationSource())
    }

    http.csrf {
      it.disable()
        .addFilterBefore(authenticationFilter(authenticationManager(authenticationConfiguration)), UsernamePasswordAuthenticationFilter::class.java)
    }

//    http.exceptionHandling {

//    }

    //session 비활성화
    http.sessionManagement {
      it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    http.authorizeHttpRequests {

//      it.requestMatchers("/admin/**").hasRole("ADMIN")

      // permitAll: 아무나 요청 허용
      it.requestMatchers(
        "/",
        "/css/**",
        "/html/**",
        "/js/**",
        "/images/**",
        "/png/**",
        "/jpg/**"
      ).permitAll()

      //로그인 전
      it.requestMatchers(
        "/**",
        "/auth/**"
      ).permitAll()

      //로그인 이후
      it.requestMatchers(
        "/user/**",
        "/bus/**",
        "/buses/**",
        "/notices/**",
        "/reservations/**",
        "/rules/**"
      ).authenticated()

      it.requestMatchers(
        "/admin/**"
      ).hasRole("ADMIN")

      it.requestMatchers(HttpMethod.OPTIONS, "/**/*")
        .permitAll()

      it.anyRequest()
        .authenticated()
    }

    //provider등록
    authProviders!!.forEach {
      http.authenticationProvider(it)
    }

    return http.build()
  }
  @Bean
  fun corsConfigurationSource(): CorsConfigurationSource? {
    val configuration = CorsConfiguration()//요청이 허용되지 않은 새 인스턴스
    configuration.allowedOriginPatterns = listOf(
      "*"
    )
    if (Arrays.stream(env.activeProfiles)
        .anyMatch { profile: String -> "dev" == profile || "local" == profile || "linkprod" == profile || "staging" == profile }
    ) {
      configuration.allowedOriginPatterns = listOf(
        "**"
      )
    }
    configuration.allowedMethods = listOf(
      "HEAD",
      "GET",
      "POST",
      "PUT",
      "DELETE",
      "OPTIONS"
    )
    configuration.allowedHeaders = listOf(
      "Authorization",
      "TOKEN_ID",
      "X-Requested-With",
      "Content-Type",
      "Content-Length",
      "Cache-Control",
      "Cookie",
      "Tracker"
    )
    configuration.allowCredentials = true
    val source = UrlBasedCorsConfigurationSource()
    source.registerCorsConfiguration("/**", configuration)
    return source
  }

  @Bean
  fun authenticationFilter(authenticationManager: AuthenticationManager): JwtAuthenticationFilter? {
    return JwtAuthenticationFilter(authenticationManager)
  }

  @Bean
  fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
    return authenticationConfiguration.authenticationManager
  }

  @Bean
  fun passwordEncoder(): PasswordEncoder {
    return BCryptPasswordEncoder()
  }

}