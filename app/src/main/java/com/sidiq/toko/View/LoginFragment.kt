package com.sidiq.toko.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sidiq.toko.DAO.DatabaseToko
import com.sidiq.toko.Model.Login
import com.sidiq.toko.Model.TokoModel
import com.sidiq.toko.Network.ApiServices
import com.sidiq.toko.R


import com.sidiq.toko.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response


class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null
    private lateinit var navControler: NavController
    private lateinit var db : DatabaseToko


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        db = DatabaseToko.getInstance(requireActivity())!!
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





        navControler = Navigation.findNavController(view)

        binding?.btnLogin!!.setOnClickListener {
              val email = binding?.etNip!!.text.toString()              
              val password = binding?.etpassword!!.text.toString()      
            login(email, password)
        }

    }

    private fun login(
        username: String,
        password: String

    ) {
        val retrofit = ApiServices.restApi()
        retrofit.getLogin(username,password)
            .enqueue(object : Callback<Login> {
                override fun onFailure(call: Call<Login>, t: Throwable) {
                    when (t) {
                        is HttpException -> {
                            Log.e(
                                "TAG",
                                "onFailure: ${t.response()?.errorBody()?.string().toString()}  "
                            )
                        }
                    }
                }

                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    if (!response.body()?.status?.startsWith("failure")!!) {


                        Log.e("", "onResponse: ${response.isSuccessful} ")
                           navControler.navigate(R.id.action_loginFragment_to_homeActivity2)


                        val data = ArrayList<TokoModel>()

                        val dataResponse = response.body()?.stores

                        dataResponse?.map {
                            val toko = TokoModel(
                                account_id = it.account_id,
                                account_name = it.account_name,
                                address = it.address,
                                area_id = it.area_id,
                                area_name = it.area_name,
                                channel_id = it.channel_id,
                                channel_name = it.channel_name,
                                dc_id = it.dc_id,
                                dc_name = it.dc_name,
                                latitude = it.latitude,
                                longitude = it.longitude,
                                region_id = it.region_id,
                                region_name = it.region_name,
                                store_code = it.store_code,
                                store_id = it.store_id,
                                store_name = it.store_name,
                                subchannel_id = it.subchannel_id,
                                subchannel_name = it.subchannel_name
                            )
                            data.add(toko)
                        }
                        lifecycleScope.launch(IO) {
                            db.tokoDAO().insert(data)
                        }

                    } else {
                        Toast.makeText(requireContext(), "password dan username salah", Toast.LENGTH_SHORT).show()


                    }
                }
            })
    }
}