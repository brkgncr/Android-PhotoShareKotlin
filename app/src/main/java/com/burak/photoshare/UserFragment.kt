package com.burak.photoshare

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.burak.photoshare.databinding.FragmentUserBinding


class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signInButton.setOnClickListener{ signIn(it) }
        binding.signUpButton.setOnClickListener{ signUp(it) }
    }

    fun signUp(view: View) {
        println("Sign Up Clicked")
    }

    fun signIn(view: View) {
        println("Sign In Clicked")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}