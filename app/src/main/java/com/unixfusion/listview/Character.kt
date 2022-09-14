package com.unixfusion.listview

class Character(
    val id: Long,
    val name: String,
    val isCustom: Boolean
) {
    override fun toString(): String {
        return name
    }
}