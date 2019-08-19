package com.fh.bdc.ui.login

import android.util.Log
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.fh.baselib.base.BaseActivity
import com.fh.baselib.http.BaseObserver
import com.fh.baselib.utils.AppUtil
import com.fh.baselib.utils.LogUtil
import com.fh.baselib.utils.rx.MyRxScheduler
import com.fh.bdc.bean.UpgradeBean
import com.fh.bdc.ui.dialog.UpgradeAppDialog
import com.fh.bdc.ui.dialog.UpgradeProgressDialog
import com.fh.bdc.utils.JumpUtil
import com.fh.bdc.utils.RouteUrl
import com.weicai.upgradelib.AppDownloadManager
import com.ygfh.doctor.net.DcServiceFactory
import kotlinx.android.synthetic.main.activity_login.*
import java.text.NumberFormat
import java.util.concurrent.TimeUnit


/**
 * Author: YongChao
 * Date: 19-8-16 上午11:50
 * Description: 登录
 */
@Route(path = RouteUrl.login)
class LoginActivity : BaseActivity() {
    var upgradeDialog: UpgradeAppDialog? = null
    override fun layoutId(): Int {
        return com.fh.bdc.R.layout.activity_login
    }

    override fun onResume() {
        super.onResume()
        appDownloadManager?.resume()
    }

    override fun onPause() {
        super.onPause()
//        appDownloadManager?.onPause()
    }

    override fun initView() {
        appDownloadManager = AppDownloadManager(this)
        tv_version_name.text = AppUtil.getVersionName(this)

    }

    override fun initData() {
    }

    override fun initListener() {
        btn_login.setOnClickListener {
//            startActivity(Intent(mContext,TwoActivity::class.java))
            JumpUtil.jumpActivity(RouteUrl.home)
        }

        btn_update.setOnClickListener {
            getUpgrade()

        }
    }

    fun getUpgrade() {
        DcServiceFactory.getService().upgrade()
            .delay(5, TimeUnit.SECONDS)
            .compose(MyRxScheduler.ioMain(mContext))
            .subscribe(object : BaseObserver<UpgradeBean>(){
                override fun onSuccess(t: UpgradeBean?) {
                    LogUtil.e("升级:" + t!! + "-- ")
                    showUpgradeDailog(t!!)
                }
            })
    }

    var progressDialog: UpgradeProgressDialog? = null
    var appDownloadManager : AppDownloadManager? = null
    val url = "http://192.168.43.48:8080/MyDemo/aa/ac.html"

    private fun showUpgradeDailog(bean: UpgradeBean) {
        upgradeDialog = UpgradeAppDialog(mContext,bean.discription!!,bean.url!!, View.OnClickListener {
            progressDialog?.show()

            upgradeDialog?.dismiss()
            var progressData = ""
            appDownloadManager?.setUpdateListener(object : AppDownloadManager.OnUpdateListener{
                override fun update(currentByte: Int, totalByte: Int) {
                    progressData = getRate(currentByte,totalByte)
                    Log.e("aa","current:" + currentByte + "---total:" + totalByte)
                    Log.e("aa","比率：" + progressData )
                    progressDialog?.setTvText(progressData)
                }
            })

            appDownloadManager?.downloadApk(bean.url, "dayiketang_" + bean.versionName!!, "版本更新")
        })
        upgradeDialog?.show()

        progressDialog = UpgradeProgressDialog(mContext,bean.url!!,bean.versionName!!)



//        progressDialog?.setTest("111222")

    }

    private fun getRate(current : Int , total: Int): String {
        // 创建一个数值格式化对象
        val numberFormat = NumberFormat.getInstance()
// 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2)
        val result = numberFormat.format(current.toFloat() / total.toFloat() * 100)
        return result
    }
}
