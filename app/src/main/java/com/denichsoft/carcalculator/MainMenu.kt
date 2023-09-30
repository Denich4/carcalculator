package com.denichsoft.carcalculator

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.denichsoft.carcalculator.destinations.CalculatorDestination
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun Menu(navigator: DestinationsNavigator) {
    val font = FontFamily(
        Font(R.font.blkchcry)
    )

    val systemUiController: SystemUiController = rememberSystemUiController()
    systemUiController.isStatusBarVisible = false

    Box(modifier = Modifier
        .fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Button(modifier = Modifier.size(200.dp, 50.dp), onClick = { navigator.navigate(CalculatorDestination) }) {
                Text(text = "Calculator", fontSize = 30.sp, fontFamily = font)
            }
        }
    }
}