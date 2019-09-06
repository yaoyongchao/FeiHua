package com.fh.baselib.base

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.fh.baselib.R
import com.fh.baselib.utils.ActivityManagers
import com.fh.baselib.utils.ActivityUtil
import com.fh.baselib.utils.ToastUtil
import com.fh.baselib.widget.CustomToolBar
import com.fh.baselib.widget.LoadingDialog
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import gorden.rxbus2.RxBus
import qiu.niorgai.StatusBarCompat

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

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        mContext = this
        if (isFullScreen()) {
            ActivityUtil.transparentStatusBar(window)
        } else {
            StatusBarCompat.setStatusBarColor(this,resources.getColor(R.color.bg_toolbar))
            StatusBarCompat.setStatusBarColor(this,resources.getColor(R.color.transparent))
//            StatusBarCompat.setStatusBarColor(this,resources.getColor(R.color.white))

//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            }
        }
//        UtilsStyle.statusBarLightMode(this)
        //修改状态栏字体颜色
        setStatusTextColor()
        setStatusColor()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT//禁止横屏
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

    fun setStatusTextColor() {
        //https://blog.csdn.net/dummyo/article/details/90108788
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        //设置状态栏字体颜色为黑色
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun setStatusColor() {
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        //设置状态栏背景色
        window.setStatusBarColor(resources.getColor(R.color.bg_toolbar));
    }
}