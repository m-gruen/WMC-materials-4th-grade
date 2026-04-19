package dev.htl.electorapp

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object Home : NavKey

@Serializable
data object Count : NavKey

@Serializable
data object About : NavKey

@Serializable
data object Overview : NavKey
