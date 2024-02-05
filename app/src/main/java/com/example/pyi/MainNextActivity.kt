package com.example.pyi

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pyi.databinding.ActivityMainNextBinding
import androidx.appcompat.widget.Toolbar


class MainNextActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityMainNextBinding
    private var datas: MutableList<String>? = null

    private val requestLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.getStringExtra("result")?.let {
                    datas?.add(it)
                    // 메모리스트 갱신
                    /*(binding.memoRecyclerview.adapter as? MyAdapter2)?.notifyDataSetChanged()*/
                }
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainNextBinding.inflate(layoutInflater)
        setContentView(binding.root)



        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        val layoutManager2 = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        layoutManager2.stackFromEnd = true
        /* binding.memoRecyclerview.layoutManager = layoutManager2
 */
        val memoAdapter = MyAdapter2 { memoTitle, memoDetails, position, memoCreated ->
            // 메모 버튼 클릭 처리, 예를 들어 다른 페이지로 이동
            navigateToOtherActivity(memoTitle, memoDetails, position)
        }


        /* binding.memoRecyclerview.adapter = memoAdapter*/

        binding.addButton.setOnClickListener {
            val intent = Intent(this, QuickMemoActivity::class.java)
            requestLauncher.launch(intent)
        }

        datas = savedInstanceState?.getStringArrayList("datas")?.toMutableList() ?: mutableListOf()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun navigateToOtherActivity(memoTitle: String, memoDetails: String, position: Int) {
        val intent = Intent(this, QuickMemoActivity::class.java)
        intent.putExtra("memoTitle", memoTitle)
        intent.putExtra("memoDetails", memoDetails)
        intent.putExtra("memoUuid", position)
        startActivity(intent)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList("datas", ArrayList(datas))
    }
}