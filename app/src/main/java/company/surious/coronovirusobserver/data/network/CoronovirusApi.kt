package company.surious.coronovirusobserver.data.network

import company.surious.coronovirusobserver.data.network.models.StatusNetworkResponse
import io.reactivex.Single
import retrofit2.http.GET

interface CoronovirusApi {

    @GET("2/query?where=OBJECTID>0&objectIds=&time=&geometry=&geometryType=esriGeometryEnvelope&inSR=&spatialRel=esriSpatialRelIntersects&resultType=standard&distance=0.0&units=esriSRUnit_Meter&returnGeodetic=false&outFields=Last_Update%2C+Country_Region%2C+Confirmed%2C+Deaths%2C+Recovered&returnGeometry=true&featureEncoding=esriDefault&multipatchOption=xyFootprint&maxAllowableOffset=&geometryPrecision=&outSR=&datumTransformation=&applyVCSProjection=false&returnIdsOnly=false&returnUniqueIdsOnly=false&returnCountOnly=false&returnExtentOnly=false&returnQueryGeometry=false&returnDistinctValues=false&cacheHint=false&orderByFields=&groupByFieldsForStatistics=&outStatistics=&having=&resultOffset=&resultRecordCount=&returnZ=false&returnM=false&returnExceededLimitFeatures=true&quantizationParameters=&sqlFormat=standard&f=pjson&token=")
    fun getStatus(): Single<StatusNetworkResponse>

}