package com.voidx.hearthstone.util

import android.util.Log
import okhttp3.mockwebserver.MockWebServer

fun MockWebServer.start8080() {
    try {
        start(8080)
    } catch (e: Exception) {
        Log.e("MockWebServer", e.message, e)
        e.printStackTrace()
    }
}