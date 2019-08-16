package com.fh.baselib.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.fh.baselib.R
import com.fh.baselib.utils.ActivityManagers
import com.fh.baselib.utils.ActivityUtil
import com.fh.baselib.utils.ToastUtil
import com.fh.baselib.widget.CustomToolBar
import com.fh.baselib.widget.LoadingDialog
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import gorden.rxbus2.RxBus

/**
 * Author: Austin
 * Date: 19-3-28
 * Description: Activity基类
 */
abstract class BaseActivity : RxAppCompatActivity() , CustomToolBar.OnClickLeftListener, CustomToolBar.OnClickRightListener,View.OnClickListener {
    private val BASE_VIEW_ID: Int = R.layout.activity_base
    lateinit var mContext: Context
    lateinit var customToolBar: CustomToolBar
    lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        mContext = this
        if (isFullScreen())
            ActivityUtil.transparentStatusBar(window)
        super.onCreate(savedInstanceState)
        setContentView(initRootView())
        ActivityManagers.instance.addActivity(this)
        RxBus.get().register(this)
        loadingDialog = LoadingDialog(mContext)
        initView()
        initListener()
        initData()

    }

    /**
     * 初始化根部View 并添加自定义的ToolBar
     */
    private fun initRootView(): View {
        var baseView: LinearLayout = layoutInflater.inflate(BASE_VIEW_ID,null) as LinearLayout
        customToolBar = CustomToolBar(this)
        customToolBar.onClickLeftListener = this
        customToolBar.onClickRightListener = this
        baseView.addView(customToolBar)

//        //添加网络链接状态
//        var netView: LinearLayout = layoutInflater.inflate(R.layout.inclue_socket_status,null) as LinearLayout
//        var paramsNet: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                /*LinearLayout.LayoutParams.WRAP_CONTENT*/ScreenUtils.dip2px(this,40f))
//        netView.layoutParams = paramsNet
//        baseView.addView(netView)

        if (layoutId() != 0) {
            var subView: View = getLayoutInflater().inflate(layoutId(), null)
            var params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT, 1.0f)
            subView.setLayoutParams(params)
            baseView.addView(subView)
        }



        return baseView
    }

    /**
     * 加载布局
     */
    abstract fun layoutId(): Int

    /**
     * 初始化 View
     */
    abstract fun initView()

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 设置事件
     */
    abstract fun initListener()

    override fun onClick(v: View?) {

    }

    /**
     * 设置标题
     */
    fun setToolbarTitle(title: String) {

        customToolBar!!.setTitle(title)
    }

    /**
     * 设置标题
     */
    fun setToolbarTitle(title: Int) {
        customToolBar!!.setTitle(title)
    }

    /**
     * 是否显示标题栏
     */
    fun showToolbar(visibility: Int) {
        customToolBar!!.visibility = visibility
    }

    /**
     * 是否显示标题栏
     */
    fun showToolbar(bool: Boolean) {
        customToolBar!!.visibility = if (bool) View.VISIBLE else View.GONE
    }

    /**
     * Toolbar 左边ICON点击事件
     */
    override fun onClickLeft(view: View) {
        finish();
    }

    /**
     * Toolbar 右边ICON点击事件
     */
    override fun onClickRight(view: View) {
    }

    fun showRightIcon(visibility: Int) {
        customToolBar?.showRightIcon(visibility)
    }

    override fun onStart() {
        super.onStart()
//        L.d("onStart")
    }

    override fun onResume() {
        super.onResume()
//        L.d("onResume")
    }

    override fun onPause() {
        super.onPause()
//        L.d("onPause")
    }

    override fun onStop() {
        super.onStop()
//        L.d("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
//        L.d("")
        if (loadingDialog?.isShowing!!)
            loadingDialog?.dismiss()
        RxBus.get().unRegister(this)
        ActivityManagers.instance.removeActivity(this)

    }

    /**
     * 显示进度条
     */
    open fun showDialog() {
        if (loadingDialog != null && !loadingDialog?.isShowing!!)
            loadingDialog?.show()
    }

    /**
     * 取消进度条显示
     */
    open fun dismissDialog() {
        if (loadingDialog?.isShowing!!)
            loadingDialog?.dismiss()
    }
    /**
     * 显示短时间吐司
     */
    fun toast(msg : String) {
        ToastUtil.show(mContext,msg)
    }

    /**
     * 显示一个长时间的吐司
     */
    fun toastLong(msg : String) {
        ToastUtil.showLong(mContext,msg)
    }

    /**
     * 是否显示网络状态： true ，表示一直显示，不管是否链接都显示
     */
//    fun showNetStatus():Boolean {
//        return false
//    }

    open fun isFullScreen(): Boolean {
        return false
    }

    fun addFragment(viewId: Int,fragment: Fragment) {
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(viewId,fragment)
//        fragmentTransaction.commit()
        fragmentTransaction.commitAllowingStateLoss()
    }

}