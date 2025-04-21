package com.mamadiyorov_lazizbek.a4piic1suz_ver12.screens.splash


import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Shader

import com.mamadiyorov_lazizbek.a4piic1suz_ver12.R

import android.content.Context

import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class ImageTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private var textShader: Shader? = null

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {

        if (textShader == null) {
            // Load your image as a bitmap
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.img_text2)
            if (bitmap != null) {
                textShader = BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
            }
        }

        // Apply the shader to the paint used for drawing the text
        if (textShader != null) {
            paint.shader = textShader
        }

        // Draw the text with the image fill
        super.onDraw(canvas)
    }
}
