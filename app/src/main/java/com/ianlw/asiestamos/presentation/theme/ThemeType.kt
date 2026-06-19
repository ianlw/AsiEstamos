package com.ianlw.asiestamos.presentation.theme

enum class ThemeType(val displayName: String) {
    LIQUID_GLASS("Liquid Glass"),
    FINTECH_DARK("Fintech Dark"),
    MATERIAL_YOU("Material You"),
    MINIMAL_PAPER("Minimal Paper");

    companion object {
        fun fromString(value: String): ThemeType =
            entries.find { it.name == value } ?: LIQUID_GLASS
    }
}
