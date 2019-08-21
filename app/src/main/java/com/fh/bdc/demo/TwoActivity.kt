package com.fh.bdc.demo

import android.content.Intent
import android.os.Environment
import android.util.Log
import com.fh.baselib.mvp.MvpBaseActivity
import com.fh.baselib.net.file.DownloadProgressHandler
import com.fh.baselib.net.file.FileDownloader
import com.fh.baselib.utils.LogUtil
import com.fh.bdc.BuildConfig
import com.ygfh.doctor.net.DcServiceFactory
import com.ygfh.doctor.ui.demo.TwoContract
import com.ygfh.doctor.ui.demo.TwoPresenter
import kotlinx.android.synthetic.main.activity_two.*
import java.io.File
import java.text.NumberFormat


class TwoActivity : MvpBaseActivity<TwoContract.TwoView,TwoPresenter>(),TwoContract.TwoView {
    var url = "http://192.168.10.38:8080/MyDemo/"
    val ROOT_PATH = Environment.getExternalStorageDirectory()//+File.separator// sd路径

    /**
     * 更新apk 路径
     */
    val DOWNLOAD_APK_PATH = ROOT_PATH.toString() + File.separator + "downloadApk"


    override fun layoutId(): Int {
        return com.fh.bdc.R.layout.activity_two
    }

    override fun initView() {
        tv.text = "状态--" + BuildConfig.DEBUG
        Log.e("aa","--"+ ROOT_PATH.toString()+ "***" + File.separator)
    }

    override fun initData() {
        LogUtil.i("init1111")
    }

    override fun initListener() {
        LogUtil.i("init")
        btn1.setOnClickListener {

            mPresenter?.login()

//            showDialog()
//            loadingFragment.show(supportFragmentManager,"loading")
            /*DcServiceFactory.getService().login2()
                .delay(5, TimeUnit.SECONDS)
                .compose(MyRxScheduler.ioMain())
                .subscribe(object : BaseObserver<Login>(){
                    override fun onSuccess(t: Login?) {
                        LogUtil.e("login:" + t!! + "-- " )
                    }

                    override fun onFinish() {
                        super.onFinish()
                        dismissDialog()
                    }


                })*/
        }

        btn_down.setOnClickListener {
            FileDownloader.downloadFile2(DcServiceFactory.getService(url).downloadApkFile2("yaoyongchao"),DOWNLOAD_APK_PATH,"aa.apk",object :
                DownloadProgressHandler(){
                override fun onProgress(progress: Int, total: Long, speed: Long) {

                    Log.e("aa","total: $total ------progress: $progress" + "----" + getRate(progress,total) )
                }

                override fun onCompleted(file: File?) {
                    Log.e("aa","下载成功")
                }

                override fun onError(e: Throwable?) {
                    Log.e("aa","onError")
                }

            })
        }

        btn_picture.setOnClickListener {
            startActivity(Intent(mContext,PictureActivity::class.java))
        }

        btn_vv.setOnClickListener {
            startActivity(Intent(mContext,AvActivity::class.java))
        }
    }

    override fun loginSuccess() {

    }

    private fun getRate(current : Int , total: Long): String {
        // 创建一个数值格式化对象
        val numberFormat = NumberFormat.getInstance()
// 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2)
        val result = numberFormat.format(current.toFloat() / total.toFloat() * 100)
        return result
    }
}
