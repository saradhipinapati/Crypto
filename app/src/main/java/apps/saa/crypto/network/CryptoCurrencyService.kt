package apps.saa.crypto.network

import apps.saa.crypto.data.CryptoCurrency
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface CryptoCurrencyService {

    @Headers("X-CMC_PRO_API_KEY:adfd8138-492f-4b9c-85aa-8674c13cb0fc")
    @GET("v1/cryptocurrency/listings/latest")
    fun getLatestListings(): Call<CryptoCurrency>
}