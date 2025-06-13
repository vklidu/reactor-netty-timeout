package org.example

import org.springframework.boot.SpringApplication

object ReactorNettyServerApplication {

  @JvmStatic
  fun main(args: Array<String>) {
    SpringApplication(ReactorNettyServerConfiguration::class.java).run(*args)
  }
}
