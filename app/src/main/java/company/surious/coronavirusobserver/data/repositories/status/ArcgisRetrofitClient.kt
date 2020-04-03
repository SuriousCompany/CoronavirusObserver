package company.surious.coronavirusobserver.data.repositories.status

import company.surious.coronavirusobserver.BuildConfig
import company.surious.coronavirusobserver.data.network.JsonRetrofitClient


class ArcgisRetrofitClient : JsonRetrofitClient() {
    override val baseUrl: String = BuildConfig.ARCGIS_URL
}