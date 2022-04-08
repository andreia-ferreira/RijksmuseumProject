package pt.penguin.rijksmuseumproject.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import dagger.hilt.android.AndroidEntryPoint
import pt.penguin.rijksmuseumproject.details.model.ArtworkDetailsUiModel
import pt.penguin.rijksmuseumproject.ui.screen.ErrorScreen
import pt.penguin.rijksmuseumproject.ui.screen.LoadingScreen
import pt.penguin.rijksmuseumproject.ui.theme.RijksmuseumProjectTheme

@AndroidEntryPoint
@ExperimentalCoilApi
class ItemDetailsFragment: Fragment() {

    private val viewModel: ItemDetailsViewModel by viewModels()
    private val args: ItemDetailsFragmentArgs by navArgs()

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
                            TopAppBar(
                                title = {},
                                navigationIcon = {
                                    IconButton(onClick = { findNavController().navigateUp() }) {
                                        Icon(
                                            imageVector = Icons.Filled.ArrowBack,
                                            contentDescription = "Back"
                                        )
                                    }

                                }
                            )
                        }
                    ) {
                        ItemDetailsScreen()
                    }
                }
            }
        }
    }

    @Composable
    fun ItemDetailsScreen() {
        val state: ArtworkDetailsUiModel? by viewModel.uiModel.observeAsState()
        viewModel.init(args.objectNumber)

        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            state?.let {
                when(it) {
                    is ArtworkDetailsUiModel.Loading -> LoadingScreen()
                    is ArtworkDetailsUiModel.Error -> ErrorScreen {
                        viewModel.init(args.objectNumber)
                    }
                    is ArtworkDetailsUiModel.Success -> ArtworkDetails(it)
                }
            }
        }
    }

    @Composable
    private fun ArtworkDetails(uiModel: ArtworkDetailsUiModel.Success) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Image(
                painter = rememberImagePainter(uiModel.image),
                contentDescription = uiModel.plaqueDescription,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .height(280.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = uiModel.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraLight,
                text = uiModel.mediumAndDimensionsDetails
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = uiModel.plaqueDescription
            )
        }
    }
}