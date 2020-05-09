package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.btnCreate
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_login.editEmail as editEmail1
import kotlinx.android.synthetic.main.fragment_login.editPassword as editPassword1

/**
 * A simple [Fragment] subclass.
 */
class login_fragment : Fragment() {
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }



    private fun doLogin() {

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

        auth.signInWithEmailAndPassword(editEmail.toString(), editPassword.toString())
            .addOnCompleteListener {

                if (it.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {

                    updateUI(null)
                }

            }
    }





    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser:FirebaseUser?) {
        if(currentUser !=null){
            Toast.makeText(requireContext(), "PROVA SENZA REINDIRIZZAMENTO",
                Toast.LENGTH_SHORT).show()
           /* Navigation.findNavController(requireActivity(), R.id.NavHost)

                .navigate(R.id.action_login_fragment_to_menu_fragment)*/
        }else{
            Toast.makeText(requireContext(), "Authentication failed.",
                Toast.LENGTH_SHORT).show()
        }
    }







    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnCreate.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_login_fragment_to_register_fragment)
        }
        btnLogin.setOnClickListener {
            doLogin()
        }

    }
}



