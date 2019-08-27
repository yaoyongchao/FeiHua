package com.yyc.vgalib

import android.content.Context
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions

/**
 * Author: YongChao
 * Date: 19-8-27 下午1:42
 * Description:
 */
class HuanXinApplicationHelper {
    companion object {
        fun initHuanXin(context: Context) {
            val options = EMOptions()
            //默认添加好友时，是不需要验证的， 改成需要验证
            options.acceptInvitationAlways = false
            //是否自动将消息附件上传到环信服务器， 默认为true，是使用环信服务器
            options.autoTransferMessageAttachments = true
            options.setAutoDownloadThumbnail(true)
            EMClient.getInstance().init(context, options)
            EMClient.getInstance().setDebugMode(true)

        }
    }
}