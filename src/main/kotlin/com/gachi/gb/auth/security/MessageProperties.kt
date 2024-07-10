package com.gachi.gb.auth.security

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "cool-sms")
data class MessageProperties (
  var apiKey: String? = null,
  var apiSecret: String? = null
) {
}