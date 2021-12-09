package com.example.demo.infra.repository

import com.example.demo.domain.task.Task
import com.example.demo.infra.repository.mapper.TaskMapper
import org.junit.jupiter.api.Test
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
internal class TaskRepositoryImplTest {

    @Autowired
    private lateinit var taskMapper: TaskMapper

    companion object {
        @Container
        @JvmStatic
        val mysqlContainer = MySQLContainer<Nothing>(DockerImageName.parse("mysql")).apply {
            withUsername("devuser")
            withPassword("devuser")
            withDatabaseName("devdb")
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

    @Test
    fun `hoge`() {
        val task = Task.create("task1", "assignee")
        taskMapper.insert(task)
        val tasks = taskMapper.findByTaskId(1)
    }
}