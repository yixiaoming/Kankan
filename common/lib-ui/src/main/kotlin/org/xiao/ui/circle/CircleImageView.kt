package org.xiao.ui.circle

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import org.xiao.ui.R


class CircleImageView @JvmOverloads constructor(context: Context, attr: AttributeSet? = null, defStyleAttr: Int = 0)
    : androidx.appcompat.widget.AppCompatImageView(context, attr, defStyleAttr) {

    enum class ShapeType {
        SHAPE_CIRCLE,
        SHAPE_ROUND
    }

    //defAttr var
    private var mShapeType: ShapeType = ShapeType.SHAPE_CIRCLE
        set(value) {
            field = value
            invalidate()
        }
    private var mBorderWidth: Float = 20f
        set(value) {
            field = value
            invalidate()
        }
    private var mBorderColor: Int = Color.parseColor("#ff9900")
        set(value) {
            field = value
            invalidate()
        }

    private var mLeftTopRadiusX: Float = 0f
        set(value) {
            field = value
            invalidate()
        }
    private var mLeftTopRadiusY: Float = 0f
        set(value) {
            field = value
            invalidate()
        }
    private var mRightTopRadiusX: Float = 0f
        set(value) {
            field = value
            invalidate()
        }
    private var mRightTopRadiusY: Float = 0f
        set(value) {
            field = value
            invalidate()
        }
    private var mLeftBottomRadiusX: Float = 0f
        set(value) {
            field = value
            invalidate()
        }
    private var mLeftBottomRadiusY: Float = 0f
        set(value) {
            field = value
            invalidate()
        }
    private var mRightBottomRadiusX: Float = 0f
        set(value) {
            field = value
            invalidate()
        }
    private var mRightBottomRadiusY: Float = 0f
        set(value) {
            field = value
            invalidate()
        }
    private var mShowBorder: Boolean = true
        set(value) {
            field = value
            invalidate()
        }
    private var mShowCircleDot: Boolean = false
        set(value) {
            field = value
            invalidate()
        }
    private var mCircleDotColor: Int = Color.RED
        set(value) {
            field = value
            invalidate()
        }

    private var mCircleDotRadius: Float = 20f
        set(value) {
            field = value
            invalidate()
        }

    private lateinit var mShapePath: Path
    private lateinit var mBorderPath: Path
    private lateinit var mBitmapPaint: Paint
    private lateinit var mBorderPaint: Paint
    private lateinit var mCircleDotPaint: Paint
    private lateinit var mMatrix: Matrix

    private var mWidth = 200
    private var mHeight = 200
    private var mRadius = 200F

    init {
        initAttrs(context, attr, defStyleAttr)
        initDrawTools()
    }

    private fun initAttrs(context: Context, attr: AttributeSet?, defStyleAttr: Int) {
        val array = context.obtainStyledAttributes(attr, R.styleable.CircleImageView)
        (0..array.indexCount)
                .asSequence()
                .map { array.getIndex(it) }
                .forEach {
                    when (it) {
                        R.styleable.CircleImageView_shape_type -> {
                            mShapeType = when {
                                array.getInt(it, 0) == 0 -> ShapeType.SHAPE_CIRCLE
                                array.getInt(it, 0) == 1 -> ShapeType.SHAPE_ROUND
                                else -> ShapeType.SHAPE_CIRCLE
                            }
                        }
                        R.styleable.CircleImageView_border_width ->
                            mBorderWidth = array.getDimension(it, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4f, resources.displayMetrics))
                        R.styleable.CircleImageView_border_color ->
                            mBorderColor = array.getColor(it, Color.parseColor("#ff0000"))
                        R.styleable.CircleImageView_left_top_radiusX ->
                            mLeftTopRadiusX = array.getDimension(it, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0f, resources.displayMetrics))
                        R.styleable.CircleImageView_left_top_radiusY ->
                            mLeftTopRadiusY = array.getDimension(it, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0f, resources.displayMetrics))
                        R.styleable.CircleImageView_left_bottom_radiusX ->
                            mLeftBottomRadiusX = array.getDimension(it, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0f, resources.displayMetrics))
                        R.styleable.CircleImageView_left_bottom_radiusY ->
                            mLeftBottomRadiusY = array.getDimension(it, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0f, resources.displayMetrics))
                        R.styleable.CircleImageView_right_bottom_radiusX ->
                            mRightBottomRadiusX = array.getDimension(it, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0f, resources.displayMetrics))
                        R.styleable.CircleImageView_right_bottom_radiusY ->
                            mRightBottomRadiusY = array.getDimension(it, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0f, resources.displayMetrics))
                        R.styleable.CircleImageView_right_top_radiusX ->
                            mRightTopRadiusX = array.getDimension(it, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0f, resources.displayMetrics))
                        R.styleable.CircleImageView_right_top_radiusY ->
                            mRightTopRadiusY = array.getDimension(it, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0f, resources.displayMetrics))
                        R.styleable.CircleImageView_show_border ->
                            mShowBorder = array.getBoolean(it, false)
                        R.styleable.CircleImageView_show_circle_dot ->
                            mShowCircleDot = array.getBoolean(it, false)
                        R.styleable.CircleImageView_circle_dot_color ->
                            mCircleDotColor = array.getColor(it, Color.parseColor("#ff0000"))
                        R.styleable.CircleImageView_circle_dot_radius ->
                            mCircleDotRadius = array.getDimension(it, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5f, resources.displayMetrics))
                    }

                }
        array.recycle()
    }

    private fun initDrawTools() {
        mBitmapPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
        }
        mBorderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            color = mBorderColor
            strokeCap = Paint.Cap.ROUND
            strokeWidth = mBorderWidth
        }
        mCircleDotPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
            color = mCircleDotColor
        }
        mShapePath = Path()
        mBorderPath = Path()
        mMatrix = Matrix()
        scaleType = ScaleType.CENTER_CROP
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (mShapeType == ShapeType.SHAPE_CIRCLE) {
            mWidth = Math.min(measuredWidth, measuredHeight)
            mRadius = mWidth / 2.0F
            setMeasuredDimension(mWidth, mWidth)
        } else {
            mWidth = measuredWidth
            mHeight = measuredHeight
            setMeasuredDimension(mWidth, mHeight)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mBorderPath.reset()
        mShapePath.reset()
        when (mShapeType) {
            ShapeType.SHAPE_ROUND -> {
                mWidth = w
                mHeight = h
                buildRoundPath()
            }
            ShapeType.SHAPE_CIRCLE -> {
                buildCirclePath()
            }
        }
    }

    private fun buildRoundPath() {
        if (!mShowBorder) {
            floatArrayOf(
                    mLeftTopRadiusX, mLeftTopRadiusY,
                    mRightTopRadiusX, mRightTopRadiusY,
                    mRightBottomRadiusX, mRightBottomRadiusY,
                    mLeftBottomRadiusX, mLeftBottomRadiusY
            ).run {
                mShapePath.addRoundRect(RectF(0F, 0F, mWidth.toFloat(), mHeight.toFloat()), this, Path.Direction.CW)
            }
        } else {
            floatArrayOf(mLeftTopRadiusX - mBorderWidth / 2.0f, mLeftTopRadiusY - mBorderWidth / 2.0f,
                    mRightTopRadiusX - mBorderWidth / 2.0f, mRightTopRadiusY - mBorderWidth / 2.0f,
                    mRightBottomRadiusX - mBorderWidth / 2.0f, mRightBottomRadiusY - mBorderWidth / 2.0f,
                    mLeftBottomRadiusX - mBorderWidth / 2.0f, mLeftBottomRadiusY - mBorderWidth / 2.0f).run {
                mBorderPath.addRoundRect(RectF(mBorderWidth / 2.0f, mBorderWidth / 2.0f, mWidth.toFloat() - mBorderWidth / 2.0f, mHeight.toFloat() - mBorderWidth / 2.0f), this, Path.Direction.CW)
            }
            floatArrayOf(mLeftTopRadiusX - mBorderWidth, mLeftTopRadiusY - mBorderWidth,
                    mRightTopRadiusX - mBorderWidth, mRightTopRadiusY - mBorderWidth,
                    mRightBottomRadiusX - mBorderWidth, mRightBottomRadiusY - mBorderWidth,
                    mLeftBottomRadiusX - mBorderWidth, mLeftBottomRadiusY - mBorderWidth).run {
                mShapePath.addRoundRect(RectF(mBorderWidth, mBorderWidth, mWidth.toFloat() - mBorderWidth, mHeight.toFloat() - mBorderWidth),
                        this, Path.Direction.CW)
            }
        }
    }

    private fun buildCirclePath() {
        if (!mShowBorder) {
            mShapePath.addCircle(mRadius, mRadius, mRadius, Path.Direction.CW)
        } else {
            mShapePath.addCircle(mRadius, mRadius, mRadius - mBorderWidth, Path.Direction.CW)
            mBorderPath.addCircle(mRadius, mRadius, mRadius - mBorderWidth / 2.0F, Path.Direction.CW)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        drawable ?: return
        mBitmapPaint.shader = getBitmapShader()
        when (mShapeType) {
            ShapeType.SHAPE_CIRCLE -> {
                if (mShowBorder) {
                    canvas?.drawPath(mBorderPath, mBorderPaint)
                }
                canvas?.drawPath(mShapePath, mBitmapPaint)
            }
            ShapeType.SHAPE_ROUND -> {
                if (mShowBorder) {
                    canvas?.drawPath(mBorderPath, mBorderPaint)
                }
                canvas?.drawPath(mShapePath, mBitmapPaint)
            }
        }
        if (mShowCircleDot) {
            drawCircleDot(canvas)
        }
    }

    private fun drawCircleDot(canvas: Canvas?) {
        canvas?.run {
            drawCircle((mRadius + mRadius * (Math.sqrt(2.0) / 2.0f)).toFloat(), (mRadius - mRadius * (Math.sqrt(2.0) / 2.0f)).toFloat(), mCircleDotRadius, mCircleDotPaint)
        }
    }

    private fun getBitmapShader(): Shader? {
        var bitmap = drawableToBitmap(drawable)
        return BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP).apply {
            var scale = 1.0F
            when (mShapeType) {
                ShapeType.SHAPE_CIRCLE -> {
                    scale = (mWidth * 1.0F / Math.min(bitmap.width, bitmap.height))
                }
                ShapeType.SHAPE_ROUND -> {
                    if (!(width == bitmap.width && width == bitmap.height)) {
                        scale = Math.max(width * 1.0f / bitmap.width, height * 1.0f / bitmap.height)
                    }
                }
            }
            mMatrix.setScale(scale, scale)
            setLocalMatrix(mMatrix)
        }
    }

    private fun drawableToBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }
        return Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888).apply {
            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
            drawable.draw(Canvas(this@apply))
        }
    }

    companion object {
        private const val STATE_INSTANCE = "state_instance"
        private const val STATE_INSTANCE_SHAPE_TYPE = "state_shape_type"
        private const val STATE_INSTANCE_BORDER_WIDTH = "state_border_width"
        private const val STATE_INSTANCE_BORDER_COLOR = "state_border_color"
        private const val STATE_INSTANCE_RADIUS_LEFT_TOP_X = "state_radius_left_top_x"
        private const val STATE_INSTANCE_RADIUS_LEFT_TOP_Y = "state_radius_left_top_y"
        private const val STATE_INSTANCE_RADIUS_LEFT_BOTTOM_X = "state_radius_left_bottom_x"
        private const val STATE_INSTANCE_RADIUS_LEFT_BOTTOM_Y = "state_radius_left_bottom_y"
        private const val STATE_INSTANCE_RADIUS_RIGHT_TOP_X = "state_radius_right_top_x"
        private const val STATE_INSTANCE_RADIUS_RIGHT_TOP_Y = "state_radius_right_top_y"
        private const val STATE_INSTANCE_RADIUS_RIGHT_BOTTOM_X = "state_radius_right_bottom_x"
        private const val STATE_INSTANCE_RADIUS_RIGHT_BOTTOM_Y = "state_radius_right_bottom_y"
        private const val STATE_INSTANCE_RADIUS = "state_radius"
        private const val STATE_INSTANCE_SHOW_BORDER = "state_radius_show_border"
    }

    //View State Save
    override fun onSaveInstanceState(): Parcelable = Bundle().apply {
        putParcelable(STATE_INSTANCE, super.onSaveInstanceState())
        putInt(STATE_INSTANCE_SHAPE_TYPE, when (mShapeType) {
            ShapeType.SHAPE_CIRCLE -> 0
            ShapeType.SHAPE_ROUND -> 1
        })
        putFloat(STATE_INSTANCE_BORDER_WIDTH, mBorderWidth)
        putInt(STATE_INSTANCE_BORDER_COLOR, mBorderColor)
        putFloat(STATE_INSTANCE_RADIUS_LEFT_TOP_X, mLeftTopRadiusX)
        putFloat(STATE_INSTANCE_RADIUS_LEFT_TOP_Y, mLeftTopRadiusY)
        putFloat(STATE_INSTANCE_RADIUS_LEFT_BOTTOM_X, mLeftBottomRadiusX)
        putFloat(STATE_INSTANCE_RADIUS_LEFT_BOTTOM_Y, mLeftBottomRadiusY)
        putFloat(STATE_INSTANCE_RADIUS_RIGHT_TOP_X, mRightTopRadiusX)
        putFloat(STATE_INSTANCE_RADIUS_RIGHT_TOP_Y, mRightTopRadiusY)
        putFloat(STATE_INSTANCE_RADIUS_RIGHT_BOTTOM_X, mRightBottomRadiusX)
        putFloat(STATE_INSTANCE_RADIUS_RIGHT_BOTTOM_Y, mRightBottomRadiusY)
        putFloat(STATE_INSTANCE_RADIUS, mRadius)
        putBoolean(STATE_INSTANCE_SHOW_BORDER, mShowBorder)
    }

    //View State Restore
    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state !is Bundle) {
            super.onRestoreInstanceState(state)
            return
        }

        with(state) {
            super.onRestoreInstanceState(getParcelable(STATE_INSTANCE))
            mShapeType = when {
                getInt(STATE_INSTANCE_SHAPE_TYPE) == 0 -> ShapeType.SHAPE_CIRCLE
                getInt(STATE_INSTANCE_SHAPE_TYPE) == 1 -> ShapeType.SHAPE_ROUND
                else -> ShapeType.SHAPE_CIRCLE
            }
            mBorderWidth = getFloat(STATE_INSTANCE_BORDER_WIDTH)
            mBorderColor = getInt(STATE_INSTANCE_BORDER_COLOR)
            mLeftTopRadiusX = getFloat(STATE_INSTANCE_RADIUS_LEFT_TOP_X)
            mLeftTopRadiusY = getFloat(STATE_INSTANCE_RADIUS_LEFT_TOP_Y)
            mLeftBottomRadiusX = getFloat(STATE_INSTANCE_RADIUS_LEFT_BOTTOM_X)
            mLeftBottomRadiusY = getFloat(STATE_INSTANCE_RADIUS_LEFT_BOTTOM_Y)
            mRightTopRadiusX = getFloat(STATE_INSTANCE_RADIUS_RIGHT_TOP_X)
            mRightTopRadiusY = getFloat(STATE_INSTANCE_RADIUS_RIGHT_TOP_Y)
            mRightBottomRadiusX = getFloat(STATE_INSTANCE_RADIUS_RIGHT_BOTTOM_X)
            mRightBottomRadiusY = getFloat(STATE_INSTANCE_RADIUS_RIGHT_BOTTOM_Y)
            mRadius = getFloat(STATE_INSTANCE_RADIUS)
            mShowBorder = getBoolean(STATE_INSTANCE_SHOW_BORDER)
        }
    }

}