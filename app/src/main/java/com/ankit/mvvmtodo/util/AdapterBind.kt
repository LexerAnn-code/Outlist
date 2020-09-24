package com.ankit.mvvmtodo.util

import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter(value = ["setAdapter"])
fun recyclerView(recyclerView: RecyclerView,adapter: RecyclerView.Adapter<*>){
recyclerView.adapter=adapter
}
fun recyclerView2(recyclerView: RecyclerView,adapter: RecyclerView.Adapter<*>){
    recyclerView.adapter=adapter
}
