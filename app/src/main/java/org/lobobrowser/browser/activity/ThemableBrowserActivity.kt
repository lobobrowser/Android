package org.lobobrowser.browser.activity

import org.lobobrowser.AppTheme
import org.lobobrowser.R
import org.lobobrowser.di.injector
import org.lobobrowser.preferences.UserPreferences
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Menu
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.withStyledAttributes
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.iterator
import javax.inject.Inject

abstract class ThemableBrowserActivity : AppCompatActivity() {

    @Inject protected lateinit var userPreferences: UserPreferences

    private var themeId: AppTheme = AppTheme.DARK
    private var showTabsInDrawer: Boolean = false
    private var shouldRunOnResumeActions: Boolean = false

    /**
     * Override this to provide an alternate theme that should be set for every instance of this
     * activity regardless of the user's preference
     */
    @StyleRes
    protected open fun provideThemeOverride(): Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        themeId = userPreferences.useTheme

        // set the theme
        setTheme(provideThemeOverride() ?: when (userPreferences.useTheme) {
            AppTheme.LIGHT -> R.style.Theme_LightTheme
            AppTheme.DARK -> R.style.Theme_DarkTheme
        })
        super.onCreate(savedInstanceState)

    }


}