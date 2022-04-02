package pt.penguin.datasource.model.details


import com.google.gson.annotations.SerializedName

data class AcquisitionApiModel(
    @SerializedName("creditLine")
    val creditLine: String?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("method")
    val method: String?
)