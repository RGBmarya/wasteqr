package com.example.todolist

import okhttp3.*
import java.io.IOException
import java.io.Serializable


class QRCode(val size: Int, val wasteType: String, val notes: String, val color : String) : Serializable{
}