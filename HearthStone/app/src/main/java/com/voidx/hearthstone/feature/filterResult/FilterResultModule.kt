package com.voidx.hearthstone.feature.filterResult

import androidx.navigation.NavController
import com.voidx.hearthstone.feature.filterResult.coordinator.FilterResultCoordinator
import com.voidx.hearthstone.feature.filterResult.coordinator.FilterResultCoordinatorImpl
import org.koin.dsl.module

val filterResultModule = module {

    scope<FilterResultFragment> {

        scoped<FilterResultCoordinator> { (navigation: NavController) ->
            FilterResultCoordinatorImpl(navigation)
        }
    }
}