package company.surious.coronavirusobserver.presentation.di

import android.app.Application
import company.surious.coronavirusobserver.data.network.RepositoriesModule
import company.surious.coronavirusobserver.data.resources.ResourcesModule
import company.surious.coronavirusobserver.presentation.core.CoronavirusApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AndroidInjectionModule::class, AppModule::class, RepositoriesModule::class,
        ActivityModule::class, FragmentModule::class, ServiceModule::class, ResourcesModule::class]
)
interface AppComponent {

    fun inject(app: CoronavirusApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}