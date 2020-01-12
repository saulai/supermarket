package com.sdos.supermarket.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sdos.supermarket.BuildConfig
import com.sdos.supermarket.data.room.dao.TaskDao
import com.sdos.supermarket.data.room.dao.BaseTaskDao
import com.sdos.supermarket.data.room.dao.UserDao
import com.sdos.supermarket.domain.model.Task
import com.sdos.supermarket.domain.model.BaseTask
import com.sdos.supermarket.domain.model.User

const val SUPERMARKET_DATABASE = "sdos.supermarketdatabase"

@Database(
    entities = [
        Task::class,
        BaseTask::class,
        User::class
    ],
    version = BuildConfig.VERSION_CODE
)

@TypeConverters(Converters::class)
abstract class SupermarketDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun baseTaskDao(): BaseTaskDao
    abstract fun userDao(): UserDao
}
