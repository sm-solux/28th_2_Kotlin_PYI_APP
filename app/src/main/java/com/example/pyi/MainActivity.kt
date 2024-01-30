//메인페이지
package com.example.pyi

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pyi.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        toggle= ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()


        val layoutManeger = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        layoutManeger.stackFromEnd = true
        binding.folderRecyclerview.layoutManager = layoutManeger


        val folderAdapter = MyAdapter {
            // 폴더 버튼 클릭 처리, 예를 들어 다른 페이지로 이동
            val intent = Intent(this, MainNextActivity::class.java)
            startActivity(intent)
        }

        binding.folderRecyclerview.adapter = folderAdapter

        val layoutManeger2 = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        layoutManeger.stackFromEnd = true
        binding.memoRecyclerview.layoutManager = layoutManeger2
        //binding.memoRecyclerview.adapter = MyAdapter2()

        val memoAdapter = MyAdapter2 {
            // 메모 버튼 클릭 처리, 예를 들어 다른 페이지로 이동
            val intent = Intent(this, QuickMemoActivity::class.java)
            startActivity(intent)
        }

        binding.memoRecyclerview.adapter = memoAdapter


        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun navigateToOtherActivity() {
        val intent = Intent(this, QuickMemoActivity::class.java)  // OtherActivity는 이동할 대상 액티비티 클래스입니다. 클래스명을 실제 액티비티 클래스명으로 변경하세요.
        startActivity(intent)
    }


}



