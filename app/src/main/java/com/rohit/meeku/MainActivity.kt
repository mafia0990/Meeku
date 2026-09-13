package com.rohit.meeku

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(32, 32, 32, 32)
            background = GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                intArrayOf(Color.rgb(3, 7, 20), Color.rgb(10, 18, 42), Color.rgb(3, 7, 20))
            )
        }

        val orb = TextView(this).apply {
            text = "◉"
            textSize = 92f
            gravity = Gravity.CENTER
            setTextColor(Color.rgb(80, 245, 255))
            background = GradientDrawable().apply {
                shape = GradientDrawable.OVAL
                setColor(Color.rgb(8, 18, 35))
                setStroke(5, Color.rgb(95, 210, 255))
            }
        }
        root.addView(orb, LinearLayout.LayoutParams(280, 280))

        val title = TextView(this).apply {
            text = "MEEKU"
            textSize = 32f
            gravity = Gravity.CENTER
            setTextColor(Color.WHITE)
            setPadding(0, 28, 0, 8)
        }
        root.addView(title)

        val subtitle = TextView(this).apply {
            text = "Your AI companion • Ready"
            textSize = 15f
            gravity = Gravity.CENTER
            setTextColor(Color.rgb(150, 190, 220))
        }
        root.addView(subtitle)

        setContentView(root)
    }
}
