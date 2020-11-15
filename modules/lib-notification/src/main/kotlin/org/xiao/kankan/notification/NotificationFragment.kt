package org.xiao.kankan.notification

import org.xiao.mvvm.MvvmFragment

class NotificationFragment : MvvmFragment<NotificationViewModel>() {
    companion object {
        fun newInstance(): NotificationFragment {
            return NotificationFragment()
        }
    }

    override fun getRootLayoutId(): Int {
        return R.layout.notification_fragment
    }

    override fun getViewModelClass(): Class<NotificationViewModel> {
        return NotificationViewModel::class.java
    }
}