package com.fh.baselib.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fh.baselib.R

/**
 * Author: YongChao
 * Date: 19-8-30 下午4:29
 * Description: RecyclerView.Adapter 基础类封装
 */
open class BaseAdapter<T>(val layoutResourceId: Int, val items: List<T>, val init: (View, T,Int) -> Unit) :
    RecyclerView.Adapter<BaseAdapter.ViewHolder<T>>() {
    /**
     * viewType--分别为item以及空view
     */
    val VIEW_TYPE_ITEM = 1
    val VIEW_TYPE_EMPTY = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T> {
        if (viewType == VIEW_TYPE_EMPTY) {
            val viewEmpty = LayoutInflater.from(parent.context).inflate(R.layout.layout_rv_empty,parent,false)
            return ViewHolder(viewEmpty,init)
        }
        val view = LayoutInflater.from(parent.context).inflate(layoutResourceId,parent,false)
        return ViewHolder(view, init)
    }

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        if (items.size != 0) {
            holder.bindForecast(items[position],position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (items.size == 0) VIEW_TYPE_EMPTY else VIEW_TYPE_ITEM
    }

    override fun getItemCount() = if (items.size == 0) 1 else items.size

    class ViewHolder<in T>(view: View, val init: (View, T,Int) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bindForecast(item: T,position: Int) {
                init(itemView, item,position)
        }
    }
}