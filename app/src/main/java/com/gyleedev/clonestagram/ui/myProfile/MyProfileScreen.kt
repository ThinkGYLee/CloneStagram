package com.gyleedev.clonestagram.ui.myProfile

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.gyleedev.clonestagram.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyProfileScreen(
    modifier: Modifier
) {
    val verticalScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    scrolledContainerColor = TopAppBarDefaults.topAppBarColors().containerColor
                ),
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Outlined.Lock, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "think_gy_lee")
                        Spacer(modifier = Modifier.width(8.dp))
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Outlined.KeyboardArrowDown,
                                contentDescription = null
                            )
                        }
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable._4px_threads__app__logo_svg),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icons8_add_100),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Outlined.Menu,
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
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(28.dp))

            Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(16.dp))
                Box(modifier = Modifier.wrapContentSize(), Alignment.Center) {
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .border(
                                width = 4.dp,
                                color = Color.LightGray.copy(alpha = 0.5f),
                                shape = CircleShape
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .wrapContentSize()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.profile),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(88.dp)
                                    .align(Alignment.Center)
                            )

                            Box(
                                modifier = Modifier
                                    .offset {
                                        IntOffset(
                                            0,
                                            36
                                        )
                                    }
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
                        }
                    }

                    Canvas(modifier = Modifier.background(Color.White), onDraw = {
                        drawCircle(
                            color = Color.DarkGray.copy(alpha = 0.6f),
                            radius = 44.dp.toPx(),
                            center = Offset(0.dp.toPx(), 0.dp.toPx())
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
                Spacer(modifier = Modifier.weight(1f))
                Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "18")
                    Text(text = "게시물")
                }
                Spacer(modifier = Modifier.weight(1f))
                Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "84")
                    Text(text = "팔로워")
                }
                Spacer(modifier = Modifier.weight(1f))
                Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "122")
                    Text(text = "팔로잉")
                }
                Spacer(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ) {
                Text(text = "이금용", style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ) {
                FilledTonalButton(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.filledTonalButtonColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
                    modifier = Modifier.height(36.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable._4px_threads__app__logo_svg),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "think_gy_lee", style = MaterialTheme.typography.bodyMedium)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ) {
                Text(text = "이금용", style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                FilledTonalButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.filledTonalButtonColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "프로필 편집")
                }
                Spacer(modifier = Modifier.width(8.dp))
                FilledTonalButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.filledTonalButtonColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "프로필 공유")
                }
                Spacer(modifier = Modifier.width(12.dp))
                FilledTonalIconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.wrapContentSize(),
                    colors = IconButtonDefaults.filledTonalIconButtonColors(
                        containerColor =
                        MaterialTheme.colorScheme.surfaceContainer
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icons8_add_friend_48),
                        contentDescription = null
                    )
                }
            }
        }
    }
}
