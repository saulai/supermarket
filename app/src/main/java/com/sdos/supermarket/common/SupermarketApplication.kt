package com.sdos.supermarket.common

import android.app.Application
import com.sdos.supermarket.common.di.*
import com.sdos.supermarket.login.di.loginModule
import com.sdos.supermarket.manager.di.managerModule
import com.sdos.supermarket.product.di.productModule
import com.sdos.supermarket.splash.di.splashModule
import com.sdos.supermarket.technician.di.technicianModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class SupermarketApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SupermarketApplication)
            androidLogger(Level.DEBUG)
            modules(
                listOf(
                    netModule,
                    cacheModule,
                    repositoryModule,
                    interactorModule,
                    databaseModule,
                    splashModule,
                    loginModule,
                    managerModule,
                    technicianModule,
                    productModule
                )
            )
        }
    }

}
