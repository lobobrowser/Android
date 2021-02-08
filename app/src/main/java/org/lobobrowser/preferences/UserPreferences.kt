package org.lobobrowser.preferences

import org.lobobrowser.preferences.delegates.*
import android.content.SharedPreferences
import org.lobobrowser.AppTheme
import org.lobobrowser.device.ScreenSize
import org.lobobrowser.di.UserPrefs
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferences @Inject constructor(
    @UserPrefs preferences: SharedPreferences,
    screenSize: ScreenSize
) {
    var useTheme by preferences.enumPreference(THEME, AppTheme.DARK)
}

private const val THEME = "Theme"

