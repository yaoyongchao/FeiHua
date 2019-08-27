package com.yyc.vgalib

import android.content.Context
import android.media.AudioManager
import android.media.Ringtone
import android.media.SoundPool
import com.fh.baselib.base.BaseActivity
import com.hyphenate.chat.EMCallStateChangeListener
import com.hyphenate.chat.EMClient
import gorden.rxbus2.Subscribe



abstract class CallActivity : BaseActivity() {
    lateinit var audioManager: AudioManager
    lateinit var soundPool: SoundPool
    lateinit var ringtone: Ringtone
    lateinit var callStateListener: EMCallStateChangeListener
    protected var isInComingCall: Boolean = false
    protected var username: String? = null
    protected var isAnswered = false

    protected val MSG_CALL_MAKE_VIDEO = 0
    protected val MSG_CALL_MAKE_VOICE = 1
    protected val MSG_CALL_ANSWER = 2
    protected val MSG_CALL_REJECT = 3
    protected val MSG_CALL_END = 4
    protected val MSG_CALL_RELEASE_HANDLER = 5
    protected val MSG_CALL_SWITCH_CAMERA = 6
    protected val MSG_SEND_CODE = 10001

    /**
     * 0：voice call，1：video call
     */
    protected var callType = 0
    protected var msgid: String = ""

    override fun initView() {
        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        showToolbar(false)
    }

    override fun isFullScreen(): Boolean {
        return true
    }


    override fun onDestroy() {
        soundPool?.release()
        if (ringtone?.isPlaying)
            ringtone.stop()
        audioManager.mode = AudioManager.MODE_NORMAL
        audioManager.isMicrophoneMute = false

        if (callStateListener != null)
            EMClient.getInstance().callManager().removeCallStateChangeListener(callStateListener)
        super.onDestroy()
    }

    @Subscribe(code = 10001)
    fun receive10001(code: Int?) {
        when(code) {
            MSG_CALL_MAKE_VIDEO -> 0
            MSG_CALL_MAKE_VOICE -> 1
            MSG_CALL_ANSWER -> {
                ringtone?.stop()
                if (isInComingCall) {
                    EMClient.getInstance().callManager().answerCall()
                    isAnswered = true

                }
            }

            MSG_CALL_REJECT -> 3
            MSG_CALL_END -> 4
            MSG_CALL_RELEASE_HANDLER -> 5
            MSG_CALL_SWITCH_CAMERA -> 6
            else -> {

            }

        }
    }
}
