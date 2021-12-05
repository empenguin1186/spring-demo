package com.example.demo.infra.repository.mapper

import com.example.demo.domain.activityhistory.ActivityHistory
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Component

@Mapper
@Component
interface ActivityHistoryMapper {

    @Insert("""
        INSERT INTO ActivityHistories(
            detail
        ) VALUES (
            #{detail}
        )
    """)
    fun insert(activityHistory: ActivityHistory)
}