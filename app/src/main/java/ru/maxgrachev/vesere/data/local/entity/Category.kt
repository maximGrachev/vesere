package ru.maxgrachev.vesere.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "category")
@Parcelize
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    var name: String? = null,
    var parentId: Int? = null
) : Parcelable