package com.example.lab6.task4

import java.text.SimpleDateFormat
import java.util.*

suspend fun getLast10DaysRates(currencyName: String): List<Double> {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val calendar = Calendar.getInstance()
    val rates = mutableListOf<Double>()

    for (i in 0 until 10) {
        val date = dateFormat.format(calendar.time)
        val xmlData = RetrofitInstance.api.getCurrenciesByDate(date)
        val dailyRates = parseCurrencyRates(xmlData)
        val rate = dailyRates.find { it.name == currencyName }?.value?.replace(",", ".")?.toDoubleOrNull()
        if (rate != null) {
            rates.add(rate)
        }
        calendar.add(Calendar.DAY_OF_YEAR, -1)
    }
    return rates
}
