package com.gyleedev.clonestagram.ui.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gyleedev.clonestagram.R
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val comment = rememberTextFieldState()
    val focusManager = LocalFocusManager.current
    var isFocused by remember {
        mutableStateOf(false)
    }

    val searchedItem by viewModel.searchedList.collectAsStateWithLifecycle()

    Scaffold(modifier = modifier) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            SearchTextField(
                searchQuery = comment,
                focusManager = focusManager,
                onReset = {
                    comment.edit {
                        delete(
                            0,
                            comment.text.length
                        )
                    }
                },
                isFocused = isFocused,
                onFocusChange = { isFocused = it },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
            if (isFocused) {
                SearchedList(
                    searchedItem,
                    modifier = Modifier
                        .padding(innerPadding)
                        .consumeWindowInsets(innerPadding)
                )
            } else {
                MyPhotoGird(
                    modifier = Modifier
                        .padding(innerPadding)
                        .consumeWindowInsets(innerPadding)
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchTextField(
    searchQuery: TextFieldState,
    onReset: () -> Unit,
    isFocused: Boolean,
    onFocusChange: (Boolean) -> Unit,
    focusManager: FocusManager,
    modifier: Modifier = Modifier
) {
    var alpha by remember { mutableFloatStateOf(1f) }
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        if (isFocused) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clickable { focusManager.clearFocus(force = true) }
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
        BasicTextField2(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.surfaceContainerLow,
                    shape = RoundedCornerShape(8.dp)
                )
                .onFocusChanged {
                    alpha = if (it.isFocused) 0.6f else 1f
                    onFocusChange(it.isFocused)
                }
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            state = searchQuery,
            lineLimits = TextFieldLineLimits.SingleLine,
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurface),
            decorator = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
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

@Composable
fun MyPhotoGird(modifier: Modifier) {

    val itemSize = searchImageList.size
    val windowWidth = LocalConfiguration.current.screenWidthDp
    val maxHeight = windowWidth / 3 * (itemSize / 3) // * 2
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        flingBehavior = ScrollableDefaults.flingBehavior(),
        modifier = modifier
            .height(maxHeight.dp)
            .fillMaxSize(),
        userScrollEnabled = true,
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items(searchImageList) { item ->
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
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
        }
    }
}

@Composable
fun SearchedList(
    list: List<SearchedItemData>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceAround
        ) {
            Text(
                text = "최근 검색",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(10f))
            Text(
                text = "모두 검색",
                style = MaterialTheme.typography.labelMedium,
                color = colorResource(id = R.color.facebook_blue)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        list.forEach {
            SearchedItem(searchedItemData = it)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun SearchedItem(
    searchedItemData: SearchedItemData,
    modifier: Modifier = Modifier
) {
    val brushModifier = if (searchedItemData.unWatchedStory) {
        Modifier
            .border(
                width = 2.dp,
                brush = Brush.linearGradient(
                    colors = listOf(Color.Yellow, Color.Red),
                    start = Offset(0f, 0f),
                    end = Offset(180f, 180f)
                ),
                shape = CircleShape
            )
    } else {
        Modifier
    }
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(modifier = brushModifier) {
            Box(modifier = Modifier.padding(4.dp)) {
                CoilImage(
                    imageModel = { searchedItemData.link },
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
                        .size(48.dp)
                        .clip(CircleShape)
                )
            }
        }

        Spacer(modifier = Modifier.width(20.dp))
        Column {
            Text(
                text = searchedItemData.id,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = searchedItemData.status,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
        // TODO public component에서 한 것 처럼 weight를 사용하지 않고 row의 속성으로 수정
        Spacer(modifier = Modifier.weight(10f))
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
                .clickable { },
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}

data class SearchedItemData(
    val link: String,
    val id: String,
    val status: String,
    val unWatchedStory: Boolean
)

val searchImageList = listOf(
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
