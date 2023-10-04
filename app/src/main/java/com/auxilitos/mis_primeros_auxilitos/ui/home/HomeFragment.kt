package com.auxilitos.mis_primeros_auxilitos.ui.home

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
import com.auxilitos.mis_primeros_auxilitos.model.response.ContentResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private val toast = ToastCustom()

    private val dogImages = mutableListOf<String>()
    private val allContent: MutableList<ContentResponse> = mutableListOf()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ContentAdapter

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        getAllContent()

        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = ContentAdapter(allContent)
        recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun getAllContent() {
        val apiGetContent = ApiClient.getApiService().getOneContent("1")
        apiGetContent.enqueue(object : Callback<ContentResponse> {
            /**
             * Invoked for a received HTTP response.
             *
             *
             * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
             * Call [Response.isSuccessful] to determine if the response indicates success.
             */
            override fun onResponse(
                call: Call<ContentResponse>,
                response: Response<ContentResponse>
            ) {
                if (response.isSuccessful) {
                    val contentResponse = response.body()
                    contentResponse?.let {
                        //allContent.add(it)
                        val arrayContent = ContentResponse(
                            id = it.id,
                            title = it.title,
                            autor = it.autor,
                            slug =  it.slug,
                            description = it.description,
                            url = ApiClient.baseUrl + it.url,
                            user_id = it.user_id,
                            created_at = it.created_at,
                            updated_at = it.updated_at
                        )
                        Log.e("asdsfasdfd", ApiClient.baseUrl + it.url)
                        allContent.add(
                            arrayContent
                        )
                    }
                }
            }

            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected exception
             * occurred creating the request or processing the response.
             */
            override fun onFailure(call: Call<ContentResponse>, t: Throwable) {
                Log.e("Error content", t.toString())
                toast.toastErrorFragment(
                    this@HomeFragment,
                    "Conexión",
                    "Ups!, ha ocurrido un error inesperado 😢"
                )
            }

        })
    }

}