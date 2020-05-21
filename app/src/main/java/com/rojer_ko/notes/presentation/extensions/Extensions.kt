package com.rojer_ko.notes.presentation.extensions

import android.content.Context
import android.content.res.Resources
import androidx.core.content.ContextCompat
import com.rojer_ko.notes.R
import com.rojer_ko.notes.data.model.Color
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

const val DATE_TIME_FORMAT = "dd.MMM.yy HH:mm"
const val SAVE_DELAY = 2000L

fun Date.format(): String =
    SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault())
        .format(this)

fun Color.getColorInt(context: Context): Int =
    ContextCompat.getColor(context, getColorRes())

fun Color.getColorRes(): Int = when (this) {
    Color.WHITE -> R.color.color_white
    Color.VIOLET -> R.color.color_violet
    Color.YELLOW -> R.color.color_yello
    Color.RED -> R.color.color_red
    Color.PINK -> R.color.color_pink
    Color.GREEN -> R.color.color_green
    Color.BLUE -> R.color.color_blue
}

fun Float.toPx(): Float = (this * Resources.getSystem().displayMetrics.density)
fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()