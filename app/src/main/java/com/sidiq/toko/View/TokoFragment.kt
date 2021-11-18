package com.sidiq.toko.View

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.Task
import com.sidiq.toko.Adapter.AdapterToko
import com.sidiq.toko.DAO.DatabaseToko
import com.sidiq.toko.Model.Store
import com.sidiq.toko.Model.TokoModel
import com.sidiq.toko.Network.ApiServices
import com.sidiq.toko.R
import com.sidiq.toko.databinding.FragmentTokoBinding
import kotlinx.android.synthetic.main.card_view.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response


class TokoFragment : Fragment(), OnMapReadyCallback {

    private var binding: FragmentTokoBinding? = null
    private lateinit var navController: NavController
    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var mapFragment: SupportMapFragment

    private var adapter: AdapterToko? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )

        // Inflate the layout for this fragment
        binding = FragmentTokoBinding.inflate(inflater, container, false)
        mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        return binding?.root
    }


    @SuppressLint("MissingPermission")
    val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    if (ActivityCompat.checkSelfPermission(
                            requireContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                            requireContext(),
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {

                        return@registerForActivityResult
                    }
                    fusedLocationClient.lastLocation
                        .addOnSuccessListener { location: Location? ->

                            val latlang = LatLng(location!!.latitude, location!!.longitude)
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latlang, 17f))

                        }
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {


                    if (ActivityCompat.checkSelfPermission(
                            requireContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                            requireContext(),
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {

                        return@registerForActivityResult
                    }
                    fusedLocationClient.lastLocation
                        .addOnSuccessListener { location: Location? ->

                            val latlang = LatLng(location!!.latitude, location!!.longitude)
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latlang, 17f))

                        }
                }
                else -> {

                }
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        lifecycleScope.launch(IO) {
            showData(DatabaseToko.getInstance(requireActivity())?.tokoDAO()?.getAll()!!)
        }


    }


    private fun showData(toko: List<TokoModel>) {
        lifecycleScope.launch(Main) {
            binding?.recylerView?.adapter = AdapterToko(toko) {
                Toast.makeText(requireContext(), "Click ", Toast.LENGTH_SHORT)
                    .show()
                val bundle = bundleOf(
                    "name" to tvNamaToko.text.toString(),
                    "type" to tvType.text.toString(),
                    "alamat" to tvUkuran.text.toString()
                )

                navController.navigate(R.id.action_tokoFragment_to_detailFragment,bundle)


            }.also {
                it.notifyDataSetChanged()
            }
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        val builder = LocationSettingsRequest.Builder()


        googleMap.isMyLocationEnabled = true
        map = googleMap

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }

    }

}