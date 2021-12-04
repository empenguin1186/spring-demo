package com.example.demo.infra.repository.mapper

import com.example.demo.domain.task.Task
import org.apache.ibatis.annotations.Mapper

@Mapper
interface TaskMapper {
    fun insert(task: Task)
}