package com.yyc.vgalib

import android.content.Context
import android.media.AudioManager
import android.media.Ringtone
import android.media.SoundPool
import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.util.Log
import com.fh.baselib.base.BaseActivity
import com.fh.baselib.utils.LogUtil
import com.hyphenate.chat.EMCallStateChangeListener
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import gorden.rxbus2.Subscribe






abstract class CallActivity : BaseActivity() {
    lateinit var audioManager: AudioManager
    lateinit var soundPool: SoundPool
    lateinit var ringtone: Ringtone
    lateinit var callStateListener: EMCallStateChangeListener
    protected var isInComingCall: Boolean = false
    protected var username: String? = null
    protected var isAnswered = false

    protected val MSG_CALL_MAKE_VIDEO:Int = 1000
    protected val MSG_CALL_MAKE_VOICE = 1001
    protected val MSG_CALL_ANSWER = 1002
    protected val MSG_CALL_REJECT = 1003
    protected val MSG_CALL_END = 1004
    protected val MSG_CALL_RELEASE_HANDLER = 1005
    protected val MSG_CALL_SWITCH_CAMERA = 1006
    protected val MSG_SEND_CODE:Int = 1101

    protected var streamID = -1
    protected var callingState = CallingState.CANCELLED
    protected var callDruationText: String = ""
    protected var outgoing: Int = 0


    /**
     * 0：voice call，1：video call
     */
    protected var callType = 0
    protected var msgid: String = ""

    var callHandlerThread = HandlerThread("callHandlerThread")
    var handler : Handler = object :Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            Log.e("aa","111" + msg?.what)
            when(msg?.what) {
                MSG_CALL_MAKE_VIDEO -> 0
                MSG_CALL_MAKE_VOICE -> 1
                MSG_CALL_ANSWER -> {
                    LogUtil.d("点击了接收按钮")
                    ringtone?.stop()
                    if (isInComingCall) {
                        EMClient.getInstance().callManager().answerCall()
                        isAnswered = true

                    } else {
                        LogUtil.d("answer call isInComingCall:false")
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

    val timeoutHangup = Runnable {
        handler.sendEmptyMessage(MSG_CALL_END);
    }

    override fun initView() {
        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        showToolbar(false)
        callHandlerThread.start()
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








    @Subscribe(code = 1006)
    fun receive1006(msg: Int?) {
        LogUtil.d("RXjava2")
        when(msg) {
            MSG_CALL_MAKE_VIDEO -> 0
            MSG_CALL_MAKE_VOICE -> 1
            MSG_CALL_ANSWER -> {
                LogUtil.d("点击了接收按钮")
                ringtone?.stop()
                if (isInComingCall) {
                    EMClient.getInstance().callManager().answerCall()
                    isAnswered = true

                } else {
                    LogUtil.d("answer call isInComingCall:false")
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

    /**
     * play the incoming call ringtone
     *
     */
    protected fun playMakeCallSounds(): Int {
        try {
            audioManager.mode = AudioManager.MODE_RINGTONE
            audioManager.isSpeakerphoneOn = true

            // play
            return soundPool.play(
                outgoing, // sound resource
                0.3f, // left volume
                0.3f, // right volume
                1, // priority
                -1, // loop，0 is no loop，-1 is loop forever
                1.0f
            )
        } catch (e: Exception) {
            return -1
        }

    }

    public fun closeSpeakerOn() {
        if (audioManager?.isSpeakerphoneOn)
            audioManager.isSpeakerphoneOn = false
        audioManager.mode = AudioManager.MODE_IN_COMMUNICATION

    }
    fun saveCallRecord() {
        var message: EMMessage ?= null
        var txtBody: EMTextMessageBody ?= null
        if (!isInComingCall) {//outgoing call
            message = EMMessage.createSendMessage(EMMessage.Type.TXT)
            message.to = username
        } else {
            message = EMMessage.createReceiveMessage(EMMessage.Type.TXT)
            message.from = username
        }

        var st = ""
//        val st1 = resources.getString(R.string.call_duration)
//        val st2 = resources.getString(R.string.Refused)
//        val st3 = resources.getString(R.string.The_other_party_has_refused_to)
//        val st4 = resources.getString(R.string.The_other_is_not_online)
//        val st5 = resources.getString(R.string.The_other_is_on_the_phone)
//        val st6 = resources.getString(R.string.The_other_party_did_not_answer)
//        val st7 = resources.getString(R.string.did_not_answer)
//        val st8 = resources.getString(R.string.Has_been_cancelled)
//        val st12 = "service not enable"
//        val st13 = "service arrearages"
//        val st14 = "service forbidden"


        when(callingState) {
            CallingState.NORMAL ->{st = resources.getString(R.string.call_duration) + callDruationText}
            CallingState.REFUSED ->{st = resources.getString(R.string.Refused)}
            CallingState.BEREFUSED ->{st = resources.getString(R.string.The_other_party_has_refused_to)}
            CallingState.OFFLINE ->{st = resources.getString(R.string.The_other_is_not_online)}
            CallingState.BUSY ->{st = resources.getString(R.string.The_other_is_on_the_phone)}
            CallingState.NO_RESPONSE ->{st = resources.getString(R.string.The_other_party_did_not_answer)}
            CallingState.UNANSWERED ->{st = resources.getString(R.string.did_not_answer)}
            CallingState.VERSION_NOT_SAME ->{st = resources.getString(R.string.call_version_inconsistent)}
            CallingState.SERVICE_ARREARAGES ->{st = "service arrearages"}
            CallingState.SERVICE_NOT_ENABLE ->{st = "service not enable"}
            else -> {
                st = resources.getString(R.string.Has_been_cancelled)
            }
        }

        txtBody = EMTextMessageBody(st)

        if (callType == 0) {
            message.setAttribute("is_voice_call",true)
        } else {
            message.setAttribute("is_video_call",true)
        }

        //set message body
        message.addBody(txtBody)
        message.msgId = msgid
        message.setStatus(EMMessage.Status.SUCCESS)
        //save
        EMClient.getInstance().chatManager().saveMessage(message)

    }

    enum class CallingState {
        CANCELLED, NORMAL, REFUSED, BEREFUSED, UNANSWERED, OFFLINE, NO_RESPONSE, BUSY, VERSION_NOT_SAME, SERVICE_ARREARAGES, SERVICE_NOT_ENABLE
    }
}
