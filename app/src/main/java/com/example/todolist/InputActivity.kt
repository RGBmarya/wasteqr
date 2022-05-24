package com.example.todolist

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.example.todolist.databinding.InputActivityMainBinding

class InputActivity : AppCompatActivity() {

    private lateinit var binding: InputActivityMainBinding
    private lateinit var selectedColor: ColorObject

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = InputActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        supportActionBar?.setBackgroundDrawable(Color.parseColor("#0F823B"))
        //Set initial color spinner state
        loadColorSpinner()

    }

    fun sendMessage(view: View){
        val qrCode = QRCode(200,
                            binding.wasteTypeSpinner.selectedItem.toString(),
                            binding.notes.text.toString(),
                            binding.colorSpinner.selectedItem.toString())
        val intent = Intent(this, OutputActivity::class.java).apply{
            putExtra("qrCode", qrCode)
        }
        startActivity(intent)
    }
    //Credit: Code With Cal - Color Picker Android Studio Kotlin Custom Spinner Tutorial
    private fun loadColorSpinner(){
        selectedColor = ColorList().defaultColor
        binding.colorSpinner.apply{
            adapter = ColorSpinnerAdapter(applicationContext, ColorList().basicColors())
            setSelection(ColorList().colorPosition(selectedColor), false)
            }
        }
}