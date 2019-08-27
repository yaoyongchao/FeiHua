package com.yyc.vgalib.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.fh.baselib.utils.LogUtil
import com.yyc.vgalib.HxHelper
import com.yyc.vgalib.VoiceCallActivity

/**
 * Author: YongChao
 * Date: 19-8-27 下午3:17
 * Description:
 */

class CallReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (!HxHelper.instance.isLoggedIn())
            return
        //userName
        val from = intent?.getStringExtra("from")
        //call type
        val type = intent?.getStringExtra("type")
        if ("video".equals(type)) {//video call
            LogUtil.d("有视频进来")
        } else {//voice call
            LogUtil.d("有音频进来")
            context?.startActivity(Intent(context, VoiceCallActivity::class.java)
                .putExtra("username",from)
                .putExtra("isComingCall",true)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )

        }
        LogUtil.d("app received a incoming call")
    }

}