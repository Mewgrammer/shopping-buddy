package github.mewgrammer.shopbuddy.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.annotations.servers.Server
import org.springdoc.core.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@OpenAPIDefinition(
    info = Info(
        title = "Shopbuddy API",
        version = "0.0.1-SNAPSHOT",
        description = "API for a sample project",
        contact = Contact(
            name = "Mewgrammer GitHub",
            url = "https://github.com/Mewgrammer",
        ),
        license = License(
            name = "MIT Licence",
            url = "https://github.com/git/git-scm.com/blob/main/MIT-LICENSE.txt"
        )
    ),
    servers = [Server(url = "http://localhost:9000")],
)
@SecurityScheme(
    name = "api",
    scheme = "basic",
    type = SecuritySchemeType.HTTP,
    `in` = SecuritySchemeIn.HEADER
)
@Configuration
class OpenApiConfig {
    @Bean
    fun hideApis(): GroupedOpenApi? {
        return GroupedOpenApi.builder().group("default")
            .pathsToMatch("/api/**")
            .build()
    }
}