package eder.padilla.dev.starwarssample.client

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import eder.padilla.dev.starwarssample.ui.main.model.People

interface Client {

    @GET("people/{id}/")
    fun getPeople(@Path("id") id: String): Observable<Response<People>>

}
