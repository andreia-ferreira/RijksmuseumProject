package pt.penguin.rijksmuseumproject.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import pt.penguin.rijksmuseumproject.R
import pt.penguin.rijksmuseumproject.details.model.ArtworkDetailsUiModel
import pt.penguin.rijksmuseumproject.ui.screen.ErrorScreen
import pt.penguin.rijksmuseumproject.ui.screen.LoadingScreen

@Composable
fun ItemDetailsScreen(uiState: ArtworkDetailsUiModel?, onRetry: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        uiState?.let {
            when(it) {
                is ArtworkDetailsUiModel.Loading -> LoadingScreen()
                is ArtworkDetailsUiModel.Error -> ErrorScreen { onRetry() }
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
            modifier = Modifier.testTag(stringResource(id = R.string.test_tag_detail_title)),
            text = uiModel.title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.testTag(stringResource(id = R.string.test_tag_detail_dimensions)),
            fontSize = 12.sp,
            fontWeight = FontWeight.ExtraLight,
            text = uiModel.mediumAndDimensionsDetails
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.testTag(stringResource(id = R.string.test_tag_detail_plaque)),
            text = uiModel.plaqueDescription
        )
    }
}