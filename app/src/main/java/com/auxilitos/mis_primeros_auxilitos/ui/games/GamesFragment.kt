package com.auxilitos.mis_primeros_auxilitos.ui.games

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.auxilitos.mis_primeros_auxilitos.databinding.FragmentGamesBinding
import com.auxilitos.mis_primeros_auxilitos.games.AhorcadoActivity
import com.auxilitos.mis_primeros_auxilitos.games.AparecerObjetosAuxilitos
import com.auxilitos.mis_primeros_auxilitos.games.DibujarAdivinarActivity
import com.auxilitos.mis_primeros_auxilitos.games.QuestionsActivity
import com.auxilitos.mis_primeros_auxilitos.games.SelectObjectWeb
import com.auxilitos.mis_primeros_auxilitos.games.TriviasActivity

class GamesFragment : Fragment() {

    private var _binding: FragmentGamesBinding? = null

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

        _binding = FragmentGamesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        gamesViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }


        binding.btnTouchFirstAidGame.setOnClickListener{
            //toast.toastSuccess(this.requireActivity(), "Mis Primeros Auxilitos", "Perfil del usuario")
            startActivity(Intent(this.requireContext(), AparecerObjetosAuxilitos::class.java))
        }

        binding.btnSelectObjectWeb.setOnClickListener {
          startActivity(Intent(this.requireContext(), SelectObjectWeb::class.java))
        }

        binding.btnAdivinar.setOnClickListener {
            startActivity(Intent(this.requireContext(), QuestionsActivity::class.java))
        }

        binding.btnTrivias.setOnClickListener{
            startActivity(Intent(this.requireContext(), TriviasActivity::class.java))
        }

        binding.btnAhorcado.setOnClickListener{
            startActivity(Intent(this.requireContext(), AhorcadoActivity::class.java))
        }

        binding.btnDraw.setOnClickListener{
            startActivity(Intent(this.requireContext(), DibujarAdivinarActivity::class.java))
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}