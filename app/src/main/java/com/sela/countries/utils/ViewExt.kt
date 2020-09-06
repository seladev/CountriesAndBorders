package com.sela.countries.utils

import android.view.View

/**
 * Created by seladev
 */
fun View.show(){
    this.visibility = View.VISIBLE
}

fun View.hide(){
    this.visibility = View.GONE
}

fun View.invisible(){
    this.visibility = View.INVISIBLE
}

fun View.setVisible(visible:Boolean){
    if(visible) show() else hide()
}