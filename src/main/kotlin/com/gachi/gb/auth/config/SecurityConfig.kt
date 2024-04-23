package com.gachi.gb.auth.config

import com.gachi.gb.auth.security.filter.JwtAuthenticationFilter
import org.springframework.context.EnvironmentAware
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
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

  @Bean
  fun filterChain(
    authenticationConfiguration: AuthenticationConfiguration,
    http: HttpSecurity
  ): SecurityFilterChain {
    //동일한 출처의 프레임 허용
    //ClickJacking 공격을 방지하기 위해.
    http.headers {
      it.frameOptions{ frames ->
        frames.sameOrigin()
      }
    }
    http.cors {
      it.configurationSource(corsConfigurationSource())
    }
    http.addFilterBefore(JwtAuthenticationFilter(authenticationManager(authenticationConfiguration)), UsernamePasswordAuthenticationFilter::class.java)

    http.authorizeHttpRequests {
      //정적 소스 허용
      it.requestMatchers(
        "/",
        "/css/**",
        "/html/**",
        "/js/**",
        "/images/**",
        "/png/**",
        "/jpg/**"
      ).permitAll()

      it.requestMatchers(
        "/**", //TODO: 테스팅용으로 모든 request 허용
        "/auth/**",
        "/user/**"
      ).permitAll()

      it.requestMatchers(HttpMethod.OPTIONS, "/**")
        .permitAll()
        .anyRequest().authenticated()
    }
    http.formLogin {
      it.loginPage("/login")
        .defaultSuccessUrl("/articles")
    }
    http.logout {
      it.logoutSuccessUrl("/login")
        .invalidateHttpSession(true)
    }
    http.csrf {
      it.disable()
    }

    authProviders!!.forEach {
      http.authenticationProvider(it)
    }

    return http.build()
  }
  @Bean
  fun corsConfigurationSource(): CorsConfigurationSource? {
    val configuration = CorsConfiguration()//요청이 허용되지 않은 새 인스턴스
    configuration.allowedOriginPatterns = listOf(
      // 추후 도메인 추가
      "*"
    )
    if (Arrays.stream(env.activeProfiles)
        .anyMatch { profile: String -> "dev" == profile || "local" == profile || "linkprod" == profile || "staging" == profile }
    ) {
      configuration.allowedOriginPatterns = listOf(
        "*"
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