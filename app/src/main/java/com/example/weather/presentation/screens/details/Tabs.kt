package com.example.weather.presentation.screens.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.weather.R
import com.example.weather.domain.models.Weather
import kotlinx.coroutines.launch

enum class TabsItem(val value: Int) {
    Today(R.string.today),
    Tomorrow(R.string.tomorrow),
    Days(R.string.days)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabComponent(weather: Weather) {

    val tabs = listOf(
        TabsItem.Today,
        TabsItem.Tomorrow,
        TabsItem.Days
    )

    val pagerState = rememberPagerState()

    Column {
        Tabs(tabs = tabs, pagerState = pagerState)
        HorizontalPager(
            state = pagerState,
            pageCount = tabs.size,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            Column {
                when (page) {
                    0 -> TodayScreen(weather = weather)
                    1 -> TomorrowScreen(weather = weather)
                    2 -> NextDaysScreen(weather = weather)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tabs(tabs: List<TabsItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()

    TabRow(
        modifier = Modifier.fillMaxWidth(),
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colors.background,
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                text = {
                    Text(
                        text = stringResource(id = tab.value),
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
}