package com.paymint.payssam_aos_manager.config.net;

import android.content.Context
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONException
import org.json.JSONObject


class VolleyRequest<T> {

    companion object {
        val TAG = VolleyRequest::class.java.simpleName
    }

    /**
     * 통신 헤더 공통 정보 생성
     */
    private fun commonHeaderInfo(context: Context?): kotlin.collections.Map<String?, String?>? {
        //        headerMap.put("device_type", Constant.DEVICE_AOS);  // Android
        return HashMap()
    }


    fun getJsonStr(obj: T): String {
        val gson = GsonBuilder().create()
        return gson.toJson(obj)
    }

    fun getJson(obj: T): JSONObject? {
        val jsonStr = getJsonStr(obj)
        var json: JSONObject? = null
        try {
            json = JSONObject(jsonStr)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return json
    }

    fun getObjFromJson(json: String?, classOfT: Class<*>): T {
        return Gson().fromJson(json, classOfT.javaClass) as T
    }


    //get 통신
    fun reqGet(
        context: Context,
        url: String,
        listener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener
    ) {
        // prepare the Request
        // prepare the Request
        val getRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            listener,
            errorListener
        )

        VolleyQueueMng.getInstance(context)?.addToRequestQueue(getRequest)
    }

    //post 통신
    fun reqPostByMap(
        context: Context,
        url: String,
        listener: Response.Listener<String>,
        errorListener: Response.ErrorListener,
        map: HashMap<String, String>,
        isIncludeHeader: Boolean
    ) {
        val postRequest: StringRequest = object : StringRequest(
            Method.POST, url,
            listener,
            errorListener
        ) {
            override fun getParams(): kotlin.collections.Map<String, String>? {
                if (map != null) {
                    return map
                } else { //errorListener 호출
                    errorListener.onErrorResponse(VolleyError())
                    return null
                }
            }

            @Throws(AuthFailureError::class)
            override fun getHeaders(): kotlin.collections.Map<String?, String?>? {
                return if (isIncludeHeader) {
                    commonHeaderInfo(context)
                } else {
                    super.getHeaders()
                }
            }
        }

        VolleyQueueMng.getInstance(context)?.addToRequestQueue(postRequest)
    }

    fun reqPostByVo(
        context: Context?,
        url: String?,
        listener: Response.Listener<JSONObject>?
        ,
        errorListener: Response.ErrorListener?,
        vo: T,
        isIncludeHeader: Boolean
    ) {
        val parameter: JSONObject? = getJson(vo)
        val postRequest: JsonObjectRequest = object : JsonObjectRequest(
            Method.POST
            , url
            , parameter
            , listener
            , errorListener
        ) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String?, String?>? {
                return commonHeaderInfo(context)
//                if (isIncludeHeader) {
//
//                    return commonHeaderInfo(context)
//
//                } else {
//                    super.getHeaders()
//                    return null
//                }
            }
        }

        VolleyQueueMng.getInstance(context)?.addToRequestQueue(postRequest)
    }
}




