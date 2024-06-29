package com.gyleedev.clonestagram.ui.myProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gyleedev.clonestagram.R
import com.gyleedev.clonestagram.ui.public.CommentInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel @Inject constructor() : ViewModel() {
    private val _commentList = MutableStateFlow(list)
    val commentList: StateFlow<List<CommentInformation>> = _commentList

    fun addComment(comment: String) {
        viewModelScope.launch {
            val copyList = mutableListOf<CommentInformation>()
            copyList.addAll(_commentList.value)
            copyList.add(
                CommentInformation(
                    userImage = R.drawable.icons8_test_account_48,
                    userId = "think_gy_lee",
                    writtenHourAgo = 0,
                    isWrittenByAuthor = true,
                    content = comment,
                    heartCount = 0
                )
            )
            _commentList.emit(copyList)
        }
    }

    companion object {
        val list = listOf(
            CommentInformation(
                userId = "think_gy_lee",
                userImage = R.drawable.icons8_test_account_48,
                writtenHourAgo = 2,
                content = "I can't wait for the New Android Os !",
                heartCount = 3621,
                isWrittenByAuthor = true
            )
        )
    }
}
