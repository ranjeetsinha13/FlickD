package com.rs.flickd.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.rs.flickd.R

class FixedAspectRatioImage : AppCompatImageView {

    private var aspectRatio: Float = 0.0f

    constructor(context: Context) : super(context) {
        init(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val measuredWidth = measuredWidth
        if (measuredWidth > 0 && aspectRatio > 0) {
            val height = (measuredWidth * aspectRatio).toInt()
            setMeasuredDimension(measuredWidth, height)
        }
    }


    private fun init(context: Context, attrs: AttributeSet?, defStyle: Int) {
        if (attrs != null) {
            val typeArray = context.obtainStyledAttributes(
                    attrs, R.styleable.FixedAspectRatioImage,
                    defStyle, 0
            )

            val n = typeArray.indexCount
            for (i in 0 until n) {
                val attr = typeArray.getIndex(i)
                if (attr == R.styleable.FixedAspectRatioImage_aspect_ratio) {
                    aspectRatio = typeArray.getFloat(
                            R.styleable.FixedAspectRatioImage_aspect_ratio, -1f
                    )
                }
            }

            typeArray.recycle()
        }


    }
}