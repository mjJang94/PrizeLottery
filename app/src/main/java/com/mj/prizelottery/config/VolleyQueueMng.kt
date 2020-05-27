package com.paymint.payssam_aos_manager.config.net;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

import java.io.File;

class VolleyQueueMng {

    companion object {
        fun getInstance(context: Context?): VolleyQueueMng? {
            if (instance == null) {
                instance = VolleyQueueMng(context)
            }
            return instance
        }

        var instance: VolleyQueueMng? = null
        var mRequestQueue: RequestQueue? = null
    }


    private constructor(context: Context?) { // Instantiate the cache
        val cache: Cache = DiskBasedCache(
            File(context?.cacheDir.toString() + File.separator + "volley"),
            1024 * 1024
        ) // 1MB cap
        // Set up the network to use HttpURLConnection as the HTTP client.
        val network: Network = BasicNetwork(HurlStack())
        // Instantiate the RequestQueue with the cache and network.
        mRequestQueue = RequestQueue(cache, network)
        // Start the queue
        mRequestQueue?.start()
    }


    fun addToRequestQueue(request: Request<*>?) {
        mRequestQueue?.add(request)
    }

    fun addToRequestQueueForStoreInfo(storeInfo: Request<*>?) {
        mRequestQueue?.add(storeInfo)
    }

    fun stopRequestQueue() {
        mRequestQueue?.stop()
    }


}
