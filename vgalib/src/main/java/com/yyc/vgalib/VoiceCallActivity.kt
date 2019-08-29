package com.yyc.vgalib

import android.media.AudioManager
import android.media.RingtoneManager
import android.media.SoundPool
import android.os.Bundle
import android.telephony.TelephonyManager
import android.view.View
import android.widget.Toast
import com.fh.baselib.utils.LogUtil
import com.hyphenate.chat.EMCallStateChangeListener
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import com.yyc.vgalib.utils.PhoneStateManager
import kotlinx.android.synthetic.main.activity_voice_call.*
import java.util.*







/**
 * Author: YongChao
 * Date: 19-8-27 下午3:55
 * Description: 音频界面
 */
class VoiceCallActivity : CallActivity() {
    private val isMuteState: Boolean = false
    private val isHandsfreeState: Boolean = false
    val MAKE_CALL_TIMEOUT:Long = 50 * 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState !=null) {
            finish()
            return
        }
    }

    override fun initView() {
        super.initView()
        callType = 0
        addCallStateListener()
        msgid = UUID.randomUUID().toString()
        username = getIntent().getStringExtra("username");
        isInComingCall = getIntent().getBooleanExtra("isComingCall", false);
        llyt_incoming.visibility = if (isInComingCall) View.VISIBLE else View.GONE
        llyt_outgoing.visibility = if (isInComingCall) View.GONE else View.VISIBLE

        if (!isInComingCall) {//outgoing call
            soundPool = SoundPool(1,AudioManager.STREAM_RING,0)
            outgoing = soundPool.load(this,R.raw.em_outgoing,1)
            handler.sendEmptyMessage(MSG_CALL_MAKE_VOICE)
            handler.postDelayed({ streamID = playMakeCallSounds() }, 300)

        } else {
            val ringUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
            audioManager.mode = AudioManager.MODE_RINGTONE
            audioManager.isSpeakerphoneOn = true
            ringtone = RingtoneManager.getRingtone(this, ringUri)
            ringtone.play()
        }


        handler.removeCallbacks(timeoutHangup);
        handler.postDelayed(timeoutHangup, MAKE_CALL_TIMEOUT);
    }

    override fun layoutId(): Int {
        return R.layout.activity_voice_call
    }

    override fun initData() {
    }

    override fun initListener() {
        img_answer.setOnClickListener {
            img_answer.isEnabled = false
            tv_call_state.text = "正在接听..."
            LogUtil.d("点击了 answer按钮")
            var msg = handler.obtainMessage()
            msg.arg1 = 1234
//            handler.sendMessage(msg)
            handler.sendEmptyMessage(MSG_CALL_ANSWER)
        }

    }



    private fun addCallStateListener() {
//        callStateListener = EMCallStateChangeListener()
        callStateListener = EMCallStateChangeListener { callState, error ->
            // Message msg = handler.obtainMessage();
            when (callState) {

                EMCallStateChangeListener.CallState.CONNECTING -> runOnUiThread {
                    tv_call_state.text = "正在连接中..."
                }
                EMCallStateChangeListener.CallState.CONNECTED -> runOnUiThread {
                    tv_call_state.text = "已经和对方建立连接"
                }

                EMCallStateChangeListener.CallState.ACCEPTED -> runOnUiThread{//接收

                    soundPool?.stop(streamID)
                    if (!isHandsfreeState)
                        closeSpeakerOn()

                    tv_call_state.text = getResources().getString(R.string.In_the_call);
                    callingState = CallingState.NORMAL

                    PhoneStateManager.get(mContext).addStateCallback(phoneStateCallback)

//                    handler.removeCallbacks(timeoutHangup)
//                    runOnUiThread {
//                        try {
//                            if (soundPool != null)
//                                soundPool.stop(streamID)
//                        } catch (e: Exception) {
//                        }
//
//                        if (!isHandsfreeState)
//                            closeSpeakerOn()
//                        //show relay or direct call, for testing purpose
//                        (findViewById<View>(R.id.tv_is_p2p) as TextView).setText(
//                            if (EMClient.getInstance().callManager().isDirectCall)
//                                R.string.direct_call
//                            else
//                                R.string.relay_call
//                        )
//                        chronometer.setVisibility(View.VISIBLE)
//                        chronometer.setBase(SystemClock.elapsedRealtime())
//                        // duration start
//                        chronometer.start()
//                        val str4 = resources.getString(R.string.In_the_call)
//                        callStateTextView.setText(str4)
//                        callingState = CallingState.NORMAL
//                        startMonitor()
//                        // Start to watch the phone call state.
//                        PhoneStateManager.get(this@VoiceCallActivity)
//                            .addStateCallback(phoneStateCallback)
//                    }
                }
                EMCallStateChangeListener.CallState.NETWORK_UNSTABLE -> runOnUiThread {
//                    netwrokStatusVeiw.setVisibility(View.VISIBLE)
//                    if (error == CallError.ERROR_NO_DATA) {
//                        netwrokStatusVeiw.setText(R.string.no_call_data)
//                    } else {
//                        netwrokStatusVeiw.setText(R.string.network_unstable)
//                    }
                }
                EMCallStateChangeListener.CallState.NETWORK_NORMAL -> runOnUiThread {
//                    netwrokStatusVeiw.setVisibility(
//                        View.INVISIBLE
//                    )
                }
                EMCallStateChangeListener.CallState.VOICE_PAUSE -> runOnUiThread {
                    Toast.makeText(
                        applicationContext,
                        "VOICE_PAUSE",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                EMCallStateChangeListener.CallState.VOICE_RESUME -> runOnUiThread {
                    Toast.makeText(
                        applicationContext,
                        "VOICE_RESUME",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                EMCallStateChangeListener.CallState.DISCONNECTED -> {
//                    handler.removeCallbacks(timeoutHangup)
//                    runOnUiThread(object : Runnable {
//                        private fun postDelayedCloseMsg() {
//                            handler.postDelayed(Runnable {
//                                runOnUiThread {
//                                    Log.d("AAA", "CALL DISCONNETED")
//                                    removeCallStateListener()
//
//                                    // Stop to watch the phone call state.
//                                    PhoneStateManager.get(this@VoiceCallActivity)
//                                        .removeStateCallback(phoneStateCallback)
//
//                                    saveCallRecord()
//                                    val animation = AlphaAnimation(1.0f, 0.0f)
//                                    animation.duration = 800
//                                    findViewById<View>(R.id.root_layout).startAnimation(animation)
//                                    finish()
//                                }
//                            }, 200)
//                        }
//
//                        override fun run() {
//                            chronometer.stop()
//                            callDruationText = chronometer.getText().toString()
//                            val st1 = resources.getString(R.string.Refused)
//                            val st2 =
//                                resources.getString(R.string.The_other_party_refused_to_accept)
//                            val st3 = resources.getString(R.string.Connection_failure)
//                            val st4 = resources.getString(R.string.The_other_party_is_not_online)
//                            val st5 = resources.getString(R.string.The_other_is_on_the_phone_please)
//
//                            val st6 =
//                                resources.getString(R.string.The_other_party_did_not_answer_new)
//                            val st7 = resources.getString(R.string.hang_up)
//                            val st8 = resources.getString(R.string.The_other_is_hang_up)
//
//                            val st9 = resources.getString(R.string.did_not_answer)
//                            val st10 = resources.getString(R.string.Has_been_cancelled)
//                            val st11 = resources.getString(R.string.hang_up)
//                            val st12 = "service not enable"
//                            val st13 = "service arrearages"
//                            val st14 = "service forbidden"
//
//                            if (error == CallError.REJECTED) {
//                                callingState = CallingState.BEREFUSED
//                                callStateTextView.setText(st2)
//                            } else if (error == CallError.ERROR_TRANSPORT) {
//                                callStateTextView.setText(st3)
//                            } else if (error == CallError.ERROR_UNAVAILABLE) {
//                                callingState = CallingState.OFFLINE
//                                callStateTextView.setText(st4)
//                            } else if (error == CallError.ERROR_BUSY) {
//                                callingState = CallingState.BUSY
//                                callStateTextView.setText(st5)
//                            } else if (error == CallError.ERROR_NORESPONSE) {
//                                callingState = CallingState.NO_RESPONSE
//                                callStateTextView.setText(st6)
//                            } else if (error == CallError.ERROR_LOCAL_SDK_VERSION_OUTDATED || error == CallError.ERROR_REMOTE_SDK_VERSION_OUTDATED) {
//                                callingState = CallingState.VERSION_NOT_SAME
//                                callStateTextView.setText(R.string.call_version_inconsistent)
//                            } else if (error == CallError.ERROR_SERVICE_NOT_ENABLE) {
//                                callingState = CallingState.SERVICE_NOT_ENABLE
//                                callStateTextView.setText(st12)
//                            } else if (error == CallError.ERROR_SERVICE_ARREARAGES) {
//                                callingState = CallingState.SERVICE_ARREARAGES
//                                callStateTextView.setText(st13)
//                            } else if (error == CallError.ERROR_SERVICE_FORBIDDEN) {
//                                callingState = CallingState.SERVICE_NOT_ENABLE
//                                callStateTextView.setText(st14)
//                            } else {
//                                if (isRefused) {
//                                    callingState = CallingState.REFUSED
//                                    callStateTextView.setText(st1)
//                                } else if (isAnswered) {
//                                    callingState = CallingState.NORMAL
//                                    if (endCallTriggerByMe) {
//                                        //                                        callStateTextView.setText(st7);
//                                    } else {
//                                        callStateTextView.setText(st8)
//                                    }
//                                } else {
//                                    if (isInComingCall) {
//                                        callingState = CallingState.UNANSWERED
//                                        callStateTextView.setText(st9)
//                                    } else {
//                                        if (callingState !== CallingState.NORMAL) {
//                                            callingState = CallingState.CANCELLED
//                                            callStateTextView.setText(st10)
//                                        } else {
//                                            callStateTextView.setText(st11)
//                                        }
//                                    }
//                                }
//                            }
//                            postDelayedCloseMsg()
//                        }
//
//                    })
                }

                else -> {
                }
            }
        }

        EMClient.getInstance().callManager().addCallStateChangeListener(callStateListener)


    }

    var phoneStateCallback: PhoneStateManager.PhoneStateCallback =
        PhoneStateManager.PhoneStateCallback { state, incomingNumber ->
            when (state) {
                TelephonyManager.CALL_STATE_RINGING   // 电话响铃
                -> {
                }
                TelephonyManager.CALL_STATE_IDLE      // 电话挂断
                ->
                    // resume current voice conference.
                    if (isMuteState) {
                        try {
                            EMClient.getInstance().callManager().resumeVoiceTransfer()
                        } catch (e: HyphenateException) {
                            e.printStackTrace()
                        }

                    }
                TelephonyManager.CALL_STATE_OFFHOOK   // 来电接通 或者 去电，去电接通  但是没法区分
                ->
                    // pause current voice conference.
                    if (!isMuteState) {
                        try {
                            EMClient.getInstance().callManager().pauseVoiceTransfer()
                        } catch (e: HyphenateException) {
                            e.printStackTrace()
                        }

                    }
            }
        }

    override fun onDestroy() {
        super.onDestroy()
    }

}
