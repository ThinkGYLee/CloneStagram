package com.gyleedev.clonestagram.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gyleedev.clonestagram.R
import com.gyleedev.clonestagram.ui.public.CommentModalBottomSheet
import com.gyleedev.clonestagram.ui.public.ItemData
import com.gyleedev.clonestagram.ui.public.MenuModalBottomSheet
import com.gyleedev.clonestagram.ui.public.PublicItemDetail
import com.gyleedev.clonestagram.ui.public.ShareModalBottomSheet
import com.gyleedev.clonestagram.ui.public.UserIconDefinition
import com.gyleedev.clonestagram.ui.public.UserIconImageType
import com.gyleedev.clonestagram.ui.public.UserIconType
import com.gyleedev.clonestagram.ui.public.UserImageComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val verticalScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    var isCommentBottomSheetTrue by remember { mutableStateOf(false) }
    val comments = viewModel.commentList.collectAsStateWithLifecycle()
    var bottomSheetBelongedId by remember {
        mutableStateOf("")
    }
    var isShareBottomSheetTrue by remember { mutableStateOf(false) }
    var isMenuBottomSheetTrue by remember { mutableStateOf(false) }

    val commentSheetState = rememberModalBottomSheetState()
    val shareSheetState = rememberModalBottomSheetState()
    val menuSheetState = rememberModalBottomSheetState()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    scrolledContainerColor = TopAppBarDefaults.topAppBarColors().containerColor
                ),
                title = {
                    Icon(
                        painter = painterResource(id = R.drawable.instagram_text_logo),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxHeight()
                            .sizeIn(maxWidth = 100.dp)
                            .clickable { }
                    )
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.heart_svgrepo_com),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.facebook_messenger_2880),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                modifier = Modifier,
                scrollBehavior = verticalScrollBehavior
            )
        },
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(verticalScrollBehavior.nestedScrollConnection)
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // story row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    UserImageComponent(
                        userIconDefinition = UserIconDefinition(
                            iconImageType = UserIconImageType.IconFromDrawableType(R.drawable.icons8_test_account_96),
                            hasStory = false,
                            userIconType = UserIconType.IconStory(isAddable = true)
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "내 스토리",
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                for (i in 0..20) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        UserImageComponent(
                            userIconDefinition = UserIconDefinition(
                                iconImageType = UserIconImageType.IconFromDrawableType(R.drawable.icons8_test_account_96),
                                hasStory = true,
                                userIconType = UserIconType.IconStory(isAddable = false)
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "내 스토리",
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier
                        )
                    }

                    if (i < 20) {
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                }
            }
            // 게시물
            ItemData.initialItem.forEach { item ->
                PublicItemDetail(
                    itemData = item,
                    comments = comments.value,
                    onCommentBottomSheetStatusChanged = {
                        isCommentBottomSheetTrue = true
                        bottomSheetBelongedId = item.ownerId
                    },
                    onShareBottomSheetStatusChanged = {
                        isShareBottomSheetTrue = true
                        bottomSheetBelongedId = item.ownerId
                    },
                    onMenuBottomSheetStatusChanged = {
                        isMenuBottomSheetTrue = true
                    }
                )
            }
        }

        if (isCommentBottomSheetTrue) {
            CommentModalBottomSheet(
                ownerId = bottomSheetBelongedId,
                comments = comments.value,
                closeSheet = {
                    isCommentBottomSheetTrue = false
                    bottomSheetBelongedId = ""
                },
                onAddComment = {
                    viewModel.addComment(it)
                },
                sheetState = commentSheetState
            )
        }

        if (isShareBottomSheetTrue) {
            ShareModalBottomSheet(
                ownerId = bottomSheetBelongedId,
                closeSheet = {
                    isShareBottomSheetTrue = false
                    bottomSheetBelongedId = ""
                },
                sheetState = shareSheetState
            )
        }
        if (isMenuBottomSheetTrue) {
            MenuModalBottomSheet(
                closeSheet = {
                    isMenuBottomSheetTrue = false
                },
                sheetState = menuSheetState
            )
        }
    }
}
