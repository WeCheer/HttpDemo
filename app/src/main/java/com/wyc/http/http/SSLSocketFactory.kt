package com.wyc.http.http

import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*
import javax.net.ssl.SSLSocketFactory

/**
 *作者： wyc
 * <p>
 * 创建时间： 2019/2/21 14:31
 * <p>
 * 文件名字： com.wyc.http.http
 * <p>
 * 类的介绍：
 */
object SSLSocketFactory {

    @JvmStatic
    fun getSSLSocketFactory(): SSLSocketFactory {
        try {
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, getTrustManager(), SecureRandom())
            return sslContext.socketFactory
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    private fun getTrustManager(): Array<TrustManager> {
        return arrayOf(object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            }

            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        })
    }

    @JvmStatic
    fun getHostnameVerifier(): HostnameVerifier {
        return HostnameVerifier { _, _ -> true }
    }
}