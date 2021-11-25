package com.example.imageappktor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.imageappktor.ui.theme.ImageAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalCoilApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageAppTheme() {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    val viewModel: MainViewModel = hiltViewModel()
                    val image = viewModel.state.value.image
                    val isLoading = viewModel.state.value.isLoading
                    image?.let {
                        Image(
                            painter = rememberImagePainter(
                                data = image.imageUrl,
                                builder = { crossfade(true) }
                            ),
                            contentDescription = "image"
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = image.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = image.description)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Button(
                        onClick = viewModel::getRandomImage,
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(text = "Next image!")
                    }
                    Spacer(Modifier.height(8.dp))
                    if(isLoading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}