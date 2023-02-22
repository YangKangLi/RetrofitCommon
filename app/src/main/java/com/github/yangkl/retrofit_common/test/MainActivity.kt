package com.github.yangkl.retrofit_common.test

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), View.OnClickListener, CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn_get_github_user_info).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (R.id.btn_get_github_user_info == v?.id) {
            findViewById<TextView>(R.id.tv_result).text = ""
            getUGithubUserInfo()
        }
    }

    private fun getUGithubUserInfo() {
        launch {
            val result = withContext(Dispatchers.IO) {
                Log.d("YangKL", "当前线程：${Thread.currentThread().name}")
                GithubModel.instant.getUserInfo("retrofit")
            }
            Log.d("YangKL", "当前线程：${Thread.currentThread().name}")
            findViewById<TextView>(R.id.tv_result).text = result.toString()
        }
    }


}