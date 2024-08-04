package com.biswa.randomcolorgenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.biswa.randomcolorgenerator.ui.theme.RandomColorGeneratorTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RandomColorGeneratorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {

                        val color = remember {
                            mutableStateOf(Color.Yellow)
                        }

                        val colorString = remember {
                            mutableStateOf("R: ${color.value.red}\nG: ${color.value.green}\nB: ${color.value.blue}")
                        }

                        val colorHex = remember {
                            mutableStateOf(
                                "#%02X%02X%02X".format(
                                    (color.value.red * 255).toInt(),
                                    (color.value.green * 255).toInt(),
                                    (color.value.blue * 255).toInt()
                                )
                            )
                        }

                        ColorBox(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxSize(),
                            colorString = colorString.value,
                            colorCode = colorHex.value
                        ) {
                            color.value = it
                            colorString.value = "R: ${"%.2f".format(it.red)}\n" +
                                    "G: ${"%.2f".format(it.green)}\n" +
                                    "B: ${"%.2f".format(it.blue)}"
                            colorHex.value = "#%02X%02X%02X".format(
                                (it.red * 255).toInt(),
                                (it.green * 255).toInt(),
                                (it.blue * 255).toInt()
                            )
                        }


                        Box(
                            modifier = Modifier
                                .background(color.value)
                                .weight(1f)
                                .fillMaxSize()
                        )

                    }
                }
            }
        }
    }
}

@Composable
fun ColorBox(
    modifier: Modifier = Modifier,
    colorString: String,
    colorCode: String,
    updateColor: (Color) -> Unit
) {

    Box(
        modifier = modifier
            .background(Color.Red)
            .clickable {
                updateColor(Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat(), 1f))
            }, contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = colorCode,
                modifier = Modifier
                    .background(Color.Black)
                    .padding(4.dp),
                color = Color.White,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = colorString,
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 24.sp
            )
        }
    }
}