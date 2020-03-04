package au.com.swivelgroup.newsapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import au.com.swivelgroup.newsapp.R
import au.com.swivelgroup.newsapp.base.BaseActivity
import au.com.swivelgroup.newsapp.databinding.ActivityMainBinding
import au.com.swivelgroup.newsapp.ui.main.tabs.profile.ProfileFragment
import au.com.swivelgroup.newsapp.ui.main.tabs.topnews.TopNewsHeadlinesFragment
import au.com.swivelgroup.newsapp.ui.main.tabs.userpref.UserPreferenceNewsFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.tab_item.view.*
import java.util.*

class MainActivity : BaseActivity() {

    companion object {
        var mBinding: ActivityMainBinding? = null
        lateinit var fm: FragmentManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // setup tab view
        setupTabBar()
    }

    private fun setupTabBar() {

        val tabLayout = mBinding?.tabLayout
        val viewPager = mBinding?.mainTabContent

        setupViewPager(viewPager)
        tabLayout?.setupWithViewPager(viewPager)

        var viewTabOne = LayoutInflater.from(this).inflate(R.layout.tab_item, null)
        var viewTabTwo = LayoutInflater.from(this).inflate(R.layout.tab_item, null)
        var viewTabThree = LayoutInflater.from(this).inflate(R.layout.tab_item, null)

        viewTabOne.tv_tab_item.text = resources.getString(R.string.hot_news)
        viewTabTwo.tv_tab_item.text = resources.getString(R.string.preference_news)
        viewTabThree.tv_tab_item.text = resources.getString(R.string.profile)

        viewTabOne.iv_tab_item.setImageDrawable(resources.getDrawable(R.drawable.tab_topnews))
        viewTabTwo.iv_tab_item.setImageDrawable(resources.getDrawable(R.drawable.tab_preference))
        viewTabThree.iv_tab_item.setImageDrawable(resources.getDrawable(R.drawable.tab_profile))

        tabLayout?.getTabAt(0)?.customView = viewTabOne
        tabLayout?.getTabAt(1)?.customView = viewTabTwo
        tabLayout?.getTabAt(2)?.customView = viewTabThree

        tabLayout?.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> {
                        viewTabOne.tv_tab_item.visibility = View.VISIBLE
                        viewTabOne.tv_hidden_tab_item.visibility = View.GONE
                    }
                    1 -> {
                        viewTabTwo.tv_tab_item.visibility = View.VISIBLE
                        viewTabTwo.tv_hidden_tab_item.visibility = View.GONE
                    }
                    2 -> {
                        viewTabThree.tv_tab_item.visibility = View.VISIBLE
                        viewTabThree.tv_hidden_tab_item.visibility = View.GONE
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> {
                        viewTabOne.tv_tab_item.visibility = View.GONE
                        viewTabOne.tv_hidden_tab_item.visibility = View.INVISIBLE
                    }
                    1 -> {
                        viewTabTwo.tv_tab_item.visibility = View.GONE
                        viewTabTwo.tv_hidden_tab_item.visibility = View.INVISIBLE
                    }
                    2 -> {
                        viewTabThree.tv_tab_item.visibility = View.GONE
                        viewTabThree.tv_hidden_tab_item.visibility = View.INVISIBLE
                    }
                }
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        viewTabOne.tv_tab_item.visibility = View.VISIBLE
                        viewTabOne.tv_hidden_tab_item.visibility = View.GONE
                    }
                    1 -> {
                        viewTabTwo.tv_tab_item.visibility = View.VISIBLE
                        viewTabTwo.tv_hidden_tab_item.visibility = View.GONE
                    }
                    2 -> {
                        viewTabThree.tv_tab_item.visibility = View.VISIBLE
                        viewTabThree.tv_hidden_tab_item.visibility = View.GONE
                    }
                }
            }
        })

        tabLayout?.getTabAt(0)!!.select()
    }

    /**
     * setup fragments on viewpager for each tab item
     */
    private fun setupViewPager(viewPager: ViewPager?) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        // 1st tab - top news headlines
        adapter.insertNewFragment(TopNewsHeadlinesFragment())
        // 2nd tab - User preference news
        adapter.insertNewFragment(UserPreferenceNewsFragment())
        // 3rd tab - Profile
        adapter.insertNewFragment(ProfileFragment())

        viewPager?.adapter = adapter
    }

    internal inner class ViewPagerAdapter(manager: FragmentManager) :
        FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun insertNewFragment(fragment: Fragment) {
            mFragmentList.add(fragment)
        }
    }
}
