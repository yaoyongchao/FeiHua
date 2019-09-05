package com.fh.baselib.widget

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.fh.baselib.R
import kotlinx.android.synthetic.main.layout_item_view.view.*

/**
 * Author: YongChao
 * Date: 19-9-5 下午4:53
 * Description:
 */
class ItemView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {
    private lateinit var view: View
    private lateinit var attributes: TypedArray
    init {
        initViews(context, attrs)
    }

    private fun initViews(context: Context, attrs: AttributeSet?) {
        val mInflater = LayoutInflater.from(context)
        view = mInflater.inflate(R.layout.layout_item_view, this)
        addView(view)

        attributes = context.obtainStyledAttributes(attrs, R.styleable.ItemView)
        if (attributes != null) {
            view.tv_title.text = attributes.getString(R.styleable.ItemView_text)
            view.tv_right.text = attributes.getString(R.styleable.ItemView_text_right)
            view.img_arrow.visibility = if(attributes.getBoolean(R.styleable.ItemView_show_right_arrow,false)) View.VISIBLE  else View.GONE
            view.view_border.visibility = if(attributes.getBoolean(R.styleable.ItemView_show_border,false)) View.VISIBLE  else View.GONE
            attributes.recycle()
        }
    }

    private fun initViews() {

    }
}