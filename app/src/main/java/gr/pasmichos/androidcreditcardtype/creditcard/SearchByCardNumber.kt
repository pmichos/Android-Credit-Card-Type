package gr.pasmichos.androidcreditcardtype.creditcard

object SearchByCardNumber {
    fun search(cardNumber: String, cardBrandList: List<CardBrand>): CardBrand {
        val listOfCardBrands = cardBrands(cardNumber, cardBrandList)

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

                            cardBrands.add(CardBrand(cardBrand.name, cardBrand.displayName, listOf(), listOf(), patternInRange.toString().length))
                        }

                    } else {
                        val matches = matchesPatternLength(cardNumber, pattern)

                        if(!matches) {
                            continue
                        }

                        //Log.i("CreditCardTypeManager", "cardBrand - name=[${cardBrand.cardName}] - matches=[${matches}] pattern.length=[${pattern.length}]")

                        cardBrands.add(CardBrand(cardBrand.name, cardBrand.displayName, listOf(), listOf(), pattern.length))
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
            return CardBrand("UNKNOWN", "UNKNOWN", listOf(), listOf(), 0)
        }
    }

    private fun matchesPatternLength(cardNumber: String, pattern: String): Boolean {
        return pattern == cardNumber.substring(0, pattern.length)
    }

    private fun cardBrands(cardNumber: String, cardBrandList: List<CardBrand>): MutableList<CardBrand> {

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
}