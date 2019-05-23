package com.tistory.mybstory.room_livedata_paging_example.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tistory.mybstory.room_livedata_paging_example.R
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ItemListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private lateinit var itemListAdapter: ItemListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        initUI()

        viewModel.getAllItems().observe(this, Observer { pagedList ->
            pagedList?.let { itemListAdapter.submitList(pagedList) }
        })

        btn_add.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.insertItem(10)
                withContext(Dispatchers.Main) {
                    rv_items.smoothScrollToPosition(itemListAdapter.itemCount - 1)
                }
            }
        }

        btn_clear.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.clearAllItems()
            }
        }
    }

    private fun initUI() {
        itemListAdapter = ItemListAdapter()
        rv_items.adapter = itemListAdapter
    }
}