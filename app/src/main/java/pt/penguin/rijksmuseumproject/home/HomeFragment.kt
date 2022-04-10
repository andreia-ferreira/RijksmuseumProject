package pt.penguin.rijksmuseumproject.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import coil.annotation.ExperimentalCoilApi
import dagger.hilt.android.AndroidEntryPoint
import pt.penguin.rijksmuseumproject.ui.RijksmuseumTopAppBar
import pt.penguin.rijksmuseumproject.ui.theme.RijksmuseumProjectTheme

@ExperimentalCoilApi
@AndroidEntryPoint
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
                        topBar = { RijksmuseumTopAppBar(navController = findNavController()) }
                    ) {
                        val uiState by viewModel.uiModel.collectAsState()
                        HomeScreen(
                            uiState = uiState,
                            onLoadMore = { viewModel.loadData() },
                            onClickItem = { dest -> findNavController().navigate(dest) }
                        )
                    }
                }
            }
        }
    }
}