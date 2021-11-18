package com.sidiq.toko.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sidiq.toko.R
import com.sidiq.toko.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {


    var binding: FragmentDetailBinding? =null
    lateinit var navController: NavController
    var get_name: String? = null
    var get_type: String? = null
    var get_adress: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        get_name = arguments?.getString("name")
        get_type = arguments?.getString("type")
        get_adress = arguments?.getString("alamat")


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        val view = binding?.root
        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.tvJudulDetail!!.setText(get_name)
        binding?.tvIsiSubTypleDisiplay!!.setText(get_type)
        binding?.tvJalanDetail!!.setText(get_adress)

        navController = Navigation.findNavController(view)

        binding?.btnVisit!!.setOnClickListener {


            navController.navigate(R.id.action_detailFragment_to_visitFragment)


        }

    }

}