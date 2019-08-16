package com.fh.bdc.bean

/**
 * Author: YongChao
 * Date: 19-8-13 上午11:45
 * Description:
 */
class Login {

    /**
     * name : 张三
     * age : 12
     */

    var name: String? = null
    var age: Int = 0

    override fun toString(): String {
        return "Login{" +
                "name='" + name + '\''.toString() +
                ", age=" + age +
                '}'.toString()
    }
}
