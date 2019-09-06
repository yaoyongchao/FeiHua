package com.fh.bdc.base

import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.annotation.RequiresApi
import com.alibaba.android.arouter.facade.annotation.Route
import com.fh.baselib.base.BaseActivity
import com.fh.baselib.http.BaseApi
import com.fh.baselib.utils.LogUtil
import com.fh.bdc.R
import com.fh.bdc.utils.RouteUrl
import com.github.lzyzsd.jsbridge.BridgeWebViewClient
import kotlinx.android.synthetic.main.activity_base_web_view.*


@Route(path = RouteUrl.webView)
class BaseWebViewActivity : BaseActivity() {
    var webSettings: WebSettings? = null
    var url = ""

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        val extrsa = intent.extras
        url = extrsa.getString("0")
        super.onCreate(savedInstanceState)

    }
    override fun layoutId(): Int {
        return R.layout.activity_base_web_view
    }

    override fun initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings?.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        val ua = webSettings?.userAgentString
        //必须设置
        webSettings?.userAgentString = "$ua; app/lottchina  Android"
        LogUtil.i("userAgent: ${webSettings?.userAgentString}")
        webSettings?.javaScriptCanOpenWindowsAutomatically = true
        webSettings?.javaScriptEnabled = true
        webSettings?.setAppCacheEnabled(true)
        webSettings?.cacheMode = WebSettings.LOAD_DEFAULT
        /**必须的设置， 访问网页版的H5，一定要设置。该方法是设置支持DomStorage，
         * DOM Storage 分为 sessionStorage 和 localStorage。
         * localStorage 对象和 sessionStorage 对象使用方法基本相同，它们的区别在于作用的范围不同。
         * sessionStorage 用来存储与页面相关的数据，它在页面关闭后无法使用。而 localStorage 则持久存在，在页面关闭后也可以使用。
         */
        webSettings?.domStorageEnabled = true

        webView?.addJavascriptInterface(JsOperation(),"lottchina")
//        webView?.setDefaultHandler(DefaultHandler())


        webView.webViewClient = object : BridgeWebViewClient(webView){
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//                view?.loadUrl(url)
                LogUtil.i("webViewClient url: $url")
                if (url!!.contains("quit?mainClient=true") || url.startsWith(BaseApi.URL_DOMAIN + "?_v=")) {
                    finish()
                    return false
                } else {
                    return super.shouldOverrideUrlLoading(view, url)
                }
//                return super.shouldOverrideUrlLoading(view, url)
            }

            /*override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                L.i("request: ${request.toString()}")
                return super.shouldOverrideUrlLoading(view, request)
            }*/
        }

        webView.webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView?, title: String?) {
                setToolbarTitle(title!!)
                super.onReceivedTitle(view, title)
            }
        }

        webView.loadUrl(url)

    }

    override fun initData() {
    }

    override fun initListener() {
    }

    internal inner class JsOperation {

        /*val userInfo: String
            @JavascriptInterface
            get() {
                val userInfo = UserInfo(CommonMethod.getUserId(), CommonMethod.getToken())
                val s = GsonUtil.GsonString(userInfo)
                L.e("getUserInfo用户信息：$s")
                return s
            }*/

        //            return JSON.toJSONString(new LatLngLocation(LotteryApplication.getInstance().getLatLngLocation().getLatitude(),LotteryApplication.getInstance().getLatLngLocation().getLongitude()));
        //            TencentLocation tencentLocation = SPObjUtil.getObject(mContext,TencentLocation.class);
        /* val latLngLocation: String
             @JavascriptInterface
             get() {
                 val latLngLocation = CommonMethod.getLatLngLocation()
                 return JSON.toJSONString(LatLngLocation(latLngLocation.getLan(), latLngLocation.getLon()))
             }*/

        @JavascriptInterface
        fun showStationMap() {
            //            startActivity(new Intent(WebViewActivity.this,MapLocationActivity.class));
        }
    }

    override fun onDestroy() {
        destoryWebView()
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        webView?.clearHistory()
    }

    fun destoryWebView() {
        webView.stopLoading()//停止加载
        webView.removeAllViews()//移除webview上子view
        webView.clearCache(true)//清除缓存
        webView.clearHistory()//清楚历史
        webView.destroy()//销毁WebView自身。
        (webView.parent as ViewGroup).removeView(webView)//把webview从视图中移除
    }

}
