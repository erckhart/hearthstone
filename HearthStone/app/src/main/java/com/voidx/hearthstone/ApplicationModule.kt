package com.voidx.hearthstone

import com.voidx.hearthstone.feature.featureModule
import com.voidx.presentation.dto.ConfigurationDTO
import com.voidx.presentation.presentationModule
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import org.koin.dsl.module

val module = module {

    factory<Scheduler> {
        AndroidSchedulers.mainThread()
    }

    single {
        ConfigurationDTO(
            BuildConfig.SERVER_URL,
            BuildConfig.SERVER_HOST,
            BuildConfig.API_KEY,
            BuildConfig.DEBUG
        )
    }

}

val applicationModule = listOf(module) + presentationModule + featureModule