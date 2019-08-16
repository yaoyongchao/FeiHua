package com.fh.bdc.ui.login

import android.view.KeyEvent
import com.alibaba.android.arouter.facade.annotation.Route
import com.fh.baselib.base.BaseActivity
import com.fh.baselib.utils.rx.RxTimer
import com.fh.bdc.R
import com.fh.bdc.utils.CommonUtil
import com.fh.bdc.utils.JumpUtil
import com.fh.bdc.utils.RouteUrl

/**
 * Author: YongChao
 * Date: 19-8-7 下午4:52
 * Description:  启动页
 */
@Route(path = RouteUrl.startup)
class StartupActivity : BaseActivity() {
    private val time : Long = 1000 * 1
    override fun layoutId(): Int {
        return R.layout.activity_startup
    }

    override fun initView() {
        showToolbar(false)
        if (CommonUtil.isFirstUse()) {
            JumpUtil.jumpActivity(RouteUrl.guide)
        } else {
            RxTimer().timer(time, RxTimer.RxAction {
                finish()
                JumpUtil.jumpActivity(RouteUrl.login)

            })
        }

    }

    override fun isFullScreen(): Boolean {
        return true
    }

    override fun initData() {
    }

    override fun initListener() {
    }

    /**
     * 传入Boolean参数：如果是false，在这个Activity是任务的根Activity时，方法才会起效。
     * 传入true，任务中任意Activity都会起效。
     * @param keyCode
     * @param event
     * @return
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true)
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
