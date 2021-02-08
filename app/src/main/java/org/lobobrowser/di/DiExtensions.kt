@file:JvmName("Injector")

package org.lobobrowser.di

import org.lobobrowser.LoboBrowser
import android.content.Context
import androidx.fragment.app.Fragment

/**
 * The [AppComponent] attached to the application [Context]
 */
val Context.injector: AppComponent
    get() = (applicationContext as LoboBrowser).applicationComponent

/**
 * The [AppComponent] attached to the context, note that the fragment must be attached
 */
val Fragment.injector: AppComponent
    get() = (context!!.applicationContext as LoboBrowser).applicationComponent