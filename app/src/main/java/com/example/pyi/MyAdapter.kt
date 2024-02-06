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
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pyi.databinding.ActivityMainBinding
import com.example.pyi.databinding.ActivityRecyclerViewBinding
import com.example.pyi.databinding.ItemMainBinding
import com.example.pyi.databinding.ItemMemoBinding

// MyAdapter.kt
class FolderViewHolder(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root)

class MyAdapter(private val onItemClick: (Long) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var folderUuids: List<Long> = emptyList()
    private var folderNames: List<String> = emptyList()

    // 데이터 갱신을 위한 메소드 추가
    fun setData(newFolderNames: List<String>, newFolderUuids: List<Long>) {
        folderUuids = newFolderUuids
        folderNames = newFolderNames
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FolderViewHolder(ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as? FolderViewHolder)?.binding

        // 폴더 아이템 UI 설정
        binding?.folderbutton?.text = folderNames[position]

        // 버튼에 대한 클릭 리스너 설정
        binding?.folderbutton?.setOnClickListener {
            onItemClick.invoke(folderUuids[position])

        }
    }

    //override fun getItemCount(): Int = folderNames.size
    override fun getItemCount(): Int = folderNames.size
}
// MyAdapter2.kt
// MyAdapter2.kt
class MemoViewHolder(val binding: ItemMemoBinding) : RecyclerView.ViewHolder(binding.root)

class MyAdapter2(private val onItemClick: (String, String, Int, String) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var memoList: List<String> = emptyList()
    private var memoDetails: List<String> = emptyList()
    private var memoCreated: List<String> = emptyList()

    // 데이터 갱신을 위한 메소드 추가
    fun setData(newMemoTitles: List<String>, newMemoDetails: List<String>, newMemoCreated: List<String>) {
        memoList = newMemoTitles
        memoDetails = newMemoDetails
        memoCreated = newMemoCreated
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MemoViewHolder(ItemMemoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as? MemoViewHolder)?.binding

        // 메모 아이템 UI 설정
        binding?.writeBtn?.text = memoList[position]
        val memoDetails = memoDetails[position]

        // 최대 20글자까지만 표시
        val truncatedText = if (memoDetails.length > 50) {
            memoDetails.substring(0, 50) + "..."
        } else {
            memoDetails
        }

        binding?.name?.text = truncatedText

        val memoCreatedDate = memoCreated[position]

        // 최대 20글자까지만 표시
        val truncatedText2 = if (memoDetails.length > 10) {
            memoCreatedDate.substring(0, 10) + "에 작성됨"
        } else {
            memoCreatedDate
        }
        binding?.date?.text = truncatedText2


        // 버튼에 대한 클릭 리스너 설정
        binding?.writeBtn?.setOnClickListener {
            onItemClick.invoke(memoList[position], memoDetails, position, memoCreated[position])
            val intent = Intent(binding.root.context, QuickMemoActivity::class.java)
            intent.putExtra("memoContent", memoDetails)
            //binding.root.context.startActivity(intent)
        }
    }




    override fun getItemCount(): Int = memoList.size
}