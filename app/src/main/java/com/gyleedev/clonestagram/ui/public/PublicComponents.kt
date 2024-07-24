package com.gyleedev.clonestagram.ui.public

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.foundation.text2.input.delete
import androidx.compose.foundation.text2.input.insert
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.gyleedev.clonestagram.R
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin

@Composable
fun PublicItemDetail(
    itemData: ItemData,
    comments: List<CommentInformation>,
    onCommentBottomSheetStatusChanged: (Boolean) -> Unit,
    onShareBottomSheetStatusChanged: (Boolean) -> Unit,
    onMenuBottomSheetStatusChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val isHeartTrue = remember {
        mutableStateOf(itemData.heartBoolean)
    }
    val isBookMarkTrue = remember {
        mutableStateOf(itemData.bookMarkBoolean)
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
    ) {
        // 게시물 제목
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                UserImageComponent(
                    userIconDefinition = UserIconDefinition(
                        iconImageType = itemData.icon,
                        hasStory = true,
                        userIconType = UserIconType.IconDetail()
                    )
                )
                Spacer(modifier = Modifier.width(20.dp))
                Column {
                    Text(itemData.ownerId, fontWeight = FontWeight.SemiBold)
                    Text(
                        itemData.music,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }

            IconButton(onClick = { onMenuBottomSheetStatusChanged(true) }) {
                Icon(
                    imageVector = Icons.Outlined.MoreVert,
                    contentDescription = null,
                    modifier = Modifier
                )
            }
        }
        CoilImage(
            imageModel = { itemData.image },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            ),
            component = rememberImageComponent {
                +ShimmerPlugin(
                    Shimmer.Flash(
                        baseColor = Color.White,
                        highlightColor = Color.LightGray
                    )
                )
            },
            modifier = Modifier.sizeIn(
                minWidth = LocalConfiguration.current.screenWidthDp.dp,
                minHeight = LocalConfiguration.current.screenWidthDp.dp
            )
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            if (isHeartTrue.value) {
                Icon(
                    painter = painterResource(id = R.drawable.heart_filled_svgrepo_com__1_),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .size(24.dp)
                        .bounceClick(
                            onClick = {},
                            onClickEnd = { isHeartTrue.value = !isHeartTrue.value }
                        ),
                    tint = Color.Red
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.heart_svgrepo_com),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .size(24.dp)
                        .bounceClick(
                            onClick = {},
                            onClickEnd = { isHeartTrue.value = !isHeartTrue.value }
                        ),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Icon(
                painter = painterResource(id = R.drawable.instagram_comment_13416),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .clickable {
                        onCommentBottomSheetStatusChanged(true)
                    }
                    .size(24.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.instagram_share_13423),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .clickable {
                        onShareBottomSheetStatusChanged(true)
                    }
                    .size(24.dp)
            )
            Spacer(modifier = Modifier.weight(10f))
            if (isBookMarkTrue.value) {
                Icon(
                    painter = painterResource(id = R.drawable.black_save_instagram_18316),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .size(24.dp)
                        .bounceClick(
                            onClick = {},
                            onClickEnd = { isBookMarkTrue.value = !isBookMarkTrue.value }
                        )
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.save_instagram_black_lineal_18315),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .size(24.dp)
                        .bounceClick(
                            onClick = {},
                            onClickEnd = { isBookMarkTrue.value = !isBookMarkTrue.value }
                        )
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            "좋아요 ${itemData.heartCount}개",
            modifier = Modifier.padding(start = 20.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                itemData.ownerId,
                modifier = Modifier.padding(start = 20.dp),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(itemData.content)
        }
        Spacer(modifier = Modifier.height(4.dp))

        ProvideTextStyle(
            value = MaterialTheme.typography.bodyMedium
                .copy(
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                    fontWeight = FontWeight.Light
                )
        ) {
            Text(
                "댓글 ${comments.size}개 모두 보기",
                modifier = Modifier
                    .padding(start = 20.dp)
                    .clickable { onCommentBottomSheetStatusChanged(true) }
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "2시간 전",
                modifier = Modifier.padding(start = 20.dp)
            )
        }
    }
}

// bounceClick
// TODO supperessLint 수정할것
@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.bounceClick(
    scaleDown: Float = 0.30f,
    onClick: () -> Unit,
    onClickEnd: () -> Unit
) = composed {
    val interactionSource = remember { MutableInteractionSource() }

    val animatable = remember {
        Animatable(1f)
    }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> animatable.animateTo(scaleDown)
                is PressInteraction.Release -> animatable.animateTo(scaleDown)
                is PressInteraction.Cancel -> animatable.animateTo(1f)
            }
        }
    }

    LaunchedEffect(animatable.value) {
        if (animatable.value == scaleDown) {
            onClickEnd()
        }
    }

    Modifier
        .graphicsLayer {
            val scale = animatable.value
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            interactionSource = interactionSource,
            indication = null
        ) {
            onClick()
        }
}

