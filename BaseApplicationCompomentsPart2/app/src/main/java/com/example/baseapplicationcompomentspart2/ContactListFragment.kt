package com.example.baseapplicationcompomentspart2

import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.baseapplicationcompomentspart2.databinding.FragmentContactListBinding
import com.example.baseapplicationcompomentspart2.model.Contact


class ContactListFragment : Fragment() {

    private var _binding: FragmentContactListBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (haveReadContactsPermission(requireActivity())) {
            fun navigateToDetailsFragment(name: String, organization: String, email: String, phoneNumber: String) {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, DetailFragment.newInstance(name, organization, email, phoneNumber))
                    .addToBackStack(null)
                    .commit()
            }
            binding.recyclerView.adapter = ContactListAdapter(getContacts(), ::navigateToDetailsFragment)
        } else {
            Toast.makeText(requireContext(), "You didn't grand the permission", Toast.LENGTH_SHORT).show()
        }

    }

    private fun getContacts(): List<Contact> {
        val cr = requireActivity().contentResolver
        val PROJECTION = arrayOf(
            ContactsContract.RawContacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Organization.COMPANY,
            ContactsContract.CommonDataKinds.Email.DATA
        )
        val cursor: Cursor? = cr.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            PROJECTION,
            null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        )
        cursor ?: return emptyList()

        val contacts = mutableListOf<Contact>()

        try {
            val nameIndex: Int = cursor.getColumnIndex(PROJECTION[1])
            val numberIndex: Int = cursor.getColumnIndex(PROJECTION[2])
            val organizationIndex: Int = cursor.getColumnIndex(PROJECTION[3])
            val emailIndex: Int = cursor.getColumnIndex(PROJECTION[4])
            var name: String
            var number: String
            var organization: String
            var email: String
            while (cursor.moveToNext()) {
                name = cursor.getString(nameIndex)
                number = cursor.getString(numberIndex).apply { replace(" ", "") }
                organization = cursor.getString(organizationIndex)
                email = cursor.getString(emailIndex)
                if (!contacts.any { it.phoneNumber == number }) contacts += Contact(
                    name,
                    organization,
                    email,
                    number
                )
            }
        } finally {
            cursor.close()
        }
        return contacts
    }

    companion object {
        @JvmStatic
        fun newInstance() = ContactListFragment()
    }
}