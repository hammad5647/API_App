package com.example.api_application.interfaces

import com.example.api_application.models.PostModel

interface Network_State {
    fun loadingData(postList: MutableList<PostModel>)

    fun loadedData(postList: MutableList<PostModel>)

    fun error(message: String)
}