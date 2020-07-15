package com.voidx.hearthstone.feature.list.coordinator

import androidx.navigation.NavController
import com.voidx.hearthstone.feature.list.ListGameOptionsFragmentDirections

class ListGameOptionsCoordinatorImpl(private val navigation: NavController) :
    ListGameOptionsCoordinator {

    override fun showDetails(category: String, type: String) {
        val directions = ListGameOptionsFragmentDirections.actionListToResults(category, type)
        navigation.navigate(directions)
    }
}