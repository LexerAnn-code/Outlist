package com.ankit.mvvmtodo.Util

import com.ankit.mvvmtodo.Util.SwipeToDelete
import android.widget.SimpleAdapter
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter(value = ["setAdapter"])
fun recyclerView(recyclerView: RecyclerView,adapter: RecyclerView.Adapter<*>){
    adapter.notifyDataSetChanged()
recyclerView.adapter=adapter
}
fun recyclerView2(recyclerView: RecyclerView,adapter: RecyclerView.Adapter<*>){
    recyclerView.adapter=adapter
}