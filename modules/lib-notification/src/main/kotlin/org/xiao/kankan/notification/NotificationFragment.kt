package org.xiao.kankan.notification

import org.xiao.kankan.notification.databinding.NotificationFragmentBinding
import org.xiao.mvvm.MvvmFragment

class NotificationFragment : MvvmFragment<NotificationFragmentBinding, NotificationViewModel>() {
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