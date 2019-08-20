package com.fh.bdc.ui.home

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import com.fh.baselib.base.BaseActivity
import com.fh.baselib.base.BaseFragment
import com.fh.baselib.utils.ActivityUtil.setStatusBarTransparent
import com.fh.baselib.utils.TabUtils
import com.fh.bdc.utils.RouteUrl
import com.google.android.material.tabs.TabLayout
import com.ygfh.doctor.ui.home.Tab1Fragment
import com.ygfh.doctor.ui.home.Tab2Fragment
import com.ygfh.doctor.ui.home.Tab3Fragment
import kotlinx.android.synthetic.main.activity_home.*


@Route(path = RouteUrl.home)
class HomeActivity : BaseActivity() , ViewPager.OnPageChangeListener{


    private  var tabTitles = mutableListOf(com.fh.bdc.R.string.tab1, com.fh.bdc.R.string.tab2, com.fh.bdc.R.string.tab3)
    private  var tabImgs = mutableListOf(com.fh.bdc.R.drawable.tab2_selector, com.fh.bdc.R.drawable.tab2_selector, com.fh.bdc.R.drawable.tab2_selector)
    private  var tabFragments = mutableListOf<BaseFragment>()
    private lateinit var homePagerAdapter: HomePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun layoutId(): Int {
        return com.fh.bdc.R.layout.activity_home
    }

    override fun initView() {
        showToolbar(false)
        tabFragments.add(Tab1Fragment.instance)
        tabFragments.add(Tab2Fragment.instance)
        tabFragments.add(Tab3Fragment.instance)

        homePagerAdapter = HomePagerAdapter(supportFragmentManager)
        viewpager.adapter = homePagerAdapter
        TabUtils.setTabsImg(tablayout,layoutInflater,tabTitles,tabImgs)
        homePagerAdapter.notifyDataSetChanged()



    }



    override fun isFullScreen(): Boolean {
        return false
    }

    override fun initData() {

    }

    override fun initListener() {
        viewpager.setScroll(true)
        viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tablayout))
        tablayout.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewpager))
        viewpager.setOnPageChangeListener(this)
        viewpager.offscreenPageLimit = 2
    }

    /**
     * @description: ViewPager 适配器
     */
    inner class HomePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return tabFragments.get(position)
        }

        override fun getCount(): Int {
            return tabFragments.size
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        }

    }

    override fun onPageScrollStateChanged(p0: Int) {

    }

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
    }

    override fun onPageSelected(p0: Int) {
    }
}
