package com.fh.bdc.demo

import cn.jzvd.Jzvd
import com.fh.baselib.base.BaseActivity
import com.fh.baselib.utils.img.ImgUtil
import com.fh.bdc.R
import kotlinx.android.synthetic.main.activity_jiao_zi.*

class JiaoZiActivity : BaseActivity() {
    override fun layoutId(): Int {
     return R.layout.activity_jiao_zi
    }

    override fun initView() {
        jz_video.setUp("http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4"
            , "饺子快长大");

//        Glide.with(this).load("http://jzvd-pic.nathen.cn/jzvd-pic/1bb2ebbe-140d-4e2e-abd2-9e7e564f71ac.png").into(jz_video.thumbImageView)
        ImgUtil.loadImg(mContext,"http://jzvd-pic.nathen.cn/jzvd-pic/1bb2ebbe-140d-4e2e-abd2-9e7e564f71ac.png",jz_video.thumbImageView)

    }

    override fun initData() {
    }

    override fun initListener() {
    }

    override fun onPause() {
        super.onPause()
        Jzvd.releaseAllVideos()
    }

    override fun onBackPressed() {
        if (Jzvd.backPress())
            return
        super.onBackPressed()

    }
}
