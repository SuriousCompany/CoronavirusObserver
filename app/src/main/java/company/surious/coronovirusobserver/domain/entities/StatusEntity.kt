package company.surious.coronovirusobserver.domain.entities

import android.os.Parcel
import android.os.Parcelable


data class StatusEntity(
    var totalInfected: Int = 0,
    var totalDead: Int = 0,
    var totalRecovered: Int = 0,
    var lastUpdate: String = "",
    var countryStatus: HashMap<String, CountryStatusEntity> = HashMap()
) : Parcelable {

    @Suppress("UNCHECKED_CAST")
    constructor(source: Parcel) : this(
        source.readInt(),
        source.readInt(),
        source.readInt(),
        source.readString()!!,
        source.readSerializable() as HashMap<String, CountryStatusEntity>
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(totalInfected)
        writeInt(totalDead)
        writeInt(totalRecovered)
        writeString(lastUpdate)
        writeSerializable(countryStatus)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<StatusEntity> = object : Parcelable.Creator<StatusEntity> {
            override fun createFromParcel(source: Parcel): StatusEntity = StatusEntity(source)
            override fun newArray(size: Int): Array<StatusEntity?> = arrayOfNulls(size)
        }
    }
}