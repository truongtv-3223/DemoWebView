package com.lime.demowebview

import android.webkit.WebView
import android.webkit.WebViewClient

class WebViewImpl(val callBack: WebViewCallBack, val typeLayer: Int) : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, request: String?): Boolean {
        val url = request?.split("#")?.first().toString()
        if (typeLayer == Constant.LAYER1) {
            return if (url == Constant.BASE_URL) false
            else {
                url?.let { callBack.newLayerCallBack(it) }
                true
            }
        } else {
            return when {
                url.endWithHTML() -> {
                    callBack.newLayerCallBack(url)
                    true
                }
                url.isTab() -> false
                else -> {
                    callBack.browerCallBack(url)
                    true
                }
            }
        }
    }
}
