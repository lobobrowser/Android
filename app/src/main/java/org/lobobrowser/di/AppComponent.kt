package org.lobobrowser.di

import org.lobobrowser.LoboBrowser
import org.lobobrowser.browser.activity.ThemableBrowserActivity
import org.lobobrowser.view.LoboView
import android.app.Application
import dagger.BindsInstance
import dagger.Component
import org.lobobrowser.device.BuildInfo
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun buildInfo(buildInfo: BuildInfo): Builder

        fun build(): AppComponent
    }

    fun inject(activity: ThemableBrowserActivity)

    fun inject(app: LoboBrowser)

    fun inject(loboView: LoboView)
}