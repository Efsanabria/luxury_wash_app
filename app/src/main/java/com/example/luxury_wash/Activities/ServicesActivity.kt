package com.example.luxury_wash.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.luxury_wash.R
import com.example.luxury_wash.adapters.AdapterService
import com.example.luxury_wash.models.ItemService
import com.google.android.material.bottomnavigation.BottomNavigationView

class ServicesActivity : AppCompatActivity() {

    private lateinit var rv: RecyclerView
    private lateinit var adapter: AdapterService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_services)

        rv = findViewById(R.id.rv_Services)
        rv.layoutManager = LinearLayoutManager(this)

        adapter = AdapterService { service ->
            // Aquí luego haces algo cuando hagan clic en un servicio
        }
        rv.adapter = adapter

        val data = listOf(
            ItemService(
                title = "LAVADO DE AUTOS",
                subtitle = "Todo tipo de Autos",
                desc = "Lavado sencillo, detallado, premium y full",
                priceLabel = "Desde $30.000",
                imageRes = R.drawable.car_wash
            ),
            ItemService(
                title = "LAVADO DE MOTOS",
                subtitle = "Todo tipo de Motos",
                desc = "Lavado sencillo, detallado, premium y full",
                priceLabel = "Desde $20.000",
                imageRes = R.drawable.moto_wash
            ),
            ItemService(
                title = "LAVADO DE CAMIONES",
                subtitle = "Todo tipo de Camiones",
                desc = "Lavado sencillo, detallado, premium y full",
                priceLabel = "Desde $50.000",
                imageRes = R.drawable.truck_wash
            )
        )
        adapter.submit(data)

        val bottom = findViewById<BottomNavigationView>(R.id.nav_bottom)
        bottom.selectedItemId = R.id.nav_services
        bottom.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home     -> true
                R.id.nav_services -> true
                R.id.nav_car      -> true
                R.id.nav_profile  -> true
                else -> false
            }
        }
    }
}