package com.ygfh.doctor.ui.demo

import com.fh.baselib.mvp.BasePresenter
import com.fh.baselib.mvp.BaseView

/**
 * Author: YongChao
 * Date: 19-8-14 下午3:25
 * Description:
 */
interface TwoContract {
    interface TwoView: BaseView {
        fun loginSuccess()
    }

    abstract class TwoPresenter: BasePresenter<TwoView>() {
        abstract fun login()
    }
}