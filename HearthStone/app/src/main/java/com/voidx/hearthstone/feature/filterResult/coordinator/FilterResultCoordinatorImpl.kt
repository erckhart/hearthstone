package com.voidx.hearthstone.feature.filterResult.coordinator

import androidx.navigation.NavController

class FilterResultCoordinatorImpl(private val navigation: NavController): FilterResultCoordinator {

    override fun back() {
        navigation.popBackStack()
    }
}