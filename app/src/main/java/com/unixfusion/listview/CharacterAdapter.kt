package com.unixfusion.listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.unixfusion.listview.databinding.ItemCharacterBinding

typealias OnDeletePressedListener = (Character) -> Unit

class CharacterAdapter(
    private val characters: List<Character>,
    private val onDeletePressedListener: OnDeletePressedListener
): BaseAdapter(), View.OnClickListener {
    override fun getCount(): Int {
        return characters.size
    }

    override fun getItem(position: Int): Character {
        return characters[position]
    }

    override fun getItemId(position: Int): Long {
        return characters[position].id
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding =
            convertView?.tag as ItemCharacterBinding? ?:
            createBinding(parent.context)

        val character = getItem(position)

        binding.tvTitle.text = character.name
        binding.ivDeleteCharacter.tag = character
        binding.ivDeleteCharacter.visibility = if (character.isCustom) View.VISIBLE else View.GONE
        
        return binding.root
    }

    private fun createBinding(context: Context?): ItemCharacterBinding {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(context))
        binding.ivDeleteCharacter.setOnClickListener(this)
        binding.root.tag = binding
        return binding
    }

    override fun onClick(v: View) {
        val character = v.tag as Character
        onDeletePressedListener.invoke(character)
    }

}