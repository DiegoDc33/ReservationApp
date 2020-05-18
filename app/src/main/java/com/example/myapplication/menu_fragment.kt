package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.datamodel.Database
import kotlinx.android.synthetic.main.fragment_menu.*
import kotlinx.android.synthetic.main.riga_prodotto.*

/**
 * A simple [Fragment] subclass.
 */
class menu_fragment : Fragment() {
    private lateinit var adapter: ProdottiAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ListaProdotti.layoutManager = LinearLayoutManager(activity)
        //associo l adapter alla recyclerview
        adapter = ProdottiAdapter(Database.getElencoProdotti(),requireContext())
        ListaProdotti.adapter= adapter

        Database.notificaLetturaProdotti {
            var Prodotti = Database.getElencoProdotti()
            adapter.datiAggiornati(Prodotti)
        }





    }






}
