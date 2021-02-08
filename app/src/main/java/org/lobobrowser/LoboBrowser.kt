package org.lobobrowser

import android.app.Application;
import android.content.Context
import android.os.Build
import android.os.StrictMode
import android.webkit.WebView
import androidx.appcompat.app.AppCompatDelegate
import org.lobobrowser.device.BuildInfo
import org.lobobrowser.device.BuildType
import org.lobobrowser.di.AppComponent
import org.lobobrowser.di.DaggerAppComponent
import org.lobobrowser.di.injector
import javax.inject.Inject
import kotlin.system.exitProcess

class LoboBrowser : Application() {

    @Inject internal lateinit var buildInfo: BuildInfo

    lateinit var applicationComponent: AppComponent

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

        applicationComponent = DaggerAppComponent.builder()
            .application(this)
            .buildInfo(createBuildInfo())
            .build()
        injector.inject(this)

        if (!isRelease()) {
            WebView.setWebContentsDebuggingEnabled(true)
        }

    }

    private fun createBuildInfo() = BuildInfo(when {
        BuildConfig.DEBUG -> BuildType.DEBUG
        BuildConfig.FLAVOR == "alpha" -> BuildType.ALPHA
        BuildConfig.FLAVOR == "beta" -> BuildType.BETA
        else -> BuildType.RELEASE
    })

    fun isRelease(): Boolean {
        return buildInfo.buildType == BuildType.RELEASE
    }

    companion object {
        private const val TAG = "LoboBrowser"

        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT)
        }
    }
}