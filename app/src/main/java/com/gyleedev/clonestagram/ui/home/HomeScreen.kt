package com.gyleedev.clonestagram.ui.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.foundation.text2.input.insert
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val verticalScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val isCommentBottomSheetTrue = remember {
        mutableStateOf(false)
    }

    val comments = homeViewModel.commentList.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    scrolledContainerColor = TopAppBarDefaults.topAppBarColors().containerColor
                ),
                title = {
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.sizeIn(minWidth = 100.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.instagram_text_logo),
                            contentDescription = null,
                            modifier = Modifier.fillMaxHeight()
                        )
                    }
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
                    .padding(start = 24.dp)
                    .horizontalScroll(rememberScrollState()),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(end = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.icons8_test_account_96),
                            contentDescription = null,
                            modifier = Modifier
                                .size(72.dp)
                                .align(Alignment.Center)
                        )
                        Box(modifier = Modifier.align(Alignment.BottomEnd)) {
                            Canvas(modifier = Modifier.background(Color.White), onDraw = {
                                drawCircle(
                                    color = Color.White,
                                    radius = 8.dp.toPx(),
                                    center = Offset(12.dp.toPx(), 12.dp.toPx())
                                )
                            })
                            Icon(
                                imageVector = Icons.Filled.AddCircle,
                                contentDescription = null,
                                tint = colorResource(id = R.color.facebook_blue),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "내 스토리",
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier
                    )
                }
                for (i in 0..6) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(end = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .wrapContentSize()
                                .border(
                                    width = 3.dp,
                                    brush = Brush.linearGradient(
                                        colors = listOf(Color.Yellow, Color.Red),
                                        start = Offset(0f, 0f),
                                        end = Offset(180f, 180f)
                                    ),
                                    shape = CircleShape
                                )
                        ) {
                            Box(
                                modifier = Modifier
                                    .wrapContentSize()
                                    .padding(7.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.icons8_test_account_96),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(72.dp)
                                        .align(Alignment.Center)

                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "내 스토리",
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier
                        )
                    }
                }
            }
            // 게시물

            PublicItemDetail(
                itemData = ItemData.initialItem,
                comments = comments.value,
                onBottomSheetStateChange = { isCommentBottomSheetTrue.value = true },
                modifier = Modifier
            )
        }

        if (isCommentBottomSheetTrue.value) {
            CommentModalBottomSheet(
                modifier = Modifier,
                comments = comments.value,
                closeSheet = { isCommentBottomSheetTrue.value = false },
                onAddComment = {
                    homeViewModel.addComment(it)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun CommentModalBottomSheet(
    modifier: Modifier = Modifier,
    comments: List<CommentInformation>,
    onAddComment: (String) -> Unit,
    closeSheet: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val comment = rememberTextFieldState()

    ModalBottomSheet(
        onDismissRequest = { closeSheet() },
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
                    CommentItem(item = it, modifier = Modifier)
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
                            replyTextHint = "think_gy_lee에게 댓글 쓰기",
                            onReply = {
                                onAddComment(comment.text.toString())
                            },
                            modifier = Modifier.fillMaxHeight()
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
    modifier: Modifier
) {
    Column(modifier = modifier.padding(vertical = 4.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = 16.dp, end = 20.dp)
                    .border(
                        width = 2.dp,
                        brush = Brush.linearGradient(
                            colors = listOf(Color.Yellow, Color.Red),
                            start = Offset(0f, 0f),
                            end = Offset(70f, 70f)
                        ),
                        shape = CircleShape
                    )
            ) {
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(4.dp)
                ) {
                    Image(
                        painter = painterResource(id = item.userImage),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
            Column(
                modifier = Modifier.wrapContentSize(),
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
            Spacer(modifier = Modifier.weight(10f))

            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.heart_svgrepo_com),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
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
            Spacer(modifier = Modifier.width(12.dp))
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
            Box(modifier = Modifier) {
                if (replyText.text.isEmpty()) {
                    Text(
                        text = replyTextHint,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFF848484),
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .wrapContentSize()
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
                        )
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
