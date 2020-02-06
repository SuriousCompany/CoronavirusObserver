package company.surious.coronovirusobserver.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryStatusEntity(
    var countryName: String = "",
    var lastUpdateMillis: Long = -1L,
    var infected: Int = 0,
    var dead: Int = 0,
    var recovered: Int = 0,
    var provinces: ArrayList<String> = ArrayList()
) : Parcelable