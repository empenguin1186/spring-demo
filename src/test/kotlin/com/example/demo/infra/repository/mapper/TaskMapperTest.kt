package com.example.demo.infra.repository.mapper

import com.example.demo.domain.task.Task
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@MybatisTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnabledIfEnvironmentVariable(named = "DB_TEST_ENABLE", matches = "true")
internal class TaskMapperTest {

    @Autowired
    private lateinit var taskMapper: TaskMapper

    companion object {
        @Container
        @JvmStatic
        val mysqlContainer = MySQLContainer<Nothing>(DockerImageName.parse("mysql")).apply {
            withUsername("user")
            withPassword("mysql")
            withDatabaseName("testdb")
            withInitScript("initdb/schema.sql")
        }

        @DynamicPropertySource
        @JvmStatic
        fun setUp(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl)
            registry.add("spring.datasource.username", mysqlContainer::getUsername)
            registry.add("spring.datasource.password", mysqlContainer::getPassword)
        }
    }

    /**
     * Kotlin ではデフォルトではネストしたクラスは Java での static クラスに近いものとなる
     * @Nested を付与するクラスは非static である必要があるため、Kotlin では inner を付与する
     * https://junit.org/junit5/docs/current/user-guide/#writing-tests-nested
     */
    @Nested
    inner class FindByAssigned {
        @Test
        fun `Taskを作成および取得できることを確認するテスト`() {
            // given
            val taskName = "task1"
            val assignee = "assignee1"
            val task = Task.create(taskName, assignee)

            // when
            taskMapper.insert(task)
            val tasks = taskMapper.findByAssigned(assignee)

            // then
            SoftAssertions().apply {
                assertThat(tasks.size).isEqualByComparingTo(1)
                assertThat(tasks[0].taskName).isEqualTo(taskName)
                assertThat(tasks[0].assignee).isEqualTo(assignee)
            }.assertAll()
        }

        @Test
        fun `作成していないTaskを取得できないことを確認するテスト`() {
            // given
            val assignee = "assignee1"

            // when
            val tasks = taskMapper.findByAssigned(assignee)

            // then
            SoftAssertions().apply {
                assertThat(tasks.size).isEqualTo(0)
            }.assertAll()
        }
    }

    @Nested
    inner class FindByTaskNameAndAssigned {
        @Test
        fun `Taskを作成および取得できることを確認するテスト`() {
            // given
            val taskName = "task1"
            val assignee = "assignee1"
            val expected = Task.create(taskName, assignee)

            // when
            taskMapper.insert(expected)
            val actual = taskMapper.findByTaskNameAndAssigned(taskName, assignee)

            // then
            SoftAssertions().apply {
                actual.apply {
                    assertThat(actual).isNotNull
                    assertThat(actual).isEqualTo(expected)
                }
            }.assertAll()
        }

        @Test
        fun `作成していないTaskを取得できないことを確認するテスト`() {
            // given
            val taskName = "task1"
            val assignee = "assignee1"

            // when
            val tasks = taskMapper.findByTaskNameAndAssigned(taskName, assignee)

            // then
            SoftAssertions().apply {
                assertThat(tasks).isNull()
            }.assertAll()
        }
    }
}
