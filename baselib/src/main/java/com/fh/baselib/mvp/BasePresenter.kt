package com.fh.baselib.mvp

import java.lang.ref.WeakReference

abstract class BasePresenter<V: BaseView> {
    private var mView: WeakReference<V>? = null

    /**
     * 绑定view，一般在初始化中调用该方法
     */
    fun bindView(view: V) {
        this.mView = WeakReference(view)
//        L.e("view: $view" )
    }

    /**
     * View是否绑定
     *
     * @return
     */
    fun isBind(): Boolean {
        return mView != null && mView!!.get() != null
    }

    /**
     * 解除绑定view，一般在onDestroy中调用
     */
    fun unBindView() {
        if (isBind()) {
            mView!!.clear()
            mView = null
        }
    }

    fun getView(): V? {
        return if (isBind()) mView!!.get() else null
    }

}