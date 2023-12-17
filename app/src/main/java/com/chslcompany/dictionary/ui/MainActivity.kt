package com.chslcompany.dictionary.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chslcompany.dictionary.feature_dictionary.presentation.WordInfoItem
import com.chslcompany.dictionary.feature_dictionary.presentation.viewmodel.WordInfoViewModel
import com.chslcompany.dictionary.ui.theme.DictionaryTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DictionaryTheme {
               val viewModel : WordInfoViewModel = hiltViewModel()
               val state = viewModel.state.value
               val scaffoldState = rememberScaffoldState()

               LaunchedEffect(key1 = true){
                   viewModel.eventFlow.collectLatest { event ->
                       when(event){
                           is WordInfoViewModel.UiEvent.ShowSnackBar ->{
                               scaffoldState.snackbarHostState.showSnackbar(
                                   message = event.message
                               )
                           }
                       }
                   }
               }
                Scaffold(
                  scaffoldState = scaffoldState
                ) { paddingValue ->
                    Box(
                        modifier = Modifier
                            .background(MaterialTheme.colors.background)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValue)
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
                                items(state.wordInfoItems.size){ position->
                                   val wordInfo = state.wordInfoItems[position]
                                   if (position > 0){
                                       Spacer(modifier = Modifier.height(8.dp))
                                   }
                                   WordInfoItem(wordInfo = wordInfo)
                                    if (position < state.wordInfoItems.size - 1){
                                        Divider()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

