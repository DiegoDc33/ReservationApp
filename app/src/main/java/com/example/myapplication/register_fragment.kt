package com.example.myapplication

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_login.btnCreate as btnCreate1
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_login.editEmail as editEmail1
import kotlinx.android.synthetic.main.fragment_register.editPassword as editPassword1
import com.google.android.gms.tasks.OnSuccessListener
import android.content.Intent
import com.google.firebase.auth.AuthResult
import android.util.Log
import android.widget.Toast

/**
 * A simple [Fragment] subclass.
 */
class register_fragment: Fragment() {
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()


    }

 private fun signUpUser() {

     if (editEmail.text.toString().isEmpty()) {
         editEmail.error = "Per favore inserisci email"
         editEmail.requestFocus()
         return
     }
     if (!Patterns.EMAIL_ADDRESS.matcher(editEmail.text.toString()).matches()) {
         editEmail.error = "Per favore inserisci email valida"
         editEmail.requestFocus()
         return
     }
     if (editPassword.text.toString().isEmpty()) {
         editPassword.error = "Per favore inserisci password"
         editPassword.requestFocus()
         return
     }


    auth.createUserWithEmailAndPassword(editEmail.text.toString(), editPassword.text.toString())

        .addOnCompleteListener {

            if (it.isSuccessful) { // it rappresenta il task avviato

                Navigation.findNavController(requireActivity(), R.id.NavHost)

                    .navigate(R.id.action_register_fragment_to_login_fragment)

            } else {

                Toast.makeText(

                    requireContext(), "Authentication failed.",

                    Toast.LENGTH_SHORT

                ).show()

            }

        }


}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnFreccia.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_register_fragment_to_login_fragment)


        }
        btnCreate.setOnClickListener {
            signUpUser()
        }
    }

}


