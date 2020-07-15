package com.voidx.hearthstone.feature.list

import androidx.navigation.NavController
import com.voidx.hearthstone.feature.list.coordinator.ListGameOptionsCoordinator
import com.voidx.hearthstone.feature.list.coordinator.ListGameOptionsCoordinatorImpl
import org.koin.dsl.module

val listModule = module {

    scope<ListGameOptionsFragment> {

        scoped<ListGameOptionsCoordinator> { (navigation: NavController) ->
            ListGameOptionsCoordinatorImpl(navigation)
        }

    }

}