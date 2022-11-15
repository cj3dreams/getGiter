package com.cj3dreams.getgiter.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity(tableName = "test")
data class TestEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "full_name")
    val full_name: String,
    @ColumnInfo(name = "html_url")
    val html_url: String,
    @ColumnInfo(name = "created_at")
    val created_at: String,
    @ColumnInfo(name = "description")
    val description: String,
)


