package com.ygfh.doctor.net
import com.fh.baselib.http.ServiceFactory

/**
 * Author: Austin
 * Date: 19-4-11
 * Description:
 */


object DcServiceFactory {
    fun getService(): DcApiService {
        return ServiceFactory.getService(DcApi.CP_URL,DcApiService::class.java)
    }

    fun getService(url: String): DcApiService {
        return ServiceFactory.getService(url,DcApiService::class.java)
    }
}
