package com.example.fragments

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragments.databinding.Fragment2Binding

private const val ARG_COLOUR = "colour"

class Fragment2 : Fragment(), ColourChanger {

    var colour: Int? = null


    private lateinit var binding: Fragment2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            colour = it.getInt(ARG_COLOUR)
        }
    }
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
        colour?.also {
            setColour(it)
        }
        colour = getColour()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(ARG_COLOUR, colour!!)
    }
    companion object {
        @JvmStatic
        fun newInstance() = Fragment2()
    }

    override fun getColour(): Int = (binding.fragmentFrameLayout.background as ColorDrawable).color
    override fun setColour(colour: Int) {
        binding.fragmentFrameLayout.background = ColorDrawable(colour)
        this.colour = colour
    }

    override fun toString(): String {
        return "Fragment2 color: $colour"
    }
}