package com.example.api_application

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.transition.Visibility
import com.example.api_application.adapter.DataAdapter
import com.example.api_application.databinding.ActivityMainBinding
import com.example.api_application.domains.ApiService
import com.example.api_application.interfaces.Network_State
import com.example.api_application.models.PostModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var list: MutableList<PostModel>
    private var adapter: DataAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val netState: Network_State = object : Network_State {
            override fun loadingData(postList: MutableList<PostModel>) {
                Log.d("TAG", "apiCalling: ===$postList")
            }

            override fun loadedData(postList: MutableList<PostModel>) {
                binding.processView.visibility = View.GONE
                list = postList
                adapter = DataAdapter(list)
                binding.recyclerView.adapter = adapter
                Log.d("TAG", "apiCalling: ===$postList")
            }

            override fun error(message: String) {
                Log.d("TAG", "apiCalling: ===${error("error")}")
            }
        }

        val service = ApiService()
        service.postApi(this, netState)
    }
}