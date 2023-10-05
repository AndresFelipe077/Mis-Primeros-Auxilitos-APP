package com.auxilitos.mis_primeros_auxilitos.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.auxilitos.mis_primeros_auxilitos.R
import com.auxilitos.mis_primeros_auxilitos.client.ApiClient
import com.auxilitos.mis_primeros_auxilitos.databinding.FragmentHomeBinding
import java.util.concurrent.TimeUnit
import okhttp3.*

class HomeFragment : Fragment() {

    private lateinit var webSocket: WebSocket

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

        // Configurar el cliente WebSocket
        val client = OkHttpClient.Builder()
            .readTimeout(3, TimeUnit.SECONDS) // Configurar el tiempo de espera para leer mensajes
            .build()

        // URL del servidor WebSocket (reemplázala con tu URL)
        val socketUrl = ApiClient.baseUrl

        // Crear una solicitud de conexión WebSocket
        val request = Request.Builder().url(socketUrl).build()

        // Establecer el listener para manejar mensajes WebSocket
        val socketListener = object : WebSocketListener() {
            override fun onMessage(webSocket: WebSocket, text: String) {

            }
        }

        // Conectar al servidor WebSocket
        webSocket = client.newWebSocket(request, socketListener)

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

        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        homeViewModel.contentData.observe(viewLifecycleOwner) { newData ->
            adapter = ContentAdapter(newData)
            recyclerView.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        webSocket.close(1000, "Usuario salió del fragmento")
        _binding = null
    }

}