package com.example.unitconventer

import android.graphics.Paint.Style
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconventer.ui.theme.UnitConventerTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConventerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConventer()
                }
            }
        }
    }
}

@Composable
fun UnitConventer() {
    var inputValue by remember {
        mutableStateOf("")
    }
    var outputValue by remember {
        mutableStateOf("")
    }
    var inputUnit by remember {
        mutableStateOf("Meters")
    }
    var outputUnit by remember {
        mutableStateOf("Meters")
    }
    var iexpanded by remember {
        mutableStateOf(false)
    }
    var oexpanded by remember {
        mutableStateOf(false)
    }
    val conversionFactor = remember {
        mutableStateOf(1.00)
    }
    val oconversionFactor = remember {
        mutableStateOf(1.00)
    }

    fun convertUnits() {
        // ?: - elvis operator
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0 / oconversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        //Ui elements stacked
        Text("Unit Conventer",
            style = MaterialTheme.typography.headlineMedium,
            )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue = it
            convertUnits()
        },label = { Text("Enter value")})
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            //Input box
            Box {
                //Input button
                Button(onClick = { iexpanded=true }) {
                    Text(text=inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iexpanded, onDismissRequest = { iexpanded=false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters")},
                        onClick = {
                            iexpanded = false
                            inputUnit="Centimeters"
                            conversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters")},
                        onClick = {
                            iexpanded = false
                            inputUnit="Meters"
                            conversionFactor.value = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet")},
                        onClick = {
                            iexpanded = false
                            inputUnit="Feet"
                            conversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Milimeters")},
                        onClick = {
                            iexpanded = false
                            inputUnit="Milimeters"
                            conversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            //Output box
            Box {
                //Output button
                Button(onClick = { oexpanded=true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oexpanded, onDismissRequest = { oexpanded=false}) {
                    DropdownMenuItem(
                        text = { Text("Centimeters")},
                        onClick = {
                            oexpanded = false
                            outputUnit="Centimeters"
                            oconversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters")},
                        onClick = {
                            oexpanded = false
                            outputUnit="Meters"
                            oconversionFactor.value = 1.00
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet")},
                        onClick = {
                            oexpanded = false
                            outputUnit="Feet"
                            oconversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Milimeters")},
                        onClick = {
                            oexpanded = false
                            outputUnit="Milimeters"
                            oconversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        //Result text
        Text("Result: $outputValue $outputUnit",
                style = MaterialTheme.typography.headlineMedium
            )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConventerPreview() {
    UnitConventer()
}


