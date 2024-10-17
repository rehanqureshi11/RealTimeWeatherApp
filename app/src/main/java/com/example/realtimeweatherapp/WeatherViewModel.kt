package com.example.realtimeweatherapp


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realtimeweatherapp.api.Constent
import com.example.realtimeweatherapp.api.NetworkResponse
import com.example.realtimeweatherapp.api.Retrofitinstance
import com.example.realtimeweatherapp.api.Weathermodel
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val weatherApi = Retrofitinstance.weatherApi
    private val _weatherResult = MutableLiveData<NetworkResponse<Weathermodel>>()
 val weatherResult : LiveData<NetworkResponse<Weathermodel>> = _weatherResult


    fun getData(city : String) {
        _weatherResult.postValue(NetworkResponse.Loading)
        viewModelScope.launch {
            try {
                val response =    weatherApi.getWeather(Constent.apiKey,city)
                if (response.isSuccessful){
                    response.body()?.let {
                        _weatherResult.postValue(NetworkResponse.Success(it))
                    }
                    //  Log.i("Response",response.body().toString())
                }else
                {
                    _weatherResult.postValue(NetworkResponse.Error("Failed to load data"))
                    // Log.i("Response",response.errorBody().toString())
                }
            }
            catch (e : Exception){
                _weatherResult.postValue(NetworkResponse.Error("Failed to load data"))
            }

        }


    }
}