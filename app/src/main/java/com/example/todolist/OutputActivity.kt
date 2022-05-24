package com.example.todolist

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.databinding.OutputActivityBinding
import com.squareup.picasso.Picasso
import okhttp3.*
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder


class OutputActivity : AppCompatActivity() {

    private lateinit var binding: OutputActivityBinding
    private val client = OkHttpClient()


    override fun onCreate(savedInstanceState: Bundle?) {
        var imageView: ImageView

        super.onCreate(savedInstanceState)
        binding = OutputActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar!!.title = "WasteQR"
        actionBar.setDisplayHomeAsUpEnabled(true)


        val testCode = intent.getSerializableExtra("testCode")
        val qrCode: QRCode? = intent.getSerializableExtra("qrCode") as QRCode?

        binding.wasteTypeText.text = qrCode?.wasteType
        binding.notesText.text = qrCode?.notes


        generateQrCode(qrCode)
    }

    fun generateQrCode(qr: QRCode?){
        var url = ""
        val cht = "qr"
        val chs = "${qr?.size}x${qr?.size}"
        val chl = "https://wasteqr.com/index.html?wasteType=${qr?.wasteType}%26note=${qr?.notes}"
        val request = Request.Builder()
            .url("https://chart.googleapis.com/chart?cht=${cht}&chs=${chs}&chl=${chl}")
            .build()
        //Source: https://square.github.io/ok
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    url = response.toString().substring(
                        response.toString().indexOf("https://"),
                        response.toString().length - 1
                    )

                    println("URL: " + url)
                    println("CHL: " + chl)
                    binding.qrCodeViewer.post(Runnable {  Picasso.get().load(url).into(binding.qrCodeViewer) });
                    binding.qrCodeViewer.setColorFilter(Color.parseColor(qr?.color), PorterDuff.Mode.SCREEN);
                }
            }
        })
    }
}