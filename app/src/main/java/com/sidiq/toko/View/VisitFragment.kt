package com.sidiq.toko.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sidiq.toko.R
import com.sidiq.toko.databinding.FragmentVisitBinding


class VisitFragment : Fragment() {

    var binding: FragmentVisitBinding? = null

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentVisitBinding.inflate(inflater, container, false)

        val view = binding?.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        binding?.btnSelesai!!.setOnClickListener {

            navController.navigate(R.id.action_visitFragment_to_homeFragment)

        }
        binding?.btnKeluarr!!.setOnClickListener {
                activity?.finish()

        }

    }


}