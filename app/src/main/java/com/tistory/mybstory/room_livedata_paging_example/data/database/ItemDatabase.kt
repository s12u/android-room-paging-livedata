package com.tistory.mybstory.room_livedata_paging_example.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tistory.mybstory.room_livedata_paging_example.data.model.Item

@Database(
    entities = [Item::class],
    version = 1,
    exportSchema = false
)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun ItemDao(): ItemDao

    companion object {
        private var instance: ItemDatabase? = null

        fun getInstance(context: Context): ItemDatabase =
            instance ?: synchronized(this) {
                instance = Room.databaseBuilder(context, ItemDatabase::class.java, "item_db")
                    .build()
                instance!!
            }
    }

}