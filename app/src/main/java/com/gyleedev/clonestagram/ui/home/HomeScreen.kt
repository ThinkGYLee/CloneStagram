package com.gyleedev.clonestagram.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.foundation.text2.input.insert
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gyleedev.clonestagram.R
import com.gyleedev.clonestagram.ui.public.CommentInformation
import com.gyleedev.clonestagram.ui.public.ItemData
import com.gyleedev.clonestagram.ui.public.PublicItemDetail
import com.gyleedev.clonestagram.ui.public.UserIconDefinition
import com.gyleedev.clonestagram.ui.public.UserIconImageType
import com.gyleedev.clonestagram.ui.public.UserIconType
import com.gyleedev.clonestagram.ui.public.UserImageComponent
import com.gyleedev.clonestagram.ui.public.bounceClick

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
    var isShareBottomSheetTrue by remember {
        mutableStateOf(false)
    }

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
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.heart_svgrepo_com),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
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
                    onBottomSheetStateChange = {
                        isCommentBottomSheetTrue = true
                        bottomSheetBelongedId = item.ownerId
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
                }
            )
        }

        if (isShareBottomSheetTrue) {
            ShareModalBottomSheet(
                ownerId = bottomSheetBelongedId,
                comments = comments.value,
                closeSheet = {
                    isCommentBottomSheetTrue = false
                    bottomSheetBelongedId = ""
                },
                onAddComment = {
                    viewModel.addComment(it)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun CommentModalBottomSheet(
    ownerId: String,
    comments: List<CommentInformation>,
    onAddComment: (String) -> Unit,
    closeSheet: () -> Unit,
    modifier: Modifier = Modifier
) {
    val sheetState = rememberModalBottomSheetState()
    val comment = rememberTextFieldState()

    ModalBottomSheet(
        onDismissRequest = closeSheet,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier.fillMaxHeight(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Column(modifier = Modifier.align(Alignment.TopStart)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "댓글")
                }
                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider(
                    thickness = 0.125.dp
                )

                Spacer(modifier = Modifier.height(20.dp))
                comments.forEach {
                    CommentItem(item = it)
                }
            }

            BottomAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset {
                        IntOffset(
                            x = 0,
                            y = -sheetState
                                .requireOffset()
                                .toInt()
                        )
                    },
                containerColor = MaterialTheme.colorScheme.surfaceContainerLow
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 80.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Absolute.SpaceEvenly
                    ) {
                        Text(
                            text = "❤️",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.clickable {
                                comment.edit {
                                    insert(
                                        comment.text.length,
                                        "❤️"
                                    )
                                }
                            }
                        )
                        Text(
                            text = "✨",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.clickable {
                                comment.edit {
                                    insert(
                                        comment.text.length,
                                        "✨"
                                    )
                                }
                            }
                        )
                        Text(
                            text = "👍",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.clickable {
                                comment.edit {
                                    insert(
                                        comment.text.length,
                                        "👍"
                                    )
                                }
                            }
                        )
                        Text(
                            text = "👏",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.clickable {
                                comment.edit {
                                    insert(
                                        comment.text.length,
                                        "👏"
                                    )
                                }
                            }
                        )
                        Text(
                            text = "😭",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.clickable {
                                comment.edit {
                                    insert(
                                        comment.text.length,
                                        "😭"
                                    )
                                }
                            }
                        )
                        Text(
                            text = "😍",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.clickable {
                                comment.edit {
                                    insert(
                                        comment.text.length,
                                        "😍"
                                    )
                                }
                            }
                        )
                        Text(
                            text = "😮",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.clickable {
                                comment.edit {
                                    insert(
                                        comment.text.length,
                                        "😮"
                                    )
                                }
                            }
                        )
                        Text(
                            text = "😂",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.clickable {
                                comment.edit {
                                    insert(
                                        comment.text.length,
                                        "😂"
                                    )
                                }
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Image(
                            painter = painterResource(id = R.drawable.icons8_test_account_48),
                            contentDescription = null,
                            modifier = Modifier
                                .height(48.dp)
                                .width(48.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        ReplyTextField(

                            replyText = comment,
                            replyTextHint = "${ownerId}에게 댓글 쓰기",
                            onReply = {
                                onAddComment(comment.text.toString())
                            }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun ShareModalBottomSheet(
    ownerId: String,
    comments: List<CommentInformation>,
    onAddComment: (String) -> Unit,
    closeSheet: () -> Unit,
    modifier: Modifier = Modifier
) {
    val sheetState = rememberModalBottomSheetState()
    val comment = rememberTextFieldState()

    ModalBottomSheet(
        onDismissRequest = closeSheet,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier.fillMaxHeight(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Column(modifier = Modifier.align(Alignment.TopStart)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "댓글")
                }
                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider(
                    thickness = 0.125.dp
                )

                Spacer(modifier = Modifier.height(20.dp))
                comments.forEach {
                    CommentItem(item = it)
                }
            }

            BottomAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset {
                        IntOffset(
                            x = 0,
                            y = -sheetState
                                .requireOffset()
                                .toInt()
                        )
                    },
                containerColor = MaterialTheme.colorScheme.surfaceContainerLow
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 80.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Absolute.SpaceEvenly
                    ) {
                        Text(
                            text = "❤️",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.clickable {
                                comment.edit {
                                    insert(
                                        comment.text.length,
                                        "❤️"
                                    )
                                }
                            }
                        )
                        Text(
                            text = "✨",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.clickable {
                                comment.edit {
                                    insert(
                                        comment.text.length,
                                        "✨"
                                    )
                                }
                            }
                        )
                        Text(
                            text = "👍",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.clickable {
                                comment.edit {
                                    insert(
                                        comment.text.length,
                                        "👍"
                                    )
                                }
                            }
                        )
                        Text(
                            text = "👏",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.clickable {
                                comment.edit {
                                    insert(
                                        comment.text.length,
                                        "👏"
                                    )
                                }
                            }
                        )
                        Text(
                            text = "😭",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.clickable {
                                comment.edit {
                                    insert(
                                        comment.text.length,
                                        "😭"
                                    )
                                }
                            }
                        )
                        Text(
                            text = "😍",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.clickable {
                                comment.edit {
                                    insert(
                                        comment.text.length,
                                        "😍"
                                    )
                                }
                            }
                        )
                        Text(
                            text = "😮",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.clickable {
                                comment.edit {
                                    insert(
                                        comment.text.length,
                                        "😮"
                                    )
                                }
                            }
                        )
                        Text(
                            text = "😂",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.clickable {
                                comment.edit {
                                    insert(
                                        comment.text.length,
                                        "😂"
                                    )
                                }
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Image(
                            painter = painterResource(id = R.drawable.icons8_test_account_48),
                            contentDescription = null,
                            modifier = Modifier
                                .height(48.dp)
                                .width(48.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        ReplyTextField(

                            replyText = comment,
                            replyTextHint = "${ownerId}에게 댓글 쓰기",
                            onReply = {
                                onAddComment(comment.text.toString())
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CommentItem(
    item: CommentInformation,
    modifier: Modifier = Modifier
) {
    val isHeartTrue = remember {
        mutableStateOf(false)
    }

    Column(modifier = modifier.padding(vertical = 4.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Spacer(modifier = Modifier.width(16.dp))
                UserImageComponent(
                    userIconDefinition = UserIconDefinition(
                        iconImageType = UserIconImageType.IconFromDrawableType(R.drawable.icons8_test_account_96),
                        hasStory = true,
                        userIconType = UserIconType.IconComment()
                    )
                )
                Spacer(modifier = Modifier.width(20.dp))
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Row {
                        Text(
                            item.userId,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "${item.writtenHourAgo}일전",
                            fontWeight = FontWeight.ExtraLight,
                            style = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            " ・ ",
                            fontWeight = FontWeight.ExtraLight,
                            style = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            "작성자",
                            fontWeight = FontWeight.ExtraLight,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        item.content,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(end = 8.dp)
            ) {
                if (isHeartTrue.value) {
                    Icon(
                        painter = painterResource(id = R.drawable.heart_svgrepo_com),
                        contentDescription = null,
                        modifier = Modifier
                            .size(16.dp)
                            .bounceClick(
                                onClick = { },
                                onClickEnd = { isHeartTrue.value = !isHeartTrue.value }
                            ),
                        tint = Color.Red
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${item.heartCount + 1}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                        modifier = Modifier.sizeIn(28.dp),
                        textAlign = TextAlign.Center
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.heart_svgrepo_com),
                        contentDescription = null,
                        modifier = Modifier
                            .size(16.dp)
                            .bounceClick(
                                onClick = { },
                                onClickEnd = { isHeartTrue.value = !isHeartTrue.value }
                            ),
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${item.heartCount}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                        modifier = Modifier.sizeIn(28.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReplyTextField(
    replyText: TextFieldState,
    replyTextHint: String,
    onReply: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    BasicTextField2(
        modifier = modifier
            .fillMaxWidth()
            .imePadding(),
        state = replyText,
        textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurface),
        decorator = { innerTextField ->
            Box {
                if (replyText.text.isEmpty()) {
                    Text(
                        text = replyTextHint,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFF848484),
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                    )
                }
                Row(modifier = Modifier.align(Alignment.CenterStart)) {
                    innerTextField()
                }
                if (replyText.text.isNotEmpty()) {
                    FilledIconButton(
                        onClick = { onReply(replyText.text.toString()) },
                        modifier = Modifier.align(Alignment.CenterEnd),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = colorResource(
                                id = R.color.facebook_blue
                            )
                        ),
                        enabled = replyText.text.isNotEmpty()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_upward_alt_24dp_fill0_wght400_grad0_opsz24),
                            contentDescription = "Reply Icon",
                            tint = Color.White,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    }
                }
            }
        }
    )
}
