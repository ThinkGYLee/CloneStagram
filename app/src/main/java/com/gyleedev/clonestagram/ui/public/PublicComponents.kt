package com.gyleedev.clonestagram.ui.public

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
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
    modifier: Modifier
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
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = 16.dp, end = 20.dp)
                    .border(
                        width = 1.5.dp,
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
                        painter = painterResource(id = itemData.icon),
                        contentDescription = null,
                        modifier = Modifier.size(36.dp)
                    )
                }
            }
            Column(modifier = Modifier.wrapContentSize()) {
                Text(itemData.ownerId, fontWeight = FontWeight.SemiBold)
                Text(
                    itemData.music,
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Spacer(modifier = Modifier.weight(5f))
            Icon(
                imageVector = Icons.Outlined.MoreVert,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .wrapContentSize()
                    .padding(end = 8.dp)
                    .clickable { }
            )
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
            modifier = Modifier.fillMaxWidth()
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
        Text(
            "댓글 ${comments.size}개 모두 보기",
            modifier = Modifier
                .padding(start = 20.dp)
                .clickable {
                    onBottomSheetStateChange(true)
                },
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
            fontWeight = FontWeight.Light
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            "2시간 전",
            modifier = Modifier.padding(start = 20.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
            fontWeight = FontWeight.Light
        )
    }
}

// bounceClick
// TODO supperessLint 수정할것
@SuppressLint("ModifierFactoryUnreferencedReceiver")
private fun Modifier.bounceClick(
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

data class CommentInformation(
    val userImage: Int,
    val userId: String,
    val writtenHourAgo: Int,
    val isWrittenByAuthor: Boolean,
    val content: String,
    val heartCount: Int
)