package company.surious.coronavirusobserver.data.repositories.news

import company.surious.coronavirusobserver.BuildConfig
import company.surious.coronavirusobserver.data.network.JsonRetrofitClient

class CnnRetrofitClient : JsonRetrofitClient() {
    override val baseUrl: String = BuildConfig.CNN_URL
}