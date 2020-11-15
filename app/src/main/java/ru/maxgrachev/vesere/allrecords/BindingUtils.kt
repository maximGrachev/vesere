package ru.maxgrachev.vesere.allrecords

import android.widget.TextView
import androidx.databinding.BindingAdapter
import ru.maxgrachev.vesere.convertLongToDateString
import ru.maxgrachev.vesere.database.allrecords.Event

@BindingAdapter("textDate")
fun TextView.setEventDataFormatted(item: Event?) {
    item?.let {
        text = convertLongToDateString(item.dateMilli)
    }
}

@BindingAdapter("textEvent")
fun TextView.setEventType(item: Event?) {
    item?.let {
        text = item.eventName
    }
}

@BindingAdapter("textServiceLife")
fun TextView.setEventServiceLife(item: Event?) {
    item?.let {
        text = item.serviceLife.toString()
    }
}

@BindingAdapter("textCarMileage")
fun TextView.setEventCarMileage(item: Event?) {
    item?.let {
        text = item.carMileage.toString()
    }
}

@BindingAdapter("textPrice")
fun TextView.setEventPrice(item: Event?) {
    item?.let {
        text = item.price.toString()
    }
}

@BindingAdapter("textServiceStation")
fun TextView.setEventServiceStation(item: Event?) {
    item?.let {
        text = item.serviceStationName
    }
}

@BindingAdapter("textRating")
fun TextView.setEventRating(item: Event?) {
    item?.let {
        text = if (item.serviceRating == false) {
            "Not ok"
        } else {
            "Ok"
        }
    }
}

@BindingAdapter("textComment")
fun TextView.setEventComment(item: Event?) {
    item?.let {
        text = item.comment
    }
}



