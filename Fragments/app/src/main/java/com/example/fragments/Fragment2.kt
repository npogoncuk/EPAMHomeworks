package com.example.fragments

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragments.databinding.Fragment2Binding

private const val ARG_COLOUR = "colour"

class Fragment2 : Fragment(), ColourChanger {
    private var colour: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            colour = it.getInt(ARG_COLOUR)
        }
    }

    private lateinit var binding: Fragment2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Fragment2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.text = "Fragment 2"
    }

    companion object {
        @JvmStatic
        fun newInstance(colour: Int?) =
            Fragment2().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLOUR, colour ?: 0)
                }
            }
    }

    override fun getColour(): Int = (binding.fragmentFrameLayout.background as ColorDrawable).color
    override fun setColour(colour: Int) {
        binding.fragmentFrameLayout.background = ColorDrawable(colour)
    }
}