package com.rs.flickd.databinding

import android.view.View
import androidx.databinding.BindingAdapter

object BindingAdapters {
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }
}