@Composable
fun UserImageComponent(
    userIconDefinition: UserIconDefinition,
    modifier: Modifier = Modifier
) {
    val hasStory = userIconDefinition.hasStory
    val isAddable: Boolean
    val iconSize: Int
    val borderSize: Float
    val padding: Int
    val isNew: Boolean

    val imageType = userIconDefinition.iconImageType
    when (val iconType = userIconDefinition.userIconType) {
        is UserIconType.IconReels -> {
            isAddable = false
            isNew = false
            iconSize = iconType.iconSize
            borderSize = iconType.borderSize
            padding = iconType.padding
        }

        is UserIconType.IconComment -> {
            isAddable = false
            isNew = false
            iconSize = iconType.iconSize
            borderSize = iconType.borderSize
            padding = iconType.padding
        }

        is UserIconType.IconMyProfile -> {
            isAddable = false
            iconSize = iconType.iconSize
            borderSize = iconType.borderSize
            padding = iconType.padding
            isNew = iconType.isNew
        }

        is UserIconType.IconSearch -> {
            isAddable = false
            isNew = false
            iconSize = iconType.iconSize
            borderSize = iconType.borderSize
            padding = iconType.padding
        }

        is UserIconType.IconStory -> {
            iconSize = iconType.iconSize
            isNew = false
            borderSize = iconType.borderSize
            padding = iconType.padding
            isAddable = iconType.isAddable
        }

        is UserIconType.IconDetail -> {
            iconSize = iconType.iconSize
            isNew = false
            borderSize = iconType.borderSize
            padding = iconType.padding
            isAddable = false
        }
    }

    val brushModifier = if (hasStory) {
        modifier
            .border(
                width = borderSize.dp,
                brush = Brush.linearGradient(
                    colors = listOf(Color.Yellow, Color.Red),
                    start = Offset(0f, 0f),
                    end = Offset(iconSize * 2.5f, iconSize * 2.5f)
                ),
                shape = CircleShape
            )
    } else {
        modifier
    }

    Box(modifier = brushModifier) {
        Box(modifier = Modifier.padding(padding.dp)) {
            when (imageType) {
                is UserIconImageType.IconFromUrlType -> {
                    CoilImage(
                        imageModel = { imageType.url },
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.Fit,
                            alignment = Alignment.Center
                        ),
                        component = rememberImageComponent {
                            +ShimmerPlugin(
                                Shimmer.Flash(
                                    baseColor = Color.White,
                                    highlightColor = Color.LightGray
                                )
                            )
                        },
                        modifier = Modifier
                            .size(iconSize.dp)
                            .clip(CircleShape)
                    )
                }

                is UserIconImageType.IconFromDrawableType -> {
                    Image(
                        painter = painterResource(id = imageType.drawable),
                        contentDescription = null,
                        modifier = Modifier
                            .size(iconSize.dp)
                            .align(Alignment.Center)
                    )
                }
            }
            if (isAddable) {
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
            if (isNew) {
                Box(
                    modifier = Modifier
                        .offset(y = 12.dp)
                        .background(
                            color = colorResource(id = R.color.facebook_blue),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .border(
                            color = if (isSystemInDarkTheme()) {
                                Color.Black
                            } else {
                                colorResource(
                                    id = R.color.facebook_blue
                                )
                            },
                            width = 4.dp,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(6.dp)
                        .align(Alignment.BottomCenter)

                ) {
                    Text(
                        text = "신규",
                        color = Color.White,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier
                            .background(color = colorResource(id = R.color.facebook_blue))
                            .align(Alignment.BottomCenter)

                    )
                }
                Canvas(modifier = Modifier.background(Color.White), onDraw = {
                    drawCircle(
                        color = Color.DarkGray.copy(alpha = 0.6f),
                        radius = 40.dp.toPx(),
                        center = Offset(40.dp.toPx(), 40.dp.toPx())
                    )
                })

                Icon(
                    painter = painterResource(id = R.drawable.camera_fill_svgrepo_com),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuModalBottomSheet(
    sheetState: SheetState,
    closeSheet: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        onDismissRequest = closeSheet,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        modifier = modifier
    ) {
        Scaffold(bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.surfaceContainerLowest)
                    .height(40.dp)
                    .offset {
                        IntOffset(
                            x = 0,
                            y = -sheetState
                                .requireOffset()
                                .toInt()
                        )
                    }
            ) {
                Spacer(modifier = Modifier.weight(10f))
            }
        }, modifier = Modifier) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .consumeWindowInsets(innerPadding)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .border(
                                    BorderStroke(
                                        1.dp,
                                        color = MaterialTheme.colorScheme.onSurface
                                    ),
                                    shape = CircleShape
                                )
                                .padding(16.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.save_instagram_black_lineal_18315),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(24.dp)
                            )
                        }

                        Text(text = "저장")
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .border(
                                    BorderStroke(
                                        1.dp,
                                        color = MaterialTheme.colorScheme.onSurface
                                    ),
                                    shape = CircleShape
                                )
                                .padding(16.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.icons8_qr_code_64),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(24.dp)
                            )
                        }
                        Text(text = "QR 코드")
                    }
                }
                Spacer(modifier = Modifier.height(40.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.icons8_star_50),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "즐겨찾기에 추가")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.icons8_people_64),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "팔로우 취소")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.icons8_information_50),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "이 게시물이 표시되는 이유")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.icons8_hide_64),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "숨기기")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "이 계정 정보")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.icons8_alert_64),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp),
                        tint = Color.Red
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "신고", color = Color.Red)
                }
                Spacer(modifier = Modifier.height(72.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ShareModalBottomSheet(
    ownerId: String,
    sheetState: SheetState,
    closeSheet: () -> Unit,
    modifier: Modifier = Modifier
) {
    val searchQuery = rememberTextFieldState()

    ModalBottomSheet(
        onDismissRequest = closeSheet,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        modifier = modifier.fillMaxSize()
    ) {
        Scaffold(
            bottomBar = {
                BottomAppBar(
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.surfaceContainerLowest)
                        .offset {
                            IntOffset(
                                x = 0,
                                y = -sheetState
                                    .requireOffset()
                                    .toInt()
                            )
                        }
                ) {
                    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            IconButton(onClick = { /*TODO*/ }) {
                                CoilImage(
                                    imageModel = { ItemData.initialItem.find { it.ownerId == ownerId }?.icon?.url },
                                    imageOptions = ImageOptions(
                                        contentScale = ContentScale.Crop,
                                        alignment = Alignment.Center
                                    ),
                                    component = rememberImageComponent {
                                        +ShimmerPlugin(
                                            Shimmer.Flash(
                                                baseColor = Color.White,
                                                highlightColor = Color.LightGray
                                            )
                                        )
                                    },
                                    modifier = Modifier
                                        .aspectRatio(1f)
                                        .clip(CircleShape)
                                        .size(40.dp)
                                )
                            }
                            Text(text = "메모 추가", style = MaterialTheme.typography.labelMedium)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            IconButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier.background(
                                    color = MaterialTheme.colorScheme.surfaceContainerHighest,
                                    shape = CircleShape
                                )
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.icons8_share_48),
                                    contentDescription = null,
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                            Text(text = "공유하기", style = MaterialTheme.typography.labelMedium)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            IconButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier.background(
                                    color = MaterialTheme.colorScheme.surfaceContainerHighest,
                                    shape = CircleShape
                                )
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.icons8_kakaotalk_50),
                                    contentDescription = null,
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                            Text(text = "카카오톡", style = MaterialTheme.typography.labelMedium)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            IconButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier.background(
                                    color = MaterialTheme.colorScheme.surfaceContainerHighest,
                                    shape = CircleShape
                                )
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.icons8_link_48),
                                    contentDescription = null,
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                            Text(text = "링크복사", style = MaterialTheme.typography.labelMedium)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            IconButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier.background(
                                    color = MaterialTheme.colorScheme.surfaceContainerHighest,
                                    shape = CircleShape
                                )
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.icons8_x_50),
                                    contentDescription = null,
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                            Text(text = "X", style = MaterialTheme.typography.labelMedium)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            IconButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier.background(
                                    color = MaterialTheme.colorScheme.surfaceContainerHighest,
                                    shape = CircleShape
                                )
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.icons8_add_100),
                                    contentDescription = null,
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                            Text(text = "스토리에 추가", style = MaterialTheme.typography.labelMedium)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            IconButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier.background(
                                    color = MaterialTheme.colorScheme.surfaceContainerHighest,
                                    shape = CircleShape
                                )
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.icons8_facebook_50),
                                    contentDescription = null,
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                            Text(text = "Facebook", style = MaterialTheme.typography.labelMedium)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            IconButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier.background(
                                    color = MaterialTheme.colorScheme.surfaceContainerHighest,
                                    shape = CircleShape
                                )
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable._4px_threads__app__logo_svg),
                                    contentDescription = null,
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                            Text(text = "Threads", style = MaterialTheme.typography.labelMedium)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            IconButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier.background(
                                    color = MaterialTheme.colorScheme.surfaceContainerHighest,
                                    shape = CircleShape
                                )
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.icons8_sms_50),
                                    contentDescription = null,
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                            Text(text = "SMS", style = MaterialTheme.typography.labelMedium)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            IconButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier.background(
                                    color = MaterialTheme.colorScheme.surfaceContainerHighest,
                                    shape = CircleShape
                                )
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.icons8_snapchat_50),
                                    contentDescription = null,
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                            Text(text = "Snapchat", style = MaterialTheme.typography.labelMedium)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            IconButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier.background(
                                    color = MaterialTheme.colorScheme.surfaceContainerHighest,
                                    shape = CircleShape
                                )
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.facebook_messenger_2880),
                                    contentDescription = null,
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                            Text(text = "Messenger", style = MaterialTheme.typography.labelMedium)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            IconButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier.background(
                                    color = MaterialTheme.colorScheme.surfaceContainerHighest,
                                    shape = CircleShape
                                )
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.icons8_whatsapp_48),
                                    contentDescription = null,
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                            Text(text = "WhatsApp", style = MaterialTheme.typography.labelMedium)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            IconButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier.background(
                                    color = MaterialTheme.colorScheme.surfaceContainerHighest,
                                    shape = CircleShape
                                )
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.icons8_band_app_100),
                                    contentDescription = null,
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                            Text(text = "밴드", style = MaterialTheme.typography.labelMedium)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                }
            },
            modifier = Modifier
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .consumeWindowInsets(innerPadding)
                    .fillMaxSize()
            ) {
                SearchTextField(
                    searchQuery = searchQuery,
                    onReset = {
                        searchQuery.edit {
                            delete(
                                0,
                                searchQuery.text.length
                            )
                        }
                    },
                    modifier = Modifier
                        .padding(16.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                MyPhotoGird()
            }
        }
    }
}

