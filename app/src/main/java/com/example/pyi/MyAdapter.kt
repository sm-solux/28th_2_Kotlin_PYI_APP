package com.example.pyi

import android.content.ClipData.Item
import android.content.Intent
import android.provider.ContactsContract.Profile
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pyi.databinding.ActivityMainBinding
import com.example.pyi.databinding.ActivityRecyclerViewBinding
import com.example.pyi.databinding.ItemMainBinding
import com.example.pyi.databinding.ItemMemoBinding

class FolderViewHolder(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root)

class MyAdapter(private val onItemClick: () -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FolderViewHolder(ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as? FolderViewHolder)?.binding

        // 폴더 아이템 UI 설정
        // 버튼에 대한 클릭 리스너 설정
        binding?.folderbutton?.setOnClickListener {
            onItemClick.invoke()
        }
    }

    override fun getItemCount(): Int = Int.MAX_VALUE / 2
}


class MemoViewHolder(val binding: ItemMemoBinding) : RecyclerView.ViewHolder(binding.root)

class MyAdapter2(private val onItemClick: () -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MemoViewHolder(ItemMemoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as? MemoViewHolder)?.binding

        // 메모 아이템 UI 설정
        // 버튼에 대한 클릭 리스너 설정
        binding?.writeBtn?.setOnClickListener {
            onItemClick.invoke()
        }
    }

    override fun getItemCount(): Int = Int.MAX_VALUE / 2
}