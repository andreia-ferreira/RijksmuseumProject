package pt.penguin.datasource.model.details


import com.google.gson.annotations.SerializedName

data class DatingApiModel(
    @SerializedName("period")
    val period: Int?,
    @SerializedName("presentingDate")
    val presentingDate: String?,
    @SerializedName("sortingDate")
    val sortingDate: Int?,
    @SerializedName("yearEarly")
    val yearEarly: Int?,
    @SerializedName("yearLate")
    val yearLate: Int?
)