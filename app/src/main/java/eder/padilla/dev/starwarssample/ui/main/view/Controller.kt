package eder.padilla.dev.starwarssample.ui.main.view

import android.app.Activity
import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher
import android.view.animation.Animation
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import eder.padilla.dev.starwarssample.StarWarsApplication
import urevent.uble.mx.starwarssample.R

object Controller {

    fun validateId(animation : Animation,etId : EditText, textInputLayout : TextInputLayout, activity : Activity){
        etId.addTextChangedListener(object : TextWatcher {
            private var finalText: String? = null
            private var t: Thread? = null
            private val runnable_EditTextWatcher = object : Runnable {
                override fun run() {
                    try {
                        synchronized(this) {
                            (this as java.lang.Object).wait(1200)
                            activity.runOnUiThread {
                                if (etId.text.isEmpty()) {
                                    invalidId(etId,textInputLayout,animation)
                                } else {
                                    etId.setError(null)
                                }
                                t = null
                            }

                        }
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

                }

            }

            override fun onTextChanged(ss: CharSequence, start: Int, before: Int, count: Int) {
                finalText = ss.toString()
                textInputLayout.setError(null)
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(ss: Editable) {
                if (t == null) {
                    t = Thread(runnable_EditTextWatcher)
                    t!!.start()
                }
            }
        })
    }

    fun invalidId(etId : EditText, textInputLayout : TextInputLayout,shake: Animation) {
        etId.startAnimation(shake)
        textInputLayout.error = StarWarsApplication.context!!.getString(R.string.invalid_id)
    }

}