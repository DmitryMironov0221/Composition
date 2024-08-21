package com.example.composition.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [GameResultDbModel::class], version = 2, exportSchema =false)
@TypeConverters(LocalDateTimeConverter::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun gameResultListDao(): GameResultListDao

    companion object {

        private var INSTANCE: AppDataBase? = null
        private val LOCK = Any()
        private const val DB_NAME = "game_result.db"

        fun getInstance(application: Application): AppDataBase {
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }

                // Создаем миграцию
                val MIGRATION_1_2 = object : Migration(1, 2) {
                    override fun migrate(database: SupportSQLiteDatabase) {
                        database.execSQL("ALTER TABLE game_results ADD COLUMN dateTime INTEGER")
                    }
                }

                val db = Room.databaseBuilder(
                    application,
                    AppDataBase::class.java,
                    DB_NAME
                )
                    .addMigrations(MIGRATION_1_2) // Добавляем миграцию
                    .build()
                INSTANCE = db
                return db
            }
        }
    }
}