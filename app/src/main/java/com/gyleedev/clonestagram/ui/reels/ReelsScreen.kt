package com.gyleedev.clonestagram.ui.reels

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gyleedev.clonestagram.R
import com.gyleedev.clonestagram.ui.public.CommentModalBottomSheet
import com.gyleedev.clonestagram.ui.public.MenuModalBottomSheet
import com.gyleedev.clonestagram.ui.public.ShareModalBottomSheet
import com.gyleedev.clonestagram.ui.public.UserIconDefinition
import com.gyleedev.clonestagram.ui.public.UserIconImageType
import com.gyleedev.clonestagram.ui.public.UserIconType
import com.gyleedev.clonestagram.ui.public.UserImageComponent
import com.gyleedev.clonestagram.ui.public.bounceClick
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReelsScreen(
    modifier: Modifier = Modifier,
    viewModel: ReelsViewModel = hiltViewModel()
) {
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

    val isHeartTrue = remember {
        mutableStateOf(false)
    }

    Box(modifier = modifier.fillMaxSize()) {
        CoilImage(
            imageModel = { imageLink },
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
            modifier = Modifier.fillMaxSize()
        )
        ReelsTop(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        )
        Row(
            Modifier
                .align(Alignment.BottomStart)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    UserImageComponent(
                        userIconDefinition = UserIconDefinition(
                            iconImageType = UserIconImageType.IconFromDrawableType(R.drawable.icons8_test_account_48),
                            hasStory = true,
                            userIconType = UserIconType.IconReels()
                        )
                    )

                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "think_gy_lee", color = Color.White, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier.border(
                            BorderStroke(1.dp, Color.White),
                            RoundedCornerShape(8.dp)
                        )
                    ) {
                        Text(
                            text = "팔로우",
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Text(
                        text = "portugal legends",
                        color = Color.White,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Box(
                        modifier = Modifier
                            .background(
                                color = Color.DarkGray.copy(
                                    alpha = 0.4f
                                ),
                                shape = RoundedCornerShape(16.dp)
                            )
                    ) {
                        Text(
                            text = "♫ 뎁트・타이밍 (feat 김뮤지엄 & 에이민)",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .background(
                                color = Color.DarkGray.copy(
                                    alpha = 0.4f
                                ),
                                shape = RoundedCornerShape(16.dp)
                            )
                    ) {
                        Text(
                            text = "🎁기프트",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .background(
                                color = Color.DarkGray.copy(
                                    alpha = 0.4f
                                ),
                                shape = RoundedCornerShape(16.dp)
                            )
                    ) {
                        Text(
                            text = "+2개",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                if (isHeartTrue.value) {
                    Icon(
                        painter = painterResource(id = R.drawable.heart_filled_svgrepo_com__1_),
                        contentDescription = null,
                        modifier = Modifier
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
                            .size(24.dp)
                            .bounceClick(
                                onClick = {},
                                onClickEnd = { isHeartTrue.value = !isHeartTrue.value }
                            ),
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "좋아요", color = Color.White)
                Spacer(modifier = Modifier.height(12.dp))
                Icon(
                    painter = painterResource(id = R.drawable.instagram_comment_13416),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            isCommentBottomSheetTrue = true
                            bottomSheetBelongedId = "think_gy_lee"
                        }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "330", color = Color.White)
                Spacer(modifier = Modifier.height(12.dp))
                Icon(
                    painter = painterResource(id = R.drawable.instagram_share_13423),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            isShareBottomSheetTrue = true
                            bottomSheetBelongedId = "think_gy_lee"
                        }
                )
                Spacer(modifier = Modifier.height(12.dp))
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            isMenuBottomSheetTrue = true
                        }
                )
                Spacer(modifier = Modifier.height(12.dp))
                Box(
                    modifier = Modifier.border(
                        BorderStroke(2.dp, Color.White),
                        shape = RoundedCornerShape(4.dp)
                    )
                ) {
                    CoilImage(
                        imageModel = { imageLink },
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.Inside,
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
                            .size(28.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
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

@Composable
fun ReelsTop(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .clickable { }
        ) {
            Text(
                text = "릴스",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Filled.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier.size(28.dp),
                tint = Color.White
            )
        }
        IconButton(onClick = { }) {
            Icon(
                painter = painterResource(id = R.drawable.camera_fill_svgrepo_com),
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                tint = Color.White
            )
        }
    }
}

const val imageLink = "https://pbs.twimg.com/media/FjYmnWuWYAMk7se.jpg:large"
