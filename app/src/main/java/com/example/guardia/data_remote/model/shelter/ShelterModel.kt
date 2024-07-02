package com.example.guardia.data_remote.model.shelter

data class ShelterListModel(
    val sheltersList: List<ShelterModel>?,
    val totalShelters: Int? = 1
)

data class ShelterModel(
    val id: Int? = 1,
    val name: String? = "Abrigo Santa Fé",
    val phone: String? = "(47) 3396-0665",
    val website: String? = "www.santa-fe-shelter.com.br",
    val addressModel: AddressModel?
)

data class AddressModel(
    val street: String? = "Av. Cônsul Carlos Renaux",
    val number: String? = "100",
    val neighborhood: String? = "Centro",
    val city: String? = "Brusque",
    val zipCode: String? = "06115-000",
    val state: String? = "Santa Catarina"
)