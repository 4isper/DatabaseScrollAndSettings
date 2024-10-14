package com.example.lab6.task4

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

data class CurrencyRate(val name: String, val value: String)

fun parseCurrencyRates(xml: String): List<CurrencyRate> {
    val rates = mutableListOf<CurrencyRate>()
    val factory = XmlPullParserFactory.newInstance()
    val parser = factory.newPullParser()
    parser.setInput(xml.reader())

    var eventType = parser.eventType
    var name: String? = null
    var value: String? = null

    while (eventType != XmlPullParser.END_DOCUMENT) {
        when (eventType) {
            XmlPullParser.START_TAG -> {
                when (parser.name) {
                    "Name" -> name = parser.nextText()
                    "Value" -> value = parser.nextText()
                }
            }
            XmlPullParser.END_TAG -> {
                if (parser.name == "Valute" && name != null && value != null) {
                    rates.add(CurrencyRate(name, value))
                    name = null
                    value = null
                }
            }
        }
        eventType = parser.next()
    }
    return rates
}
