package company.surious.coronovirusobserver.data.network

import company.surious.coronovirusobserver.data.network.models.StatusNetworkResponse
import io.reactivex.Single
import retrofit2.http.GET

interface CoronovirusApi {

    @GET("2/query?where=OBJECTID%3E0&geometryType=esriGeometryEnvelope&spatialRel=esriSpatialRelIntersects&resultType=tile&distance=0.0&units=esriSRUnit_Meter&returnGeodetic=false&outFields=Last_Update%2C+Country_Region%2C+Confirmed%2C+Deaths%2C+Recovered&returnGeometry=true&featureEncoding=esriDefault&multipatchOption=xyFootprint&applyVCSProjection=false&returnIdsOnly=false&returnUniqueIdsOnly=false&returnCountOnly=false&returnExtentOnly=false&returnQueryGeometry=false&returnDistinctValues=false&cacheHint=false&returnZ=false&returnM=false&returnExceededLimitFeatures=true&quantizationParameters=&sqlFormat=none&f=pjson")
    fun getStatus(): Single<StatusNetworkResponse>

}