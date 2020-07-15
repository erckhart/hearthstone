package com.voidx.presentation.dto

data class ConfigurationDTO(
    val serverUrl: String,
    val serverHost: String,
    val apiKey: String,
    val isDebug: Boolean
)