package com.gyleedev.clonestagram.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchScreen(
    modifier: Modifier
) {
    Scaffold(
        topBar = {},
        modifier = modifier
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .consumeWindowInsets(innerPadding)) {

        }

    }
}
