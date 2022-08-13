package com.tojare.borutoapp.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tojare.borutoapp.R
import com.tojare.borutoapp.ui.theme.LightGray
import com.tojare.borutoapp.ui.theme.StarColor


@Composable
fun RatingWidget(
    modifier: Modifier,
    rating: Double
) {

    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember {
        PathParser().parsePathString(pathData = starPathString).toPath()
    }
    val starPathBounds = remember {
        starPath.getBounds()
    }
    FilledStar(startPath = starPath, startPathBounds = starPathBounds)

}

@Composable
fun FilledStar(
    startPath: Path,
    startPathBounds: Rect,
    scaleFactor: Float = 1f
) {
    Canvas(modifier = Modifier.size(24.dp)) {
        scale(scaleFactor) {
            val canvasSize = this.size
            val pathWidth = startPathBounds.width
            val pathHeight = startPathBounds.height
            val left = (canvasSize.width / 2f) - (pathWidth / 1.7f)
            val top = (canvasSize.height / 2f) - (pathHeight / 1.7f)

            translate(left = left, top = top) {
                drawPath(path = startPath, color = StarColor)
            }
        }
    }
}

@Composable
fun HalfFilledStar(
    startPath: Path,
    startPathBounds: Rect,
    scaleFactor: Float = 1f
) {
    Canvas(modifier = Modifier.size(24.dp)) {
        scale(scaleFactor) {
            val canvasSize = this.size
            val pathWidth = startPathBounds.width
            val pathHeight = startPathBounds.height
            val left = (canvasSize.width / 2f) - (pathWidth / 1.7f)
            val top = (canvasSize.height / 2f) - (pathHeight / 1.7f)

            translate(left = left, top = top) {
                drawPath(path = startPath, color = LightGray.copy(alpha = 0.5f))
            }
            clipPath(path = startPath) {
                drawRect(
                    color = StarColor,
                    size = Size(
                        width = (startPathBounds.maxDimension / 1.7f),
                        height = startPathBounds.maxDimension * scaleFactor
                    )
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FilledStarPreview() {
    RatingWidget(modifier = Modifier, rating = 1.0)
}

@Composable
@Preview(showBackground = true)
fun HalfFilledStarPreview() {
    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember {
        PathParser().parsePathString(pathData = starPathString).toPath()
    }
    val starPathBounds = remember {
        starPath.getBounds()
    }
    HalfFilledStar(startPath = starPath, startPathBounds = starPathBounds)
}