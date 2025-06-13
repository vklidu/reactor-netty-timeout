package org.example

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import reactor.netty.http.HttpProtocol
import reactor.netty.http.client.HttpClient
import reactor.netty.resources.ConnectionProvider
import java.util.concurrent.TimeoutException
import kotlin.time.Duration.Companion.seconds
import kotlin.time.toJavaDuration

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ReactorNettyServerTest {

  @Test
  fun uploadTest404() {
    val connectionProvider = ConnectionProvider.create("my-pool", 1)
    val httpClient = HttpClient.create(connectionProvider)
      .protocol(HttpProtocol.HTTP11, HttpProtocol.H2C)
      .http2Settings {
        it.maxConcurrentStreams(10)
        it.maxStreams(100)
      }

    httpClient
      .get()
      .uri("http://localhost:8080/test")
      .response()
      .block(2.seconds.toJavaDuration())!!.status().code() shouldBe 200

    shouldThrow<RuntimeException> {
      httpClient
        .get()
        .uri("http://localhost:8080/timeout")
        .response()
        .block(2.seconds.toJavaDuration())
    }.cause.shouldBeInstanceOf<TimeoutException>()

    Thread.sleep(2.seconds.toJavaDuration())
    httpClient
      .get()
      .uri("http://localhost:8080/test")
      .response()
      .block(2.seconds.toJavaDuration())!!.status().code() shouldBe 200
  }
}
