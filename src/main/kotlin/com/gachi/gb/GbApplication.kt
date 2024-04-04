package com.gachi.gb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GbApplication

fun main(args: Array<String>) {
  runApplication<GbApplication>(*args)
}
