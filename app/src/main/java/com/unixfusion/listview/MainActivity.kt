package com.unixfusion.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleAdapter
import com.unixfusion.listview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListViewsimple()

    }

    private fun setupListViewsimple() {
        val data = listOf<Map<String, String>>(
            mapOf(KEY_TITLE to "First title 111",
                KEY_DESCRIPTION to "First description 111"),
            mapOf(KEY_TITLE to "Second title 222",
                KEY_DESCRIPTION to "Second description 222"),
            mapOf(KEY_TITLE to "Third title 333",
                KEY_DESCRIPTION to "Third description 333")
        )

        val adapter = SimpleAdapter(
            this, //context
            data, // data
            android.R.layout.simple_list_item_2, // layout file
            arrayOf(KEY_TITLE, KEY_DESCRIPTION), // keys for each element in layout file
            intArrayOf(android.R.id.text1, android.R.id.text2) // ids for each element in layout file
        )

        binding.listView.adapter = adapter

    }
    companion object {
        @JvmStatic val KEY_TITLE = "title"
        @JvmStatic val KEY_DESCRIPTION = "description"
    }
}