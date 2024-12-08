package com.example.movie_data.data.model

import com.example.movie_data.data.model.Cast
import com.google.gson.annotations.SerializedName

data class MovieCastResponse(
    @SerializedName("id")
    val id:Int=0,
    @SerializedName("cast")
    val castList:List<Cast>
)
