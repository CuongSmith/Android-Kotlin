package com.example.maps

import android.content.Intent
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import android.widget.Toast
import com.example.maps.DTB.AppDatabase
import com.example.maps.Table.LocationEntity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object {
        val EXTRA_TEXTNAME: String = MainActivity::class.java.name + "extra.TEXTNAME"
        val EXTRA_TEXTLAGITUDE: String = MainActivity::class.java.name + "extra.TEXTLAGITUDE"
        val EXTRA_TEXTLONGITUDE: String = MainActivity::class.java.name + "extra.TEXTLONGITUDE"
    }

    lateinit var googleMap: GoogleMap
    lateinit var fragment: SupportMapFragment
    val geocoder by lazy { Geocoder(this) }

    val list: List<LocationEntity> by lazy {
        AppDatabase.newInstance(applicationContext)
            .itemDao().getAllItems()
    }

    lateinit var latLng: LatLng
    val markerOptions: MarkerOptions = MarkerOptions()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        fragment.getMapAsync(this)
    }

    fun addMarker(latLng: LatLng) {
        markerOptions.position(latLng).title(getAddress(latLng.latitude, latLng.longitude))
        markerOptions.title(getAddress(latLng.latitude, latLng.longitude))
        googleMap.addMarker(markerOptions).showInfoWindow()
    }

    fun addListMarker() {
        for (i in list) {
            addMarker(LatLng(i.lat, i.lon))
        }
    }

    override fun onMapReady(p0: GoogleMap?) {
        latLng = LatLng(14.0583, 108.2772)
        this.googleMap = p0!!
        addListMarker()

        val dialog = Remove_Dialog(this)

        googleMap.setOnInfoWindowLongClickListener {
            dialog.setListener(object : Remove_Dialog.RemoveDialogListener {
                override fun onYesClick() {
                    AppDatabase.newInstance(applicationContext).itemDao()
                        .delete(lat = it.position.latitude, lon = it.position.longitude)
                    it.remove()
                    dialog.dismiss()
                }
            })
            dialog.show()
        }

        googleMap.setOnInfoWindowClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.apply {
                putExtra(EXTRA_TEXTNAME, getAddress(it.position.latitude, it.position.longitude))
                putExtra(EXTRA_TEXTLAGITUDE, it.position.latitude.toString())
                putExtra(EXTRA_TEXTLONGITUDE, it.position.longitude.toString())
            }
            startActivityForResult(intent, 1)
        }

        this.googleMap.setOnMapClickListener { latLng ->
            markerOptions.position(latLng).title(getAddress(latLng.latitude, latLng.longitude))
            markerOptions.title(getAddress(latLng.latitude, latLng.longitude))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5f))
            googleMap.addMarker(markerOptions).showInfoWindow()

            AppDatabase.newInstance(applicationContext).itemDao()
                .insertItem(LocationEntity(lat = latLng.latitude, lon = latLng.longitude))
        }
    }

    fun getAddress(lat: Double, lon: Double): String {
        return geocoder.getFromLocation(lat, lon, 1).get(0).getAddressLine(0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "No Photos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}