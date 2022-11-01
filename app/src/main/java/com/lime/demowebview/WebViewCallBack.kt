package com.lime.demowebview

interface WebViewCallBack {
    fun innerCallBack(url: String)
    fun newLayerCallBack(url: String)
    fun browerCallBack(url: String)
}
