package org.lobobrowser.view

import org.lobobrowser.R
import org.lobobrowser.di.injector
import org.lobobrowser.ssl.SslState
import org.lobobrowser.preferences.UserPreferences
import android.app.Activity
import android.net.http.SslCertificate
import android.view.*;
import androidx.collection.ArrayMap
import org.mozilla.geckoview.GeckoRuntime
import org.mozilla.geckoview.GeckoSession
import org.mozilla.geckoview.GeckoSessionSettings
import org.mozilla.geckoview.GeckoView
import java.util.logging.Logger
import javax.inject.Inject

class LoboView(
    private val activity: Activity,
    val isIncognito: Boolean,
    private val logger: Logger
) {

    /**
     * The unique ID of the view
     */
    val id = View.generateViewId()

    var isSecure: Boolean = false

    var geckoSession: GeckoSession? = null
        private set

    private var geckoRuntime: GeckoRuntime? = null

    /**
     * Gets the current GeckoView instance of the tab
     *
     * @return the GeckoView instance of the tab, which can be null
     */
    var geckoView: GeckoView? = null
        private set

    /**
     * Sets whether this tab was the result of a new intent sent to the browser.
     */
    var isNewTab: Boolean = false

    var isForegroundTab: Boolean = false
        set(isForeground) {
            field = isForeground
            if (isForeground) {
                geckoView?.let {

                }
            }
        }

    private val toggleDesktop = false

    /**
     * This method gets the additional headers that should be added
     * with each request the browser makes.
     *
     * @return a non null Map of Strings with the additional
     * request headers
     */
    internal val requestHeaders = ArrayMap<String, String>()

    @Inject internal lateinit var userPreferences: UserPreferences

    /**
     * This method determines whether the current tab is visible
     * or not.
     *
     * @return true if the GeckoView is non-null and visible,
     * false otherwise.
     */
    val isShown: Boolean
        get() = geckoView?.isShown == true

    // todo: figure out progress

    private val userAgent: String
        get() = geckoView?.session?.userAgent.toString() ?: ""


    init {
        activity.injector.inject(this)

        geckoSession = createGeckoSession()
        geckoRuntime = GeckoRuntime.create(activity)
        geckoSession!!.open(geckoRuntime!!)
    }

    private fun createGeckoSession(): GeckoSession {
        var builder = GeckoSessionSettings.Builder()
        builder.useMultiprocess(true)
        builder.usePrivateMode(this.isIncognito)
        builder.suspendMediaWhenInactive(true)
        return  GeckoSession(builder.build())
    }

    private fun applySettingsAndSetDelegates() {

    }
}