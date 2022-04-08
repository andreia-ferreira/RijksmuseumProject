package pt.penguin.rijksmuseumproject.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Colors
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
        val listState = rememberLazyListState()
        val showButton by remember {
            derivedStateOf {
                listState.firstVisibleItemIndex > 1
            }
        }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            uiState.let {
                when(it) {
                    is MuseumCollectionUiModel.Loading -> LoadingScreen()
                    is MuseumCollectionUiModel.Error -> ErrorScreen { viewModel.loadData() }
                    is MuseumCollectionUiModel.Success -> {
                        LazyColumn(
                            horizontalAlignment = CenterHorizontally,
                            state = listState
                        ) {
                            it.itemList.forEachIndexed { index, itemUiModel ->
                                val isNewSection = index == 0 || itemUiModel.author != it.itemList[index - 1].author
                                if (isNewSection) {
                                    item {
                                        Column( modifier = Modifier.padding(horizontal = 16.dp)) {
                                            Text(
                                                modifier = Modifier.padding(vertical = 8.dp),
                                                text = itemUiModel.author,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.ExtraLight,
                                            )
                                            Divider(
                                                modifier = Modifier.fillMaxWidth(),
                                                thickness = 1.dp,
                                                color = MaterialTheme.colors.primary
                                            )
                                        }
                                    }
                                }
                                item { MuseumCard(uiState = itemUiModel, onClickItem) }
                            }
                            item {
                                AnimatedVisibility(visible = showButton) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.padding(16.dp)
                                    )
                                    viewModel.loadData()
                                }
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
                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
                .clickable {
                    val action = HomeFragmentDirections.actionOpenDetails(uiState.objectNumber)
                    onNavigate(action)
                },
            elevation = 8.dp
        ) {
            Row(
                modifier = Modifier
                    .height(80.dp)
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
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(CenterVertically),
                    text = uiState.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }

}