package company.surious.coronavirusobserver.data.network.models

import com.google.gson.annotations.SerializedName

data class StatusNetworkModel(
    @SerializedName("Last_Update")
    var lastUpdateMillis: Long = -1L,
    @SerializedName("Country_Region")
    var countryRegion: String = "",
    @SerializedName("Confirmed")
    var confirmed: Int = 0,
    @SerializedName("Deaths")
    var deaths: Int = 0,
    @SerializedName("Recovered")
    var recovered: Int = 0
)