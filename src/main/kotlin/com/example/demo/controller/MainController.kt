package com.example.demo.controller

import com.example.demo.usecase.task.CreateTaskUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class MainController(
    private val createTaskUseCase: CreateTaskUseCase
) {
    @GetMapping
    fun get(): String {
        createTaskUseCase.execute("hoge", "assignee")
        return "Hello, World"
    }
}