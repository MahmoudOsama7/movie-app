package com.example.home.data.model

import com.google.gson.annotations.SerializedName

data class MovieCastResponse(
    @SerializedName("id")
    val id:Int=0,
    @SerializedName("cast")
    val castList:List<Cast>
)
