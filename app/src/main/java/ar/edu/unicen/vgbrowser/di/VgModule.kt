package ar.edu.unicen.vgbrowser.di

import ar.edu.unicen.vgbrowser.ddl.data.ApiResponseApi
import ar.edu.unicen.vgbrowser.ddl.data.VideogameApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class VgModule {

    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.rawg.io/api/")
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    //OkHttp client hace la conexion Http, envia la peticion y devuelve la respuesta obtenida
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val originalUrl = original.url()

                val url = originalUrl.newBuilder()
                    .addQueryParameter("key", "94fa16c6b1fb46ba9d80044b7c12aca4")
                    .build()

                val requestBuilder = original.newBuilder().url(url)
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .build()
    }

    //Retrofit crea una subclase con las implementaciones de esta interface
    @Provides
    fun providesVideogameApi(retrofit: Retrofit):VideogameApi{
        return retrofit.create(VideogameApi::class.java)
    }

    @Provides
    fun providesApiRespoonseApi(retrofit: Retrofit): ApiResponseApi {
        return retrofit.create(ApiResponseApi::class.java)
    }

}