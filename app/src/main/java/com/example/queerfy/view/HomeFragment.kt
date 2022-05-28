package com.example.queerfy.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.queerfy.databinding.FragmentHomeBinding
import com.example.queerfy.view.residenceRegister.ResidenceRegisterStepOneActivity
import com.example.queerfy.viewModel.HomeViewModel
import com.example.queerfy.viewModel.LoginViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel = HomeViewModel()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        this.binding.btnHost.setOnClickListener {
            val residenceRegisterPage = Intent(activity, ResidenceRegisterStepOneActivity::class.java)

            startActivity(residenceRegisterPage)
        }

        this.binding.btnRegister.setOnClickListener {
            val registerChoosePage = Intent(activity, RegisterChooseActivity::class.java)

            startActivity(registerChoosePage)
        }

        return root
    }

    // TODO
    fun setup() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}