package org.xiao.ui.tablayout

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.children
import androidx.viewpager.widget.ViewPager
import org.xiao.ui.R

class TabLayout @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    companion object {
        const val TAG = "TabLayout"
    }

    private lateinit var mTitles: Array<String>
    private var mPaint: Paint
    private var mIndicatorColor = Color.BLACK
    private var mIndication: RectF
    private var mIndicatorLeft = 0F
    private var mIndicatorTop = 0F
    private var mIndicatorHeight = 0F
    private var mIndicatorWidth = 0F
    private var mIndicatorRadius = 0F
    private var mItemWidth = 0
    private var mLastPosition = 0
    private var mTextSize = 20F

    init {
        attrs?.let {
            val typeArray = context.obtainStyledAttributes(attrs, R.styleable.TabLayout)
            mIndicatorColor = typeArray.getColor(R.styleable.TabLayout_indicator_color, Color.BLACK)
            mIndicatorWidth = typeArray.getDimension(R.styleable.TabLayout_indicator_width, 60F)
            mIndicatorHeight = typeArray.getDimension(R.styleable.TabLayout_indicator_height, 15F)
            mItemWidth = typeArray.getDimensionPixelSize(R.styleable.TabLayout_item_width, 180)
            mIndicatorRadius = typeArray.getDimension(R.styleable.TabLayout_indicator_radius, 0F)
            mTextSize = typeArray.getDimension(R.styleable.TabLayout_indicator_font_size, 0F)

            typeArray.recycle()
        }
        setWillNotDraw(false)
        orientation = HORIZONTAL
        gravity = Gravity.CENTER
        mPaint = Paint()
        mPaint.color = mIndicatorColor
        mPaint.style = Paint.Style.FILL
        mPaint.isAntiAlias = true

        mIndication = RectF(0F, 0F, 0F, 0F)
    }

    fun setTitles(titles: Array<String>) {
        Log.d(TAG, "setTitles")
        clearOldViews()

        mTitles = titles
        for (title in mTitles) {
            addTextView(title)
        }
    }

    private fun clearOldViews() {
        for (itemView in children) {
            removeView(itemView)
        }
    }

    private fun addTextView(title: String) {
        val textView = TextView(context)
        textView.width = mItemWidth
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = mTextSize
        val lp = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        textView.text = title
        addView(textView, lp)
    }


    fun setViewPager(viewPager: ViewPager) {
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                val child = getChildAt(position)
                mIndicatorLeft = child.left + (child.width - mIndicatorWidth) / 2 + (positionOffset * child.width).toInt()
//                Log.d(TAG, "onPageScrolled: p:$position offset:$positionOffset pixels:$positionOffsetPixels mLeft:$mIndicatorLeft")
                invalidate()
            }

            override fun onPageSelected(position: Int) {
                mLastPosition = position
            }
        })
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mIndication.left = mIndicatorLeft
        mIndication.right = mIndicatorLeft + mIndicatorWidth
        mIndication.top = mIndicatorTop - mIndicatorHeight
        mIndication.bottom = mIndicatorTop

//        Log.d(TAG, "onDraw: left:${mIndication.left} right:${mIndication.right} top:${mIndication.top} bottom:${mIndication.bottom}")
        canvas?.drawRoundRect(mIndication, mIndicatorRadius, mIndicatorRadius, mPaint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
//        Log.d(TAG, "onSizeChanged:w:$w h:$h oldw:$oldw oldh:$oldh")
        mIndicatorTop = h.toFloat()
    }
}