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

        //println("found [${cardTypes.cardTypes.size}] cardTypes.")
        for (cardType in cardTypes.cardTypes) {
            //println("cardType=[${cardType.name}]")

            val aidsPatternList = mutableListOf<String>()
            for(aid in cardType.aids) {
                aidsPatternList.add(aid)
            }

            val binsPatternList = mutableListOf<String>()
            for(bin in cardType.bins) {
                binsPatternList.add(bin)
            }
            cardBrandList.add(CardBrand(cardType.name, cardType.displayName, aidsPatternList, binsPatternList, 0))
        }
    }

    fun detect(aid: String = "", cardNumberInput: String = ""): CardBrand {
        if (aid.isEmpty() && cardNumberInput.isEmpty()) {
            return CardBrand("UNKNOWN", "UNKNOWN", listOf(), listOf(),0)
        }

        val cardNumber = cardNumberInput.trim()

        return if (aid.isNotEmpty()) {
            SearchByAid.search(aid, cardNumber, cardBrandList)
        } else { // cardNumber
            SearchByCardNumber.search(cardNumber, cardBrandList)
        }
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