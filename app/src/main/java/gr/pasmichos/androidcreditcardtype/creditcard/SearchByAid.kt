package gr.pasmichos.androidcreditcardtype.creditcard

object SearchByAid {
    fun search(aid: String, fallbackCardNumber: String, cardBrandList: List<CardBrand>): CardBrand {
        val listOfCardBrands = cardBrands(aid, cardBrandList)

        // Need to use best match
        if(listOfCardBrands.size > 1) {
            val cardBrands = mutableListOf<CardBrand>()

            for (cardBrand in listOfCardBrands) {

                for (pattern in cardBrand.aidsPattern) {

                    if(pattern.contains("-")) {
                        //Log.i("CreditCardTypeManager", "multiple ranges - pattern=[${pattern}]")
                        val splittedPattern = pattern.split("-")
                        for(patternInRange in splittedPattern[0].toInt()..splittedPattern[1].toInt()) {
                            //Log.i("CreditCardTypeManager", "multiple range - patternInRange=[${patternInRange}]")

                            // Process
                            val matches = matchesPatternLength(aid, patternInRange.toString())

                            if(!matches) {
                                continue
                            }

                            //Log.i("CreditCardTypeManager", "cardBrand - name=[${cardBrand.cardName}] - matches=[${matches}] pattern.length=[${patternInRange.toString().length}]")

                            cardBrands.add(CardBrand(cardBrand.name, cardBrand.displayName, cardBrand.kernelId, listOf(), listOf(), patternInRange.toString().length))
                        }

                    } else {
                        val matches = matchesPatternLength(aid, pattern)

                        if(!matches) {
                            continue
                        }

                        //Log.i("CreditCardTypeManager", "cardBrand - name=[${cardBrand.cardName}] - matches=[${matches}] pattern.length=[${pattern.length}]")

                        cardBrands.add(CardBrand(cardBrand.name, cardBrand.displayName, cardBrand.kernelId, listOf(), listOf(), pattern.length))
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

            // Not in list. Fallback to card number
            if (fallbackCardNumber.isNotEmpty()) {
                return SearchByCardNumber.search(fallbackCardNumber, cardBrandList)
            } else {
                return CardBrand("UNKNOWN", "UNKNOWN", 0, listOf(), listOf(), 0)
            }
        }
    }

    private fun matchesPatternLength(cardNumber: String, pattern: String): Boolean {
        return pattern == cardNumber.substring(0, pattern.length)
    }

    private fun cardBrands(aid: String, cardBrandList: List<CardBrand>): MutableList<CardBrand> {

        val mutableList = mutableListOf<CardBrand>()

        for (cardBrand in cardBrandList) {
            if(mutableList.contains(cardBrand)) {
                continue
            }

            // Patterns
            for (pattern in cardBrand.aidsPattern) {

                // MIN MAX FUNCTIONALITY
                if(pattern.contains("-")) {
                    //Log.i("CreditCardTypeManager", "multiple ranges - pattern=[${pattern}]")
                    val splittedPattern = pattern.split("-")
                    for(patternInRange in splittedPattern[0].toInt()..splittedPattern[1].toInt()) {
                        //Log.i("CreditCardTypeManager", "multiple range - patternInRange=[${patternInRange}]")

                        // Process
                        if(aid.startsWith(patternInRange.toString())) {

                            if(mutableList.contains(cardBrand)) {
                                continue
                            }

                            //Log.i("CreditCardTypeManager", "cardNumber in pattern =[${pattern}]")
                            mutableList.add(cardBrand)
                        }
                    }

                } else {
                    // Process
                    if(aid.startsWith(pattern)) {

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