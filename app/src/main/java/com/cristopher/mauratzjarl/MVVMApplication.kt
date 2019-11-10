package com.cristopher.mauratzjarl

import android.app.Application
import com.cristopher.mauratzjarl.data.db.entities.AppDatabase
import com.cristopher.mauratzjarl.data.network.Api
import com.cristopher.mauratzjarl.data.network.responces.NetworkConnectionInterceptor
import com.cristopher.mauratzjarl.data.repositories.UserRepository
import com.cristopher.mauratzjarl.ui.auth.AuthViewModelFactory
import com.cristopher.mauratzjarl.ui.splash.SplashViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication :Application(),KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { Api(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance(),instance()) }
        bind() from provider { SplashViewModelFactory(instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }


    }
}