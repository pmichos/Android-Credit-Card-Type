package gr.pasmichos.androidcreditcardtype.creditcard

import android.content.Context
import gr.pasmichos.androidcreditcardtype.creditcard.model.CardTypes
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class CreditCardTypeManager {

    private val json: Json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
        coerceInputValues = true
    }

    private val cardBrandList: MutableList<CardBrand> = mutableListOf()

    fun loadFromAssets(context: Context) {
        val jsonTemplate =
            context.assets.open("card_types.json").bufferedReader().use {
                it.readText()
            }

        val cardTypes: CardTypes = json.decodeFromString(jsonTemplate)

        //println("jsonTemplate=[${jsonTemplate}]")

        println("found [${cardTypes.cardTypes.size}] cardTypes.")
        for (cardType in cardTypes.cardTypes) {
            //println("cardType=[${cardType.name}]")

            val binsPatternList = mutableListOf<String>()
            for(bin in cardType.bins) {
                binsPatternList.add(bin)
            }
            cardBrandList.add(CardBrand(cardType.name, cardType.displayName, binsPatternList, 0))
        }
    }

    fun detect(cardNumber: String): CardBrand {
        //Log.i("CreditCardTypeManager", "detect - cardNumber=[${cardNumber}]")

        val listOfCardBrands = cardBrands(cardNumber)
        for (cardBrand in listOfCardBrands) {
            //Log.i("CreditCardTypeManager", "cardBrand - name=[${cardBrand.cardName}]")
        }

        // Need to use best match
        if(listOfCardBrands.size > 1) {
            val cardBrands = mutableListOf<CardBrand>()

            for (cardBrand in listOfCardBrands) {

                for (pattern in cardBrand.binsPattern) {

                    if(pattern.contains("-")) {
                        //Log.i("CreditCardTypeManager", "multiple ranges - pattern=[${pattern}]")
                        val splittedPattern = pattern.split("-")
                        for(patternInRange in splittedPattern[0].toInt()..splittedPattern[1].toInt()) {
                            //Log.i("CreditCardTypeManager", "multiple range - patternInRange=[${patternInRange}]")

                            // Process
                            val matches = matchesPatternLength(cardNumber, patternInRange.toString())

                            if(!matches) {
                                continue
                            }

                            //Log.i("CreditCardTypeManager", "cardBrand - name=[${cardBrand.cardName}] - matches=[${matches}] pattern.length=[${patternInRange.toString().length}]")

                            cardBrands.add(CardBrand(cardBrand.name, cardBrand.displayName, listOf(), patternInRange.toString().length))
                        }

                    } else {
                        val matches = matchesPatternLength(cardNumber, pattern)

                        if(!matches) {
                            continue
                        }

                        //Log.i("CreditCardTypeManager", "cardBrand - name=[${cardBrand.cardName}] - matches=[${matches}] pattern.length=[${pattern.length}]")

                        cardBrands.add(CardBrand(cardBrand.name, cardBrand.displayName, listOf(), pattern.length))
                    }
                }
            }

            val res = cardBrands.reduce  { acc, cur ->
                if (acc.strengthMatches < cur.strengthMatches) {
                    return@reduce cur
                }

                acc
            }

            return res
        } else if (listOfCardBrands.size == 1) {
            return listOfCardBrands[0]
        } else { // Unknown
            return CardBrand("UNKNOWN", "UNKNOWN", listOf(), 0)
        }
    }

    fun matchesPatternLength(cardNumber: String, pattern: String): Boolean {
        return pattern == cardNumber.substring(0, pattern.length)
    }

    fun cardBrands(cardNumber: String): MutableList<CardBrand> {

        val mutableList = mutableListOf<CardBrand>()

        for (cardBrand in cardBrandList) {
            if(mutableList.contains(cardBrand)) {
               continue
            }

            // Patterns
            for (pattern in cardBrand.binsPattern) {

                // MIN MAX FUNCTIONALITY
                if(pattern.contains("-")) {
                    //Log.i("CreditCardTypeManager", "multiple ranges - pattern=[${pattern}]")
                    val splittedPattern = pattern.split("-")
                    for(patternInRange in splittedPattern[0].toInt()..splittedPattern[1].toInt()) {
                        //Log.i("CreditCardTypeManager", "multiple range - patternInRange=[${patternInRange}]")

                        // Process
                        if(cardNumber.startsWith(patternInRange.toString())) {

                            if(mutableList.contains(cardBrand)) {
                                continue
                            }

                            //Log.i("CreditCardTypeManager", "cardNumber in pattern =[${pattern}]")
                            mutableList.add(cardBrand)
                        }
                    }

                } else {
                    // Process
                    if(cardNumber.startsWith(pattern)) {

                        if(mutableList.contains(cardBrand)) {
                            continue
                        }

                        //Log.i("CreditCardTypeManager", "cardNumber in pattern =[${pattern}]")
                        mutableList.add(cardBrand)
                    }
                }
            }
        }

        return mutableList
    }

    companion object {
        @Volatile private var mInstance: CreditCardTypeManager? = null

        fun getInstance(): CreditCardTypeManager =
            mInstance ?: synchronized(this) {
                val newInstance = mInstance ?: CreditCardTypeManager().also { mInstance = it }
                newInstance
            }
    }
}