package com.feresr.composebug

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.onActive
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import com.feresr.composebug.ui.ComposeBugTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBugTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Box(Modifier.fillMaxSize()) {
                        GrowingImage()
                    }
                }
            }
        }
    }
}

@Composable
fun GrowingImage() {

    val image = imageResource(id = R.drawable.duck)

    val animatedProgress = animatedFloat(400f)

    onActive(callback = {
        animatedProgress.animateTo(
                targetValue = 3000f,
                anim = InfiniteRepeatableSpec(
                        tween(durationMillis = 8000, easing = LinearEasing),
                        RepeatMode.Restart
                )
        )
    })
    
    val modifier = with(AmbientDensity.current) {
        Modifier
                .size(
                        animatedProgress.value.toDp(),
                        animatedProgress.value.toDp(),
                )
    }

    Image(
            alignment = Alignment.TopStart,
            bitmap = image,
            contentScale = ContentScale.FillBounds,
            modifier = modifier
    )
}