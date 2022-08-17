package com.tojare.borutoapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.tojare.borutoapp.R
import com.tojare.borutoapp.domain.model.Hero
import com.tojare.borutoapp.navigation.Screen
import com.tojare.borutoapp.presentation.components.RatingWidget
import com.tojare.borutoapp.presentation.components.ShimmerEffect
import com.tojare.borutoapp.ui.theme.*
import com.tojare.borutoapp.util.Constant.BASE_URL
import com.tojare.borutoapp.util.Constant.SAMPLE_HERO

@Composable
fun ListContent(
    heroes: LazyPagingItems<Hero>,
    navController: NavHostController
) {

    val result = handlePagingResult(heroes = heroes)
    if (result) {
        LazyColumn(
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
        ) {
            items(
                items = heroes,
                key = { hero -> hero.id }
            ) { hero ->
                hero?.let {
                    HeroItem(hero = it, navController = navController)
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(
    heroes: LazyPagingItems<Hero>
): Boolean {
    heroes.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            error != null -> {
                EmptyScreen(error = error, heroes = heroes)
                false
            }
            itemCount < 1 -> {
                EmptyScreen()
                false
            }

            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }

            else -> true
        }
    }
}

@Composable
fun HeroItem(
    hero: Hero,
    navController: NavHostController
) {

    Box(
        modifier = Modifier
            .height(HERO_ITEM_HEIGHT)
            .clickable {
                navController.navigate(Screen.Details.passHeroId(heroId = hero.id))
            },
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(shape = RoundedCornerShape(LARGE_PADDING)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("$BASE_URL${hero.image}")
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_placeholder),
                contentDescription = stringResource(R.string.hero_image),
                error = painterResource(id = R.drawable.ic_placeholder),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(
                bottomStart = LARGE_PADDING,
                bottomEnd = LARGE_PADDING
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = MEDIUM_PADDING)
            ) {
                Text(
                    text = hero.name,
                    color = MaterialTheme.colors.topBarContentColor,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = hero.about,
                    color = Color.White.copy(alpha = ContentAlpha.medium),
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier.padding(top = SMALL_PADDING),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RatingWidget(
                        modifier = Modifier.padding(end = SMALL_PADDING),
                        rating = hero.rating
                    )
                    Text(
                        text = "(${hero.rating})",
                        textAlign = TextAlign.Center,
                        color = Color.White.copy(alpha = ContentAlpha.medium)
                    )
                }

            }

        }
    }
}

@Composable
@Preview
fun HeroItemPreview() {
    val sample = SAMPLE_HERO
    HeroItem(hero = sample, navController = rememberNavController())

}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun HeroItemDarkPreview() {
    val sample = SAMPLE_HERO
    HeroItem(hero = sample, navController = rememberNavController())

}