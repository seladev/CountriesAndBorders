package com.sela.countries.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.sela.countries.utils.logView

/**
 * BaseActivity - for all activities in app
 */
abstract class BaseActivity :AppCompatActivity() {

    abstract var resourceLayout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(resourceLayout)

    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        logView("onCreate")
    }

    override fun onResume() {
        super.onResume()
        logView("onResume")
    }

    override fun onPause() {
        super.onPause()
        logView("onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        logView("onDestroy")
    }

}