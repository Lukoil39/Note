package com.rojer_ko.notes.di

import com.rojer_ko.notes.presentation.ui.main.MainViewModel
import com.rojer_ko.notes.presentation.ui.note.NoteViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.rojer_ko.notes.data.provider.FireStoreProvider
import com.rojer_ko.notes.data.provider.RemoteDataProvider
import com.rojer_ko.notes.data.repository.Repository
import com.rojer_ko.notes.presentation.ui.splash.SplashViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    single { FirebaseAuth.getInstance() }
    single { Firebase.firestore }
    single { FireStoreProvider(get(), get()) } bind RemoteDataProvider::class
    single { Repository(get()) }
}

val splasModule = module {
    viewModel { SplashViewModel(get()) }
}


val mainModule = module {
    viewModel { MainViewModel(get()) }
}

val noteModule = module {
    viewModel { NoteViewModel(get()) }
}
