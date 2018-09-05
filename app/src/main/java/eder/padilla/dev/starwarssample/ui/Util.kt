package eder.padilla.dev.starwarssample.ui

import android.util.Base64
import eder.padilla.dev.starwarssample.StarWarsApplication
import java.io.IOException
import java.nio.charset.StandardCharsets
import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager


object Util {

    fun Z2V0QmFzZVVybA(): String {
        var text = ""
        try {
            val stream = StarWarsApplication.context!!.getAssets().open("file.txt")
            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            text = String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val decodeBytes = Base64.decode(text, Base64.URL_SAFE)
        return String(decodeBytes, StandardCharsets.UTF_8)
    }

    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm!!.hideSoftInputFromWindow(view!!.windowToken, 0)
    }
}