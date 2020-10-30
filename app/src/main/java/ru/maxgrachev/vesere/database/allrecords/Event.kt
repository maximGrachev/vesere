package ru.maxgrachev.vesere.database.allrecords

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="events_table")
data class Event(
    @PrimaryKey(autoGenerate = true)
    var eventID: Long = 0L,

    @ColumnInfo(name="event_name")
    var eventName: String = "Other",

    @ColumnInfo(name="date_milli")
    val dateMilli: Long = System.currentTimeMillis(),

    @ColumnInfo(name="service_life")
    var serviceLife: Int = -1,

    @ColumnInfo(name="car_mileage")
    var carMileage: Int = -1,

    @ColumnInfo(name="event_price")
    var price: Int = -1,

    @ColumnInfo(name="service_station_name")
    var serviceStationName: String = "",

    @ColumnInfo(name = "service_rating")
    var serviceRating: Boolean = false,

    @ColumnInfo(name="comment")
    var comment: String = "-"

)