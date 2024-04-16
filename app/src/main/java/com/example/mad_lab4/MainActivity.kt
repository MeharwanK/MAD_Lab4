package com.example.mad_lab4

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.mad_lab4.ui.theme.MAD_Lab4Theme
import androidx.preference.PreferenceManager


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MAD_Lab4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    Lab4Task1()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Lab4Task1(context: Context= LocalContext.current) {
    val colorSharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs",Context.MODE_PRIVATE)
    val savedColor = remember { mutableStateOf(colorSharedPreferences.getString("favoriteColor","") ?: "") }
    val newColor = remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Favorite Color") }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TextField(value =newColor.value ,
                    onValueChange ={newColor.value=it },
                    label = {Text("Enter Your Favorite Color")},
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                    )
                Button(onClick = {
                    val editor = colorSharedPreferences.edit()
                    editor.putString("favoriteColor",newColor.value)
                    editor.apply()
                    savedColor.value = newColor.value
                },modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)) {
                    Text(text = "Save Color")
                }
                Button(onClick = {
                    savedColor.value = colorSharedPreferences.getString("favoriteColor", "") ?: ""
                }, modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)) {
                    Text("Retrieve Color")
                }
                Text(text = "Your favorite color: ${savedColor.value}",modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally))
            }
        }
    )
}


@Composable
fun Lab4Task2(modifier: Modifier=Modifier){

}

class DataStoreManager(private val context: Context){
    private val Context.dataStore by preferencesDataStore(name = "myprefs")
    private val count = intPreferencesKey("counter")
}