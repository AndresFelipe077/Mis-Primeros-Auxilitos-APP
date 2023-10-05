package com.auxilitos.mis_primeros_auxilitos.ui.games

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.auxilitos.mis_primeros_auxilitos.databinding.FragmentDashboardBinding
import com.auxilitos.mis_primeros_auxilitos.games.AparecerObjetosAuxilitos

class GamesFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val gamesViewModel =
            ViewModelProvider(this)[GamesViewModel::class.java]

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        gamesViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }


        binding.game1.setOnClickListener{
            //toast.toastSuccess(this.requireActivity(), "Mis Primeros Auxilitos", "Perfil del usuario")
            startActivity(Intent(this.requireContext(), AparecerObjetosAuxilitos::class.java))
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}