package com.fh.bdc.demo

import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.fh.baselib.adapter.BaseAdapter
import com.fh.baselib.base.BaseActivity
import com.fh.baselib.widget.MyDivider
import com.fh.bdc.R
import kotlinx.android.synthetic.main.activity_list_demo.*
import kotlinx.android.synthetic.main.item_demo.view.*

/**
 * Author: YongChao
 * Date: 19-8-26 下午4:04
 * Description: 列表Demo
 */

class ListDemoActivity : BaseActivity() {
    lateinit var adapter : com.fh.baselib.adapter.BaseAdapter<String>
    var list = mutableListOf<String>()
    var  s = ""
    override fun layoutId(): Int {
        return R.layout.activity_list_demo
    }

    override fun initView() {
        initRv()
    }

    private fun initRv() {
        rv.layoutManager = LinearLayoutManager(mContext)
        rv.addItemDecoration(MyDivider(mContext, DividerItemDecoration.VERTICAL))
        list.add("1")
        list.add("2")
        list.add("3")
        list.add("4")
        list.add("5 ")
        list.add("6")
        list.add("7")
        list.add("8")
        list.add("9")
        adapter = BaseAdapter(R.layout.item_demo,list) { view: View, bean: String,i: Int ->
            view.tv.text = bean
        }
        rv.adapter = adapter
    }

    override fun initData() {
    }

    override fun initListener() {
    }

}
