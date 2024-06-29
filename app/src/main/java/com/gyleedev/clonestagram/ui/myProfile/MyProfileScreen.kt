package com.gyleedev.clonestagram.ui.myProfile

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MyProfileScreen(
    modifier: Modifier
) {
    val verticalScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val tabList = listOf(
        TabIcons(R.drawable.icons8_grid_50__1_, R.drawable.icons8_grid_50__2_),
        TabIcons(R.drawable.icons8_instagram_reels__1_, R.drawable.icons8_instagram_reels),
        TabIcons(R.drawable.instagram_tag_icon, R.drawable.instagram_tag_icon)
    )
    val pagerState = rememberPagerState {
        tabList.size
    }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    scrolledContainerColor = TopAppBarDefaults.topAppBarColors().containerColor
                ),
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Lock,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "think_gy_lee")
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowDown,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable._4px_threads__app__logo_svg),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icons8_add_100),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Outlined.Menu,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
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
            Spacer(modifier = Modifier.height(12.dp))

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
                                    .size(80.dp)
                                    .align(Alignment.Center)
                            )

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
                        }
                    }

                    Canvas(modifier = Modifier.background(Color.White), onDraw = {
                        drawCircle(
                            color = Color.DarkGray.copy(alpha = 0.6f),
                            radius = 40.dp.toPx(),
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
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ) {
                Text(text = "이금용", style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ) {
                FilledTonalButton(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.filledTonalButtonColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh),
                    modifier = Modifier
                        .wrapContentSize()
                        .heightIn(max = 30.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable._4px_threads__app__logo_svg),
                        contentDescription = null,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "think_gy_lee", style = MaterialTheme.typography.labelMedium)
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ) {
                Text(text = "이금용", style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                FilledTonalButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .weight(1f)
                        .heightIn(max = 32.dp),
                    colors = ButtonDefaults.filledTonalButtonColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "프로필 편집", style = MaterialTheme.typography.bodySmall)
                }
                Spacer(modifier = Modifier.width(8.dp))
                FilledTonalButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .weight(1f)
                        .heightIn(max = 32.dp),
                    colors = ButtonDefaults.filledTonalButtonColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "프로필 공유", style = MaterialTheme.typography.bodySmall)
                }
                Spacer(modifier = Modifier.width(12.dp))
                FilledTonalIconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .wrapContentSize()
                        .sizeIn(maxHeight = 32.dp, maxWidth = 32.dp),
                    colors = IconButtonDefaults.filledTonalIconButtonColors(
                        containerColor =
                        MaterialTheme.colorScheme.surfaceContainerHigh
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icons8_add_friend_48),
                        contentDescription = null
                    )
                }
            }
            Spacer(modifier = Modifier.height(28.dp))
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                indicator = { tabPositions ->
                    Box(
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                            .padding(horizontal = 32.dp)
                            .height(2.dp)
                            .background(color = MaterialTheme.colorScheme.onSurface)
                    )
                }
            ) {
                tabList.forEach { item ->
                    MyProfileTab(
                        isSelected = pagerState.currentPage == tabList.indexOf(item),
                        item = item,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.scrollToPage(tabList.indexOf(item))
                            }
                        },
                        modifier = Modifier
                    )
                }
            }
            HorizontalPager(state = pagerState) {
                when (pagerState.currentPage) {
                    0 -> {
                        MyPhotoGird(modifier = Modifier)
                    }

                    1 -> {
                        MyReelsGird(modifier = Modifier)
                    }

                    2 -> {
                        MediaWithMeGird(modifier = Modifier)
                    }
                }
            }
        }
    }
}

@Composable
private fun MyProfileTab(
    isSelected: Boolean,
    item: TabIcons,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Tab(
        selected = isSelected,
        onClick = { onClick() },
        icon = {
            if (isSelected) {
                Icon(
                    painter = painterResource(id = item.selectedIcon),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
            } else {
                Icon(
                    painter = painterResource(id = item.unselectedIcon),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
            }
        },
        modifier = modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun MyPhotoGird(modifier: Modifier) {
    val list = listOf(
        "https://i.pinimg.com/736x/3f/92/2e/3f922eff0b495e6ff697178758d323da.jpg",
        "https://as2.ftcdn.net/v2/jpg/05/38/31/61/1000_F_538316173_VRwdsZDXy1HQqMNP6XiYLaUIZ1MhN3pq.jpg",
        "https://img.freepik.com/premium-photo/beautiful-landscapes-scenic-views-natural-wonders-breathtaking-scenery-tranquil-forests-serene_980716-15925.jpg",
        "https://www.cnn.co.jp/storage/2015/05/03/fbe00d376d9ae2bc25f0ebcfdbd5829e/35064036_007.jpg",
        "https://fujifilmsquare.jp/photosalon/tokyo/images/2019/190104012/190104012_02.jpg",
        "https://italian-guide.com/wp-content/uploads/2018/11/Beautiful-landscape-in-Tuscany-Italy.jpg"
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        flingBehavior = ScrollableDefaults.flingBehavior(),
        modifier = modifier.fillMaxSize()
    ) {
        items(list) { item ->
            CoilImage(
                imageModel = { item },
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
                modifier = Modifier.sizeIn(minWidth = 128.dp, minHeight = 128.dp)
            )
        }
    }
}

@Composable
fun MyReelsGird(modifier: Modifier) {
    val list = listOf(1, 2, 3, 4, 5)
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(list) { item ->
            Text(text = item.toString())
        }
    }
}

@Composable
fun MediaWithMeGird(modifier: Modifier) {
    val list = emptyList<String>()
    if (list.isNotEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            modifier = modifier.fillMaxSize()
        ) {
            items(list) { item ->
                Text(text = item)
            }
        }
    } else {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "회원님이 나온 사진 및 동영상",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "사람들이 회원님을 사진 및 동영상에 태그하면",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "태그된 사진 및 동영상이 여기에 표시됩니다.",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
            )
        }
    }
}

data class TabIcons(
    val selectedIcon: Int,
    val unselectedIcon: Int
)
