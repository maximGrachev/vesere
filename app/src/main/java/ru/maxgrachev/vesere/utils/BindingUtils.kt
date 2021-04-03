package ru.maxgrachev.vesere.utils

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.switchmaterial.SwitchMaterial
import ru.maxgrachev.vesere.data.local.entity.Parameter
import ru.maxgrachev.vesere.data.local.entity.relations.CategoryWithParameters

//@BindingAdapter("textEvent")
//fun TextView.setEventType(item: CategoryWithParameters?) {
//    item?.let {
//        text = item.category.name
//    }
//}

@BindingAdapter("textEventAllRec")
fun TextView.setEventType(item: CategoryWithParameters?) {
    item?.let {
        text = item.category.name
    }
}

@BindingAdapter("textDate")
fun TextView.setEventDataFormatted(item: CategoryWithParameters?) {
    item?.let {
        val dateText = findParameterValueByName(item.parameters, "Date Milli")
        text =
            convertLongToDateString(dateText!!.toLong()) //TODO как выбрать дату из списка параметров
    }
}

@BindingAdapter("textServiceLife")
fun TextView.setEventServiceLife(item: CategoryWithParameters?) {
    item?.let {
        val serviceLifeText = findParameterValueByName(item!!.parameters, "Service Life")

        text = if (serviceLifeText!!.toInt() < 0) {
            ""
        } else {
            serviceLifeText
        }
    }
}

@BindingAdapter("visibilityServiceLife")
fun TextView.setVisibilityEventServiceLife(item: CategoryWithParameters?) {
    item?.let {
        val serviceLifeText = findParameterValueByName(item!!.parameters, "Service Life")

        visibility = if (serviceLifeText!!.toInt() < 0) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }
}

@BindingAdapter("textCarMileage")
fun TextView.setEventCarMileage(item: CategoryWithParameters?) {
    item?.let {
        val carMileageText = findParameterValueByName(item!!.parameters, "Car Mileage")

        text = if (carMileageText!!.toInt() < 0) {
            ""
        } else {
            carMileageText
        }
    }
}


@BindingAdapter("visibilityCarMileage")
fun TextView.setVisibilityCarMileage(item: CategoryWithParameters?) {
    item?.let {
        val carMileageText = findParameterValueByName(item!!.parameters, "Car Mileage")

        visibility = if (carMileageText!!.toInt() < 0) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }
}

@BindingAdapter("textPrice")
fun TextView.setEventPrice(item: CategoryWithParameters?) {
    item?.let {
        val priceText = findParameterValueByName(item.parameters, "Price")

        text = if (priceText!!.toInt() < 0) {
            ""
        } else {
            priceText
        }
    }
}


@BindingAdapter("visibilityPrice")
fun TextView.setVisibilityPrice(item: CategoryWithParameters?) {
    item?.let {
        val priceText = findParameterValueByName(item.parameters, "Price")

        visibility = if (priceText!!.toInt() < 0) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }
}


@BindingAdapter("textServiceStation")
fun TextView.setEventServiceStation(item: CategoryWithParameters?) {
    item?.let {
        val serviceStationText = findParameterValueByName(item.parameters, "Service Station Name")

        text = if (serviceStationText!!.toInt() < 0) {
            ""
        } else {
            serviceStationText
        }
    }
}

@BindingAdapter("visibilityServiceStation")
fun TextView.setVisibilityServiceStation(item: CategoryWithParameters?) {
    item?.let {
        val serviceStationText = findParameterValueByName(item.parameters, "Service Station Name")

        visibility = if (serviceStationText!!.toInt() < 0) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }
}

@BindingAdapter("textRating")
fun TextView.setEventRating(item: CategoryWithParameters?) {
    item?.let {
        val ratingText = findParameterValueByName(item.parameters, "Service Rating")

        text = if (ratingText!!.toBoolean()) {
            "Ok"
        } else {
            "Not ok"
        }
    }
}

@BindingAdapter("visibilityRating")
fun TextView.setVisibilityRating(item: CategoryWithParameters?) {
    item?.let {
        val ratingText = findParameterValueByName(item.parameters, "Service Rating")
        visibility = if (ratingText == null) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }
}

@BindingAdapter("ratingSwitchIsChecked")
fun SwitchMaterial.setRatingSwitchToChecked(item: CategoryWithParameters?) {
    item?.let {
        val ratingText = findParameterValueByName(item.parameters, "Service Rating")
        isChecked = ratingText!!.toBoolean()
    }
}

@BindingAdapter("textComment")
fun TextView.setEventComment(item: CategoryWithParameters?) {
    item?.let {
        val commentText = findParameterValueByName(item.parameters, "Comment")
        if (commentText!!.length < 2) {
            text = ""
        } else {
            text = commentText
        }
    }
}

@BindingAdapter("visibilityComment")
fun TextView.setVisibilityComment(item: CategoryWithParameters?) {
    item?.let {
        val commentText = findParameterValueByName(item.parameters, "Comment")
        visibility = if (commentText!!.length < 2) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }
}

fun findParameterValueByName(parameterList: List<Parameter>, parameterName: String): String? {
    var parameterValue = null
    if (parameterList.isNotEmpty()){
        var parameterValue: String? = null
        for (i in 0..parameterList.size) {
            if (parameterList[i].name == parameterName) {
                parameterValue = parameterList[i].value
            }
        }
    }

    return parameterValue
}