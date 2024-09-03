package com.example.api_application.domains

import android.content.Context
import android.util.Log
import com.android.volley.Request.Method
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.api_application.interfaces.Network_State
import com.example.api_application.models.PostModel

class ApiService {
    private val postList = mutableListOf<PostModel>()
    private var url = "https://jsonplaceholder.typicode.com/posts"
    fun postApi(context: Context, networkState: Network_State) {
        networkState.loadingData(postList)
        val volley = Volley.newRequestQueue(context)
        val jsonArrayRequest = JsonArrayRequest(Method.GET, url, null,
            {
                for (i in 0..<it.length()) {
                    val jsnObject = it.getJSONObject(i)
                    val userId = jsnObject.getInt("userId")
                    val id = jsnObject.getInt("id")
                    val title = jsnObject.getString("title")
                    val body = jsnObject.getString("body")

                    val postModel = PostModel(userId = userId, id = id, title = title, body = body)
                    postList.add(postModel)
                }
                networkState.loadedData(postList)
                Log.d("TAG", "apiCalling: ===$postList")
            }, {
                networkState.error(it.message!!)
                Log.e("TAG", "apiCalling: ${it.message}")
            })
        volley.add(jsonArrayRequest)
    }
}