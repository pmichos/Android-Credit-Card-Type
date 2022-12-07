package gr.pasmichos.androidcreditcardtype.creditcard

class CreditCardTypeManager {
    private val cardBrandList: MutableList<CardBrand> = mutableListOf()

    fun loadTest() {

        // VISA
        val visaPatternList = mutableListOf<String>()
        visaPatternList.add("4")
        cardBrandList.add(CardBrand("VISA", visaPatternList, 0))

        // Mastercard
        val mastercardPatternList = mutableListOf<String>()
        mastercardPatternList.add("51-55")
        mastercardPatternList.add("2221-2229")
        mastercardPatternList.add("223-229")
        mastercardPatternList.add("23-26")
        mastercardPatternList.add("270-271")
        mastercardPatternList.add("2720")
        cardBrandList.add(CardBrand("MASTERCARD", mastercardPatternList, 0))

        // AMEX
        val amexPatternList = mutableListOf<String>()
        amexPatternList.add("34-37")
        cardBrandList.add(CardBrand("AMEX", amexPatternList, 0))

        // Diners
        val dinersPatternList = mutableListOf<String>()
        dinersPatternList.add("300-305")
        mastercardPatternList.add("36")
        mastercardPatternList.add("38")
        mastercardPatternList.add("39")
        cardBrandList.add(CardBrand("DINERS", dinersPatternList, 0))

        // Discover
        // JCB

        // CUP
        val cupPatternList = mutableListOf<String>()
        cupPatternList.add("620")
        cupPatternList.add("62100-62182")
        cupPatternList.add("62184-62187")
        cupPatternList.add("62185-62197")
        cupPatternList.add("62200-62205")
        cupPatternList.add("622010-622999")
        cupPatternList.add("622018")
        cupPatternList.add("62207-62209")
        cupPatternList.add("623-626")
        cupPatternList.add("6270")
        cupPatternList.add("6272")
        cupPatternList.add("6276")
        cupPatternList.add("627700-627779")
        cupPatternList.add("627781-627799")
        cupPatternList.add("6282-6289")
        cupPatternList.add("6291")
        cupPatternList.add("6292")
        cupPatternList.add("810")
        cupPatternList.add("8110-8131")
        cupPatternList.add("8132-8151")
        cupPatternList.add("8152-8163")
        cupPatternList.add("8164-8171")
        cardBrandList.add(CardBrand("CUP", cupPatternList, 0))

        // MAESTRO
        val maestroPatternList = mutableListOf<String>()
        maestroPatternList.add("493698")
        cupPatternList.add("500000-504174")
        cupPatternList.add("504176-506698")
        cupPatternList.add("506779-508999")
        cupPatternList.add("56-59")
        maestroPatternList.add("63")
        maestroPatternList.add("67")
        maestroPatternList.add("6")
        cardBrandList.add(CardBrand("MAESTRO", maestroPatternList, 0))

        // ELO
        val eloPatternList = mutableListOf<String>()
        eloPatternList.add("401178")
        eloPatternList.add("401179")
        eloPatternList.add("438935")
        eloPatternList.add("457631")
        eloPatternList.add("457632")
        eloPatternList.add("431274")
        eloPatternList.add("451416")
        eloPatternList.add("457393")
        eloPatternList.add("504175")
        eloPatternList.add("506699-506778")
        eloPatternList.add("509000-509999")
        eloPatternList.add("627780")
        eloPatternList.add("636297")
        eloPatternList.add("636368")
        eloPatternList.add("650031-650033")
        eloPatternList.add("650035-650051")
        eloPatternList.add("650405-650439")
        eloPatternList.add("650485-650538")
        eloPatternList.add("650541-650598")
        eloPatternList.add("650700-650718")
        eloPatternList.add("650720-650727")
        eloPatternList.add("650901-650978")
        eloPatternList.add("651652-651679")
        eloPatternList.add("655000-655019")
        eloPatternList.add("655021-655058")


        cardBrandList.add(CardBrand("ELO", eloPatternList, 0))
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

                for (pattern in cardBrand.pattern) {

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

                            cardBrands.add(CardBrand(cardBrand.cardName, listOf(), patternInRange.toString().length))
                        }

                    } else {
                        val matches = matchesPatternLength(cardNumber, pattern)

                        if(!matches) {
                            continue
                        }

                        //Log.i("CreditCardTypeManager", "cardBrand - name=[${cardBrand.cardName}] - matches=[${matches}] pattern.length=[${pattern.length}]")

                        cardBrands.add(CardBrand(cardBrand.cardName, listOf(), pattern.length))
                    }
                }
            }

            val res = cardBrands.reduce  { acc, cur ->
                if (acc.matches < cur.matches) {
                    return@reduce cur
                }

                acc
            }

            return res
        } else if (listOfCardBrands.size == 1) {
            return listOfCardBrands[0]
        } else { // Unknown
            return CardBrand("UNKNOWN", listOf(), 0)
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
            for (pattern in cardBrand.pattern) {

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