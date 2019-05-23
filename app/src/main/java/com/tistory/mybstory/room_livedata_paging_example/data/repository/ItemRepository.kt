package com.tistory.mybstory.room_livedata_paging_example.data.repository

import androidx.paging.DataSource
import com.tistory.mybstory.room_livedata_paging_example.data.database.ItemDao
import com.tistory.mybstory.room_livedata_paging_example.data.model.Item

class ItemRepository(private val itemDao: ItemDao) {

    suspend fun insert(item: Item) = itemDao.insert(item)

    fun getAll(): DataSource.Factory<Int, Item> =
        itemDao.getAll()

    suspend fun clear() =
        itemDao.clear()
}