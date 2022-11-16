package ran.am.androidmanifestercodeskotlin

import android.Manifest
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.*
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import java.lang.Exception

class GmapLiveTracking : FragmentActivity(), OnMapReadyCallback {
    private var mMap: GoogleMap? = null
    private var fusedLocationClient: FusedLocationProviderClient? = null
    private var mLocationRequest: LocationRequest? = null
    private var mlocationCallback: LocationCallback? = null
    private var builder: LocationSettingsRequest.Builder? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps_expl)
        ///   ActivityCompat.requestPermissions(GmapLiveTracking.this,                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},  1);
        val mapFragment: SupportMapFragment =
            getSupportFragmentManager().findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fetchLastLocation()
        mlocationCallback = object : LocationCallback() {
            fun onLocationResult(locationResult: LocationResult?) {
                if (locationResult == null) {
                    return
                }
                for (location in locationResult.getLocations()) {
                    // Update UI with location data
                    // ...
                    Log.e("CONTINIOUSLOC: ", location.toString())
                    val sydney = LatLng(location.latitude, location.longitude)
                    mMap?.clear()
                    // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
                    mMap?.addMarker(MarkerOptions().position(sydney).title("Your location"))
                        ?.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.track))
                    Toast.makeText(
                        this@GmapLiveTracking,
                        "You can use a App called 'Fake Location to test this '",
                        Toast.LENGTH_LONG
                    ).show()
                    mMap?.moveCamera(CameraUpdateFactory.newLatLng(sydney))
                    ///                    ((TextView)findViewById(R.id.textv)).setText(location.getLatitude()+"\n"+location.getLongitude());
                }
            }
        }
        mLocationRequest = createLocationRequest()
        builder = Builder()
            .addLocationRequest(mLocationRequest)
        checkLocationSetting(builder)
    }

    @JvmName("onMapReady1")
    fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34, 151)
        mMap?.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
            ?.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.track))
        mMap?.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    private fun LatLng(i: Int, i1: Int): LatLng {

    }

    private fun fetchLastLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    Activity#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
//                    Toast.makeText(MainActivity.this, "Permission not granted, Kindly allow permission", Toast.LENGTH_LONG).show();
                showPermissionAlert()
                return
            }
        }
        fusedLocationClient?.getLastLocation()
            ?.addOnSuccessListener(this, object : OnSuccessListener<Location?> {
                override fun onSuccess(location: Location?) {
                    // Got last known location. In some rare situations this can be null.
                    if (location != null) {
                        // Logic to handle location object
                        Log.i(
                            "LAST LOCATION: ",
                            location.toString()
                        ) // You will get your last location here
                        Toast.makeText(this@GmapLiveTracking, "success", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        fusedLocationClient?.getLastLocation()
            ?.addOnFailureListener(this@GmapLiveTracking, object : OnFailureListener {
                override fun onFailure(e: Exception) {
                    Toast.makeText(this@GmapLiveTracking, "success in progress", Toast.LENGTH_SHORT)
                        .show()
                }
            })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            123 -> {

                // If request is cancelled, the result arrays are empty.
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    // permission was denied, show alert to explain permission
                    showPermissionAlert()
                } else {
                    //permission is granted now start a background service
                    if (ActivityCompat.checkSelfPermission(
                            getApplicationContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(
                            getApplicationContext(),
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        fetchLastLocation()
                    }
                }
            }
        }
    }

    private fun showPermissionAlert() {
        if (ActivityCompat.checkSelfPermission(
                this@GmapLiveTracking,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this@GmapLiveTracking,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this@GmapLiveTracking,
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),
                123
            )
        }
    }

    protected fun createLocationRequest(): LocationRequest {
        val mLocationRequest: LocationRequest = LocationRequest.create()
        mLocationRequest.setInterval(5000)
        mLocationRequest.setFastestInterval(6000)
        mLocationRequest.setSmallestDisplacement(30F)
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        return mLocationRequest
    }

    private fun checkLocationSetting(builder: LocationSettingsRequest.Builder?) {
        val client: SettingsClient = LocationServices.getSettingsClient(this)
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())
        task.addOnSuccessListener(this, object : OnSuccessListener<LocationSettingsResponse?>() {
            override fun onSuccess(locationSettingsResponse: LocationSettingsResponse?) {
                // All location settings are satisfied. The client can initialize
                // location requests here.
                // ...
                startLocationUpdates()
                return
            }
        })
        task.addOnFailureListener(this, object : OnFailureListener() {
            override fun onFailure(e: Exception) {
                if (e is ResolvableApiException) {
                    // Location settings are not satisfied, but this can be fixed
                    val builder1 = AlertDialog.Builder(this@GmapLiveTracking)
                    builder1.setTitle("Continious Location Request")
                    builder1.setMessage("This request is essential to get location update continiously")
                    builder1.create()
                    builder1.setPositiveButton("OK", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface, which: Int) {
                            val resolvable: ResolvableApiException = e as ResolvableApiException
                            try {
                                resolvable.startResolutionForResult(
                                    this@GmapLiveTracking,
                                    REQUEST_CHECK_SETTINGS
                                )
                            } catch (e1: IntentSender.SendIntentException) {
                                e1.printStackTrace()
                            }
                        }
                    })
                    builder1.setNegativeButton("Cancel", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface, which: Int) {
                            Toast.makeText(
                                this@GmapLiveTracking,
                                "Location update permission not granted",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    })
                    builder1.show()
                }
            }
        })
    }

    protected override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            if (resultCode == Activity.RESULT_OK) {
                // All location settings are satisfied. The client can initialize
                // location requests here.
                startLocationUpdates()
            } else {
                checkLocationSetting(builder)
            }
        }
    }

    fun startLocationUpdates() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    Activity#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return
            }
        }
        mLocationRequest?.let {
            mlocationCallback?.let { it1 ->
                fusedLocationClient?.requestLocationUpdates(
                    it,
                    it1,
                    null /* Looper */
                ) ?: 
            }
        }
    }

    companion object {
        private const val REQUEST_CHECK_SETTINGS = 102
    }

     @JvmName("onMapReady1")
     override fun onMapReady(p0: GoogleMap) {
        TODO("Not yet implemented")
    }
}