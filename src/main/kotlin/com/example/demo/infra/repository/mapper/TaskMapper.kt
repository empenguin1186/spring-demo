package com.example.demo.infra.repository.mapper

import com.example.demo.domain.task.Task
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Component

@Mapper
@Component
interface TaskMapper {

    /**
     * タスク名と担当者を指定してタスクを作成する
     */
    @Insert("""
       INSERT INTO Tasks (
           task_name,
           assignee
       ) VALUES (
           #{taskName},
           #{assignee}
       )
    """)
    fun insert(task: Task)

    /**
     * 担当者を指定してタスクを取得する
     */
    @Select("""SELECT * FROM Tasks WHERE assignee = #{assignee}""")
    fun findByAssignee(assignee: String): List<Task>

    /**
     * タスク名と担当者を指定してタスクを取得する
     */
    @Select("""SELECT * FROM Tasks WHERE task_name = #{taskName} AND assignee = #{assignee}""")
    fun findByTaskNameAndAssignee(taskName: String, assignee: String): Task?

    /**
     * タスク名と担当者が合致したタスクを削除する
     */
    @Delete("""DELETE FROM Tasks WHERE task_name = #{taskName} AND assignee = #{assignee}""")
    fun deleteByTaskNameAndAssignee(taskName: String, assignee: String)
}