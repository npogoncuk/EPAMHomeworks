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

    private var colour: Int? = null

    private lateinit var binding: Fragment1Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            colour = it.getInt(ARG_COLOUR)
        }
        Log.d("mylog", "Fr1 onCreated $colour")
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("mylog", "Fr1 onCreateView $colour")
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
        colour = getColour()
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("mylog", "Fr1 onSaveInstanceState $colour")
        outState.putInt(ARG_COLOUR, colour!!)
    }

    companion object {
        @JvmStatic
        fun newInstance() = Fragment1()

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