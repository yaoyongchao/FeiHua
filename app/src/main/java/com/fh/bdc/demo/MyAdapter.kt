package com.fh.bdc.demo

import android.view.View
import android.view.ViewGroup
import com.fh.baselib.adapter.BaseAdapter
import com.fh.bdc.R
import kotlinx.android.synthetic.main.item_demo.view.*

/**
 * Author: YongChao
 * Date: 19-8-30 下午4:10
 * Description:
 */
class MyAdapter(var list:List<String>) : BaseAdapter<String>(R.layout.item_demo,list,
    {v:View,s:String,position:Int ->
    v.tv.text = s
    }
) {

    /**
     * 多布局实现这两个方法。
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<String> {
        return super.onCreateViewHolder(parent, viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}