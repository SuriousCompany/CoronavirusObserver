package company.surious.mediator_presentation.di

import android.app.Application
import company.surious.coronovirusobserver.data.di.DataManagersModule
import company.surious.coronovirusobserver.presentation.core.CoronovirusApplication
import company.surious.coronovirusobserver.presentation.di.ActivityModule
import company.surious.coronovirusobserver.presentation.di.FragmentModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AndroidInjectionModule::class, AppModule::class, DataManagersModule::class,
        ActivityModule::class, FragmentModule::class]
)
interface AppComponent {

    fun inject(app: CoronovirusApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}