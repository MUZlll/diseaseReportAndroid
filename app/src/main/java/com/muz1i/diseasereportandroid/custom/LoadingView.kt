package com.muz1i.diseasereportandroid.custom

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.muz1i.diseasereportandroid.R

/**
 * @author: Muz1i
 * @date: 2021/4/25
 */
class LoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    AppCompatImageView(context, attrs, defStyleAttr) {

    private var mDegrees = 0f
    private var mNeedRotate = true

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setImageResource(R.mipmap.loading)
        mNeedRotate = true
        startRotate()
    }

    private fun startRotate() {
        post(object : Runnable {
            override fun run() {
                mDegrees += 10f
                if (mDegrees >= 360) {
                    mDegrees = 0f
                }
                invalidate()
                //判断是否要继续旋转
                //如果不可见或者已经detachedFromWindow就不再转动
                if (visibility != VISIBLE && !mNeedRotate) {
                    removeCallbacks(this)
                } else {
                    postDelayed(this, 10)
                }
            }
        })
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopRotate()
    }

    private fun stopRotate() {
        mNeedRotate = false
    }

    override fun onDraw(canvas: Canvas) {
        canvas.rotate(mDegrees, (width / 2).toFloat(), (height / 2).toFloat())
        super.onDraw(canvas)
    }
}
