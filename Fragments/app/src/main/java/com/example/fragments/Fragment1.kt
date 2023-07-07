package com.example.fragments

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragments.databinding.Fragment1Binding

private const val ARG_COLOUR = "colour"

class Fragment1 : Fragment(), ColourChanger {

    var colour: Int? = null

    private lateinit var binding: Fragment1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("mylog", "Fr1 onCreated $colour")
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        colour = arguments?.getInt(ARG_COLOUR)
        binding = Fragment1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.text = "Fragment 1"
        colour?.also {
            setColour(it)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(colour: Int?) =
            Fragment1().apply {
                colour?.let {
                    arguments = Bundle().apply {
                        putInt(ARG_COLOUR, colour)
                    }
                }
            }
    }


    override fun getColour(): Int = (binding.fragmentFrameLayout.background as ColorDrawable).color
    override fun setColour(colour: Int) {
        binding.fragmentFrameLayout.background = ColorDrawable(colour)
        this.colour = colour
    }

    override fun toString(): String {
        return "Fragment1 color: $colour"
    }
}