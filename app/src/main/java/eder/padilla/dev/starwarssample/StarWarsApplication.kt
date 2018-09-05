package eder.padilla.dev.starwarssample

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class StarWarsApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context?=null
    }

}
