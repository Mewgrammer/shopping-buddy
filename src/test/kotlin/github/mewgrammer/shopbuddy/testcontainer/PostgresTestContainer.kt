package github.mewgrammer.shopbuddy.testcontainer

import org.testcontainers.containers.PostgreSQLContainer


class PostgresTestContainer : PostgreSQLContainer<PostgresTestContainer>("postgres:13") {

}