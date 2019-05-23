package com.tistory.mybstory.room_livedata_paging_example.data.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tistory.mybstory.room_livedata_paging_example.data.model.Item

@Dao
interface ItemDao {

    @Insert
    suspend fun insert(item: Item)

    @Query("SELECT * FROM item ORDER BY id ASC")
    fun getAll(): DataSource.Factory<Int, Item>

    @Query("DELETE FROM item")
    suspend fun clear()
}