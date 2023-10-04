package com.auxilitos.mis_primeros_auxilitos.ui.home

import ImageAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.auxilitos.mis_primeros_auxilitos.R
import com.auxilitos.mis_primeros_auxilitos.classesImport.ToastCustom
import com.auxilitos.mis_primeros_auxilitos.client.ApiClient
import com.auxilitos.mis_primeros_auxilitos.databinding.FragmentHomeBinding
import com.auxilitos.mis_primeros_auxilitos.model.response.ContenidoResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private val toast = ToastCustom()

    private val allContent: MutableList<ContenidoResponse> = mutableListOf()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ImageAdapter

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        getAllContent()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        Log.d("CONTENT", allContent.toString())

        adapter = ImageAdapter(allContent)
        recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun getAllContent(){
        val apiGetContent = ApiClient.getApiService().getContent()
        apiGetContent.enqueue(object : Callback<ContenidoResponse> {
            /**
             * Invoked for a received HTTP response.
             *
             *
             * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
             * Call [Response.isSuccessful] to determine if the response indicates success.
             */
            override fun onResponse(
                call: Call<ContenidoResponse>,
                response: Response<ContenidoResponse>
            ) {
                if (response.isSuccessful) {
                    val contentResponse = response.body()
                    contentResponse?.let {
                        allContent.add(it)
                        // Aquí puedes notificar a tu adaptador (si estás usando un RecyclerView) que los datos han cambiado.
                        // adapter.notifyDataSetChanged()
                    }
                }
            }

            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected exception
             * occurred creating the request or processing the response.
             */
            override fun onFailure(call: Call<ContenidoResponse>, t: Throwable) {
                Log.e("Error content", t.toString())
                toast.toastErrorFragment(this@HomeFragment, "Conexión", "Ups!, ha ocurrido un error inesperado 😢")
            }

        })
    }

}