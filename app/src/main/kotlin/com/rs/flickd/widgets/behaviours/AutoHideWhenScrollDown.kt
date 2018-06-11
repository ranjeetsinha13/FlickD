package com.rs.flickd.widgets.behaviours

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
import androidx.core.view.ViewCompat

class AutoHideWhenScrollDown<V : View> : Behavior<V> {

    constructor() : super()

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private var currentAnimator: Animator? = null
    private var isHiding: Boolean = false
    private var isShowing: Boolean = false

    private val hidingAnimatorListener = object : Animator.AnimatorListener {
        override fun onAnimationStart(animator: Animator) {
            currentAnimator = animator
            isHiding = true
        }

        override fun onAnimationEnd(animator: Animator) {
            currentAnimator = null
            isHiding = false
        }

        override fun onAnimationCancel(animator: Animator) {
            currentAnimator = null
            isHiding = false
        }

        override fun onAnimationRepeat(animator: Animator) {
            // not used
        }
    }

    private val showingAnimatorListener = object : Animator.AnimatorListener {
        override fun onAnimationStart(animator: Animator) {
            currentAnimator = animator
            isShowing = true
        }

        override fun onAnimationEnd(animator: Animator) {
            currentAnimator = null
            isShowing = false
        }

        override fun onAnimationCancel(animator: Animator) {
            currentAnimator = null
            isShowing = false
        }

        override fun onAnimationRepeat(animator: Animator) {
            // not used
        }
    }

    override fun onStartNestedScroll(
            coordinatorLayout: CoordinatorLayout,
            child: V, directTargetChild: View, target: View,
            @ViewCompat.ScrollAxis axes: Int, @ViewCompat.NestedScrollType type: Int
    ): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedPreScroll(
            coordinatorLayout: CoordinatorLayout,
            child: V, target: View, dx: Int, dy: Int,
            consumed: IntArray, @ViewCompat.NestedScrollType type: Int
    ) {
        if (dy < 0) {
            showView(child)
        } else if (dy > 0) {
            hideView(child)
        }
    }

    private fun hideView(view: V) {
        if (isHiding) {
            return
        }

        if (isShowing && currentAnimator != null) {
            currentAnimator!!.cancel()
        }

        view.animate().translationY(view.height.toFloat()).setListener(hidingAnimatorListener)
    }

    private fun showView(view: V) {
        if (isShowing) {
            return
        }

        if (isHiding) {
            currentAnimator!!.cancel()
        }

        view.animate().translationY(0f).setListener(showingAnimatorListener)
    }


}