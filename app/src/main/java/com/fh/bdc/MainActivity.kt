package com.fh.bdc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.fh.baselib.BaseApplication

/**
 * Author: YongChao
 * Date: 19-8-15 下午1:55
 * Description:
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("aa","-22--" + BaseApplication.appContext)
        Log.e("aa","---" + BaseApplication.appContext)
    }
}
