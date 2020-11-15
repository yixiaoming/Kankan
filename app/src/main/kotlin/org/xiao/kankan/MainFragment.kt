package org.xiao.kankan

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.main_fragment.*
import org.xiao.kankan.community.CommunityFragment
import org.xiao.kankan.home.HomeFragment
import org.xiao.kankan.notification.NotificationFragment
import org.xiao.kankan.personal.PersonalFragment
import org.xiao.mvvm.MvvmFragment

class MainFragment : MvvmFragment<MainViewModel>() {

    companion object {
        const val TAG = "MainFragment"

        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    private val mHomeFragment by lazy {
        val tag = HomeFragment::class.java.name
        if (childFragmentManager.findFragmentByTag(tag) != null) {
            return@lazy childFragmentManager.findFragmentByTag(tag) as HomeFragment
        }
        HomeFragment.newInstance()
    }
    private val mCommunityFragment by lazy {
        val tag = CommunityFragment::class.java.name
        if (childFragmentManager.findFragmentByTag(tag) != null) {
            return@lazy childFragmentManager.findFragmentByTag(tag)
        }
        CommunityFragment.newInstance()
    }
    private val mNotificationFragment by lazy {
        val tag = NotificationFragment::class.java.name
        if (childFragmentManager.findFragmentByTag(tag) != null) {
            return@lazy childFragmentManager.findFragmentByTag(tag)
        }
        NotificationFragment.newInstance()
    }
    private val mPersonalFragment by lazy {
        val tag = PersonalFragment::class.java.name
        if (childFragmentManager.findFragmentByTag(tag) != null) {
            return@lazy childFragmentManager.findFragmentByTag(tag)
        }
        PersonalFragment.newInstance()
    }
    private var mCurFragment: Fragment? = null

    override fun getRootLayoutId(): Int {
        return R.layout.main_fragment
    }

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottom_bar.setOnNavigationItemSelectedListener {
            var willShowFragment: Fragment? = null
            when (it.itemId) {
                R.id.item_home -> {
                    willShowFragment = mHomeFragment
                }
                R.id.item_community -> {
                    willShowFragment = mCommunityFragment
                }
                R.id.item_notification -> {
                    willShowFragment = mNotificationFragment
                }
                R.id.item_personal -> {
                    willShowFragment = mPersonalFragment
                }
            }
            return@setOnNavigationItemSelectedListener switchFragment(willShowFragment)
        }
        switchFragment(mHomeFragment)
    }

    private fun switchFragment(willShowFragment: Fragment?): Boolean {
        if (willShowFragment == null) return true
        if (willShowFragment == mCurFragment) return true
        val transaction = childFragmentManager.beginTransaction()
        if (mCurFragment != null) {
            transaction.hide(mCurFragment!!)
        }
        mCurFragment = willShowFragment
        val tag = mCurFragment!!::class.java.name
        if (childFragmentManager.findFragmentByTag(tag) == null) {
            transaction.add(R.id.container, mCurFragment!!, tag)
        }
        transaction.show(mCurFragment!!)
        transaction.commit()
        return true
    }
}
