package com.example.maps.DTB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.maps.DAO.ItemDao
import com.example.maps.DAO.LinkDao
import com.example.maps.Table.Links
import com.example.maps.Table.LocationEntity

@Database(entities = arrayOf(LocationEntity::class, Links::class), version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun itemDao(): ItemDao
    abstract fun linkDao(): LinkDao
    companion object{
        private var instance: AppDatabase?= null
        fun newInstance(context: Context): AppDatabase{
            if(instance == null) {
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "mydb")
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}
