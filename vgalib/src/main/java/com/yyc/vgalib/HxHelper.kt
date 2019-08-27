package com.yyc.vgalib

import android.content.Context
import android.content.IntentFilter
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions
import com.yyc.vgalib.receiver.CallReceiver

/**
 * Author: YongChao
 * Date: 19-8-27 下午3:09
 * Description:
 */

class HxHelper {
    var callReceiver: CallReceiver ?= null


    companion object {
        val instance: HxHelper by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            HxHelper()
        }

    }

    private lateinit var appContext: Context

    fun init(context: Context) {
        appContext = context



        val options = EMOptions()
        //默认添加好友时，是不需要验证的， 改成需要验证
        options.acceptInvitationAlways = false
        //是否自动将消息附件上传到环信服务器， 默认为true，是使用环信服务器
        options.autoTransferMessageAttachments = true
        options.setAutoDownloadThumbnail(true)
        EMClient.getInstance().init(context, options)
        EMClient.getInstance().setDebugMode(true)




        val callFilter = IntentFilter(EMClient.getInstance().callManager().incomingCallBroadcastAction)
        if (callReceiver == null)
            callReceiver = CallReceiver()
        //register incoming call receiver
        appContext.registerReceiver(callReceiver,callFilter)
    }

    public fun isLoggedIn():Boolean {
        return EMClient.getInstance().isLoggedInBefore
    }
}