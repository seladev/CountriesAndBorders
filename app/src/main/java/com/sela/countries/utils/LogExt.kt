package com.sela.countries.utils

import android.util.Log

/**
 * Log Extension
 */

fun Any.logDebug(message:String){
    Log.d(this::class.simpleName, message)
}

fun Any.logError(message:String){
    Log.e(this::class.simpleName, message)
}

fun Any.logView(message:String){
    Log.d(this::class.simpleName, " ~~~~~~~~~~~~~~~~ $message  ~~~~~~~~~~~~~~~~")
}

fun Any.logDB(message:String){
    Log.d(this::class.simpleName, " DB ====== $message ======")
}