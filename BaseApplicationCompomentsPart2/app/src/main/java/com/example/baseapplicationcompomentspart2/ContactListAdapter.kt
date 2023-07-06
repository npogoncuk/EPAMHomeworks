package com.example.baseapplicationcompomentspart2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.baseapplicationcompomentspart2.databinding.ContactItemBinding
import com.example.baseapplicationcompomentspart2.model.Contact

class ContactListAdapter(
    private val contacts: List<Contact>
) : RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ContactItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ContactItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = contacts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contacts[position]
        val binding = holder.binding
        binding.textViewName.text = contact.name
        binding.textViewNumber.text = contact.phoneNumber
    }
}