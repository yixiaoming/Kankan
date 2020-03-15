package org.xiao.kankan

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import org.xiao.kankan.community.CommunityFragment
import org.xiao.kankan.databinding.MainFragmentBinding
import org.xiao.kankan.home.HomeFragment
import org.xiao.kankan.notification.NotificationFragment
import org.xiao.kankan.personal.PersonalFragment
import org.xiao.mvvm.MvvmFragment

class MainFragment : MvvmFragment<MainFragmentBinding, MainViewModel>() {

    private val mHomeFragment by lazy {
        HomeFragment.newInstance()
    }
    private val mCommunityFragment by lazy {
        CommunityFragment.newInstance()
    }
    private val mNotificationFragment by lazy {
        NotificationFragment.newInstance()
    }
    private val mPersonalFragment by lazy {
        PersonalFragment.newInstance()
    }
    private var mCurFragment: Fragment? = null

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun getRootLayoutId(): Int {
        return R.layout.main_fragment
    }

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.bottomBar.setOnNavigationItemSelectedListener {
            Toast.makeText(context, "123", Toast.LENGTH_SHORT).show()
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
        val transaction = parentFragmentManager.beginTransaction()
        if (mCurFragment != null) {
            transaction.hide(mCurFragment!!)
        }
        mCurFragment = willShowFragment
        val tag = mCurFragment!!.javaClass.canonicalName
        if (parentFragmentManager.findFragmentByTag(tag) == null) {
            transaction.add(R.id.container, mCurFragment!!, tag)
        }
        transaction.show(mCurFragment!!)
        transaction.commit()
        return true
    }
}
