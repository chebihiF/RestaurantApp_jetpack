package org.doronco.restaurantapp

import retrofit2.http.GET

interface PatientApiService {
    @GET("patients?page=0&size=50")
    suspend fun getPatients() : List<Patient>
}