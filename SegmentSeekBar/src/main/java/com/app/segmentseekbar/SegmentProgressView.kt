package com.app.segmentseekbar

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.Align
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.RectF
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.marginEnd
import androidx.core.view.marginStart


data class SegmentData(
    val name: String,
    val color: Int,
    val midValue: String = "",
)

class SegmentProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rect = RectF()
    private val segmentTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val midSegmentTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val badgeTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var segments: List<SegmentData> = listOf(
        SegmentData(
            "1",
            Color.parseColor("#51D23A"),
            "10"
        ),SegmentData(
            "2",
            Color.parseColor("#EC9F9F"),
            "20"
        ),
        SegmentData(
            "3",
            Color.parseColor("#FF000000"),
            "30"
        )
    )


    private var badgePosition: Float = 0f
    private var badgePercent: Float = 0f
    private var badgeValue: String = ""
    private var barHeight: Int = 10
    private var badgeDrawable: Drawable? = null
    private var barWidth: Int = 10
    private var badgeWidth: Int = 10
    private var badgeHeight: Int = 10

    var indicatorColor: Int = Color.WHITE
    var indicatorTintColor: Int = Color.WHITE

    private val indicatorPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        segmentTextPaint.apply {
            segmentTextPaint.textAlign = Align.CENTER
        }

        midSegmentTextPaint.apply {
            midSegmentTextPaint.textAlign = Align.CENTER
        }

        badgeTextPaint.apply {
            badgeTextPaint.textAlign = Align.CENTER
        }

        indicatorPaint.apply {
            textAlign = Align.CENTER
            foregroundGravity = Gravity.CENTER
        }
    }

    fun setSegmentTextConfig(
        typeface: Typeface,
        color: Int,
        align: Align = Align.CENTER
    ) {
        segmentTextPaint.color = color
        segmentTextPaint.typeface = typeface
        segmentTextPaint.textAlign = align
    }

    fun setSegmentBadgeConfig(
        typeface: Typeface,
        color: Int,
        align: Align = Align.CENTER
    ) {
        badgeTextPaint.color = color
        badgeTextPaint.typeface = typeface
        badgeTextPaint.textAlign = align
    }

    fun setSegmentMidValueConfig(
        typeface: Typeface,
        color: Int,
        align: Align = Align.CENTER
    ) {
        midSegmentTextPaint.color = color
        midSegmentTextPaint.typeface = typeface
        midSegmentTextPaint.textAlign = align
    }


    fun setSegments(
        barHeight: Int = 25,
        newSegments: ArrayList<SegmentData>,
        value: Float,
        max: Float,
        badgeWidth: Int = 10,
        badgeHeight: Int = 10,
        badgeDrawable: Drawable? = null,
    ) {
        badgePercent = value / max * 100
        badgeValue = value.toString()
        this.badgeWidth = badgeWidth
        this.badgeHeight = badgeHeight
        this.barHeight = barHeight
        this.badgeDrawable = badgeDrawable
        segments = newSegments
        invalidate()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(
            MeasureSpec.getSize(widthMeasureSpec),
            MeasureSpec.getSize(heightMeasureSpec)
        )
    }


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (segments.isEmpty()) return

        foregroundGravity = Gravity.CENTER
        val width = width.toFloat()
        this.barWidth = width.toInt()
        val barYTop = (bottom - barHeight) - 70f
        val barYBottom = barYTop + this.barHeight.toFloat()
        val barSize = this.barHeight.toFloat()
        val barCurve = (this.barHeight / 2).toFloat()

        // Calculate segment width based on total number of segments
        val segmentWidth = width / segments.size
        println("segmentWidth $segmentWidth")
        println("width $width")


        this.badgeWidth = barHeight * 2
        this.badgePosition = badgePercent * width / 100
        // Draw segments
        segments.forEachIndexed { index, segment ->
            paint.color = segment.color
            val startX = index * segmentWidth
            val endX = startX + segmentWidth
            // Draw segment bar
            when (index) {
                0 -> {
                    if (badgePosition in startX..endX) {
                        indicatorTintColor = segment.color
                    }
                    // First segment: round left corners only
                    rect.set(startX, barYTop, endX, barYBottom)
                    canvas.drawRoundRect(rect, barCurve, barCurve, paint)
                    rect.set(startX + barSize, barYTop, endX, barYBottom)
                    canvas.drawRect(rect, paint)
                }

                segments.lastIndex -> {
                    if (badgePosition in startX..endX) {
                        indicatorTintColor = segment.color
                    }
                    // Last segment: round right corners only
                    rect.set(startX, barYTop, endX, barYBottom)
                    canvas.drawRoundRect(rect, barCurve, barCurve, paint)
                    rect.set(startX, barYTop, endX - barSize, barYBottom)
                    canvas.drawRect(rect, paint)
                }

                else -> {
                    if (badgePosition in startX..endX) {
                        indicatorTintColor = segment.color
                    }
                    // Middle segments: no rounded corners
                    rect.set(startX, barYTop, endX, barYBottom)
                    canvas.drawRect(rect, paint)
                }
            }

            midSegmentTextPaint.textSize = ((segmentWidth/100 * 10)).toFloat()
            if (index != segments.lastIndex && segment.midValue.isNotEmpty()) {
                canvas.drawText(
                    segment.midValue,
                    startX + (segmentWidth * 2) / 2,
                    barYTop - barHeight - badgeHeight - 30f,
                    midSegmentTextPaint
                )
            }

            segmentTextPaint.textSize = ((segmentWidth/100 * 10))
            // Draw segment name
            canvas.drawText(
                segment.name,
                (startX + segmentWidth / 2),
                barYTop + barHeight + 30f,
                segmentTextPaint
            )
        }

        //OUTER CIRCLE
        val drawable = badgeDrawable ?: AppCompatResources.getDrawable(context, R.drawable.bg_circle)
        drawable?.let {
            canvas.drawBitmap(
                drawableToBitmap(it, badgeWidth, badgeWidth, indicatorTintColor),
                badgePosition,
                rect.top - (rect.height() / 2),
                indicatorPaint
            )
        }

        // Draw current segment name above indicator
        midSegmentTextPaint.textSize = ((badgeWidth/100 * 10)).toFloat()
        canvas.drawText(
            badgeValue,
            badgePosition + (badgeWidth / 2),
            barYTop - (badgeHeight + 20f / 2),
            segmentTextPaint
        )

        measure(MeasureSpec.makeMeasureSpec(this.width , MeasureSpec.EXACTLY) ,MeasureSpec.makeMeasureSpec(this.height , MeasureSpec.EXACTLY))
    }

    private fun drawableToBitmap(drawable: Drawable, width: Int, height: Int, color: Int): Bitmap {
        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC)
        drawable.draw(canvas)
        return bitmap
    }

}