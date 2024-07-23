package com.gyleedev.clonestagram.ui.myProfile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableDefaults
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.gyleedev.clonestagram.R
import com.gyleedev.clonestagram.ui.public.UserIconDefinition
import com.gyleedev.clonestagram.ui.public.UserIconImageType
import com.gyleedev.clonestagram.ui.public.UserIconType
import com.gyleedev.clonestagram.ui.public.UserImageComponent
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MyProfileScreen(
    modifier: Modifier = Modifier
) {
    val verticalScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val pagerState = rememberPagerState { tabList.size }
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
                        modifier = Modifier
                            .padding(4.dp)
                            .clickable { }
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
        modifier = modifier.nestedScroll(verticalScrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(modifier = Modifier, Alignment.Center) {
                    UserImageComponent(
                        userIconDefinition = UserIconDefinition(
                            iconImageType = UserIconImageType.IconFromDrawableType(R.drawable.icons8_test_account_96),
                            hasStory = false,
                            userIconType = UserIconType.IconMyProfile(isNew = true)
                        )
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "18", fontWeight = FontWeight.Bold)
                    Text(text = "게시물")
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "84", fontWeight = FontWeight.Bold)
                    Text(text = "팔로워")
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "122", fontWeight = FontWeight.Bold)
                    Text(text = "팔로잉")
                }
                Spacer(modifier = Modifier.width(8.dp))
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
            Spacer(modifier = Modifier.height(20.dp))
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
                        }
                    )
                }
            }
            HorizontalPager(state = pagerState) {
                when (pagerState.currentPage) {
                    0 -> {
                        MyPhotoGird()
                    }

                    1 -> {
                        MyReelsGird()
                    }

                    2 -> {
                        MediaWithMeGird()
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
    modifier: Modifier = Modifier
) {
    Tab(
        selected = isSelected,
        onClick = { onClick() },
        icon = {
            if (isSelected) {
                Icon(
                    painter = painterResource(id = item.selectedIcon),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                )
            } else {
                Icon(
                    painter = painterResource(id = item.unselectedIcon),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                )
            }
        },
        modifier = modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun MyPhotoGird(modifier: Modifier = Modifier) {
    val itemSize = photoList.size
    val windowWidth = LocalConfiguration.current.screenWidthDp
    val maxHeight = windowWidth / 3 * (itemSize / 3)
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        flingBehavior = ScrollableDefaults.flingBehavior(),
        modifier = modifier
            .height(maxHeight.dp)
            .fillMaxSize(),
        userScrollEnabled = true,
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items(photoList) { item ->
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
fun MyReelsGird(modifier: Modifier = Modifier) {
    val itemSize = photoList.size
    val windowWidth = LocalConfiguration.current.screenWidthDp
    val maxHeight = windowWidth / 3 * (itemSize / 3)
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        flingBehavior = ScrollableDefaults.flingBehavior(),
        modifier = modifier
            .height(maxHeight.dp)
            .fillMaxSize(),
        userScrollEnabled = true,
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items(photoList) { item ->
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
fun MediaWithMeGird(modifier: Modifier = Modifier) {
    val list = emptyList<String>()
    val itemSize = list.size
    val windowWidth = LocalConfiguration.current.screenWidthDp
    val maxHeight = windowWidth / 3 * (itemSize / 3)
    if (list.isNotEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            modifier = modifier
                .height(maxHeight.dp)
                .fillMaxSize(),
            userScrollEnabled = false
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
            Spacer(modifier = Modifier.height(120.dp))
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

val photoList = listOf(
    "https://i.pinimg.com/736x/3f/92/2e/3f922eff0b495e6ff697178758d323da.jpg",
    "https://as2.ftcdn.net/v2/jpg/05/38/31/61/1000_F_538316173_VRwdsZDXy1HQqMNP6XiYLaUIZ1MhN3pq.jpg",
    "https://img.freepik.com/premium-photo/beautiful-landscapes-scenic-views-natural-wonders-breathtaking-scenery-tranquil-forests-serene_980716-15925.jpg",
    "https://www.cnn.co.jp/storage/2015/05/03/fbe00d376d9ae2bc25f0ebcfdbd5829e/35064036_007.jpg",
    "https://fujifilmsquare.jp/photosalon/tokyo/images/2019/190104012/190104012_02.jpg",
    "https://italian-guide.com/wp-content/uploads/2018/11/Beautiful-landscape-in-Tuscany-Italy.jpg",
    "https://i.pinimg.com/736x/3f/92/2e/3f922eff0b495e6ff697178758d323da.jpg",
    "https://as2.ftcdn.net/v2/jpg/05/38/31/61/1000_F_538316173_VRwdsZDXy1HQqMNP6XiYLaUIZ1MhN3pq.jpg",
    "https://img.freepik.com/premium-photo/beautiful-landscapes-scenic-views-natural-wonders-breathtaking-scenery-tranquil-forests-serene_980716-15925.jpg",
    "https://www.cnn.co.jp/storage/2015/05/03/fbe00d376d9ae2bc25f0ebcfdbd5829e/35064036_007.jpg",
    "https://fujifilmsquare.jp/photosalon/tokyo/images/2019/190104012/190104012_02.jpg",
    "https://italian-guide.com/wp-content/uploads/2018/11/Beautiful-landscape-in-Tuscany-Italy.jpg",
    "https://i.pinimg.com/736x/3f/92/2e/3f922eff0b495e6ff697178758d323da.jpg",
    "https://as2.ftcdn.net/v2/jpg/05/38/31/61/1000_F_538316173_VRwdsZDXy1HQqMNP6XiYLaUIZ1MhN3pq.jpg",
    "https://img.freepik.com/premium-photo/beautiful-landscapes-scenic-views-natural-wonders-breathtaking-scenery-tranquil-forests-serene_980716-15925.jpg",
    "https://www.cnn.co.jp/storage/2015/05/03/fbe00d376d9ae2bc25f0ebcfdbd5829e/35064036_007.jpg",
    "https://fujifilmsquare.jp/photosalon/tokyo/images/2019/190104012/190104012_02.jpg",
    "https://italian-guide.com/wp-content/uploads/2018/11/Beautiful-landscape-in-Tuscany-Italy.jpg",
    "https://i.pinimg.com/736x/3f/92/2e/3f922eff0b495e6ff697178758d323da.jpg",
    "https://as2.ftcdn.net/v2/jpg/05/38/31/61/1000_F_538316173_VRwdsZDXy1HQqMNP6XiYLaUIZ1MhN3pq.jpg",
    "https://img.freepik.com/premium-photo/beautiful-landscapes-scenic-views-natural-wonders-breathtaking-scenery-tranquil-forests-serene_980716-15925.jpg",
    "https://www.cnn.co.jp/storage/2015/05/03/fbe00d376d9ae2bc25f0ebcfdbd5829e/35064036_007.jpg",
    "https://fujifilmsquare.jp/photosalon/tokyo/images/2019/190104012/190104012_02.jpg",
    "https://italian-guide.com/wp-content/uploads/2018/11/Beautiful-landscape-in-Tuscany-Italy.jpg",
    "https://i.pinimg.com/736x/3f/92/2e/3f922eff0b495e6ff697178758d323da.jpg",
    "https://as2.ftcdn.net/v2/jpg/05/38/31/61/1000_F_538316173_VRwdsZDXy1HQqMNP6XiYLaUIZ1MhN3pq.jpg",
    "https://img.freepik.com/premium-photo/beautiful-landscapes-scenic-views-natural-wonders-breathtaking-scenery-tranquil-forests-serene_980716-15925.jpg",
    "https://www.cnn.co.jp/storage/2015/05/03/fbe00d376d9ae2bc25f0ebcfdbd5829e/35064036_007.jpg",
    "https://fujifilmsquare.jp/photosalon/tokyo/images/2019/190104012/190104012_02.jpg",
    "https://italian-guide.com/wp-content/uploads/2018/11/Beautiful-landscape-in-Tuscany-Italy.jpg",
    "https://i.pinimg.com/736x/3f/92/2e/3f922eff0b495e6ff697178758d323da.jpg",
    "https://as2.ftcdn.net/v2/jpg/05/38/31/61/1000_F_538316173_VRwdsZDXy1HQqMNP6XiYLaUIZ1MhN3pq.jpg",
    "https://img.freepik.com/premium-photo/beautiful-landscapes-scenic-views-natural-wonders-breathtaking-scenery-tranquil-forests-serene_980716-15925.jpg",
    "https://www.cnn.co.jp/storage/2015/05/03/fbe00d376d9ae2bc25f0ebcfdbd5829e/35064036_007.jpg",
    "https://fujifilmsquare.jp/photosalon/tokyo/images/2019/190104012/190104012_02.jpg",
    "https://italian-guide.com/wp-content/uploads/2018/11/Beautiful-landscape-in-Tuscany-Italy.jpg"
)

val reelsList = listOf(1, 2, 3, 4, 5)

val tabList = listOf(
    TabIcons(R.drawable.icons8_grid_50__1_, R.drawable.icons8_grid_50__2_),
    TabIcons(R.drawable.icons8_instagram_reels__1_, R.drawable.icons8_instagram_reels),
    TabIcons(R.drawable.instagram_tag_icon, R.drawable.instagram_tag_icon)
)
