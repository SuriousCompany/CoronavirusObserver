package company.surious.coronovirusobserver.data.network

import company.surious.coronovirusobserver.data.network.models.ReportsNetworkResponse
import company.surious.coronovirusobserver.data.network.models.sheets.SheetsNetworkResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface CoronovirusApi {

    @GET(".")
    fun getSheets(@Query("fields") fields: String? = "sheets.properties"): Single<SheetsNetworkResponse>

    @GET
    fun getReports(@Url path: String): Single<ReportsNetworkResponse>

}