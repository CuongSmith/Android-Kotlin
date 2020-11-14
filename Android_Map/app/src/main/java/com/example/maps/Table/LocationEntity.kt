package com.example.maps.Table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "LocatioN")
data class LocationEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val lat: Double,
    val lon: Double
)

@Entity(tableName = "LinK",
    primaryKeys = ["pid", "link"],
    foreignKeys = [ForeignKey(
        entity = LocationEntity::class,
        parentColumns = ["id"],
        childColumns = ["pid"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Links(
    @ColumnInfo(name = "pid")
    val id: Int = 0,
    val link: String
)