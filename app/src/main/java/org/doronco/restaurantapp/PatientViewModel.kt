package org.doronco.restaurantapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PatientViewModel() : ViewModel() {
    val state = mutableStateOf(emptyList<Patient>())
    private var restInterface: PatientApiService

    init {
        val client = OkHttpClient
            .Builder()
            .addInterceptor(BasicAuthInterceptor("admin", "admin"))
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .client(client)
            .baseUrl(
                "http://192.168.1.4:8080/api/"
            )
            .build()


        restInterface = retrofit.create(
            PatientApiService::class.java
        )
        getPatients()
    }

    private fun getPatients() {
        viewModelScope.launch() {
            val patients = getAllPatients()
            state.value = patients
        }
    }

    private suspend fun getAllPatients(): List<Patient> {
        return withContext(Dispatchers.IO) {
            restInterface.getPatients()
        }
    }
}