package com.fh.bdc

import com.alibaba.android.arouter.launcher.ARouter
import com.fh.baselib.BaseApplication
import com.fh.baselib.utils.LogUtil

/**
 * Author: YongChao
 * Date: 19-8-15 下午2:01
 * Description:
 */
class DcApplication: BaseApplication() {


    override fun initViews() {
        super.initViews()
        LogUtil.openLog(BuildConfig.isTest)
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }

}