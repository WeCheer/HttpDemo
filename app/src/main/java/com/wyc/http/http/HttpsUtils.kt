package com.wyc.http.http

import java.io.InputStream
import java.security.KeyStore
import java.security.cert.CertificateFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory

/**
 *作者： wyc
 * <p>
 * 创建时间： 2019/2/16 10:36
 * <p>
 * 文件名字： com.wyc.http.http
 * <p>
 * 类的介绍：
 */
class HttpsUtils {

    fun getSslSocketFactory(certificates: Array<InputStream>?,
                            bksFile: InputStream?,
                            password: String?) {
        val trustManagers = certificates?.let { prepareTrustManager(*it) }
    }

    private fun prepareTrustManager(vararg certificates: InputStream): Array<TrustManager>? {
        if (certificates.isEmpty()) {
            return null
        }

        val certificateFactory = CertificateFactory.getInstance("X.509")
        val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
        keyStore.load(null)
        for ((index, certificate) in certificates.withIndex()) {
            val certificateAlias = index.toString()
            keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate))
            certificate.close()
        }
        val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        trustManagerFactory!!.init(keyStore)
        return trustManagerFactory.trustManagers
    }
}
