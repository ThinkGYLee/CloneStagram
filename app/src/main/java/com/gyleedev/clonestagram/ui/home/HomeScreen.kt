package com.gyleedev.clonestagram.ui.home

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.input.nestedscroll.nestedScroll
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier
) {
    val verticalScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val isHeartTrue = remember {
        mutableStateOf(false)
    }

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
        modifier = modifier.nestedScroll(verticalScrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
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
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(end = 20.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icons8_test_account_96),
                        contentDescription = null,
                        modifier = Modifier
                            .size(80.dp)
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
                            tint = Color.Blue,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                for (i in 0..6) {
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(end = 20.dp)
                            .border(
                                width = 4.dp,
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
                                .padding(8.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icons8_test_account_96),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(80.dp)
                                    .align(Alignment.Center)

                            )
                        }
                    }
                }
            }
            // 게시물
            Column(
                modifier = Modifier
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
                                painter = painterResource(id = R.drawable.icons8_test_account_96),
                                contentDescription = null,
                                modifier = Modifier.size(48.dp)
                            )
                        }
                    }
                    Column(modifier = Modifier.wrapContentSize()) {
                        Text("think_gy_lee", fontWeight = FontWeight.SemiBold)
                        Text(
                            "♫ 뎁트・타이밍 (feat 김뮤지엄 & 에이민)",
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
                    imageModel = { "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgX98TKIsaJF7D4wnq7YBOuMjtYH-6D5Kgm7m7VbRek7cQIGN7TNVtJMDIbSiEG5KgcGyGpgGxEOz7u9v-WhQASrQrjvCQF8-RQ7PsZpA6djqK7RA7mXrnt6aYiac8voLef_mhP-s_TucPVEP1vvmUBjspmjA2RdrbvIqVwYXQJZ1fwPyamJIxXTrgMVmg/s1600/image1.png" },
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
                                .size(28.dp)
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
                                .size(28.dp)
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
                            .size(28.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.instagram_share_13423),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 20.dp)
                            .size(28.dp)
                    )
                    Spacer(modifier = Modifier.weight(10f))
                    Icon(
                        painter = painterResource(id = R.drawable.save_instagram_black_lineal_18315),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .size(28.dp)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text("좋아요 3658개", modifier = Modifier.padding(start = 20.dp))
                Spacer(modifier = Modifier.height(4.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "think_gy_lee",
                        modifier = Modifier.padding(start = 20.dp),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Android 15 is coming")
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "댓글 2개 모두 보기",
                    modifier = Modifier.padding(start = 20.dp),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}

// bounceClick

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
