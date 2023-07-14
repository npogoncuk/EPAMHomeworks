package com.example.viewandlayouts

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.viewandlayouts.databinding.FragmentSpannableStringBinding

class SpannableStringFragment : Fragment() {

    private lateinit var binding: FragmentSpannableStringBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSpannableStringBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val text = resources.getString(R.string.text_for_spannable_string)
        val spannableSting = SpannableString(text).apply {
            val layoutNames = resources.getStringArray(R.array.layouts_names)

            val startIndex0 = text.indexOf(layoutNames[0])
            setSpan(object : ClickableSpan() {
                override fun onClick(widget: View) {
                    openCalculatorFragment(LayoutConstant.LINEAR_LAYOUT_CONSTANT)
                }

            }, startIndex0, startIndex0 + layoutNames[0].length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)

            val startIndex1 = text.indexOf(layoutNames[1])
            setSpan(object : ClickableSpan() {
                override fun onClick(widget: View) {
                    openCalculatorFragment(LayoutConstant.RELATIVE_LAYOUT_CONSTANT)
                }

            }, startIndex1, startIndex1 + layoutNames[1].length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)

            val startIndex2 = text.indexOf(layoutNames[2])
            setSpan(object : ClickableSpan() {
                override fun onClick(widget: View) {
                    openCalculatorFragment(LayoutConstant.GRID_LAYOUT_CONSTANT)
                }

            }, startIndex2, startIndex2 + layoutNames[2].length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)

            val startIndex3 = text.indexOf(layoutNames[3])
            setSpan(object : ClickableSpan() {
                override fun onClick(widget: View) {
                    openCalculatorFragment(LayoutConstant.CONSTRAINT_LAYOUT_CONSTANT)
                }

            }, startIndex3, startIndex3 + layoutNames[3].length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        }

        binding.textViewSpannable.text = spannableSting
        binding.textViewSpannable.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun openCalculatorFragment(layoutConstant: LayoutConstant) {
        Log.d("mylog", "span clicked")
        parentFragmentManager.beginTransaction()
            .replace(R.id.frameLayoutActivity, CalculatorFragment.newInstance(layoutConstant.constant))
            .addToBackStack(null)
            .commit()
    }
    companion object {
        @JvmStatic
        fun newInstance() = SpannableStringFragment()
        @JvmStatic val TAG = this::class.qualifiedName!!
    }
}