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
        Log.d("mylog", "Fr1 onCreateView $colour")
        if (colour == null) colour = arguments?.getInt(ARG_COLOUR)
        binding = Fragment1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("mylog", "Fr1 onViewCreated $colour")
        binding.textView.text = "Fragment 1"
        colour?.also {
            setColour(it)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("mylog", "Fr1 onStart $colour")
    }

    override fun onResume() {
        super.onResume()
        Log.d("mylog", "Fr1 onResume $colour")
    }

    override fun onPause() {
        super.onPause()
        Log.d("mylog", "Fr1 onPause $colour")
    }

    override fun onStop() {
        super.onStop()
        Log.d("mylog", "Fr1 onStop $colour")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("mylog", "Fr1 onDestroyView $colour")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("mylog", "Fr1 onDestroy $colour")

    }

    override fun onDetach() {
        super.onDetach()
        Log.d("mylog", "Fr1 onDetach $colour")

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
        Log.d("mylog", "Fr1 setColour $colour")
        binding.fragmentFrameLayout.background = ColorDrawable(colour)
        this.colour = colour
    }

    override fun toString(): String {
        return "Fragment1 color: $colour"
    }
}