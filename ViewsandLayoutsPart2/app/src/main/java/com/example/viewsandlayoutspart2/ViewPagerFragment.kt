package com.example.viewsandlayoutspart2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

private const val ARG_NUMBER = "param1"

class ViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentNumber = arguments?.getInt(ARG_NUMBER)
        view.findViewById<TextView>(R.id.textViewFragmentNumber).apply {
            text = getString(R.string.fragment_and_number, fragmentNumber)
        }
        view.findViewById<ImageView>(R.id.imageViewFragmentNumber).apply {
            setImageResource(when(fragmentNumber) {
                1 -> R.drawable.one
                2 -> R.drawable.two
                3 -> R.drawable.three
                else -> throw IllegalArgumentException("Fragment number must be from 1 to 3")
            })
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(number: Int) =
            ViewPagerFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_NUMBER, number)
                }
            }
    }
}