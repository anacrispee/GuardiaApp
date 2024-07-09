package com.example.guardia.features.feature_shelters

import com.example.guardia.data_remote.model.shelter.AddressModel
import com.example.guardia.data_remote.model.shelter.ShelterModel
import com.example.guardia.data_remote.model.shelter.SheltersListModel

object UtilFactory {
    val DUMMY_SHELTER_LIST_MODEL = SheltersListModel(
        shelters = listOf(
            ShelterModel(
                id = 1,
                name = "Shelter 1",
                phone = "11 99999-9999",
                website = "www.shelter1.com.br",
                addressModel = AddressModel(
                    street = "123 Main Street",
                    number = "123",
                    neighborhood = "Downtown",
                    city = "New York",
                    zipCode = "10001",
                    state = "NY"
                )
            ),
            ShelterModel(
                id = 2,
                name = "Shelter 2",
                phone = "22 99999-9999",
                website = "www.shelter2.com.br",
                addressModel = AddressModel(
                    street = "456 Elm Street",
                    number = "456",
                    neighborhood = "Central",
                    city = "Los Angeles",
                    zipCode = "90001",
                    state = "CA"
                )
            ),
            ShelterModel(
                id = 3,
                name = "Shelter 3",
                phone = "33 99999-9999",
                website = "www.shelter3.com.br",
                addressModel = AddressModel(
                    street = "789 Oak Street",
                    number = "789",
                    neighborhood = "Westside",
                    city = "Chicago",
                    zipCode = "60601",
                    state = "IL"
                )
            )
        )
    )
}