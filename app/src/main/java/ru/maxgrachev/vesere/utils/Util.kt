package ru.maxgrachev.vesere.utils

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import ru.maxgrachev.vesere.R
import ru.maxgrachev.vesere.data.local.entity.Event
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
fun convertLongToDateString(systemTime: Long): String {
    return SimpleDateFormat("yyyy/MMM/dd")
        .format(systemTime).toString()
}

fun formatEvents(events: List<Event>, resources: Resources): Spanned {
    val sb = StringBuilder()
    sb.apply {
        events.forEach {
            append("<br>")
            append("<b>${it.eventName}</b><br>")
            append(resources.getString(R.string.date_AE))
            append("\t${convertLongToDateString(it.dateMilli)}<br>")
            append(resources.getString(R.string.serviceLive_AE))
            append("\t${it.serviceLife}" + " ")
            append(resources.getString(R.string.months_AE))
            append("<br>")
            append(resources.getString(R.string.carMileage_AE))
            append("\t${it.carMileage}<br>")
            append(resources.getString(R.string.price_AE))
            append("\t${it.price}<br>")
            append(resources.getString(R.string.serviceStation_AE))
            append("\t${it.serviceStationName}<br>")
            append(resources.getString(R.string.comment_AE))
            append("\t${it.comment}<br>")
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}

class TextItemViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)