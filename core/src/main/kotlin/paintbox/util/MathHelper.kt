package paintbox.util

import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin


/**
 * An assortment of math utilities.
 */
object MathHelper {

    fun isPointIn(px: Float, py: Float, x: Float, y: Float, w: Float, h: Float): Boolean {
        // the following is to normalize negative widths and heights
        val width = if (w < 0) abs(w) else w
        val height = if (h < 0) abs(h) else h
        val realX = if (w < 0) x - width else x
        val realY = if (h < 0) y - height else y

        return px in realX..(realX + width) && py in realY..(realY + height)
    }

    fun isIntersecting(x1: Float, y1: Float, w1: Float, h1: Float, x2: Float, y2: Float, w2: Float,
                       h2: Float): Boolean {
        val width1 = if (w1 < 0) abs(w1) else w1
        val height1 = if (h1 < 0) abs(h1) else h1
        val realX1 = if (w1 < 0) x1 - width1 else x1
        val realY1 = if (h1 < 0) y1 - height1 else y1
        val width2 = if (w2 < 0) abs(w2) else w2
        val height2 = if (h2 < 0) abs(h2) else h2
        val realX2 = if (w2 < 0) x2 - width2 else x2
        val realY2 = if (h2 < 0) y2 - height2 else y2

        return (realX1 in realX2..(realX2 + width2) || (realX1 + width1) in realX2..(realX2 + width2))
                && (realY1 in realY2..(realY2 + height2) || (realY2 + height2) in realY2..(realY2 + height2))
    }

    fun snapToNearest(value: Float, interval: Float): Float {
        val abs = abs(interval)
        if (abs <= 0f)
            return value
        return (value / abs).roundToInt() * abs
    }

    fun getSawtoothWave(time: Long, seconds: Float): Float {
        if (seconds == 0f) return 0f
        return time % (seconds * 1000).roundToInt() / (seconds * 1000f)
    }

    fun getSawtoothWave(seconds: Float): Float {
        return getSawtoothWave(System.currentTimeMillis(), seconds)
    }

    fun getTriangleWave(ms: Long, seconds: Float): Float {
        val f = getSawtoothWave(ms, seconds)
        return if (f >= 0.5f) {
            (1f - f) * 2
        } else
            f * 2
    }

    fun getTriangleWave(sec: Float): Float {
        return getTriangleWave(System.currentTimeMillis(), sec)
    }

    /**
     * The peaks of the sine wave will be returned as 1.0 and the troughs will be 0.0.
     * Starts at 0.5. Note that the [seconds] argument indicates the period from centre to centre.
     */
    fun getSineWave(ms: Long, seconds: Float): Float {
        if (seconds == 0f) return 0f
        return 0.5f * sin(Math.PI / seconds * (ms / 1000.0)).toFloat() + 0.5f
    }

    fun getSineWave(sec: Float): Float {
        return getSineWave(System.currentTimeMillis(), sec)
    }

    /**
     * The peaks of the cosine wave will be returned as 1.0 and the troughs will be 0.0.
     * Starts at 1. Note that the [seconds] argument indicates the period from peak to trough.
     */
    fun getCosineWave(ms: Long, seconds: Float): Float {
        if (seconds == 0f) return 0f
        return 0.5f * cos(Math.PI / seconds * (ms / 1000.0)).toFloat() + 0.5f
    }

    fun getCosineWave(sec: Float): Float {
        return getCosineWave(System.currentTimeMillis(), sec)
    }

    /**
     * The peaks of the cosine wave will be returned as 1.0 and the troughs will be 0.0.
     * Starts at 0. Note that the [seconds] argument indicates the period from peak to trough.
     */
    fun getBaseCosineWave(ms: Long, seconds: Float): Float {
        if (seconds == 0f) return 1f
        return -0.5f * cos(Math.PI / seconds * (ms / 1000.0)).toFloat() + 0.5f
    }

    fun getBaseCosineWave(sec: Float): Float {
        return getBaseCosineWave(System.currentTimeMillis(), sec)
    }

}