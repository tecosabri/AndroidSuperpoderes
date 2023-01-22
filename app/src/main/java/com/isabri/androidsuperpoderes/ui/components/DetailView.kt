package com.isabri.androidsuperpoderes.ui.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.keepcoding.androidsuperpoderes.R


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailView(label: String = "Wolverine", photoURL: String = "https://upload.wikimedia.org/wikipedia/commons/9/90/Spiderman.JPG", onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .size(300.dp)
            .clickable {
                Log.d("LOG", "click")
            },
        elevation = 20.dp,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(4.dp, Color.LightGray),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(1f),
        ) {
            DetailImage(photoURL = photoURL)
            Text(
                text = label,
                color = Color.White,
                style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(10.dp)
            )
        }
    }
}

@Preview
@Composable
fun DetailImage(photoURL: String = "https://upload.wikimedia.org/wikipedia/commons/9/90/Spiderman.JPG") {
    // Gradient
    var sizeImage by remember { mutableStateOf(IntSize.Zero) }
    val gradient = Brush.verticalGradient(
        colors = listOf(Color.Transparent, Color.Black),
        startY = sizeImage.height.toFloat()/3,  // 1/3
        endY = sizeImage.height.toFloat())
    // Download image
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(photoURL)
            .crossfade(true)
            .build(),
        placeholder = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "Character image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .onGloballyPositioned {
                sizeImage = it.size
            }
            .fillMaxSize(3f)
    )
    // Put gradient over the image
    Box(
        modifier = Modifier
            .size(sizeImage.toSize().maxDimension.dp)
            .background(gradient)
    )
}
