package pt.penguin.rijksmuseumproject

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pt.penguin.rijksmuseumproject.details.ItemDetailsScreen
import pt.penguin.rijksmuseumproject.details.model.ArtworkDetailsUiModel
import pt.penguin.rijksmuseumproject.ui.theme.RijksmuseumProjectTheme

@RunWith(AndroidJUnit4::class)
class ItemDetailsScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainMuseumActivity>()

    private val successUiState = ArtworkDetailsUiModel.Success(
        objectNumber = "123456",
        title = "Nice painting",
        image = "",
        artist = "Jane Doe",
        mediumAndDimensionsDetails = "canvas | 10 cm | 10 cm | 100 g",
        plaqueDescription = "Indeed a nice painting. Wow."
    )

    @Test
    fun checkSuccessUiState() {
        composeTestRule.setContent {
            RijksmuseumProjectTheme {
                ItemDetailsScreen(successUiState) {}
            }
        }

        composeTestRule.onNodeWithTag(
            testTag = composeTestRule.activity.getString(R.string.test_tag_detail_title),
            useUnmergedTree = true
        ).assertIsDisplayed().assertTextEquals(successUiState.title)
        composeTestRule.onNodeWithTag(
            testTag = composeTestRule.activity.getString(R.string.test_tag_detail_dimensions),
            useUnmergedTree = true
        ).assertIsDisplayed().assertTextEquals(successUiState.mediumAndDimensionsDetails)
        composeTestRule.onNodeWithTag(
            testTag = composeTestRule.activity.getString(R.string.test_tag_detail_plaque),
            useUnmergedTree = true
        ).assertTextEquals(successUiState.plaqueDescription)
    }

    @Test
    fun checkLoadingUiState() {
        composeTestRule.setContent {
            RijksmuseumProjectTheme {
                ItemDetailsScreen(uiState = ArtworkDetailsUiModel.Loading, onRetry = {})
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
                ItemDetailsScreen(uiState = ArtworkDetailsUiModel.Error, onRetry = {})
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