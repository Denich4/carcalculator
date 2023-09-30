package com.denichsoft.carcalculator

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
class Tile() {
    var imageId: Int = R.drawable.castlecenterentry0
    var count: Int = 0
    var total: Int = 0

    constructor(_imageId: Int, _count: Int, _total: Int) : this(){
        imageId = _imageId
        count = _count
        total = _total
    }
}

@Composable
fun Tile(
    modifier: Modifier,
    tile: Tile,
    percentsVisibility: Boolean,
    totalTiles: Int,
    iter: Int,
    onChange: (Tile, Int, Int) -> Unit) {

    Box(modifier = modifier.padding(3.dp)) {
        Card(
            backgroundColor = if (tile.count == 0) Color(255, 96, 110) else MaterialTheme.colors.primary,
            shape = RoundedCornerShape(9.dp),
            modifier = Modifier
                .clickable {
                    if (tile.count > 0) {
                        tile.count = tile.count - 1

                        onChange(Tile(tile.imageId, tile.count, tile.total), iter, totalTiles - 1)
                    }
                }) {
            Column(verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(2.dp)) {
                Image(painter = painterResource(id = tile.imageId), contentDescription = null)
                if (percentsVisibility && totalTiles>0) {
                    Text(text = "${tile.count}/${tile.total}")
                    Text(text = "%.2f".format(tile.count.toDouble() / totalTiles.toDouble() * 100.0) + "%")
                } else {
                    if (percentsVisibility && totalTiles==0) {
                        Text(text = "${tile.count}/${tile.total}")
                        Text(text = "GoodGame")
                    }
                    else {
                        Text(text = "${tile.count}/${tile.total}", fontSize = 30.sp)
                    }
                }
            }
        }
    }
}


@Composable
@Destination
fun Calculator(navigator: DestinationsNavigator) {

    val percentsVisibility = remember { mutableStateOf(true)}

    val totalTiles = remember { mutableStateOf(71) }

    val systemUiController: SystemUiController = rememberSystemUiController()
    systemUiController.isStatusBarVisible = false

    val tilesMatrix = remember { mutableStateListOf<Tile>(
        Tile(R.drawable.monastery0, 4, 4),
        Tile(R.drawable.monasteryroad0, 2, 2),
        Tile(R.drawable.castlecenter0shield, 1, 1),
        Tile(R.drawable.castlecenterside0, 3, 3),
        Tile(R.drawable.castlecenterside0shield, 1, 1),
        Tile(R.drawable.castlecenterentry0, 1, 1),
        Tile(R.drawable.castlecenterentry0shield, 2, 2),
        Tile(R.drawable.castleedge3, 3, 3),
        Tile(R.drawable.castleedge3shield, 2, 2),
        Tile(R.drawable.castleedgeroad3, 3, 3),
        Tile(R.drawable.castleedgeroad3shield, 2, 2),
        Tile(R.drawable.castletube0, 1, 1),
        Tile(R.drawable.castletube0shield, 2, 2),
        Tile(R.drawable.castlesidesedge0, 2, 2),
        Tile(R.drawable.castlesides0, 3, 3),
        Tile(R.drawable.castlewall0, 5, 5),
        Tile(R.drawable.castlewallcurveleft0, 3, 3),
        Tile(R.drawable.castlewallcurveright0, 3, 3),
        Tile(R.drawable.castlewalljunction0, 3, 3),
        Tile(R.drawable.castlewallroad0, 3, 3),
        Tile(R.drawable.road0, 8, 8),
        Tile(R.drawable.roadcurve0, 9, 9),
        Tile(R.drawable.roadjunctionsmall0, 4, 4),
        Tile(R.drawable.roadjunctionlarge0, 1, 1),
        Tile(R.drawable.castlewallroad0start, 0, 1)
    ) }

    Box(modifier = Modifier
        .fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly) {
            var iter = 0
            for (i in 0..4) {
                Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.SpaceEvenly) {
                    for (j in 0..4) {
                        Tile(
                            Modifier.weight(1f),
                            tilesMatrix[iter],
                            percentsVisibility.value,
                            totalTiles.value,
                            iter,
                            onChange = { newTile, iter, newTotal ->
                                tilesMatrix[iter] = newTile
                                totalTiles.value = newTotal
                            }
                        )
                        iter+=1
                    }
                }
            }
            Row(modifier = Modifier
                .weight(0.4f)
                .fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly) {
//                Box(modifier = Modifier
//                    .fillMaxHeight()
//                    .aspectRatio(1f)
//                    .padding(2.dp)) {
//                    Icon(
//                        painter = painterResource(R.drawable.baseline_subdirectory_arrow_left_24),
//                        tint = MaterialTheme.colors.onPrimary,
//                        contentDescription = "",
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .clickable {
//                                tilesMatrix[0] =
//                                    Tile(tilesMatrix[0].imageId, 4, tilesMatrix[0].total)
//                            }
//                    )
//                }
//                Box(modifier = Modifier
//                    .fillMaxHeight()
//                    .aspectRatio(1f)
//                    .padding(2.dp)) {
//                    Icon(
//                        painter = painterResource(R.drawable.baseline_subdirectory_arrow_right_24),
//                        tint = MaterialTheme.colors.onPrimary,
//                        contentDescription = "",
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .clickable { Log.i("11", tilesMatrix[0].count.toString()) }
//                    )
//                }
                Box(modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .padding(2.dp)) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_settings_backup_restore_24),
                        tint = MaterialTheme.colors.onPrimary,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                for (i in 0 until objBaseTilesList.baseTileList.size) {
                                    tilesMatrix[i] = Tile(tilesMatrix[i].imageId, objBaseTilesList.baseTileList[i].count, tilesMatrix[i].total)
                                }
                                totalTiles.value = 71
                            }
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .padding(2.dp)) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_percent_24),
                        tint = MaterialTheme.colors.onPrimary,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable { percentsVisibility.value = !percentsVisibility.value }
                    )
                }
            }
        }
    }
}

