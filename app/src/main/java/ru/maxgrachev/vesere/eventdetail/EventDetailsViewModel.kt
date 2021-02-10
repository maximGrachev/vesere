package ru.maxgrachev.vesere.eventdetail;

import android.opengl.Visibility
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.maxgrachev.vesere.database.allrecords.Event
import ru.maxgrachev.vesere.database.allrecords.EventDatabaseDao

class EventDetailsViewModel(
    private val eventKey: Long = 0L,
    dataSource: EventDatabaseDao
) : ViewModel() {

    val database = dataSource

    private val event = MediatorLiveData<Event>()
    fun getEvent() = event

    var serviceLifeIsVisibile = View.GONE
    var carMileageIsVisibile = View.GONE
    var priceIsVisibile = View.GONE
    var serviceStationIsVisibile = View.GONE
    var commentIsVisibile = View.GONE


    fun setDetailsFragmentVisibility() {
        serviceLifeIsVisibile = if (event.value?.serviceLife!! < 0) {
            View.GONE
        } else {
            View.VISIBLE
        }

        carMileageIsVisibile = if (event.value?.carMileage!! < 0) {
            View.GONE
        } else {
            View.VISIBLE
        }

        priceIsVisibile = if (event.value?.price!! < 0) {
            View.GONE
        } else {
            View.VISIBLE
        }

        serviceStationIsVisibile = if (event.value?.serviceStationName?.length!! < 2) {
            View.GONE
        } else {
            View.VISIBLE
        }

        commentIsVisibile = if (event.value?.comment?.length!! < 2) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }


    init {
        event.addSource(database.getEventWithId(eventKey), event::setValue)
//        setDetailsFragmentVisibility() TODO realize the setDetailsFragmentVisibility() function
    }

    private val _navigateToAllRecords = MutableLiveData<Boolean?>()
    val navigateToAllRecords: LiveData<Boolean?>
        get() = _navigateToAllRecords

    fun doneNavigating() {
        _navigateToAllRecords.value = null
    }

    fun onClose() {
        _navigateToAllRecords.value = true
    }

    fun giveEventType(): String? {
        return database.getEventWithId(eventKey).value?.eventName
    }

}
