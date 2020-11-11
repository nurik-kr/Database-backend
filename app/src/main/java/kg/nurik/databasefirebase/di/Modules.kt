package kg.nurik.databasefirebase.di

import kg.nurik.databasefirebase.data.model.api.FirebaseApi
import kg.nurik.databasefirebase.data.model.api.FirebaseApiImpl
import kg.nurik.databasefirebase.data.model.repository.FirebaseRepository
import kg.nurik.databasefirebase.data.model.repository.FirebaseRepositoryImpl
import kg.nurik.databasefirebase.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val MVModules = module {
    viewModel { MainViewModel(get()) }
}

val RepositoryModules = module {
    single<FirebaseRepository> { FirebaseRepositoryImpl(get()) }
}

val ApiModules = module {
    single<FirebaseApi> { FirebaseApiImpl() }
}

val modules = listOf(MVModules, ApiModules, RepositoryModules)
