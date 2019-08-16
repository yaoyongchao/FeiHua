package com.fh.baselib.http


import android.util.Log
import com.fh.baselib.BaseApplication
import com.fh.baselib.http.entity.BaseEntity
import com.fh.baselib.utils.NetWorkUtils
import com.fh.baselib.utils.ToastUtil
import io.reactivex.Observer
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable

/**
 * Author: Austin
 * Date: 2018/10/9
 * Description:
 */
//abstract class BaseObserver<T> (context: Context) : Observer<BaseEntity<T>> {
abstract class BaseObserver<T> : Observer<BaseEntity<T>> {
//    private val mContext: Context
    val  TAG = "BaseObserver"
    private val SUCCESS_CODE = 200

    override fun onSubscribe(@NonNull d: Disposable) {
        logd("onSubscribe")
        if (!NetWorkUtils.Companion.isConnectedByState(BaseApplication.appContext)) {
            ToastUtil.show("网络异常请检查网络")
            onFail("网络异常请检查网络")
            d.dispose()
        }
    }

    override fun onNext(@NonNull tBaseEntity: BaseEntity<T>) {
        logd("onNext")
        if (SUCCESS_CODE == tBaseEntity.code) {
            val t = tBaseEntity.data
            onSuccess(t)
        } else {
            loge("onNext--Failure--code:" + tBaseEntity.code + "--Message:" + tBaseEntity.msg)
            onFail(tBaseEntity.msg)
        }
    }

    override fun onError(@NonNull e: Throwable) {
        loge("TAG--onError--" + e.toString())
        if (!NetWorkUtils.Companion.isConnectedByState(BaseApplication.appContext)) {
//            ToastUtil.show("网络异常请检查网络")
            onFail("网络异常请检查网络")
        } else {
            onFail(e.toString())
        }
//        ExceptionHandle.handleException(e)
    }

    override fun onComplete() {
        logd("TAG--onComplete")

    }

    abstract fun onSuccess(t: T?)

    open fun onFail(msg: String) {
        ToastUtil.show(msg)
    }

    /**
     * 此次事件结束 ，走完onComplete方法和 error方法， 走该方法， 原因是走onErroe方法就不走onComplete方法了。
     */
    open fun onFinish() {}

    fun logd(str: String) {
        Log.d(TAG,str)
    }
    fun loge(str: String) {
        Log.e(TAG,str)
    }
}
