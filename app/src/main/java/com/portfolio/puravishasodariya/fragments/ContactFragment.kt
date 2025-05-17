package com.portfolio.puravishasodariya.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import com.portfolio.puravishasodariya.R

class ContactFragment : Fragment() {
    private lateinit var nameInput: TextInputLayout
    private lateinit var emailInput: TextInputLayout
    private lateinit var messageInput: TextInputLayout
    private lateinit var sendButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameInput = view.findViewById(R.id.nameInputLayout)
        emailInput = view.findViewById(R.id.emailInputLayout)
        messageInput = view.findViewById(R.id.messageInputLayout)
        sendButton = view.findViewById(R.id.btnSendMessage)

        sendButton.setOnClickListener {
            if (validateInputs()) {
                sendWhatsAppMessage(nameInput, emailInput, messageInput)
                // In a real app, you would send the message to a backend
                Toast.makeText(requireContext(), "Message sent successfully!", Toast.LENGTH_SHORT).show()
                clearInputs()
            }
        }

        setupSocialButtons(view)
    }




    private fun validateInputs(): Boolean {
        var isValid = true

        val name = nameInput.editText?.text.toString().trim()
        val email = emailInput.editText?.text.toString().trim()
        val message = messageInput.editText?.text.toString().trim()

        if (name.isEmpty()) {
            nameInput.error = "Name is required"
            isValid = false
        } else {
            nameInput.error = null
        }

        if (email.isEmpty()) {
            emailInput.error = "Email is required"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput.error = "Please enter a valid email"
            isValid = false
        } else {
            emailInput.error = null
        }

        if (message.isEmpty()) {
            messageInput.error = "Message is required"
            isValid = false
        } else {
            messageInput.error = null
        }

        return isValid
    }


    private fun sendWhatsAppMessage(
        nameInput: TextInputLayout,
        emailInput: TextInputLayout,
        messageInput: TextInputLayout
    ) {
        val name = nameInput.editText?.text.toString().trim()
        val email = emailInput.editText?.text.toString().trim()
        val message = messageInput.editText?.text.toString().trim()

        val formattedMessage = """
        Name: $name
        Email: $email
        Message: $message
    """.trimIndent()

        val phoneNumber = "9687543423" // Replace with your number, no '+' sign

        try {
            val uri = Uri.parse("https://wa.me/$phoneNumber?text=${Uri.encode(formattedMessage)}")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.setPackage("com.whatsapp")
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "WhatsApp not installed.", Toast.LENGTH_SHORT).show()
        }
    }





    private fun clearInputs() {
        nameInput.editText?.text?.clear()
        emailInput.editText?.text?.clear()
        messageInput.editText?.text?.clear()
    }

    private fun setupSocialButtons(view: View) {
        view.findViewById<ImageButton>(R.id.btnLinkedin).setOnClickListener {
            openUrl("https://www.linkedin.com/in/puravish-asodariya-800161283/?originalSubdomain=in")
        }

        view.findViewById<ImageButton>(R.id.btnGithub).setOnClickListener {
            openUrl("https://github.com/Puravish08")
        }

        view.findViewById<ImageButton>(R.id.btnTwitter).setOnClickListener {
            openUrl("https://x.com/Puravish?t=yc4YelEMwz7IP7fp-5v66g&s=09")
        }

        view.findViewById<ImageButton>(R.id.btnInstagram).setOnClickListener {
            openUrl("https://www.instagram.com/puravish08/?igsh=emp4ODcydXJtZW4%3D#")
        }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}
