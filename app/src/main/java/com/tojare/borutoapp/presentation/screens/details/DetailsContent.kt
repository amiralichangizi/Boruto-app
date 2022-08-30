package com.tojare.borutoapp.presentation.screens.details

import android.graphics.Color.parseColor
import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.tojare.borutoapp.R
import com.tojare.borutoapp.domain.model.Hero
import com.tojare.borutoapp.presentation.components.InfoBox
import com.tojare.borutoapp.presentation.components.OrderedList
import com.tojare.borutoapp.ui.theme.*
import com.tojare.borutoapp.util.Constant.BASE_URL
import com.tojare.borutoapp.util.Constant.SAMPLE_HERO
import kotlin.math.log


@Composable
@ExperimentalMaterialApi
fun DetailsContent(
    navController: NavHostController,
    hero: Hero?,
    colors: Map<String, String>
) {

    var vibrant by remember { mutableStateOf("#000000") }
    var darkVibrant by remember { mutableStateOf("#000000") }
    var onDarkVibrant by remember { mutableStateOf("#ffffff") }

    LaunchedEffect(key1 = hero) {
        vibrant = colors["vibrant"]!!
        darkVibrant = colors["darkVibrant"]!!
        onDarkVibrant = colors["onDarkVibrant"]!!
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = Color(parseColor(darkVibrant))
    )

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    )


    val currentSheetFraction = scaffoldState.currentSheetFraction

    val radiusAnim by animateDpAsState(
        targetValue = (currentSheetFraction * 40).dp
    )


    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetShape = RoundedCornerShape(topStart = radiusAnim, topEnd = radiusAnim),
        sheetContent = {
            hero?.let {
                Log.d("a2debug", "DetailsContent: $vibrant ----- $darkVibrant ")
                BottomSheetContent(
                    hero = it,
                    infoBoxIconColor = Color(parseColor(vibrant)),
                    sheetBackgroundColor = Color(parseColor(darkVibrant)),
                    contentColor = Color(parseColor(onDarkVibrant)),
                )
            }
                ?: Box(modifier = Modifier.fillMaxSize())
        },
        content = {
            hero?.let { nonNullHero ->
                BackgroundContent(
                    heroImage = nonNullHero.image,
                    imageFraction = currentSheetFraction,
                    backgroundColor = Color(parseColor(darkVibrant)),
                    iconColor = Color(parseColor(onDarkVibrant))
                ) {
                    navController.navigateUp()
                }
            } ?: Text(text = "")
        },
    )


}

@Composable
fun BottomSheetContent(
    hero: Hero,
    infoBoxIconColor: Color = MaterialTheme.colors.primary,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.titleColor
) {

    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(LARGE_PADDING)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                modifier = Modifier
                    .size(32.dp)
                    .weight(2f),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = stringResource(id = R.string.app_logo),
                tint = contentColor
            )
            Text(
                modifier = Modifier
                    .weight(8f),
                text = hero.name,
                color = contentColor,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold
            )

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MEDIUM_PADDING + 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoBox(
                icon = painterResource(id = R.drawable.ic_bolt),
                iconColor = infoBoxIconColor,
                bigText = hero.power.toString(),
                smallText = stringResource(R.string.power),
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.ic_calendar),
                iconColor = infoBoxIconColor,
                bigText = hero.month,
                smallText = stringResource(R.string.month),
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.ic_cake),
                iconColor = infoBoxIconColor,
                bigText = hero.day,
                smallText = stringResource(R.string.birthday),
                textColor = contentColor
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(R.string.about),
            color = contentColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .alpha(ContentAlpha.medium)
                .padding(bottom = MEDIUM_PADDING),
            text = hero.about,
            color = contentColor,
            fontSize = MaterialTheme.typography.body1.fontSize,
            maxLines = 7
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MEDIUM_PADDING),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OrderedList(
                title = stringResource(R.string.family),
                items = hero.family,
                textColor = contentColor
            )
            OrderedList(
                title = stringResource(R.string.abilities),
                items = hero.abilities,
                textColor = contentColor
            )
            OrderedList(
                title = stringResource(R.string.nature_types),
                items = hero.natureTypes,
                textColor = contentColor
            )
        }


    }
}

@Composable
fun BackgroundContent(
    heroImage: String,
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colors.surface,
    iconColor: Color = MaterialTheme.colors.titleColor,
    onCloseClicked: () -> Unit
) {
    val imageUrl = "$BASE_URL${heroImage}"

    val painter = rememberAsyncImagePainter(
        model = imageUrl,
        error = painterResource(id = R.drawable.ic_placeholder)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(imageFraction + 0.4f)
                .align(Alignment.TopStart),
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {

            IconButton(
                modifier = Modifier.padding(SMALL_PADDING),
                onClick = { onCloseClicked() }
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    tint = iconColor,
                    contentDescription = null
                )
            }

        }
    }
}

@ExperimentalMaterialApi
val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {
        val fraction = bottomSheetState.progress.fraction
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        return when {
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Collapsed -> 1f
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Expanded -> 0f
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Expanded -> 1f - fraction
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Collapsed -> 0f + fraction
            else -> fraction
        }
    }

@Composable
@Preview
fun BottomSheetContentPreview() {
    BottomSheetContent(hero = SAMPLE_HERO)
}