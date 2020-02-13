package kr.hs.dgsw.personer.apollo_graphql_android

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.ApolloQueryCall
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.rx2.rxQuery
import io.reactivex.Observable
import kr.hs.dgsw.smartschool.dodamdodam_teacher.util.Constants
import okhttp3.OkHttpClient


object Utils {

    private const val BASE_URL = "${Constants.DEFAULT_HOST}/graphql"
    private val builder = OkHttpClient.Builder()
    private var APOLLO = ApolloClient.builder().serverUrl(BASE_URL)

//    fun  getApolloClient(token:String, query: ):  {
//        return APOLLO.okHttpClient(getClient(token)).
//    }
    fun <T,D : Operation.Data?,V : Operation.Variables?> getApollo(token: String, query: Query<D, T, V>): ApolloQueryCall<T>? {
        return APOLLO.okHttpClient(getClient(token)).build().query(query)
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