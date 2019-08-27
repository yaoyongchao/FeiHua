package com.yyc.vgalib

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hyphenate.chat.EMCallStateChangeListener
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.activity_vga.*

/**
 * Author: YongChao
 * Date: 19-8-27 下午1:30
 * Description: 音视频
 */
class VgaActivity : AppCompatActivity() {
    lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vga)
        mContext = this
        initViews()
    }

    private fun initViews() {

        btn_voice.setOnClickListener {
            voice()
        }
        btn_vodio.setOnClickListener {
            video()
        }

        EMClient.getInstance().callManager().addCallStateChangeListener(object : EMCallStateChangeListener{
            override fun onCallStateChanged(
                callState: EMCallStateChangeListener.CallState?,
                error: EMCallStateChangeListener.CallError?
            ) {
                when(callState) {
                    EMCallStateChangeListener.CallState.CONNECTED -> log("双方建立链接")
                    EMCallStateChangeListener.CallState.CONNECTING -> log("双方正在建立链接")
                    EMCallStateChangeListener.CallState.ACCEPTED -> log("电话接通成功")
                    EMCallStateChangeListener.CallState.DISCONNECTED -> log("电话断了")
                    EMCallStateChangeListener.CallState.NETWORK_DISCONNECTED -> log("NETWORK_DISCONNECTED")
                    EMCallStateChangeListener.CallState.NETWORK_UNSTABLE -> log("网络不稳定")
                    EMCallStateChangeListener.CallState.NETWORK_NORMAL -> log("网络恢复正常")
                }
            }

        } )

    }

    private fun voice() {
        EMClient.getInstance().callManager().makeVoiceCall(UserVga.callId)
    }

    private fun video() {
        EMClient.getInstance().callManager().makeVideoCall(UserVga.callId)
    }

    private fun log(s:String) {
        Log.e("VgaActivity",s)
    }
}
