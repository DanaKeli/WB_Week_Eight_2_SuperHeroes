package com.example.wb_week_five_2.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Heroes(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("powerstats")
    val powerStats: PowerStats,

    @SerializedName("biography")
    val biography: Biography,

    @SerializedName("appearance")
    val appearance: Appearance,

    @SerializedName("images")
    val image: Image
) : Parcelable

@Parcelize
data class Appearance(
    @SerializedName("gender")
    val gender: String,

    @SerializedName("race")
    val race: String
) : Parcelable

@Parcelize
data class PowerStats(
    @SerializedName("intelligence")
    val intelligence: String,

    @SerializedName("strength")
    val strength: String,

    @SerializedName("speed")
    val speed: String,

    @SerializedName("durability")
    val durability: String,

    @SerializedName("power")
    val power: String,

    @SerializedName("combat")
    val combat: String
) : Parcelable

@Parcelize
data class Image(
    @SerializedName("sm")
    val url: String
) : Parcelable

@Parcelize
data class Biography(
    @SerializedName("full-name")
    val fullName: String,

    @SerializedName("place-of-birth")
    val placeOfBirth: String
) : Parcelable





