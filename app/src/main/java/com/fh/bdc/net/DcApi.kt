package com.ygfh.doctor.net
import com.fh.bdc.BuildConfig



/**
 * Author: Austin
 * Date: 19-3-28
 * Description:
 */

object DcApi {
    val CP_URL = BuildConfig.HTTP_API  + "mock/59881797a1d30433d857c7a5/"
    val CP_FILE_URL = CP_URL + "file/"
    val CP_SERVER_IP = BuildConfig.HTTP_API.replace("http://","")
    val CP_SERVER_PORT = 8888
    val CP_SOCKET_URL = CP_SERVER_IP + ":" + CP_SERVER_PORT

}
