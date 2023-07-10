package com.example.viewandlayouts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

private const val ARG_LAYOUT_CONSTANT = "param1"

class CalculatorFragment : Fragment() {

    private var layoutConstant: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            layoutConstant = it.getInt(ARG_LAYOUT_CONSTANT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(requireContext(), "Constant: $layoutConstant", Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance(layoutConstant: Int) =
            CalculatorFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_LAYOUT_CONSTANT, layoutConstant)
                }
            }
    }
}