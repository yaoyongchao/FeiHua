package com.fh.bdc.demo

import android.view.View
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
)