package pt.penguin.rijksmuseumproject.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import dagger.hilt.android.AndroidEntryPoint
import pt.penguin.rijksmuseumproject.home.model.MuseumCollectionUiModel
import pt.penguin.rijksmuseumproject.ui.screen.ErrorScreen
import pt.penguin.rijksmuseumproject.ui.screen.LoadingScreen
import pt.penguin.rijksmuseumproject.ui.theme.RijksmuseumProjectTheme

@AndroidEntryPoint
@ExperimentalCoilApi
class HomeFragment: Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                RijksmuseumProjectTheme {
                    Scaffold(
                        topBar = {
                            TopAppBar(title = { Text(text = "Rijksmuseum") })
                        }
                    ) {
                        HomeScreen(viewModel) { dest -> findNavController().navigate(dest) }
                    }
                }
            }
        }
    }

    @Composable
    fun HomeScreen(viewModel: HomeViewModel, onClickItem: (NavDirections) -> Unit) {
        val uiState by viewModel.uiModel.collectAsState()

        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            uiState.let {
                when(it) {
                    is MuseumCollectionUiModel.Loading -> LoadingScreen()
                    is MuseumCollectionUiModel.Error -> ErrorScreen { viewModel.loadData() }
                    is MuseumCollectionUiModel.Success -> {
                        LazyColumn(
                            horizontalAlignment = CenterHorizontally
                        ) {
                            it.itemList.forEach { artwork ->
                                item { MuseumCard(uiState = artwork, onClickItem) }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun MuseumCard(
        uiState: MuseumCollectionUiModel.Success.ItemUiModel,
        onNavigate: (NavDirections) -> Unit
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp)
                .clickable {
                    val action = HomeFragmentDirections.actionOpenDetails(uiState.objectNumber)
                    onNavigate(action)
                },
            elevation = 8.dp
        ) {
            Row(
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = rememberImagePainter(uiState.image),
                    contentDescription = uiState.longTitle,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(120.dp)
                        .align(CenterVertically)
                )
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = uiState.title,
                        maxLines = 1,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = uiState.author,
                        maxLines = 1,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.ExtraLight
                    )
                }
            }
        }
    }

}