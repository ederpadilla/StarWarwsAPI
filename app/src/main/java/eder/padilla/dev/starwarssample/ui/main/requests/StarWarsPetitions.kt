package eder.padilla.dev.starwarssample.ui.main.requests

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import eder.padilla.dev.starwarssample.client.ServiceGenerator
import eder.padilla.dev.starwarssample.ui.main.model.People
import eder.padilla.dev.starwarssample.ui.main.requests.StarWarsCallbacks
import java.io.IOException

class StarWarsPetitions {

    var starWarsCallbacks : StarWarsCallbacks

    constructor(starWarsCallbacks: StarWarsCallbacks) {
        this.starWarsCallbacks = starWarsCallbacks
    }

    fun getPeople(id : String){
        ServiceGenerator.service.getPeople(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({people -> onSuccess(people) },{error -> onErrorPeople(error)}
                        ,{onComplete()})
    }

    private fun onErrorPeople(error: Throwable?) {
        if (error is IOException) {
            starWarsCallbacks.noInternetConnection()
        } else {
            starWarsCallbacks.failedGetPeople(error!!)
        }
    }

    private fun onSuccess(people: Response<People>?) {
        starWarsCallbacks.successGetPeople(people!!)
    }

    private fun onComplete() {
        starWarsCallbacks.completePeopleTask()
    }
}