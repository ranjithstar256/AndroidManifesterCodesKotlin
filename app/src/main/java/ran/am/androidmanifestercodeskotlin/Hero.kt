package ran.am.androidmanifestercodeskotlin

import com.google.gson.annotations.SerializedName

class Hero(
    @field:SerializedName("name") val name: String,
    val realname: String,
    val team: String,
    val firstappearance: String,
    val createdby: String,
    val publisher: String,
    val imageurl: String,
    val bio: String
)