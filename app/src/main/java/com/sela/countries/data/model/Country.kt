package com.sela.countries.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.sela.countries.data.db.Converters

/**
 * Country object
 */
@Entity(tableName = "DatabaseCountry")
data class Country(
    @SerializedName("name")
    val name : String = "",

    @SerializedName("nativeName")
    val nativeName : String,

    @SerializedName("area")
    val area : Double = 0.0,

    @SerializedName("alpha3Code")
    @PrimaryKey
    val alpha3Code : String = "",

    @SerializedName("borders")
    @TypeConverters(Converters::class)
    var borders : List<String> = emptyList()
)
{

}

