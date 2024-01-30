//리사이클러뷰 내부의 메모리스트
package com.example.pyi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pyi.databinding.ActivityRecyclerViewBinding

class RecyclerViewMemo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRecyclerViewBinding.inflate(layoutInflater)

        val layoutManager =LinearLayoutManager(this)
        layoutManager.orientation=
            LinearLayoutManager.VERTICAL
        //binding.recyclerView.layoutManager = layoutManager

        //setContentView(R.layout.activity_recycler_view)
        setContentView(binding.root)
        val datas = mutableListOf<Any>()
        for(i in 1..10){
            datas.add("Folder $i ")
        }

        val write_btn: Button = findViewById(R.id.write_btn)
        write_btn.setOnClickListener {
            val intent = Intent(this, QuickMemoActivity::class.java)
            startActivity(intent) }


        //binding.recyclerView.layoutManager = LinearLayoutManager(this)

        //binding.recyclerView.addItemDecoration(DividerItemDecoration(this,
        //    LinearLayoutManager.HORIZONTAL))
    }
}