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
           task_name
       ) VALUES (
           #{taskName}
       )
    """)
    fun insert(task: Task)

    @Select("""SELECT * FROM Tasks WHERE id = #{taskId}""")
    fun findByTaskId(taskId: Int)
}