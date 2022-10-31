package com.lime.demowebview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.lime.demowebview.Constant.LAYER2
import com.lime.demowebview.databinding.FragmentWebViewBinding

class FragmentWebView() : Fragment(), WebViewCallBack {
    private var typeLayer: Int = 0
    private var url: String = ""
    private lateinit var binding: FragmentWebViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWebViewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        handleEvent()
    }

    override fun innerCallBack(url: String) {
        // TODO no-ip
    }

    override fun newLayerCallBack(url: String) {
        (activity as MainActivity).loadNewLayer(url, LAYER2)
    }

    override fun browerCallBack(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun handleEvent() {
        binding.apply {
            buttonBack.setOnClickListener {
                if (webViewHome.canGoBack()) webViewHome.goBack()
                else (activity as? MainActivity)?.onBackPressed()
            }
            buttonClose.setOnClickListener {
                (activity as? MainActivity)?.closeAllSecondLayout()
            }
        }
    }

    private fun initData() {
        binding.apply {
            if (typeLayer == LAYER2) {
                containerBack.isVisible = true
                containerClose.isVisible = true
            }
            webViewHome.webViewClient = WebViewImpl(this@FragmentWebView, typeLayer)
            webViewHome.settings.javaScriptEnabled = true
            webViewHome.loadUrl(url)
        }
    }

    companion object {

        fun newInstane(type: Int, url: String) = FragmentWebView().apply {
            this.url = url
            this.typeLayer = type
        }
    }
}
