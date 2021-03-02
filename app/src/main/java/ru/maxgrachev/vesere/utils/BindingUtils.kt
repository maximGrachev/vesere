package ru.maxgrachev.vesere.utils

import android.annotation.SuppressLint
import android.view.View
import android.widget.Switch
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.switchmaterial.SwitchMaterial
import ru.maxgrachev.vesere.data.local.entity.Event

@BindingAdapter("textEvent")
fun TextView.setEventType(item: Event?) {
    item?.let {
        text = item.eventName
    }
}

@BindingAdapter("textDate")
fun TextView.setEventDataFormatted(item: Event?) {
    item?.let {
        text = convertLongToDateString(item.dateMilli)
    }
}

@BindingAdapter("textServiceLife")
fun TextView.setEventServiceLife(item: Event?) {
    item?.let {
        if (item.serviceLife != -1) {
            text = item.serviceLife.toString()
        } else {
            text = ""
        }
    }
}

@BindingAdapter("visibilityServiceLife")
fun TextView.setVisibilityEventServiceLife(item: Event?) {
    item?.let {
        visibility = if (item.serviceLife < 0) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }
}

@BindingAdapter("textCarMileage")
fun TextView.setEventCarMileage(item: Event?) {
    item?.let {
        if (item.carMileage !== -1) {
            text = item.carMileage.toString()
        } else {
            text = ""
        }

    }
}

@BindingAdapter("visibilityCarMileage")
fun TextView.setVisibilityCarMileage(item: Event?) {
    item?.let {
        visibility = if (item.carMileage < 0) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }
}

@BindingAdapter("textPrice")
fun TextView.setEventPrice(item: Event?) {
    item?.let {
        if (item.price != -1) {
            text = item.price.toString()
        } else {
            text = ""
        }
    }
}

@BindingAdapter("visibilityPrice")
fun TextView.setVisibilityPrice(item: Event?) {
    item?.let {
        visibility = if (item.price < 0) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }
}

@BindingAdapter("textServiceStation")
fun TextView.setEventServiceStation(item: Event?) {
    item?.let {
        if (item.serviceStationName.length < 2) {
            text = ""
        } else {
            text = item.serviceStationName
        }
    }
}

@BindingAdapter("visibilityServiceStation")
fun TextView.setVisibilityServiceStation(item: Event?) {
    item?.let {
        visibility = if (item.serviceStationName.length < 2) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }
}

@BindingAdapter("textRating")
fun TextView.setEventRating(item: Event?) {
    item?.let {
        text = if (item.serviceRating == true) {
            "Ok"
        } else {
            "Not ok"
        }
    }
}

@BindingAdapter("visibilityRating")
fun TextView.setVisibilityRating(item: Event?) {
    item?.let {
        visibility = if (item.serviceRating == null) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }
}

@BindingAdapter("textComment")
fun TextView.setEventComment(item: Event?) {
    item?.let {
        if (item.comment.length < 2) {
            text = ""
        } else {
            text = item.comment
        }
    }
}

@BindingAdapter("visibilityComment")
fun TextView.setVisibilityComment(item: Event?) {
    item?.let {
        visibility = if (item.comment.length < 2) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }
}

@BindingAdapter("ratingSwitchIsChecked")
fun SwitchMaterial.setRatingSwitchToChecked(item: Event?) {
    item?.let {
        isChecked = item.serviceRating
    }
}



