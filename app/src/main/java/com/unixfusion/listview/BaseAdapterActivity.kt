package com.unixfusion.listview

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AlertDialog
import com.unixfusion.listview.databinding.ActivityArrayAdapterBinding
import com.unixfusion.listview.databinding.DialogAddCharacterBinding
import java.util.*

class BaseAdapterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArrayAdapterBinding
    private lateinit var adapter: CharacterAdapter
    private val data = mutableListOf(
        Character(id = 1, name = "Raptor", false) ,
        Character(id = 2, name = "Dog", false) ,
        Character(id = 3, name = "Rat", false) ,
        Character(id = 4, name = "Cat", false) ,
        Character(id = 5, name = "Turtle", false) ,
        Character(id = 6, name = "Racoon", false) ,
        Character(id = 7, name = "Wolf", false),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArrayAdapterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupList()
        binding.btnAdd.setOnClickListener { onAddPressed() }
    }

    private fun setupList() {
        adapter = CharacterAdapter(data) {
            deleteCharacter(it)
        }
        binding.listView.adapter = adapter

        //listener for spinner
        binding.listView.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val character = data[position]
                binding.textView.text = getString(R.string.character_info, character.name, character.id)

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                binding.textView.text = "Select the character"
            }
        }

// listener for listView
//        binding.listView.setOnItemClickListener { parent, view, position, id ->
//            showCharacterInfo(adapter.getItem(position))
//        }
    }

//    private fun showCharacterInfo(character: Character) {
//        val dialog = AlertDialog.Builder(this)
//            .setTitle(character.name)
//            .setMessage(getString(R.string.character_info, character.name, character.id))
//            .setPositiveButton("OK") { _, _ -> }
//            .create()
//        dialog.show()
//    }

    private fun deleteCharacter(character: Character) {
        val listener = DialogInterface.OnClickListener { dialog, which ->
            if (which == DialogInterface.BUTTON_POSITIVE) {
                data.remove(character)
                adapter.notifyDataSetChanged()
            }
        }
        val dialog = AlertDialog.Builder(this)
            .setTitle("Delete ${character.name}")
            .setMessage("Are you sure you want to delete character ${character.name}?")
            .setPositiveButton("OK", listener)
            .setNegativeButton("Cancel", listener)
            .create()
        dialog.show()
    }

    private fun onAddPressed() {
        val dialogBinding = DialogAddCharacterBinding.inflate(layoutInflater)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Create new character")
            .setView(dialogBinding.root)
            .setPositiveButton("Add") { d, which ->
                val name = dialogBinding.etCharacterName.text.toString()
                if (name.isNotBlank()) {
                    createCharacter(name)
                }
            } .create()
        dialog.show()
    }

    private fun createCharacter(name: String) {
        val character = Character(
            id = Random().nextLong(),
            name = name,
            isCustom = true
        )
        data.add(character)
        adapter.notifyDataSetChanged()
    }
}