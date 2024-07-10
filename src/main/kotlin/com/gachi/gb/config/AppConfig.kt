package com.gachi.gb.config

import com.gachi.gb.auth.security.MessageProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(MessageProperties::class)
class AppConfig {
}