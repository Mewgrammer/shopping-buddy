package github.mewgrammer.shopbuddy.testcontainer

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext

open class TestContainerContext : ApplicationContextInitializer<ConfigurableApplicationContext> {
    override fun initialize(configurableApplicationContext: ConfigurableApplicationContext) {
        val runningContainer = PostgresTestContainer()
        TestPropertyValues.of(
            "spring.datasource.url=" + runningContainer.jdbcUrl,
            "spring.datasource.username=" + runningContainer.username,
            "spring.datasource.password=" + runningContainer.password
        ).applyTo(configurableApplicationContext.environment)
        runningContainer.start()
    }
}