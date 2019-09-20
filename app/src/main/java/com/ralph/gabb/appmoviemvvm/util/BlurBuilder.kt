package com.ralph.gabb.appmoviemvvm.util

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur

/**
 * Created by Ralph Gabrielle Orden on 9/20/2019.
 */

object BlurBuilder {

    private const val BITMAP_SCALE: Float  = 0.4f
    private const val BLUR_RADIUS: Float  = 7.5f

    fun  blur(context: Context, image: Bitmap) : Bitmap {
        val width = Math.round(image.width * BITMAP_SCALE)
        val height = Math.round(image.height * BITMAP_SCALE)

        val inputBitmap: Bitmap = Bitmap.createScaledBitmap(image, width, height, false)
        val outputBitmap: Bitmap = Bitmap.createBitmap(inputBitmap)

        val rs: RenderScript  = RenderScript.create(context)

        val theIntrinsic: ScriptIntrinsicBlur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
        val tmpIn: Allocation = Allocation.createFromBitmap(rs, inputBitmap)
        val tmpOut: Allocation  = Allocation.createFromBitmap(rs, outputBitmap)

        theIntrinsic.setRadius(BLUR_RADIUS)
        theIntrinsic.setInput(tmpIn)
        theIntrinsic.forEach(tmpOut)
        tmpOut.copyTo(outputBitmap)

        return outputBitmap
    }
}