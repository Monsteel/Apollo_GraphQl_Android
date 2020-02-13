package kr.hs.dgsw.personer.apollo_graphql_android

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.api.cache.http.HttpCachePolicy
import com.apollographql.apollo.rx2.Rx2Apollo
import com.apollographql.apollo.sample.TestQuery
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

class MainActivity : AppCompatActivity() {
    private val disposable: CompositeDisposable = CompositeDisposable()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val token = "fddea3eff0ea037d74e757960ad27b7b2a896d87"
        var testQuery: TestQuery =
            TestQuery.builder().login("Monsteel").name("Dandi_Android").build()

        val observer = object: DisposableObserver<Response<TestQuery.Data>>() {
            override fun onComplete() {
                dispose()
            }
            override fun onNext(t: Response<TestQuery.Data>) {
                Log.e("",t.data()?.user()?.company()!!)
            }

            override fun onError(e: Throwable) {
                TODO("not implemented")
            }
        }

        disposable.add(
        Rx2Apollo.from(
            Utils.getApollo(token, testQuery)?.httpCachePolicy(HttpCachePolicy.CACHE_FIRST)!!
        ).observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribeWith(observer) as Disposable)
    }


}