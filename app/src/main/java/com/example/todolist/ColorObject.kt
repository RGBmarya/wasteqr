package com.example.todolist
//Credit: Code With Cal - Color Picker Android Studio Kotlin Custom Spinner Tutorial
class ColorObject(var name: String, var hex: String, var contrastHex: String) {
    val hexHash = "#$hex"
    val contrastHexHash = "#$contrastHex"

    override fun toString(): String{
        return hexHash
    }

}