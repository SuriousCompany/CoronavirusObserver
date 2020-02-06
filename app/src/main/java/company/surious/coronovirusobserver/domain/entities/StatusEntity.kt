package company.surious.coronovirusobserver.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StatusEntity(
    var totalInfected: Int = 0,
    var totalDead: Int = 0,
    var totalRecovered: Int = 0,
    var lastUpdateMillis: Long = -1L,
    var countryStatus: ArrayList<CountryStatusEntity> = ArrayList()
) : Parcelable