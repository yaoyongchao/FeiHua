package com.fh.bdc.demo

import com.fh.baselib.base.BaseActivity
import com.fh.baselib.http.BaseObserver
import com.fh.baselib.net.file.MultipartBuilder
import com.fh.baselib.net.file.UploadFile
import com.fh.baselib.utils.LogUtil
import com.fh.baselib.utils.rx.MyRxScheduler
import com.fh.bdc.R
import com.ygfh.doctor.net.DcServiceFactory
import kotlinx.android.synthetic.main.activity_picture.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * Author: YongChao
 * Date: 19-8-21 上午10:19
 * Description: 文件上传
 */
class PictureActivity : BaseActivity() {
    val url = "http://192.168.10.38:8080/Upload/"
    override fun layoutId(): Int {

        return  R.layout.activity_picture
    }

    override fun initView() {
    }

    override fun initData() {
    }

    override fun initListener() {

        btn_uplaod.setOnClickListener {
            val filename = "/sdcard/Download/download.jpeg"
            uploadFile(filename)
        }

        btn_uplaod_more.setOnClickListener {
            val filename1 = "/sdcard/Download/aa.jpeg"
            val filename2 = "/sdcard/Download/ab.jpg"
            uploadFiles(arrayListOf(File(filename1),File(filename2)))
        }
    }
    private fun uploadFile(fileName:String) {
        //构建要上传的文件
        var file = File(fileName)
        var requestFile = RequestBody.create(MediaType.parse("application/otcet-stream"),file)
        var body = MultipartBody.Part.createFormData("aFile",file.name,requestFile)
        val descriptionString =  "This is a description"
        var description: RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"),descriptionString)

        DcServiceFactory.getService(url).upload(UploadFile.createDesc("测试文件上传"),UploadFile.createBody(fileName))
            .compose(MyRxScheduler.ioMain())
            .subscribe(object : BaseObserver<String>(){
                override fun onSuccess(t: String?) {
                    LogUtil.d("上传文件成功")
                }
                override fun onFail(msg: String) {
                    super.onFail(msg)
                    LogUtil.d("上传文件失败")
                }
            })
    }
    private fun uploadFiles(files: ArrayList<File>) {
        var multipartBody = MultipartBuilder.filesToMultipartBody(files)
        DcServiceFactory.getService(url)
            .uploadFileWithRequestBody(multipartBody)
            .compose(MyRxScheduler.ioMain())
            .subscribe(object : BaseObserver<String>(){
                override fun onSuccess(t: String?) {
                    LogUtil.d("批量上传文件成功")
                }
                override fun onFail(msg: String) {
                    super.onFail(msg)
                    LogUtil.d("批量上传文件失败")
                }
            })

    }
}
