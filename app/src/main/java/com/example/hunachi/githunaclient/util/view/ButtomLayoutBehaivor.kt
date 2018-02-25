package com.example.hunachi.githunaclient.util.view

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorListener
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout

/**
 * Created by hunachi on 2018/02/25.
 */
class ButtomLayoutBehaivor(val context: Context, attributeSet: AttributeSet) : CoordinatorLayout.Behavior<LinearLayout>(context, attributeSet) {
    
    companion object {
        private const val THRESHOLD = 20
    }
    
    private var isAnimation = false
    private var lastPosition = 0
    private var snacking = false
    
    override fun layoutDependsOn(parent: CoordinatorLayout?, child: LinearLayout?, dependency: View?): Boolean {
        return dependency is AppBarLayout
    }
    
    override fun onDependentViewChanged(parent: CoordinatorLayout?, child: LinearLayout, dependency: View): Boolean {
        if (snacking) return true
        bottomLayoutMove(child, dependency)
        return true
    }
    
    private fun bottomLayoutMove(bottomLayout: LinearLayout, dependency: View) {
        val diff = Math.abs(dependency.top - lastPosition)
        val scrollLimit = dependency.top - dependency.bottom
        when {
            lastPosition == dependency.top && lastPosition == 0 -> animation(bottomLayout, true)
            lastPosition == dependency.top && lastPosition == scrollLimit -> animation(bottomLayout, false)
            diff > THRESHOLD && lastPosition < dependency.y -> animation(bottomLayout, true)
            diff > THRESHOLD && lastPosition > dependency.y -> animation(bottomLayout, false)
        }
        lastPosition = dependency.y.toInt()
    }
    
    private fun animation(view: View, reverse: Boolean) {
        if (isAnimation) return
        isAnimation = true
        val animateValue = (view.bottom - view.top).toFloat()
        ViewCompat.animate(view).apply {
            duration = 100
            translationY(if (reverse) 0f else animateValue)
            interpolator = AccelerateDecelerateInterpolator()
            setListener(object : ViewPropertyAnimatorListener {
                override fun onAnimationEnd(view: View?) {
                    isAnimation = false
                }
                
                override fun onAnimationCancel(view: View?) {
                    isAnimation = false
                }
                
                override fun onAnimationStart(view: View?) {
                    isAnimation = true
                }
                
            }).start()
        }
    }
    
}