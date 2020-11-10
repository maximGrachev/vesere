package ru.maxgrachev.vesere.allrecords

import android.widget.TextView
import androidx.databinding.BindingAdapter
import ru.maxgrachev.vesere.convertLongToDateString
import ru.maxgrachev.vesere.database.allrecords.Event

@BindingAdapter("textDate")
fun TextView.setEventDataFormatted(item: Event){
    text = convertLongToDateString(item.dateMilli)
}

@BindingAdapter("textEvent")
fun TextView.setEventType(item: Event){
    text = item.eventName
}