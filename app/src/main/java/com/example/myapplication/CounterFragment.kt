package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentCounterBinding

class CounterFragment : Fragment() {
    private lateinit var binding: FragmentCounterBinding
    private var count = 0
    private var isIncrementing = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCounterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            if (isIncrementing) {
                if (count < 10) {
                    count++
                } else {
                    isIncrementing = false
                }
            } else {
                if (count > 0) {
                    count--
                } else {
                    // Переключаемся на второй фрагмент
                    val fragmentManager = requireActivity().supportFragmentManager
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, SecondFragment())
                        .addToBackStack(null)
                        .commit()
                }
            }
            updateButtonText()
        }
    }

    private fun updateButtonText() {
        if (isIncrementing) {
            binding.button.text = "+1"
        } else {
            binding.button.text = "-1"
        }
        binding.textView.text = count.toString()
    }
}