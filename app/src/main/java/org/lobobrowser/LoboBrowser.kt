package org.lobobrowser

import android.app.Application;
import android.content.Context
import android.os.Build
import android.os.StrictMode
import android.webkit.WebView
import kotlin.system.exitProcess


class LoboBrowser : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build())

            StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build())
        }

        if (Build.VERSION.SDK_INT >= 28) {
            if (getProcessName() == "$packageName:incognito") {
                WebView.setDataDirectorySuffix("incognito")
            }
        }

        val defaultHandler = Thread.getDefaultUncaughtExceptionHandler()

        Thread.setDefaultUncaughtExceptionHandler { thread, ex ->
            if (BuildConfig.DEBUG) {

            }

            if (defaultHandler != null) {
                defaultHandler.uncaughtException(thread, ex)
            } else {
                exitProcess(2)
            }
        }


    }

}