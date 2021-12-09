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

    @Select("""SELECT * FROM Tasks WHERE assigned = #{assigned}""")
    fun findByAssigned(assigned: String): List<Task>
}