package com.rohit.meeku

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : Activity() {
    private lateinit var status: TextView
    private lateinit var orb: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.rgb(3, 7, 20)
        window.navigationBarColor = Color.rgb(3, 7, 20)
        buildUi()
    }

    private fun buildUi() {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(28, 28, 28, 24)
            background = GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                intArrayOf(Color.rgb(2, 5, 18), Color.rgb(8, 16, 38), Color.rgb(2, 5, 18))
            )
        }

        val header = LinearLayout(this).apply {
            gravity = Gravity.CENTER_VERTICAL
        }
        val brand = TextView(this).apply {
            text = "MEEKU"
            textSize = 26f
            setTextColor(Color.WHITE)
        }
        header.addView(brand, LinearLayout.LayoutParams(0, 64, 1f))
        val online = TextView(this).apply {
            text = "● ONLINE"
            textSize = 12f
            setTextColor(Color.rgb(80, 245, 220))
            gravity = Gravity.CENTER
        }
        header.addView(online, LinearLayout.LayoutParams(100, 52))
        root.addView(header)

        val scroll = ScrollView(this).apply { isFillViewport = true }
        val content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
        }

        status = TextView(this).apply {
            text = "Hey Rohit ✨\nI'm ready."
            textSize = 20f
            gravity = Gravity.CENTER
            setTextColor(Color.rgb(220, 240, 255))
            setPadding(0, 24, 0, 18)
        }
        content.addView(status)

        orb = TextView(this).apply {
            text = "◉"
            textSize = 100f
            gravity = Gravity.CENTER
            setTextColor(Color.rgb(90, 245, 255))
            background = GradientDrawable().apply {
                shape = GradientDrawable.OVAL
                setColor(Color.rgb(5, 17, 34))
                setStroke(6, Color.rgb(90, 220, 255))
                cornerRadius = 160f
            }
            elevation = 18f
            setOnClickListener { activate() }
            contentDescription = "MEEKU AI orb"
        }
        content.addView(orb, LinearLayout.LayoutParams(300, 300).apply {
            gravity = Gravity.CENTER_HORIZONTAL
            topMargin = 12
            bottomMargin = 20
        })

        val hint = TextView(this).apply {
            text = "Tap the orb to talk with MEEKU"
            textSize = 14f
            gravity = Gravity.CENTER
            setTextColor(Color.rgb(145, 180, 210))
            setPadding(0, 0, 0, 24)
        }
        content.addView(hint)

        val actions = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }
        actions.addView(actionButton("💬  Chat", "Open conversation") { status.text = "Chat mode 💬\nType or speak when connected." })
        actions.addView(actionButton("🎙  Voice", "Voice companion") { activate() })
        actions.addView(actionButton("⚙  Settings", "Configure MEEKU") { status.text = "Settings ⚙\nCustomization and providers coming next." })
        content.addView(actions)

        scroll.addView(content)
        root.addView(scroll, LinearLayout.LayoutParams(-1, 0, 1f))

        val footer = TextView(this).apply {
            text = "MEEKU • AI Companion"
            textSize = 12f
            gravity = Gravity.CENTER
            setTextColor(Color.rgb(90, 125, 155))
            setPadding(0, 14, 0, 0)
        }
        root.addView(footer)
        setContentView(root)
    }

    private fun actionButton(label: String, sub: String, click: () -> Unit): View {
        val box = Button(this).apply {
            text = "$label\n$sub"
            textSize = 14f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
            isAllCaps = false
            setOnClickListener { click() }
            background = GradientDrawable().apply {
                shape = GradientDrawable.RECTANGLE
                setColor(Color.rgb(10, 24, 48))
                setStroke(2, Color.rgb(55, 130, 170))
                cornerRadius = 28f
            }
        }
        return box.apply {
            layoutParams = LinearLayout.LayoutParams(-1, 74).apply { bottomMargin = 12 }
        }
    }

    private fun activate() {
        status.text = "Listening… 🎙\nI'm here."
        orb.setTextColor(Color.rgb(180, 255, 245))
        val pulse = AlphaAnimation(0.45f, 1f).apply {
            duration = 500
            repeatMode = AlphaAnimation.REVERSE
            repeatCount = 3
        }
        orb.startAnimation(pulse)
    }
}
