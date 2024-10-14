package com.example.lab6.task4

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Task4(modifier: Modifier) {
    var rates by remember { mutableStateOf<List<CurrencyRate>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var selectedCurrency by remember { mutableStateOf<CurrencyRate?>(null) }
    var statistics by remember { mutableStateOf<Triple<Double, Double, Double?>?>(null) }

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            try {
                val xmlData = RetrofitInstance.api.getCurrencyRates()
                rates = parseCurrencyRates(xmlData)
            } catch (e: Exception) {
                errorMessage = "Failed to load data: ${e.localizedMessage}"
            } finally {
                isLoading = false
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Курсы валют", fontWeight = FontWeight.W500) }, expandedHeight = 47.dp)
        }
    ) {
        Box(modifier = modifier.fillMaxSize().padding(start = 10.dp, end = 10.dp, top = 47.dp)) {
            when {
                isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                errorMessage != null -> Text(errorMessage ?: "Unknown error", color = MaterialTheme.colorScheme.error, modifier = Modifier.align(Alignment.Center))
                rates.isNotEmpty() -> {
                    LazyColumn(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(7.dp)) {
                        items(rates) { rate ->
                            CurrencyRateItem(rate) {
                                scope.launch {
                                    val last10DaysRates = getLast10DaysRates(rate.name)
                                    statistics = calculateStatistics(last10DaysRates)
                                    selectedCurrency = rate
                                }
                            }
                        }
                    }
                }
                else -> Text("No data available", modifier = Modifier.align(Alignment.Center))
            }
        }
    }

    statistics?.let {
        StatisticsDialog(statistics = it) {
            statistics = null
        }
    }
}


@Composable
fun CurrencyRateItem(rate: CurrencyRate, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(15.dp))
            .background(color = Color.LightGray)
            .clickable { onClick() }
    ) {
        Text(
            text = "${rate.name}: ${rate.value}",
            fontSize = 24.sp,
            modifier = Modifier.padding(7.dp)
        )
    }
}

fun calculateStatistics(rates: List<Double>): Triple<Double, Double, Double?> {
    val average = rates.average()
    val median = rates.sorted().let {
        if (it.size % 2 == 0) {
            (it[it.size / 2] + it[it.size / 2 - 1]) / 2
        } else {
            it[it.size / 2]
        }
    }
    val mode = rates.groupingBy { it }.eachCount().maxByOrNull { it.value }?.key
    return Triple(average, median, mode)
}

@Composable
fun StatisticsDialog(statistics: Triple<Double, Double, Double?>, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Статистика за последние 10 дней") },
        text = {
            Column {
                Text("Среднее: ${statistics.first}", fontSize = 20.sp)
                Text("Медиана: ${statistics.second}", fontSize = 20.sp)
                Text("Мода: ${statistics.third ?: "Нет"}", fontSize = 20.sp)
            }
        },
        confirmButton = {
            Button(onClick = { onDismiss() }) {
                Text("OK")
            }
        }
    )
}
