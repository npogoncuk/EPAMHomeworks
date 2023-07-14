package com.example.viewandlayouts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.IdRes
import org.mariuszgromada.math.mxparser.Expression

private const val ARG_LAYOUT_CONSTANT = "param1"

class CalculatorFragment : Fragment() {

    private var layoutConstant: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            layoutConstant = it.getInt(ARG_LAYOUT_CONSTANT)
        }

    }

    private lateinit var previousCalculation: TextView
    private lateinit var displayEditText: EditText

    private fun View.setOnClickListenerFor(@IdRes id: Int, onClickEvent: () -> Unit) =
        findViewById<Button>(id).setOnClickListener { onClickEvent() }

    private fun setClickListeners(v: View) {
        fun strFromRes(@IdRes id: Int) = resources.getString(id)

        fun updateText(@IdRes id: Int) {
            val stringToAdd: String = strFromRes(id)
            val oldText = displayEditText.text.toString()
            val cursorPosition = displayEditText.selectionStart
            displayEditText.setText(
                String.format(
                    "%s%s%s",
                    oldText.substring(0, cursorPosition),
                    stringToAdd,
                    oldText.substring(cursorPosition)
                )
            )
            displayEditText.setSelection(cursorPosition + stringToAdd.length)
        }

        with(v) {
            setOnClickListenerFor(R.id.decimalButton) { updateText(R.string.decimalText) }
            setOnClickListenerFor(R.id.button0) { updateText(R.string.zeroText) }
            setOnClickListenerFor(R.id.button1) { updateText(R.string.oneText) }
            setOnClickListenerFor(R.id.button2) { updateText(R.string.twoText) }
            setOnClickListenerFor(R.id.button3) { updateText(R.string.threeText) }
            setOnClickListenerFor(R.id.button4) { updateText(R.string.fourText) }
            setOnClickListenerFor(R.id.button5) { updateText(R.string.fiveText) }
            setOnClickListenerFor(R.id.button6) { updateText(R.string.sixText) }
            setOnClickListenerFor(R.id.button7) { updateText(R.string.sevenText) }
            setOnClickListenerFor(R.id.button8) { updateText(R.string.eightText) }
            setOnClickListenerFor(R.id.button9) { updateText(R.string.nineText) }
            setOnClickListenerFor(R.id.openParenthesesButton) { updateText(R.string.parenthesesOpenText) }
            setOnClickListenerFor(R.id.closeParenthesesButton) { updateText(R.string.parenthesesCloseText) }

            setOnClickListenerFor(R.id.divideButton) { updateText(R.string.divideText) }
            setOnClickListenerFor(R.id.multiplyButton) { updateText(R.string.multiplyText) }
            setOnClickListenerFor(R.id.addButton) { updateText(R.string.addText) }
            setOnClickListenerFor(R.id.subtractButton) { updateText(R.string.subtractText) }

            setOnClickListenerFor(R.id.equalsButton) {
                previousCalculation.text = displayEditText.text
                val expression = Expression(
                    displayEditText.text.toString()
                        .replace(strFromRes(R.string.multiplyText), "*")
                        .replace(strFromRes(R.string.divideText), "/")
                )

                displayEditText.setText(expression.calculate().toString())
                displayEditText.setSelection(displayEditText.text.length)
            }

            setOnClickListenerFor(R.id.clearButton) {

                displayEditText.setText("")
                previousCalculation.text = ""
            }
            findViewById<ImageButton>(R.id.backspaceButton).setOnClickListener {
                val cursorPosition = displayEditText.selectionStart
                if (cursorPosition == 0) return@setOnClickListener
                displayEditText.setText(
                    displayEditText.text.toString().removeRange(cursorPosition - 1, cursorPosition)
                )
                displayEditText.setSelection(cursorPosition - 1)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layoutConstantObject = LayoutConstant.newInstance(layoutConstant)
        return inflater.inflate(when(layoutConstantObject) {
            LayoutConstant.LINEAR_LAYOUT_CONSTANT -> R.layout.fragment_calculator_linear
            LayoutConstant.RELATIVE_LAYOUT_CONSTANT -> R.layout.fragment_calculator_relative
            LayoutConstant.GRID_LAYOUT_CONSTANT -> R.layout.fragment_calculator_grid
            LayoutConstant.CONSTRAINT_LAYOUT_CONSTANT -> R.layout.fragment_calculator_constraint
                                                     }, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        previousCalculation = view.findViewById(R.id.previousCalculationView)
        displayEditText = view.findViewById(R.id.displayEditText)

        displayEditText.showSoftInputOnFocus = false
        setClickListeners(view)

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