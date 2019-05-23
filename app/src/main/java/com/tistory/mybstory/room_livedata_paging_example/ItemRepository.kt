package com.tistory.mybstory.room_livedata_paging_example

import androidx.paging.DataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemRepository(private val itemDao: ItemDao) {

    suspend fun insert(item: Item) = itemDao.insert(item)

    fun getAll(): DataSource.Factory<Int, Item> =
        itemDao.getAll()

    suspend fun clear() =
        itemDao.clear()
}