package kr.hs.dgsw.personer.apollo_graphql_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.apollographql.apollo.sample.TestQuery
import org.jetbrains.annotations.NotNull

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val token = "<Token>"
        Utils.getApollo(token).query(
            TestQuery.builder()
                .login("Monsteel")
                .name("Android_Repository")
                .build()
        )
            .enqueue(object : ApolloCall.Callback<TestQuery.Data?>() {
                override fun onResponse(@NotNull dataResponse: Response<TestQuery.Data?>) {
                    Log.e("EEE",dataResponse.data().toString())
                }

                override fun onFailure(e: ApolloException) {
                    Log.e("Err","Error")
                }
            })

    }
}
