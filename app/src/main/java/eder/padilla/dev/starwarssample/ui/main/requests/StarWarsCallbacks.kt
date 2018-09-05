package eder.padilla.dev.starwarssample.ui.main.requests

import retrofit2.Response
import eder.padilla.dev.starwarssample.ui.main.model.People

interface StarWarsCallbacks {
    fun successGetPeople(people : Response<People>)
    fun failedGetPeople(t : Throwable)
    fun completePeopleTask()
    fun noInternetConnection()
}