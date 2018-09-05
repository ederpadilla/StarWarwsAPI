package eder.padilla.dev.starwarssample.ui.main.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response
import urevent.uble.mx.starwarssample.R
import eder.padilla.dev.starwarssample.ui.Util
import eder.padilla.dev.starwarssample.ui.main.model.People
import eder.padilla.dev.starwarssample.ui.main.requests.StarWarsCallbacks
import eder.padilla.dev.starwarssample.ui.main.requests.StarWarsPetitions


class MainActivity : AppCompatActivity() , StarWarsCallbacks {

    lateinit var starWarsPetitions: StarWarsPetitions

    private var shake: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        shake = AnimationUtils.loadAnimation(this, R.anim.shake)
        starWarsPetitions = StarWarsPetitions(this)
        Controller.validateId(shake!!,etId,textinputid,this)
        onDoneClicked()
    }

    private fun onDoneClicked() {
        etId.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                getPeople()
                true
            } else {
                false
            }
        }
    }

    fun getPeopleClick(view :View){
        getPeople()
    }

    private fun getPeople() : Boolean{
        Util.hideKeyboard(this)
        if (etId.text.toString().isEmpty()){
            Controller.invalidId(etId,textinputid,shake!!)
            return true
        }else{
            imageView.visibility = View.VISIBLE
            animationView.visibility = View.GONE
            textView.text = ""
            starWarsPetitions.getPeople(etId.text.toString())
            return false
        }
    }

    override fun successGetPeople(people: Response<People>) {
        if(people.code()==200) {
            val people = people.body() as People
            textView.text = people.toString()
        }else{
            imageView.visibility = View.GONE
            animationView.visibility = View.VISIBLE
            animationView.setAnimation("not_found.json")
        }
    }

    override fun failedGetPeople(t: Throwable) {
        Toast.makeText(this,t.message,Toast.LENGTH_SHORT).show()
    }

    override fun completePeopleTask() {

    }

    override fun noInternetConnection() {
        imageView.visibility = View.GONE
        animationView.visibility = View.VISIBLE
    }
}
