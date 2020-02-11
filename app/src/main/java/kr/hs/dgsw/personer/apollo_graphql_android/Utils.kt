package kr.hs.dgsw.personer.apollo_graphql_android

import com.apollographql.apollo.ApolloClient
import kr.hs.dgsw.smartschool.dodamdodam_teacher.util.Constants
import okhttp3.OkHttpClient


object Utils {

    private const val BASE_URL = "${Constants.DEFAULT_HOST}/graphql"
    private val builder = OkHttpClient.Builder()
    private var APOLLO = ApolloClient.builder().serverUrl(BASE_URL)

    fun getApollo(token:String): ApolloClient {
        return APOLLO.okHttpClient(getClient(token)).build()
    }
    fun getApollo(): ApolloClient {
        return APOLLO.okHttpClient(getClient()).build()
    }

    private fun getClient(token:String): OkHttpClient {
        return builder.addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    private fun getClient(): OkHttpClient {
        return builder.build()
    }


}