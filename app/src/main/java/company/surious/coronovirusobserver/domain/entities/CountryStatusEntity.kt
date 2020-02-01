package company.surious.coronovirusobserver.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryStatusEntity(
    var provinces: ArrayList<String> = ArrayList(),
    var countryName: String = "",
    var updateDate: String = "",
    var infected: Int = 0,
    var dead: Int = 0,
    var recovered: Int = 0
) : Parcelable