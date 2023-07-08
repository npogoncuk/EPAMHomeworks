package com.example.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.DialogFragment
import com.example.fragments.databinding.FragmentAlertBinding
import com.google.android.material.textfield.TextInputEditText

class AddTextAlertDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentAlertBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentAlertBinding.inflate(layoutInflater)

        val dialog = AlertDialog.Builder(requireContext())
            .setCancelable(false)
            .setTitle("Add item")
            .setMessage("Add the message")
            .setView(R.layout.fragment_alert)
            .setPositiveButton("Add", null)
            .setNegativeButton("Cancel", null)
            .create()


        return dialog
    }

    override fun onStart() {
        super.onStart()
        dialog?.setCancelable(false)
        val positiveButton = (dialog as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE)

        val editText = (dialog as AlertDialog).findViewById<TextInputEditText>(R.id.textInputEditText)
        positiveButton.setOnClickListener {
            (parentFragmentManager.findFragmentById(R.id.ViewFragmentController) as TextReceiverFromDialog)
                .receiveText(editText.text.toString())
        }
        // binding.textInputEditText.
        // isn't working via binging, who the hell knows why
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                val string = p0.toString()
                positiveButton.isEnabled = string.length > 3
            }

        })
    }

    companion object {
        @JvmStatic
        val TAG = this::class.simpleName!!
    }

    interface TextReceiverFromDialog {
        fun receiveText(text: String)
    }
}