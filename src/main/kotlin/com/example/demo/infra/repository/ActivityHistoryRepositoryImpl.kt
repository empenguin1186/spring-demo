package com.example.demo.infra.repository

import com.example.demo.domain.activityhistory.ActivityHistory
import com.example.demo.domain.activityhistory.ActivityHistoryRepository
import com.example.demo.infra.repository.mapper.ActivityHistoryMapper
import org.springframework.stereotype.Repository

@Repository
class ActivityHistoryRepositoryImpl(
    private val activityHistoryMapper: ActivityHistoryMapper
): ActivityHistoryRepository {

    override fun insert(activityHistory: ActivityHistory) {
        activityHistoryMapper.insert(activityHistory)
    }
}