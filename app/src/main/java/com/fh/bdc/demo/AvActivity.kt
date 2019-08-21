package com.fh.bdc.demo

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer
import android.net.Uri
import android.widget.MediaController
import com.fh.baselib.base.BaseActivity
import com.fh.bdc.R
import kotlinx.android.synthetic.main.activity_av.*

/**
 * Author: YongChao
 * Date: 19-8-21 下午2:14
 * Description: 音视频播放
 */
class AvActivity : BaseActivity() {
    var player : MediaPlayer  ?= null
    lateinit var animationDrawable:AnimationDrawable
    override fun layoutId(): Int {
        return R.layout.activity_av
    }
    override fun initView() {


        img.setImageResource(R.drawable.button_play_animation)
        animationDrawable = img.drawable as AnimationDrawable
    }

    override fun initData() {
    }
    override fun initListener() {
        btn_video.setOnClickListener {
//            playVideo()
            startActivity(Intent(this,JiaoZiActivity::class.java))
        }

        btn_voice.setOnClickListener {
            playVioce()
        }

    }
    fun playVioce() {
        player = MediaPlayer()
        player?.setDataSource("http://192.168.10.38:8080/MyDemo/aa/a1.wav")
        player?.prepare()
        player?.start()
        animationDrawable.start()
        player?.setOnCompletionListener {
            player?.stop()
            player?.release()
            player = null
            animationDrawable.selectDrawable(0)
            animationDrawable.stop()
        }
    }

    fun playVideo() {
        val uri = Uri.parse("http://192.168.10.38:8080/MyDemo/aa/movie.mp4")
        videoView.setVideoURI(uri)
        videoView.setMediaController(MediaController(this))
        videoView.setOnClickListener {
            toast("播放完了")
        }
        videoView.start()
    }
}