object objBaseTilesList {
    val baseTileList = mutableStateListOf<Tile>(
        Tile(R.drawable.monastery0, 4, 4),
        Tile(R.drawable.monasteryroad0, 2, 2),
        Tile(R.drawable.castlecenter0shield, 1, 1),
        Tile(R.drawable.castlecenterside0, 3, 3),
        Tile(R.drawable.castlecenterside0shield, 1, 1),
        Tile(R.drawable.castlecenterentry0, 1, 1),
        Tile(R.drawable.castlecenterentry0shield, 2, 2),
        Tile(R.drawable.castleedge3, 3, 3),
        Tile(R.drawable.castleedge3shield, 2, 2),
        Tile(R.drawable.castleedgeroad3, 3, 3),
        Tile(R.drawable.castleedgeroad3shield, 2, 2),
        Tile(R.drawable.castletube0, 1, 1),
        Tile(R.drawable.castletube0shield, 2, 2),
        Tile(R.drawable.castlesidesedge0, 2, 2),
        Tile(R.drawable.castlesides0, 3, 3),
        Tile(R.drawable.castlewall0, 5, 5),
        Tile(R.drawable.castlewallcurveleft0, 3, 3),
        Tile(R.drawable.castlewallcurveright0, 3, 3),
        Tile(R.drawable.castlewalljunction0, 3, 3),
        Tile(R.drawable.castlewallroad0, 3, 3),
        Tile(R.drawable.road0, 8, 8),
        Tile(R.drawable.roadcurve0, 9, 9),
        Tile(R.drawable.roadjunctionsmall0, 4, 4),
        Tile(R.drawable.roadjunctionlarge0, 1, 1),
        Tile(R.drawable.castlewallroad0start, 0, 1)
    )
}