package com.ygfh.doctor.net

import com.fh.baselib.http.entity.BaseEntity
import com.fh.bdc.bean.Login
import com.fh.bdc.bean.UpgradeBean
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Streaming



/**
 * Author: Austin
 * Date: 19-4-1
 * Description:
 */
interface DcApiService {
//    @FormUrlEncoded
//    @POST("data")
//    abstract fun loadData(@Field("head") head: String, @Field("body") body: String): Observable<BaseResponseBean>

    @POST("aa/login2")
    fun login2(): Observable<BaseEntity<Login>>

    @GET("aa/upgrade")
    fun upgrade(): Observable<BaseEntity<UpgradeBean>>

    /**
     * 下载Apk2文件
     *  动态添加Header
     */
    @Streaming
    @GET("apk/aa.apk")
    fun downloadApkFile2(@Header("Content-Range") contentRange: String): Observable<ResponseBody>
}