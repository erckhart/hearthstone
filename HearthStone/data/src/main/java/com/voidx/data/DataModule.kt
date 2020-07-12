import com.google.gson.GsonBuilder
import com.voidx.data.DataSettings
import com.voidx.data.error.DataErrorHandler
import com.voidx.data.error.impl.DataErrorHandlerImpl
import com.voidx.data.model.GameInfo
import com.voidx.data.network.Api
import com.voidx.data.network.AuthorizationInterceptor
import com.voidx.data.network.deserializer.GameInfoDeserializer
import com.voidx.data.repository.InfoDataSource
import com.voidx.data.repository.impl.InfoRepository
import com.voidx.data.repository.impl.remote.InfoRemoteDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {

    factory<InfoDataSource> {
        val remote = InfoRemoteDataSource(get(), get())
        InfoRepository(remote)
    }

    factory<DataErrorHandler> {
        DataErrorHandlerImpl()
    }

    single { createOkHttpClient(get()) }

    single { createWebService<Api>(get(), get()) }

}

fun createOkHttpClient(settings: DataSettings): OkHttpClient {

    val builder = OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)

    if (settings.isDebug) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        builder.addInterceptor(httpLoggingInterceptor)
    }

    val authorizationInterceptor = AuthorizationInterceptor(settings)
    builder.addInterceptor(authorizationInterceptor)

    return builder.build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, settings: DataSettings): T {

    val gson = GsonBuilder()
        .registerTypeAdapter(GameInfo::class.java, GameInfoDeserializer())
        .create()

    val retrofit = Retrofit.Builder()
        .baseUrl(settings.serverUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build()
    return retrofit.create(T::class.java)
}