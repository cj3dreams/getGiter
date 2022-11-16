package com.cj3dreams.getgiter.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "downloads", indices = [Index(value = ["id"], unique = true)])
data class DownloadsEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Long = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "login")
    val login: String,
    @ColumnInfo(name = "downloaded_at")
    val downloaded_at: String = "",
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "isDownloaded")
    val isDownloaded: Int = 0,
): Serializable


