package org.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@SpringBootApplication
open class ReactorNettyServerConfiguration {

  @Controller
  class UploadController {
    @GetMapping("/timeout")
    fun timeout(): ResponseEntity<Void> {
      Thread.sleep(3000)
      return ResponseEntity.internalServerError().build()
    }

    @GetMapping("/test")
    fun test(): ResponseEntity<Void> {
      return ResponseEntity.ok().build()
    }
  }

}