package com.yyc.vgalib

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hyphenate.EMCallBack
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.activity_login_vga.*

class LoginVgaActivity : AppCompatActivity() {
    lateinit var mContext: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_vga)
        mContext = this
        initViews()
    }

    private fun initViews() {
        btn_login.setOnClickListener {

            EMClient.getInstance().login(UserVga.name,UserVga.password,object : EMCallBack{
                override fun onSuccess() {
                    EMClient.getInstance().groupManager().loadAllGroups()
                    EMClient.getInstance().chatManager().loadAllConversations()

                    log("登录聊天服务器成功！")
                    startActivity(Intent(mContext,VgaActivity::class.java))
                }

                override fun onProgress(progress: Int, status: String?) {
                }

                override fun onError(code: Int, error: String?) {
                    log("登录聊天服务器失败 ！")
                }

            })

        }
    }

    private fun log(s:String) {
        Log.e("LoginVgaActivity",s)
    }
}
