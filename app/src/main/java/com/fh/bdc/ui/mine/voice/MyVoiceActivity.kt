package com.fh.bdc.ui.mine.voice

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import com.fh.baselib.base.BaseActivity
import com.fh.baselib.base.BaseFragment
import com.fh.bdc.utils.RouteUrl
import com.google.android.material.tabs.TabLayout
import com.ygfh.doctor.ui.home.MyVoiceFragment
import kotlinx.android.synthetic.main.activity_home.*



/**
 * Author: YongChao
 * Date: 19-9-6 下午3:49
 * Description: My Voice
 */

@Route(path = RouteUrl.myvoice)
class MyVoiceActivity : BaseActivity(),ViewPager.OnPageChangeListener,TabLayout.OnTabSelectedListener {

    private  var tabTitles = mutableListOf(com.fh.bdc.R.string.voice_tab1, com.fh.bdc.R.string.voice_tab2, com.fh.bdc.R.string.voice_tab3)
//    private  var tabImgs = mutableListOf(R.drawable.tab2_selector, R.drawable.tab2_selector, R.drawable.tab2_selector)
    private  var tabFragments = mutableListOf<BaseFragment>()

    private lateinit var myVoicePagerAdapter: MyVoicePagerAdapter
    override fun layoutId(): Int {
        return com.fh.bdc.R.layout.activity_my_voice
    }

    override fun initView() {
        setToolbarTitle("我的语音问答")
        customToolBar?.setBackgroundTvRight(this.resources.getDrawable(com.fh.bdc.R.drawable.bg_answer_selector))
        customToolBar?.setTitleRight("去答题")
        customToolBar?.setTitleRightColor(resources.getColor(com.fh.bdc.R.color.white))
        customToolBar?.setOnClickTvRightListener(View.OnClickListener {

        })

        tabFragments.add(MyVoiceFragment())
        tabFragments.add(MyVoiceFragment())
        tabFragments.add(MyVoiceFragment())

        myVoicePagerAdapter = MyVoicePagerAdapter(supportFragmentManager)
        viewpager.adapter = myVoicePagerAdapter

        //TabLayout的基本使用
//        for(int i=0;i<4;i++){
//            TabLayout.Tab tab=mTabLayout.newTab();
//            tab.setTag(i);
//            tab.setText(mTitles[i]);
//            mTabLayout.addTab(tab);
//            ｝
        for (i in tabTitles.indices) {
            var tab = tablayout.newTab()
            tab.setTag(i)
            tab.setText(tabTitles[i])
            tablayout.addTab(tab)
        }



//        TabUtils.setTabsImg(tablayout,layoutInflater,tabTitles)
        myVoicePagerAdapter.notifyDataSetChanged()
    }

    override fun initData() {
    }

    override fun initListener() {
        viewpager.setScroll(true)
        viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tablayout))
        tablayout.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewpager))
        viewpager.setOnPageChangeListener(this)
        viewpager.offscreenPageLimit = 1

        tablayout.addOnTabSelectedListener(this)
    }

    /**
     * @description: ViewPager 适配器
     */
    inner class MyVoicePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return tabFragments.get(position)
        }

        override fun getCount(): Int {
            return tabFragments.size
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        }

    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
    }
    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
//        val textView = TextView(mContext)
//        val selectedSize =
//            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 16f, resources.displayMetrics)
//        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, selectedSize)
//        textView.setTextColor(resources.getColor(com.fh.bdc.R.color.color_eb1e03))
//        textView.text = tab.getText()
//        tab.setCustomView(textView)
    }

}
