package com.example.recipes

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import org.json.JSONObject

class Main : AppCompatActivity() {
    lateinit var search: EditText
    lateinit var name: TextView
    lateinit var temp: TextView
    lateinit var pascal: TextView
    lateinit var speed: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        search = findViewById<EditText>(R.id.search)
        name = findViewById(R.id.name)
        temp = findViewById(R.id.temp)
        pascal = findViewById(R.id.pascal)
        speed = findViewById(R.id.speed)



    }

    fun weather(view: View) {if (search.text.toString().isNotEmpty() && search.text.toString() != null) {
        var key = "0b7762895cbedc7240617a47036cf27c"
        var url =
            "https://api.openweathermap.org/data/2.5/weather?q=" + search.text.toString() + "&appid=" + key + "&units=metric&lang=ru"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                    response ->
                val obj = JSONObject(response)

                val cityName = obj.getString("name")
                name.text=cityName

                val tempOBJ = obj.getJSONObject("main")
                val tempDouble = tempOBJ.getDouble("temp")
                temp.text="$tempDouble °C"

                val airOBJ = obj.getJSONObject("main")
                val pressure = airOBJ.getDouble("pressure")
                pascal.text=pressure.toString()

                val windObject = obj.getJSONObject("wind")
                val windSpeed = windObject.getDouble("speed")
                speed.text = "$windSpeed м/с"


            },
            {

                val snackbar =  Snackbar.make(view,"Город не найден!", Snackbar.LENGTH_LONG)
                snackbar.show()
            }
        )
        queue.add(stringRequest)
    }
    else
    {

        val snackbar =  Snackbar.make(view,"Введите название города", Snackbar.LENGTH_LONG)
        snackbar.show()
    }}


}