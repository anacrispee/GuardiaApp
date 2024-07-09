package com.example.guardia.data_remote.model.shelter

data class SheltersListModel(
    val shelters: List<ShelterModel>
)

data class ShelterModel(
    val id: Int? = 0,
    val name: String? = "",
    val phone: String? = "",
    val website: String? = "r",
    val addressModel: AddressModel
)

data class AddressModel(
    val street: String? = "",
    val number: String? = "",
    val neighborhood: String? = "",
    val city: String? = "",
    val zipCode: String? = "",
    val state: String? = ""
)