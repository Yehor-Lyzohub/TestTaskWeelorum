package com.example.testtaskweelorum.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testtaskweelorum.R
import com.example.testtaskweelorum.model.FoodData
import com.example.testtaskweelorum.viewmodel.MainViewModel
import com.example.testtaskweelorum.viewmodel.FoodTab

@Composable
fun AddMealScreen(navigateToTraining: () -> Unit) {
    val viewModel = viewModel<MainViewModel>()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.new_gray))
    ) {
        val selectedTab = viewModel.selectedTab.collectAsState()

        Column {
            Column(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomEnd = 25.dp,
                            bottomStart = 25.dp
                        )
                    )
                    .background(colorResource(id = R.color.white))
                    .padding(12.dp)
            ) {
                AddFoodSection(navigateToTraining)
                SearchFieldSection(
                    viewModel.searchText.collectAsState().value,
                    viewModel::onSearchTextChange
                )
                ChipSection(selectedTab.value) {
                    viewModel.changeSelectedTab(it)
                }
            }
            val food = when (selectedTab.value) {
                FoodTab.SEARCH -> viewModel.food.collectAsState().value
                FoodTab.FAVORITE -> viewModel.favFood.collectAsState().value
                FoodTab.MY_FOOD -> viewModel.myFood.collectAsState().value
            }
            FoodSection(
                food = food,
                selectedTab.value
            )
        }
    }
}

@Composable
fun AddFoodSection(navigateToTraining: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Text(
                text = stringResource(R.string.add_food).uppercase(),
                fontSize = 12.sp,
                style = MaterialTheme.typography.titleSmall,
                color = colorResource(R.color.new_red)
            )
            Text(
                text = stringResource(R.string.breakfast),
                color = colorResource(R.color.new_black),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.headlineSmall
            )
        }
        IconButton(
            onClick = {
                navigateToTraining()
            }
        )
        {
            Image(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = "",
                modifier = Modifier
                    .background(colorResource(R.color.new_blue), shape = CircleShape)
                    .size(48.dp)
                    .padding(10.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchFieldSection(searchText: String, onSearchTextChange: (String) -> Unit) {
    TextField(
        value = searchText,
        colors = textFieldColors(containerColor = colorResource(R.color.new_gray)),
        onValueChange = onSearchTextChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 26.dp, bottom = 26.dp)
            .clip(RoundedCornerShape(12.dp)),
        placeholder = {
            Text(
                text = stringResource(R.string.search),
                color = colorResource(R.color.main_gray),
                fontSize = 16.sp
            )
        },
        leadingIcon = {
            Image(
                painter = painterResource(R.drawable.search_icon),
                contentDescription = "Search Icon"
            )
        }
    )
}

@Composable
fun ChipSection(selectedTab: FoodTab, changeSelectedTab: (FoodTab) -> Unit) {
    Row(
        Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(
                color = colorResource(id = R.color.new_gray),
                shape = RectangleShape
            )
            .padding(vertical = 2.dp, horizontal = 1.dp)
    ) {
        FoodTab(FoodTab.SEARCH, selectedTab, changeSelectedTab)
        FoodTab(FoodTab.FAVORITE, selectedTab, changeSelectedTab)
        FoodTab(FoodTab.MY_FOOD, selectedTab, changeSelectedTab)
    }
}

@Composable
fun RowScope.FoodTab(currentTab: FoodTab, selectedTab: FoodTab, changeSelectedTab: (FoodTab) -> Unit) {
    val title = when (currentTab) {
        FoodTab.SEARCH -> stringResource(R.string.search)
        FoodTab.FAVORITE -> stringResource(R.string.favorite)
        FoodTab.MY_FOOD -> stringResource(R.string.my_meals)
    }
    TabButton(isSelected = selectedTab == currentTab, text = title) {
        changeSelectedTab(currentTab)
    }
}

@Composable
fun RowScope.TabButton(isSelected: Boolean, text: String, click: () -> Unit) {
    Text(
        text = text,
        fontSize = 12.sp,
        color = if (isSelected) Color.White else colorResource(R.color.main_gray),
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .clickable { click() }
            .height(32.dp)
            .padding(horizontal = 1.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(if (isSelected) colorResource(R.color.new_red) else Color.Transparent)
            .wrapContentHeight()
            .weight(1f)
    )
}

@Composable
fun FoodSection(food: List<FoodData>, selectedTab: FoodTab) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 22.dp, bottom = 12.dp, start = 12.dp, end = 12.dp)
            .clip(RoundedCornerShape(15.dp))
    ) {

        when (selectedTab) {
            FoodTab.SEARCH -> renderSearchTabContent(food)
            FoodTab.FAVORITE -> renderFavoriteTabContent(food)
            FoodTab.MY_FOOD -> renderMyFoodTabContent(food)
        }
    }
}

