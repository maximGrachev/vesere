package ru.maxgrachev.vesere.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "parameter",
    foreignKeys = [ForeignKey(entity = Category::class,
    parentColumns = ["id"], childColumns = ["categoryId"], onDelete = ForeignKey.CASCADE
)])
@Parcelize
data class Parameter(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    var name: String? = null,
    var value: String? = null,
    var categoryId: Int? = null
) : Parcelable