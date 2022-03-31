package pt.penguin.rijksmuseumproject.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import pt.penguin.rijksmuseumproject.ui.theme.RijksmuseumProjectTheme

@ExperimentalCoilApi
class HomeFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                RijksmuseumProjectTheme {
                    HomeScreen()
                }
            }
        }
    }

    @Preview
    @Composable
    fun HomeScreen() {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(horizontalAlignment = CenterHorizontally) {
                item {
                    Card(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .height(intrinsicSize = IntrinsicSize.Max)
                                .fillMaxWidth()
                        ) {
                            Image(
                                painter = rememberImagePainter("https://lh3.googleusercontent.com/J-mxAE7CPu-DXIOx4QKBtb0GC4ud37da1QK7CzbTIDswmvZHXhLm4Tv2-1H3iBXJWAW_bHm7dMl3j5wv_XiWAg55VOM=s0"),
                                contentDescription = "De Nachtwacht, Rembrandt van Rijn, 1642",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(100.dp)
                                    .fillMaxWidth()
                                    .align(CenterHorizontally)
                            )
                            Column(
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Text(
                                    text = "De Nachtwacht (1642)",
                                    maxLines = 1,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = "Rembrandt van Rijn",
                                    maxLines = 1,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}