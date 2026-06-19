package com.ianlw.asiestamos.utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

object HapticHelper {

    fun lightTap(context: Context) {
        vibrate(context, 20L, VibrationEffect.EFFECT_TICK)
    }

    fun mediumTap(context: Context) {
        vibrate(context, 40L, VibrationEffect.EFFECT_CLICK)
    }

    fun success(context: Context) {
        vibrate(context, 50L, VibrationEffect.EFFECT_HEAVY_CLICK)
    }

    private fun vibrate(context: Context, durationMs: Long, effectId: Int) {
        val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val manager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
            manager?.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
        }

        vibrator?.let { v ->
            if (v.hasVibrator()) {
                v.vibrate(VibrationEffect.createOneShot(durationMs, VibrationEffect.DEFAULT_AMPLITUDE))
            }
        }
    }
}
