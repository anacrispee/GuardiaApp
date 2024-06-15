package com.example.guardia.features.feature_home

import androidx.annotation.StringRes
import com.example.guardia.R

val listVerticalFilters = listOf(
    SearchFiltersModel(
        id = FiltersEnum.VIOLENCE.id,
        name = R.string.home_filter_violence,
        searchableName = listOf(
            "violence",
            "violência",
            "violencia"
        )
    ),
    SearchFiltersModel(
        id = FiltersEnum.PSYCHOLOGICAL_ABUSE.id,
        name = R.string.home_filter_psychological_abuse,
        searchableName = listOf(
            "psychological abuse",
            "psychological",
            "abuse",
            "abuso psicológico",
            "abuso"
        )
    ),
    SearchFiltersModel(
        id = FiltersEnum.HARASSMENT.id,
        name = R.string.home_filter_harassment,
        searchableName = listOf(
            "harassment",
            "harasamento",
            "assédio sexual",
            "assédio",
            "assedio"
        )
    ),
    SearchFiltersModel(
        id = FiltersEnum.THREAT.id,
        name = R.string.home_filter_threat,
        searchableName = listOf(
            "threat",
            "ataque",
            "ameaça"
        )
    )
)

data class SearchFiltersModel(
    val id: Int,
    @StringRes val name: Int,
    val searchableName: List<String>
)

enum class FiltersEnum(
    val id: Int
) {
    VIOLENCE(1),
    PSYCHOLOGICAL_ABUSE(2),
    HARASSMENT(3),
    THREAT(4)
}