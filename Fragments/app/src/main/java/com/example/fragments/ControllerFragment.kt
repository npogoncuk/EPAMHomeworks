package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragments.databinding.FragmentControllerBinding

private const val TEXT_KEY = "TEXT_KEY"
class ControllerFragment : Fragment(), AddTextAlertDialogFragment.TextReceiverFromDialog {

    private lateinit var binding: FragmentControllerBinding
    private var savedText: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentControllerBinding.inflate(inflater, container, false)
        savedInstanceState?.let {
            savedText = it.getString(TEXT_KEY)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textViewList.text = savedText ?: ""
        binding.buttonSwapColours.setOnClickListener {
            val topFragment = parentFragmentManager.findFragmentById(R.id.frameLayoutTop)
            val bottomFragment = parentFragmentManager.findFragmentById(R.id.frameLayoutBottom)
            val topColourChanger = topFragment as ColourChanger
            val bottomColourChanger = bottomFragment as ColourChanger
            val topColour = topColourChanger.getColour()
            val bottomColour = bottomColourChanger.getColour()

            topColourChanger.setColour(bottomColour)
            bottomColourChanger.setColour(topColour)
        }

        binding.buttonSwapPlaces.setOnClickListener {
            val topFragment = parentFragmentManager.findFragmentById(R.id.frameLayoutTop)!!
            val bottomFragment = parentFragmentManager.findFragmentById(R.id.frameLayoutBottom)!!
            parentFragmentManager.beginTransaction()
                .remove(topFragment)
                .remove(bottomFragment)
                .detach(topFragment)
                .detach(bottomFragment)
                .commitNow()

            parentFragmentManager.beginTransaction()
                .add(R.id.frameLayoutTop, bottomFragment)
                .add(R.id.frameLayoutBottom, topFragment)
                .commit()
        }

        binding.buttonShowAlertFragment.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .add(AddTextAlertDialogFragment(), AddTextAlertDialogFragment.TAG)
                .commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(TEXT_KEY, binding.textViewList.text.toString())
    }

    override fun receiveText(text: String) {
        val previousText = binding.textViewList.text.toString()
        binding.textViewList.text = previousText + "\n" + text
    }


}