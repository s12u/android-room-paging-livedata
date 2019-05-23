package com.tistory.mybstory.room_livedata_paging_example

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.toLiveData
import kotlinx.coroutines.delay

private const val PAGE_SIZE = 10

class ListViewModel(application: Application) : AndroidViewModel(application) {

    private val itemRepository: ItemRepository

    private val allItems: LiveData<PagedList<Item>>

    init {
        val itemDao = ItemDatabase.getInstance(application).ItemDao()
        itemRepository = ItemRepository(itemDao)

        val pagingConfig = PagedList.Config.Builder()
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .setPrefetchDistance(5)
            .build()

        allItems = LivePagedListBuilder(itemRepository.getAll(), pagingConfig)
            .build()
    }

    suspend fun insertItem(size: Int) {
        for (i in 1..size) {
            itemRepository.insert(Item("test"))
        }
        delay(200)
    }

    fun getAllItems() = allItems

    suspend fun clearAllItems() = itemRepository.clear()
}
