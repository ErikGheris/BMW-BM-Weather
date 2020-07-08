package com.example.bmweather

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private var weatherData: TextView? = null

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //toaster Message
        search_button.setOnClickListener {
            Toast.makeText(
                this, "this is a toast message",
                Toast.LENGTH_SHORT
            ).show()
        }

        // clears the autoCompleteTExtView when it is clicked
        autoCompleteTextView.setOnClickListener {
            autoCompleteTextView.setText("")
        }
        //
        weatherData = findViewById(R.id.data_View)
        findViewById<View>(R.id.search_button).setOnClickListener { getCurrentData() }


    }




    internal fun getCurrentData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeatherData(lat, lon, AppId)
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: retrofit2.Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.code() == 200) {
                    val weatherResponse = response.body()!!

                    val stringBuilder = "Country: " +
                            weatherResponse.sys!!.country +
                            "\n" +
                            "Zone: " +
                            weatherResponse.name +
                            "\n" +
                            "Temperature: " +
                            weatherResponse.main!!.temp +
                            "\n" +
                            "Temperature(Min): " +
                            weatherResponse.main!!.temp_min +
                            "\n" +
                            "Temperature(Max): " +
                            weatherResponse.main!!.temp_max +
                            "\n" +
                            "Humidity: " +
                            weatherResponse.main!!.humidity +
                            "\n" +
                            "Pressure: " +
                            weatherResponse.main!!.pressure 

                    weatherData!!.text = stringBuilder
                }
            }


            override fun onFailure(call: retrofit2.Call<WeatherResponse>, t: Throwable) {
                weatherData!!.text = t.message
            }
        })
    }


    companion object {

        var BaseUrl = "http://api.openweathermap.org/"
        var AppId = "6133b390a077c487bc9ac43311b3ba26"
        var lat = "50.3535700"
        var lon = "7.5788300"
    }
}


