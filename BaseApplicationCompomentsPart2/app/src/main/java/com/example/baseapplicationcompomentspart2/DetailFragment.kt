package com.example.baseapplicationcompomentspart2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

private const val ARG_NAME = "param1"
private const val ARG_ORGANIZATION = "param2"
private const val ARG_EMAIL = "param3"
private const val ARG_PHONENUMBER = "param4"


class DetailFragment : Fragment() {

    private var name: String? = null
    private var organization: String? = null
    private var email: String? = null
    private var phoneNumber: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(ARG_NAME)
            organization = it.getString(ARG_ORGANIZATION)
            email = it.getString(ARG_EMAIL)
            phoneNumber = it.getString(ARG_PHONENUMBER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.textViewName).apply {
            text = name
        }
        view.findViewById<TextView>(R.id.textViewOrganization).apply {
            text = organization
        }
        view.findViewById<TextView>(R.id.textViewEmail).apply {
            text = email
        }
        view.findViewById<TextView>(R.id.textViewPhoneNumber).apply {
            text = phoneNumber
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(name: String, organization: String, email: String, phoneNumber: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_NAME, name)
                    putString(ARG_ORGANIZATION, organization)
                    putString(ARG_EMAIL, email)
                    putString(ARG_PHONENUMBER, phoneNumber)
                }
            }
    }
}