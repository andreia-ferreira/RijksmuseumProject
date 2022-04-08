package pt.penguin.rijksmuseumproject.ui

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import pt.penguin.rijksmuseumproject.R

@Composable
fun RijksmuseumTopAppBar(
    navController: NavController
) {
    val hasBackStack = navController.currentDestination?.id != R.id.homeFragment
    TopAppBar(
        title = {
                Text(
                    text = "Rijksmuseum"
                )
        },
        navigationIcon = {
            if (hasBackStack) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Go back"
                    )
                }
            }
        }
    )
}