fun LazyListScope.renderSearchTabContent(food: List<FoodData>) {
    item {
        FoodHeader(stringResource(R.string.latest_food))
    }
    itemsIndexed(food) { index, item ->
        val isFirst = index == 0
        val isLast = index == food.size - 1
        FoodItem(item, isFirst, isLast)
    }
    item {
        OtherOptionsTitle()
    }
    item {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
        ) {
            FastAddingItem()
            ScanningItem()
        }
    }
}

fun LazyListScope.renderFavoriteTabContent(food: List<FoodData>) {
    item {
        FoodHeader(stringResource(R.string.selected_food))
    }
    itemsIndexed(food) { index, item ->
        val isFirst = index == 0
        val isLast = index == food.size - 1
        FoodItem(item, isFirst, isLast)
    }
}

fun LazyListScope.renderMyFoodTabContent(food: List<FoodData>) {
    item {
        FoodHeader(stringResource(R.string.my_meals))
    }
    itemsIndexed(food) { index, item ->
        val isFirst = index == 0
        val isLast = index == food.size - 1
        FoodItem(item, isFirst, isLast)
    }
}

@Composable
fun FoodHeader(text: String) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.padding(bottom = 12.dp)
    )
}

@Composable
fun FoodItem(food: FoodData, isFirst: Boolean, isLast: Boolean) {
    val modifier = if (isFirst) {
        Modifier.clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
    } else if (isLast) {
        Modifier.clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
    } else {
        Modifier
    }

    Row(modifier = modifier.background(Color.White)) {
        Box(
            modifier = Modifier
                .padding(start = 16.dp, top = 14.dp, end = 12.dp, bottom = 14.dp)
        ) {
            if (food.isMine) {
                Image(
                    painter = painterResource(id = R.drawable.meal_icon),
                    contentDescription = "Food icon",
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.food_icon),
                    contentDescription = "Food icon",
                )

            }
            if (food.isFavorite) {
                Image(painter = painterResource(id = R.drawable.heart_icon), "favorite icon", modifier = Modifier.align(TopEnd))
            }
        }
        Column(
            modifier =
            Modifier
                .weight(1f)
                .align(CenterVertically)
                .fillMaxHeight()
        ) {
            Text(
                text = food.name,
                fontSize = 16.sp,
                style = MaterialTheme.typography.headlineSmall,
                color = colorResource(R.color.new_black),
                modifier =
                Modifier.align(Alignment.Start)
            )
            Text(
                text = food.calories,
                fontSize = 12.sp,
                color = colorResource(R.color.main_gray),
                style = MaterialTheme.typography.titleMedium,
            )
        }
        Text(
            text = stringResource(R.string.yesterday),
            fontSize = 12.sp,
            color = colorResource(R.color.main_gray),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp)
                .align(CenterVertically)
        )
    }
    if (!isLast) {
        Divider(thickness = 1.dp, color = colorResource(id = R.color.divider_color))
    }
}

@Composable
fun OtherOptionsTitle() {
    Text(
        text = stringResource(R.string.other_options),
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        style = MaterialTheme.typography.headlineSmall,
        color = colorResource(R.color.new_black),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 22.dp, bottom = 12.dp)
    )
}

@Composable
fun FastAddingItem() {
    val context = LocalContext.current
    Row(Modifier.clickable {
        Toast.makeText(context, "Це швидке додавання.", Toast.LENGTH_SHORT).show()
    }) {
        Image(
            painterResource(id = R.drawable.fire_icon),
            contentDescription = "",
            modifier = Modifier
                .padding(18.dp)
        )
        Text(
            text = stringResource(R.string.fast_adding),
            fontSize = 16.sp,
            style = MaterialTheme.typography.titleLarge,
            color = colorResource(R.color.new_black),
            modifier = Modifier
                .align(CenterVertically)
                .weight(1f)
                .padding(8.dp)
        )
    }
    Divider()
}

@Composable
fun ScanningItem() {
    val context = LocalContext.current
    Row(Modifier.clickable {
        Toast.makeText(context, "Це сканування.", Toast.LENGTH_SHORT).show()
    }) {
        Image(
            painterResource(id = R.drawable.scan_icon),
            contentDescription = "",
            modifier = Modifier
                .padding(18.dp)
        )
        Text(
            text = stringResource(R.string.scan),
            fontSize = 16.sp,
            style = MaterialTheme.typography.titleLarge,
            color = colorResource(R.color.new_black),
            modifier = Modifier
                .align(CenterVertically)
                .weight(1f)
                .padding(8.dp)
        )
    }
}

@Preview
@Composable
fun SimpleComposablePreview() {
    AddMealScreen {

    }
}