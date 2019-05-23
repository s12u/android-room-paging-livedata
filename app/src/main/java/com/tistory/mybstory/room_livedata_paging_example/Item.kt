package com.tistory.mybstory.room_livedata_paging_example

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(var description: String) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}