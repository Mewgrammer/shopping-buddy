package github.mewgrammer.shopbuddy.testcontainer

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext

open class TestContainerContext : ApplicationContextInitializer<ConfigurableApplicationContext> {
    override fun initialize(configurableApplicationContext: ConfigurableApplicationContext) {
        val runningContainer = PostgresTestContainer()
        TestPropertyValues.of(
            "spring.datasource.url=" + runningContainer.getJdbcUrl(),
            "spring.datasource.username=" + runningContainer.getUsername(),
            "spring.datasource.password=" + runningContainer.getPassword()
        ).applyTo(configurableApplicationContext.environment)
        runningContainer.start()
    }
}