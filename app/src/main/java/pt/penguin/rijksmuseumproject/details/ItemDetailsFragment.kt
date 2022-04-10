package pt.penguin.rijksmuseumproject.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.annotation.ExperimentalCoilApi
import dagger.hilt.android.AndroidEntryPoint
import pt.penguin.rijksmuseumproject.details.model.ArtworkDetailsUiModel
import pt.penguin.rijksmuseumproject.ui.RijksmuseumTopAppBar
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
                        topBar = { RijksmuseumTopAppBar(navController = findNavController()) }
                    ) {
                        val state: ArtworkDetailsUiModel? by viewModel.uiModel.observeAsState()
                        viewModel.init(args.objectNumber)
                        ItemDetailsScreen(state) { viewModel.init(args.objectNumber) }
                    }
                }
            }
        }
    }
}