//리사이클러뷰
package com.example.pyi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pyi.databinding.ActivityRecyclerViewBinding
import org.w3c.dom.Text

class RecyclerView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRecyclerViewBinding.inflate(layoutInflater)

        val layoutManager =LinearLayoutManager(this)
        layoutManager.orientation=
                LinearLayoutManager.HORIZONTAL
        //binding.recyclerView.layoutManager = layoutManager

        //setContentView(R.layout.activity_recycler_view)
        setContentView(binding.root)
        val datas = mutableListOf<Any>()
        for(i in 1..4){
            datas.add("Folder $i ")
        }
        //binding.recyclerView.layoutManager = LinearLayoutManager(this)

        //binding.recyclerView.addItemDecoration(DividerItemDecoration(this,
        //    LinearLayoutManager.HORIZONTAL))
    }
}