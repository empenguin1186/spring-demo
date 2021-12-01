package com.example.demo.infra.repository

import com.example.demo.domain.activityhistory.ActivityHistory
import com.example.demo.domain.activityhistory.ActivityHistoryRepository
import org.springframework.stereotype.Repository

@Repository
class ActivityHistoryRepositoryImpl: ActivityHistoryRepository {
    override fun insert(activityHistory: ActivityHistory) {
        throw Exception()
    }
}