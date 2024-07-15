package com.gyleedev.clonestagram.ui.public

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
    onBottomSheetStateChange: (Boolean) -> Unit,
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

            IconButton(onClick = { /*TODO*/ }) {
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
                        onBottomSheetStateChange(true)
                    }
                    .size(24.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.instagram_share_13423),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 20.dp)
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
                    .clickable { onBottomSheetStateChange(true) }
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
