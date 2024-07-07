package com.gyleedev.clonestagram.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _searchedList = MutableStateFlow(list)
    val searchedList: StateFlow<List<SearchedItemData>> = _searchedList

    fun updateSearchId(id: String) {
        viewModelScope.launch {
            _searchQuery.emit(id)
        }
    }

    companion object {
        val list = listOf(
            SearchedItemData(
                link = "https://i.pinimg.com/736x/3f/92/2e/3f922eff0b495e6ff697178758d323da.jpg",
                id = "android",
                status = "android",
                unWatchedStory = true
            ),
            SearchedItemData(
                link = "https://i.pinimg.com/736x/3f/92/2e/3f922eff0b495e6ff697178758d323da.jpg",
                id = "android",
                status = "android",
                unWatchedStory = false
            ),
            SearchedItemData(
                link = "https://i.pinimg.com/736x/3f/92/2e/3f922eff0b495e6ff697178758d323da.jpg",
                id = "android",
                status = "android",
                unWatchedStory = true
            ),
            SearchedItemData(
                link = "https://i.pinimg.com/736x/3f/92/2e/3f922eff0b495e6ff697178758d323da.jpg",
                id = "android",
                status = "android",
                unWatchedStory = false
            ),
            SearchedItemData(
                link = "https://i.pinimg.com/736x/3f/92/2e/3f922eff0b495e6ff697178758d323da.jpg",
                id = "android",
                status = "android",
                unWatchedStory = true
            ),
            SearchedItemData(
                link = "https://i.pinimg.com/736x/3f/92/2e/3f922eff0b495e6ff697178758d323da.jpg",
                id = "android",
                status = "android",
                unWatchedStory = false
            ),
            SearchedItemData(
                link = "https://i.pinimg.com/736x/3f/92/2e/3f922eff0b495e6ff697178758d323da.jpg",
                id = "android",
                status = "android",
                unWatchedStory = true
            ),
            SearchedItemData(
                link = "https://i.pinimg.com/736x/3f/92/2e/3f922eff0b495e6ff697178758d323da.jpg",
                id = "android",
                status = "android",
                unWatchedStory = false
            ),
            SearchedItemData(
                link = "https://i.pinimg.com/736x/3f/92/2e/3f922eff0b495e6ff697178758d323da.jpg",
                id = "android",
                status = "android",
                unWatchedStory = true
            ),
            SearchedItemData(
                link = "https://i.pinimg.com/736x/3f/92/2e/3f922eff0b495e6ff697178758d323da.jpg",
                id = "android",
                status = "android",
                unWatchedStory = false
            ),
            SearchedItemData(
                link = "https://i.pinimg.com/736x/3f/92/2e/3f922eff0b495e6ff697178758d323da.jpg",
                id = "android",
                status = "android",
                unWatchedStory = true
            ),
            SearchedItemData(
                link = "https://i.pinimg.com/736x/3f/92/2e/3f922eff0b495e6ff697178758d323da.jpg",
                id = "android",
                status = "android",
                unWatchedStory = false
            )
        )
    }
}
