package com.fh.baselib.widget

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.fh.baselib.R


class MyDivider constructor(context: Context, orientation: Int) : DividerItemDecoration(context,orientation){

    init {
        val drawable = ContextCompat.getDrawable(context, R.drawable.divider_shape)
        setDrawable(drawable!!)
    }

}