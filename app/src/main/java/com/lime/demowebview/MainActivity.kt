package com.lime.demowebview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lime.demowebview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.fragments.size == 1) finish()
        else
            super.onBackPressed()
    }

    private fun initData() {
        loadNewLayer(Constant.BASE_URL, Constant.LAYER1)
    }

    fun loadNewLayer(url: String?, type: Int) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .add(R.id.containerWebView, FragmentWebView.newInstane(type, url.toString()))
            .commit()
    }

    fun closeAllSecondLayout() {
        while (supportFragmentManager.fragments.size >= 2) {
            onBackPressed()
        }
    }
}
