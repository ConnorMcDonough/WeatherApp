package com.example.projectfour_weatherappv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SecondActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle locationData = getIntent().getExtras();
        location = locationData.getString("location");

        TextView locationID = findViewById(R.id.locationID);
        locationID.setText(location);

        getWeather();
    }

    //Weather API
    public void getWeather() {

        //instantiate the request queue
        requestQueue = Volley.newRequestQueue(this);


        //create object request
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(
                        Request.Method.GET,    //the request method
                        "https://api.openweathermap.org/data/2.5/forecast?q=" + location.toLowerCase() + "&APPID=90da82d81347db996e2549e1dc014c5f",  //the URL | OLD: https://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=90da82d81347db996e2549e1dc014c5f
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //this prints the WHOLE string
                                //Log.i("JSON response", response.toString());//8

                                try {
                                    for (int x = 0; x <= 40; x += 8) {

                                        JSONArray theList = response.getJSONArray("list");//gets the whole 5 day weather report
                                        JSONObject firstElement = theList.getJSONObject(x); //this is the first weather object
                                        String temp = "[" + firstElement.getJSONObject("main").toString() + "]";//Gets the main jsonobject string and adds [ ] (which makes it a jsonarray)
                                        JSONArray newArray = new JSONArray(temp);// makes NEW jsonarray that JUST has the newly made main jsonarray
                                        JSONObject newObject = newArray.getJSONObject(0);//sets index of newArray to 0 (only one object anyways)

                                        String theTempInKelvin = newObject.getString("temp");//finds "temp" object in newArray Main
                                        String temp_min = newObject.getString("temp_min");
                                        String temp_max = newObject.getString("temp_max");

                                        if (x == 0) {
                                            TextView mainTemp = findViewById(R.id.tempMainID);
                                            mainTemp.setText(convert(theTempInKelvin) + "°");


                                            TextView tempLowMain = findViewById(R.id.tempLowMainID);
                                            tempLowMain.setText("Low: " + convert(temp_min) + "°");


                                            TextView tempHighMain = findViewById(R.id.tempHighMainID);
                                            tempHighMain.setText("High: " + convert(temp_max) + "°");
                                        } else if (x == 8) {
                                            TextView oneDayAfter = findViewById(R.id.oneDayAfterWeatherID);
                                            oneDayAfter.setText("Tomorrow: " + convert(theTempInKelvin) + "°" + " | " + "Low: " + convert(temp_min) + "°" + " | " + "High: " + convert(temp_max) + "°");

                                        } else if (x == 16) {
                                            TextView twoDayAfter = findViewById(R.id.twoDayAfterWeatherID);
                                            twoDayAfter.setText("Third Day: " + convert(theTempInKelvin) + "°" + " | " + "Low: " + convert(temp_min) + "°" + " | " + "High: " + convert(temp_max) + "°");
                                        } else if (x == 32) {
                                            TextView twoDayAfter = findViewById(R.id.threeDayAfterWeatherID);
                                            twoDayAfter.setText("Forth Day: " + convert(theTempInKelvin) + "°" + " | " + "Low: " + convert(temp_min) + "°" + " | " + "High: " + convert(temp_max) + "°");
                                        } else {
                                            TextView twoDayAfter = findViewById(R.id.fourDayAfterWeatherID);
                                            twoDayAfter.setText("Fifth Day: " + convert(theTempInKelvin) + "°" + " | " + "Low: " + convert(temp_min) + "°" + " | " + "High: " + convert(temp_max) + "°");
                                        }

                                        //-------- Weather description
                                        JSONObject firstElement_weather = theList.getJSONObject(x);

                                        JSONArray weatherArray = firstElement_weather.getJSONArray("weather");
                                        JSONObject theWeather = weatherArray.getJSONObject(0);

                                        String theDescription = theWeather.getString("description");

                                        if (x == 0) {
                                            TextView description = findViewById(R.id.descriptionMainID);
                                            description.setText(theDescription);
                                        } else if (x == 8) {
                                            TextView oneDayAfter = findViewById(R.id.oneDayAfterWeatherID);
                                            oneDayAfter.append(" | " + theDescription);
                                        } else if (x == 16) {
                                            TextView twoDayAfter = findViewById(R.id.twoDayAfterWeatherID);
                                            twoDayAfter.append(" | " + theDescription);
                                        } else if (x == 32) {
                                            TextView threeDayAfter = findViewById(R.id.threeDayAfterWeatherID);
                                            threeDayAfter.append(" | " + theDescription);
                                        } else {
                                            TextView fourDayAfter = findViewById(R.id.fourDayAfterWeatherID);
                                            fourDayAfter.append(" | " + theDescription);
                                        }


                                    }

                                } catch (JSONException ex) {
                                    Log.e("JSON Error", ex.getMessage());
                                }
                            }
                        },
                        new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }
                );//end of JSON object request

        requestQueue.add(jsonObjectRequest);

    }//end of Weather API

    public int convert(String temp) {
        double value = (Double.parseDouble(temp) - 273.15) * 9 / 5 + (32);
        return (int) value;
    }

}
