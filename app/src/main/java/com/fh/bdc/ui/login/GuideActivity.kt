package com.fh.bdc.ui.login

import android.widget.ImageView
import cn.bingoogolapple.bgabanner.BGABanner
import cn.bingoogolapple.bgabanner.BGALocalImageSize
import com.alibaba.android.arouter.facade.annotation.Route
import com.fh.baselib.base.BaseActivity
import com.fh.baselib.utils.LogUtil
import com.fh.bdc.R
import com.fh.bdc.utils.CommonUtil
import com.fh.bdc.utils.JumpUtil
import com.fh.bdc.utils.RouteUrl
import kotlinx.android.synthetic.main.activity_guide.*

/**
 * Author: YongChao
 * Date: 19-8-7 下午3:41
 * Description: 引导页
 */
@Route(path = RouteUrl.guide)
class GuidePageActivity : BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_guide
    }

    override fun initView() {
        LogUtil.e("--" + CommonUtil.isFirstUse())
        /*if (!CommonUtil.isFirstUse()) {
            JumpUtil.jumpActivity(RouteUrl.login)
            finish()
        }*/
        showToolbar(false)


        processLogic()



    }

    override fun isFullScreen(): Boolean {
        return true
    }

    override fun initData() {
    }

    override fun initListener() {
        /**
         * 设置进入按钮和跳过按钮控件资源 id 及其点击事件
         * 如果进入按钮和跳过按钮有一个不存在的话就传 0
         * 在 BGABanner 里已经帮开发者处理了防止重复点击事件
         * 在 BGABanner 里已经帮开发者处理了「跳过按钮」和「进入按钮」的显示与隐藏
         */
        banner_guide.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, R.id.tv_guide_skip, BGABanner.GuideDelegate {
            CommonUtil.saveFirstUse()
            finish()
            JumpUtil.jumpActivity(RouteUrl.login)
        })
    }

    private fun processLogic() {
        // Bitmap 的宽高在 maxWidth maxHeight 和 minWidth minHeight 之间
        val localImageSize = BGALocalImageSize(720, 1280, 320f, 640f)
        // 设置数据源
        banner_guide.setData(localImageSize, ImageView.ScaleType.CENTER_CROP,
                R.drawable.ic_guide1,
                R.drawable.ic_guide2,
                R.drawable.ic_guide3
        )
    }


}
