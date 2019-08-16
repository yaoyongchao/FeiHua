package com.fh.baselib.utils.rx;


import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

;

/**
 * Author: Austin
 * Date: 2018/10/9
 * Description:
 */
public class MyRxScheduler {
    public static <T> ObservableTransformer<T,T> applySchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /*public static <T> ObservableTransformer<T,T> ioMain() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                    if (!NetWorkUtils.Companion.isConnectedByState(BaseApplication.appContext)) {
                        disposable.dispose();
                        L.Companion.d("doOnSubscribe -- There's no network link.");
                        ToastUtil.Companion.show("网络异常请检查网络");
                    } else {
//                        ToastUtil.Companion.show("网络连接中");
                        L.Companion.d("doOnSubscribe -- There's a network link.");

                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }*/
    public static <T> ObservableTransformer<T,T> ioMain() {
        return observable -> observable.subscribeOn(Schedulers.io())
//                .doOnSubscribe(disposable -> {
//                    if (!NetWorkUtils.Companion.isConnectedByState(BaseApplication.appContext)) {
//                        disposable.dispose();
//                        L.Companion.d("doOnSubscribe -- There's no network link.");
//                        ToastUtil.Companion.show("网络异常请检查网络");
//                    } else {
////                        ToastUtil.Companion.show("网络连接中");
//                        L.Companion.d("doOnSubscribe -- There's a network link.");
//                    }
//                })
//                .doFinally(new Action() {
//                    @Override
//                    public void run() throws Exception {
//
//                    }
//                })
                .observeOn(AndroidSchedulers.mainThread());
    }


}
