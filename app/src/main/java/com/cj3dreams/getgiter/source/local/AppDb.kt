package com.cj3dreams.getgiter.source.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TestEntity::class], version = 1, exportSchema = false)
abstract class AppDb: RoomDatabase() {

}