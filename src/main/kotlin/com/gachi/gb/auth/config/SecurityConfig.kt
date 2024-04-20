package com.gachi.gb.auth.config

import com.gachi.gb.auth.security.filter.JwtAuthenticationFilter
import org.springframework.context.EnvironmentAware
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.util.*

@Configuration
@EnableWebSecurity
class SecurityConfig (
): EnvironmentAware, SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
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
    //Clickjacking 공격을 방지하기 위해.
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
        "/html/**",
        "/css/**",
        "/js/**",
      ).permitAll()

      it.requestMatchers(
        "/login",
        "/signUp",
        "/user",
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
      it.logoutSuccessUrl("login")
        .invalidateHttpSession(true)
    }
    http.csrf {
      it.disable()
    }

    return http.build()
  }
  @Bean
  fun corsConfigurationSource(): CorsConfigurationSource? {
    val configuration = CorsConfiguration()//요청이 허용되지 않은 새 인스턴스
    configuration.allowedOriginPatterns = Arrays.asList(
      // 추후 도메인 추가
      "*"
    )
    if (Arrays.stream(env.activeProfiles)
        .anyMatch { profile: String -> "dev" == profile || "local" == profile || "linkprod" == profile || "staging" == profile }
    ) {
      configuration.allowedOriginPatterns = Arrays.asList(
        "*"
      )
    }
    configuration.allowedMethods = Arrays.asList(
      "HEAD",
      "GET",
      "POST",
      "PUT",
      "DELETE",
      "OPTIONS"
    )
    configuration.allowedHeaders = Arrays.asList(
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
  fun JwtAuthenticationFilter(authenticationManager: AuthenticationManager): JwtAuthenticationFilter? {
    return JwtAuthenticationFilter(authenticationManager)
  }

  @Bean
  fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
    return authenticationConfiguration.authenticationManager
  }

  @Bean
  fun passwordEncoder(): BCryptPasswordEncoder {
    return BCryptPasswordEncoder()
  }

}