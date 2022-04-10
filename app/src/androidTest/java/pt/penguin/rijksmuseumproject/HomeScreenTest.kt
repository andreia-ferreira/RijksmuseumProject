package pt.penguin.rijksmuseumproject

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pt.penguin.rijksmuseumproject.home.HomeScreen
import pt.penguin.rijksmuseumproject.home.model.MuseumCollectionUiModel
import pt.penguin.rijksmuseumproject.ui.theme.RijksmuseumProjectTheme

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainMuseumActivity>()

    private val successUiState = MuseumCollectionUiModel.Success(
        itemList = listOf(MuseumCollectionUiModel.Success.ItemUiModel(
            objectNumber = "123456",
            title = "Nice painting",
            longTitle = "Nice painting, Jane Doe, 2022",
            image = "",
            author = "Jane Doe"
        ))
    )

    @Test
    fun checkSuccessUiState() {
        composeTestRule.setContent {
            RijksmuseumProjectTheme {
                HomeScreen(uiState = successUiState, onLoadMore = {}, onClickItem = {} )
            }
        }

        composeTestRule.onNodeWithTag(
            testTag = composeTestRule.activity.getString(R.string.test_tag_section_title),
            useUnmergedTree = true
        ).assertIsDisplayed().assertTextEquals(successUiState.itemList[0].author)
        composeTestRule.onNodeWithTag(
            testTag = composeTestRule.activity.getString(R.string.test_tag_card),
            useUnmergedTree = true
        ).assertIsDisplayed()
        composeTestRule.onNodeWithTag(
            testTag = composeTestRule.activity.getString(R.string.test_tag_card_title),
            useUnmergedTree = true
        ).assertTextEquals(successUiState.itemList[0].title)
    }

    @Test
    fun checkLoadingUiState() {
        composeTestRule.setContent {
            RijksmuseumProjectTheme {
                HomeScreen(uiState = MuseumCollectionUiModel.Loading, onLoadMore = {}, onClickItem = {} )
            }
        }

        composeTestRule.onNodeWithTag(
            testTag = composeTestRule.activity.getString(R.string.test_tag_loading),
            useUnmergedTree = true
        ).assertIsDisplayed()
        composeTestRule.onNodeWithTag(
            testTag = composeTestRule.activity.getString(R.string.test_tag_card),
            useUnmergedTree = true
        ).assertDoesNotExist()
    }

    @Test
    fun checkErrorUiState() {
        composeTestRule.setContent {
            RijksmuseumProjectTheme {
                HomeScreen(uiState = MuseumCollectionUiModel.Error, onLoadMore = {}, onClickItem = {} )
            }
        }

        composeTestRule.onNodeWithTag(
            testTag = composeTestRule.activity.getString(R.string.test_tag_error_load),
            useUnmergedTree = true
        ).assertIsDisplayed().assertTextEquals(composeTestRule.activity.getString(R.string.error_loading))
        composeTestRule.onNodeWithTag(
            testTag = composeTestRule.activity.getString(R.string.test_tag_error_retry),
            useUnmergedTree = true
        ).assertIsDisplayed().assertTextEquals(composeTestRule.activity.getString(R.string.error_retry))
        composeTestRule.onNodeWithTag(
            testTag = composeTestRule.activity.getString(R.string.test_tag_card),
            useUnmergedTree = true
        ).assertDoesNotExist()
    }
}