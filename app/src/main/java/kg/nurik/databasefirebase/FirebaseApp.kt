package kg.nurik.databasefirebase

import android.app.Application
import kg.nurik.databasefirebase.di.modules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FirebaseApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // declare used Android context
            androidContext(this@FirebaseApp)
            // declare modules
            modules(modules)
        }
    }
}