@Composable
private fun MyPhotoGird(modifier: Modifier = Modifier) {
    val itemSize = ItemData.initialItem.size
    val windowWidth = LocalConfiguration.current.screenWidthDp
    val maxHeight = windowWidth / 2 * (itemSize / 3) // * 2
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        flingBehavior = ScrollableDefaults.flingBehavior(),
        modifier = modifier
            .height(maxHeight.dp)
            .fillMaxSize(),
        userScrollEnabled = true,
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(ItemData.initialItem) { item ->
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CoilImage(
                    imageModel = { item.icon.url },
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center
                    ),
                    component = rememberImageComponent {
                        +ShimmerPlugin(
                            Shimmer.Flash(
                                baseColor = Color.White,
                                highlightColor = Color.LightGray
                            )
                        )
                    },
                    modifier = Modifier
                        .size((windowWidth / 5).dp)
                        .aspectRatio(1f)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = item.ownerId)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchTextField(
    searchQuery: TextFieldState,
    onReset: () -> Unit,
    modifier: Modifier = Modifier
) {
    var alpha by remember { mutableFloatStateOf(1f) }

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        BasicTextField2(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.surfaceContainerHigh,
                    shape = RoundedCornerShape(8.dp)
                )
                .onFocusChanged {
                    alpha = if (it.isFocused) 0.6f else 1f
                }
                .padding(horizontal = 16.dp),
            state = searchQuery,
            lineLimits = TextFieldLineLimits.SingleLine,
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurface),
            decorator = { innerTextField ->
                Row(
                    modifier = Modifier
                        .padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.search_24dp_fill0_wght600_grad0_opsz24),
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = alpha),
                        contentDescription = null
                    )
                    Box(modifier = Modifier.weight(10f)) {
                        if (searchQuery.text.isEmpty()) {
                            Text(
                                text = "검색",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color(0xFF848484),
                                modifier = Modifier
                                    .padding(start = 4.dp)
                                    .align(Alignment.CenterStart)
                            )
                        }
                        Row(modifier = Modifier.align(Alignment.CenterStart)) {
                            innerTextField()
                        }
                    }
                    if (searchQuery.text.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null,
                            modifier = Modifier.clickable { onReset() }
                        )
                    }
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CommentModalBottomSheet(
    sheetState: SheetState,
    ownerId: String,
    comments: List<CommentInformation>,
    onAddComment: (String) -> Unit,
    closeSheet: () -> Unit,
    modifier: Modifier = Modifier
) {
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

data class UserIconDefinition(
    val iconImageType: UserIconImageType,
    val hasStory: Boolean,
    val userIconType: UserIconType
)

sealed interface UserIconType {
    data class IconDetail(
        val iconSize: Int = 36,
        val borderSize: Float = 1.5f,
        val padding: Int = 4
    ) : UserIconType

    data class IconStory(
        val iconSize: Int = 72,
        val borderSize: Float = 3f,
        val padding: Int = 7,
        val isAddable: Boolean
    ) : UserIconType

    data class IconSearch(
        val iconSize: Int = 48,
        val borderSize: Float = 2f,
        val padding: Int = 4
    ) : UserIconType

    data class IconComment(
        val iconSize: Int = 40,
        val borderSize: Float = 2f,
        val padding: Int = 4
    ) : UserIconType

    data class IconReels(
        val iconSize: Int = 32,
        val borderSize: Float = 2f,
        val padding: Int = 0
    ) : UserIconType

    data class IconMyProfile(
        val iconSize: Int = 80,
        val borderSize: Float = 2f,
        val padding: Int = 4,
        val isNew: Boolean
    ) : UserIconType
}

sealed interface UserIconImageType {
    data class IconFromUrlType(
        val url: String
    ) : UserIconImageType

    data class IconFromDrawableType(
        val drawable: Int
    ) : UserIconImageType
}

data class CommentInformation(
    val userImage: Int,
    val userId: String,
    val writtenHourAgo: Int,
    val isWrittenByAuthor: Boolean,
    val content: String,
    val heartCount: Int
)
