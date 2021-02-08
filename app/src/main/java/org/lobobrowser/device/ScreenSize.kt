package org.lobobrowser.device

import android.content.Context
import android.content.res.Configuration
import dagger.Reusable
import javax.inject.Inject

/**
 * A model used to determine the screen size info
 */
@Reusable
class ScreenSize @Inject constructor(private val context: Context) {

    fun isTablet(): Boolean =
        context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_XLARGE

}