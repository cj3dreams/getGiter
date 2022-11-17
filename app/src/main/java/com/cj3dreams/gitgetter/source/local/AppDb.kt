package com.cj3dreams.gitgetter.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cj3dreams.gitgetter.model.entities.DownloadsEntity

@Database(entities = [DownloadsEntity::class], version = 1, exportSchema = false)
abstract class AppDb: RoomDatabase() {

    abstract fun downloadsDao(): DownloadsDao?
}