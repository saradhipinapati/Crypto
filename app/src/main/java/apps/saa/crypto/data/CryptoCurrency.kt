package apps.saa.crypto.data

import com.google.gson.annotations.SerializedName

data class CryptoCurrency(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("status")
    val status: Status
)