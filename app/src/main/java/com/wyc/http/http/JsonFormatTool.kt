package com.wyc.http.http

import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject


/**
 *作者： wyc
 * <p>
 * 创建时间： 2019/2/20 17:08
 * <p>
 * 文件名字： com.wyc.http.http
 * <p>
 * 类的介绍：
 */
object FormatTool {

    inline fun <reified T> printJson(t: T, headString: String): String {
        val msg = Gson().toJson(t, T::class.java)
        var message = try {
            when {
                msg.startsWith("{") -> {
                    val jsonObject = JSONObject(msg)
                    jsonObject.toString(4)//最重要的方法，就一行，返回格式化的json字符串，其中的数字4是缩进字符数
                }
                msg.startsWith("[") -> {
                    val jsonArray = JSONArray(msg)
                    jsonArray.toString(4)
                }
                else -> msg
            }
        } catch (e: Exception) {
            msg
        }
        val sb = StringBuilder()
        sb.append("╔═══════════════════════════════════════════════════════════════════════════════════════")
        message = headString + System.getProperty("line.separator")!! + message
        message.split(System.getProperty("line.separator")!!)
                .forEach {
                    sb.append("\n║ $it")
                }
        sb.append("╚═══════════════════════════════════════════════════════════════════════════════════════")
        return sb.toString()
    }
}