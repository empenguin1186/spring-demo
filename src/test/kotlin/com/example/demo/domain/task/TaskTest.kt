package com.example.demo.domain.task

import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class TaskTest {
    @Nested
    inner class Create {
        @Test
        fun `Taskを生成するテスト`() {
            // given
            val taskName = "task1"
            val assignee = "assignee1"

            // when
            val task = Task.create(taskName, assignee)

            // then
            SoftAssertions().apply {
                assertThat(task.taskName).isEqualTo(taskName)
                assertThat(task.assignee).isEqualTo(assignee)
            }.assertAll()
        }
    }
}