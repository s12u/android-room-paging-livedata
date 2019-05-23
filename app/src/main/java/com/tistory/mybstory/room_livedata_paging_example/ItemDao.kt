package com.tistory.mybstory.room_livedata_paging_example

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItemDao {

    @Insert
    suspend fun insert(item: Item)

    @Query("SELECT * FROM item ORDER BY id ASC")
    fun getAll(): DataSource.Factory<Int, Item>

    @Query("DELETE FROM item")
    suspend fun clear()
}