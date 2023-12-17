package com.chslcompany.dictionary.feature_dictionary.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chslcompany.dictionary.feature_dictionary.presentation.viewmodel.WordInfoViewModel

@Composable
fun ShowWordInfo(
    viewModel: WordInfoViewModel,
    state: WordInfoState
) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TextField(
                value = viewModel.searchQuery.value,
                onValueChange = viewModel::onSearch,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "Pesquise...")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            )
            {
                items(state.wordInfoItems.size) { position ->
                    val wordInfo = state.wordInfoItems[position]
                    if (position > 0) {
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    WordInfoItem(wordInfo = wordInfo)
                    if (position < state.wordInfoItems.size - 1) {
                        Divider()
                    }
                }
            }
        }
    }
}