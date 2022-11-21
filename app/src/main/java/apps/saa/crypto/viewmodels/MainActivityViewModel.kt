package apps.saa.crypto.viewmodels

import androidx.lifecycle.*
import apps.saa.crypto.data.CryptoCurrency
import apps.saa.crypto.data.Data
import apps.saa.crypto.db.CryptoDatabase
import apps.saa.crypto.network.CryptoCurrencyService
import apps.saa.crypto.network.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel(val cryptoDatabase: CryptoDatabase): ViewModel() {

    private var _cryptoCoinsData = MutableLiveData<List<Data>>()
    val cryptoCoinsData: LiveData<List<Data>>
    get() = _cryptoCoinsData

    init {
        getLatestListings()
    }

    fun getLatestListings() {
        val retroService = RetrofitInstance.getRetroInstance().create(CryptoCurrencyService::class.java)
        val call = retroService.getLatestListings()
        call.enqueue(object : Callback<CryptoCurrency> {
            override fun onResponse(call: Call<CryptoCurrency>, response: Response<CryptoCurrency>) {
                if(response.isSuccessful) {
                    val body = response.body()
                    _cryptoCoinsData.value = body?.data
                    viewModelScope.launch {
                        cryptoDatabase.cryptoDao().insertAll(body!!.data)
                    }

                }
            }

            override fun onFailure(call: Call<CryptoCurrency>, t: Throwable) {

            }

        })

    }

    class MainActivityViewModelFactory(private val cryptoDatabase: CryptoDatabase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainActivityViewModel(cryptoDatabase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}