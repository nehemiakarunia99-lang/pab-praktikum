package com.nehem.pabweek3.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * Tugas Praktikum PAB (Week 5)
 * Seluruh destinasi/route aplikasi didefinisikan di sini, mengikuti
 * struktur folder yang disarankan (pemisahan Routes.kt & Compositions.kt).
 */
sealed interface Route : NavKey {

    @Serializable
    data object Login : Route

    @Serializable
    data object Home : Route

    @Serializable
    data object GenerateQr : Route

    @Serializable
    data object Profile : Route

    /**
     * Route dengan parameter (Passing Parameter).
     * Membawa ID dan Nama Course dari Halaman Home ke Halaman Detail.
     */
    @Serializable
    data class CourseDetail(val courseId: String, val courseName: String) : Route
}
