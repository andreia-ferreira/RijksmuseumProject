package pt.penguin.rijksmuseumproject.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDirections
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import dagger.hilt.android.AndroidEntryPoint
import pt.penguin.rijksmuseumproject.R
import pt.penguin.rijksmuseumproject.home.model.MuseumCollectionUiModel
import pt.penguin.rijksmuseumproject.ui.screen.ErrorScreen
import pt.penguin.rijksmuseumproject.ui.screen.LoadingScreen
import pt.penguin.rijksmuseumproject.ui.theme.RijksmuseumProjectTheme
import javax.inject.Inject
import javax.inject.Provider

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
                    HomeScreen(viewModel) { dest -> findNavController().navigate(dest) }
                }
            }
        }
    }

    @Composable
    fun HomeScreen(viewModel: HomeViewModel, onNavigate: (NavDirections) -> Unit) {
        val state: MuseumCollectionUiModel? by viewModel.uiModel.observeAsState()

        viewModel.init()
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            state?.let {
                when(it) {
                    is MuseumCollectionUiModel.Loading -> LoadingScreen()
                    is MuseumCollectionUiModel.Error -> ErrorScreen()
                    is MuseumCollectionUiModel.Success -> {
                        LazyColumn(horizontalAlignment = CenterHorizontally) {
                            it.itemList.forEach { artwork ->
                                item { MuseumCard(uiModel = artwork, onNavigate) }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun MuseumCard(
        uiModel: MuseumCollectionUiModel.Success.ItemUiModel,
        onNavigate: (NavDirections) -> Unit
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp)
                .clickable {
                    val action = HomeFragmentDirections.actionOpenDetails(uiModel.objectNumber)
                    onNavigate(action)
                }
        ) {
            Column(
                modifier = Modifier
                    .height(intrinsicSize = IntrinsicSize.Max)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = rememberImagePainter(uiModel.image),
                    contentDescription = uiModel.longTitle,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(180.dp)
                        .fillMaxWidth()
                        .align(CenterHorizontally)
                )
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = uiModel.title,
                        maxLines = 1,
                        fontSize = 14.sp
                    )
                    Text(
                        text = uiModel.author,
                        maxLines = 1,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }

}