package pt.penguin.rijksmuseumproject.home

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDirections
import coil.compose.rememberImagePainter
import pt.penguin.rijksmuseumproject.R
import pt.penguin.rijksmuseumproject.home.model.MuseumCollectionUiModel
import pt.penguin.rijksmuseumproject.ui.screen.ErrorScreen
import pt.penguin.rijksmuseumproject.ui.screen.LoadingScreen

@Composable
fun HomeScreen(
    uiState: MuseumCollectionUiModel,
    onLoadMore: () -> Unit,
    onClickItem: (NavDirections) -> Unit
) {

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
            when (it) {
                is MuseumCollectionUiModel.Loading -> LoadingScreen()
                is MuseumCollectionUiModel.Error -> ErrorScreen { onLoadMore() }
                is MuseumCollectionUiModel.Success -> {
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        state = listState
                    ) {
                        it.itemList.forEachIndexed { index, itemUiModel ->
                            val isNewSection =
                                index == 0 || itemUiModel.author != it.itemList[index - 1].author
                            if (isNewSection) {
                                item {
                                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                                        Text(
                                            modifier = Modifier
                                                .padding(vertical = 8.dp)
                                                .testTag(stringResource(
                                                    id = R.string.test_tag_section_title)
                                                ),
                                            text = itemUiModel.author,
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.ExtraLight
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
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .testTag(
                                            stringResource(
                                                id = R.string.test_tag_load_more_indicator
                                            )
                                        )
                                )
                                onLoadMore()
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
            }
            .testTag(stringResource(R.string.test_tag_card)),
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
                    .align(Alignment.CenterVertically)
            )
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
                    .testTag(stringResource(id = R.string.test_tag_card_title)),
                text = uiState.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}