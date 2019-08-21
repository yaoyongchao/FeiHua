package com.ygfh.doctor.net

import com.fh.baselib.http.entity.BaseEntity
import com.fh.bdc.bean.Login
import com.fh.bdc.bean.UpgradeBean
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*


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
    /**
     * 单文件上传
     * @param description
     * @param file @Part MultipartBody.Part file 我们使用MultipartBody.Part类，使我们能够发送实际文件 file就是要往服务器上传的文件
     * @return 状态信息
     */
    @Multipart
    @POST("UploadServer")
    fun upload(@Part("description") description: RequestBody, @Part file : MultipartBody.Part):Observable<BaseEntity<String>>

    /**
     * 通过 List<MultipartBody.Part> 传入多个part实现多文件上传
     * @param parts 每个part代表一个
     * @return 状态信息
     */
    @Multipart
    @POST("UploadServer")
    fun uploadFilesWithParts(@Part parts: List<MultipartBody.Part>):Observable<BaseEntity<String>>

    /**
     * 通过 MultipartBody和@body作为参数来实现多文件上传
     * @param multipartBody MultipartBody包含多个Part
     * @return 状态信息
     */
    @POST("UploadServer")
    fun uploadFileWithRequestBody(@Body multipartBody: MultipartBody): Observable<BaseEntity<String>>
}