package com.example.demo.infra.repository.mapper

import com.example.demo.domain.task.Task
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Component

@Mapper
@Component
interface TaskMapper {

    @Insert("""
       INSERT INTO Tasks (
           task_name,
           assigned
       ) VALUES (
           #{taskName},
           #{assigned}
       )
    """)
    fun insert(task: Task)

    @Select("""SELECT * FROM Tasks WHERE task_id = #{taskId}""")
    fun findByTaskId(taskId: Int): List<Task>
}