package com.alw.trade.Modal

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class DataToken(
    @SerializedName("id")
    @Expose
    val userId: String? = null,

    @SerializedName("name")
    @Expose
    val userFullName: String? = null,

    @SerializedName("symbol")
    @Expose
    val symbol: String? = null,

    @SerializedName("slug")
    @Expose
    val slug: String? = null,
)

