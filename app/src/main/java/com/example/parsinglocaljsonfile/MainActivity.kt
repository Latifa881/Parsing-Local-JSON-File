package com.example.parsinglocaljsonfile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    lateinit var rvMain:RecyclerView
    val detailsArray=ArrayList<details>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvMain = findViewById(R.id.rvMain)
        rvMain.adapter = RecyclerViewAdapter(detailsArray,this)
        rvMain.layoutManager = LinearLayoutManager(this)

        read_json()
    }

    fun read_json() {
        var json: String?
        try {
            val inputStream: InputStream = assets.open("Data.json")
            json = inputStream.bufferedReader().use { it.readText() }
            var jsonArray = JSONArray(json)
            for (i in 0 until jsonArray.length()) {

            val jsonObject=jsonArray.getJSONObject(i)
                val copyright=jsonObject.getString("copyright")
                val date=jsonObject.getString("date")
                val explanation=jsonObject.getString("explanation")
                val title=jsonObject.getString("title")
                val url=jsonObject.getString("url")

                detailsArray.add(details(copyright,date,explanation,title,url))

            }
            rvMain.adapter!!.notifyDataSetChanged()


        } catch (e: IOException) {
        Log.e("EXCEPTION",e.toString())
        }

    }
}