package com.onedream.androidxdemo3.framework.http.custome


import com.onedream.androidxdemo3.framework.http.gson_converter_factory.GsonConverterFactory2
import com.onedream.androidxdemo3.framework.http.support.CookieJarImpl
import com.onedream.androidxdemo3.framework.http.support.HeaderInterceptor
import com.onedream.androidxdemo3.framework.http.support.Logger
import com.onedream.androidxdemo3.framework.http.support.LoggingInterceptor
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.net.URLConnection
import java.util.HashMap
import java.util.concurrent.TimeUnit

class MovieApi private constructor() {
    private val service: MovieApiService

    init {
        val logging = LoggingInterceptor(Logger())
        logging.setLevel(LoggingInterceptor.Level.BODY)
        val builder = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true) // 失败重发
            .addInterceptor(HeaderInterceptor())
            .cookieJar(CookieJarImpl())
            .addInterceptor(logging)
        val okHttpClient = builder.build()
        //
        val retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_API_HOST)
            .addConverterFactory(GsonConverterFactory2.create()) // 添加Gson转换器
            .client(okHttpClient)
            .build()
        service = retrofit.create(MovieApiService::class.java)
    }

    suspend fun getBodyOutData(url: String, map: Map<String, String>): BodyOut {
        return service.doGet(Constant.getRealUrl(url), map)
    }

    suspend fun sendRandomPoetry(): BodyOut {
        val map = HashMap<String, String>()
        return getBodyOutData(Constant.API_RANDOM_POETRY, map)
    }

    companion object {
        @Volatile
        private var instance: MovieApi? = null//加volatile防止D指令重排

        fun getInstance(): MovieApi? {
            if (instance == null) {
                synchronized(MovieApi::class.java) {
                    if (instance == null) {
                        instance = MovieApi()
                    }
                }
            }
            return instance
        }


        //根据文件路径猜测出文件类型
        private fun guessMimeType(path1: String): MediaType? {
            var path = path1
            val fileNameMap = URLConnection.getFileNameMap()
            path = path.replace("#", "")   //解决文件名中含有#号异常的问题
            var contentType: String? = fileNameMap.getContentTypeFor(path)
            if (contentType == null) {
                contentType = "application/octet-stream"
            }
            return MediaType.parse(contentType)
        }
    }
}
