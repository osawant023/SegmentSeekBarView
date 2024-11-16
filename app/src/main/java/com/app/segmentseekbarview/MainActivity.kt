package com.app.segmentseekbarview

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.segmentseekbar.SegmentData
import com.app.segmentseekbar.SegmentProgressView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val segmentProgressView : SegmentProgressView = findViewById(R.id.segmentView)
        segmentProgressView.setPadding(5 , 0 , 5 , 0)
        segmentProgressView.setSegments(
            barHeight = 20,
            value = 50f ,
            max = 100f ,
            badgeWidth = 15,
            badgeHeight = 15,
            newSegments = arrayListOf(
                SegmentData(
                    "Segment 1",
                    ContextCompat.getColor(this, R.color.red1),
                    "25"
                ),
                SegmentData(
                    "Segment 2",
                    ContextCompat.getColor(this, R.color.red2),
                    "50"
                ),
                SegmentData(
                    "Segment 3",
                    ContextCompat.getColor(this, R.color.red3),
                    "75"
                ),
                SegmentData(
                    "Segment 4",
                    ContextCompat.getColor(this, R.color.red4),
                    "100"
                )
            ),

        )
        segmentProgressView.invalidate()

    }
}