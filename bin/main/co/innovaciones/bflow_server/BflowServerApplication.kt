package co.innovaciones.bflow_server

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.servers.Server
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@OpenAPIDefinition(
    servers = [Server(url = "/")]
)
@SpringBootApplication
class BflowServerApplication

fun main(args: Array<String>) {
    runApplication<BflowServerApplication>(*args)
}
