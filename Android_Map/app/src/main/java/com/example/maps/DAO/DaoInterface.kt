package com.example.maps.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.maps.Table.Links
import com.example.maps.Table.LocationEntity


@Dao interface ItemDao {
    @Query("select * from LocatioN")
    fun getAllItems(): List<LocationEntity>

    @Query("select id from LocatioN where lat = :lat and lon = :lon")
    fun getID(lat: Double, lon: Double): Int

    @Query("delete from LocatioN where lat = :lat and lon= :lon")
    fun delete(lat: Double, lon: Double)

    @Insert
    fun insertItem(vararg LocatioN: LocationEntity)

    @Delete
    fun deleteItem(LocatioN: LocationEntity)
}

@Dao interface LinkDao {
    @Query("select * from Link")
    fun getAllLinks(): List<Links>

    @Query("select link from Link where pid = :id")
    fun getLinkid(id: Int): List<String>

    @Insert()
    fun insertLinks(vararg LinK: Links)

    @Delete
    fun deleteLinks(LinK: Links)
}


