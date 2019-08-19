package com.ygfh.doctor.net

import com.fh.baselib.http.entity.BaseEntity
import com.fh.bdc.bean.Login
import com.fh.bdc.bean.UpgradeBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST

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
}