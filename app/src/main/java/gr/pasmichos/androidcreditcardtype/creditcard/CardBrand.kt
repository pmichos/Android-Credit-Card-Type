package gr.pasmichos.androidcreditcardtype.creditcard

data class CardBrand(var name: String, var displayName: String, val kernelId: Int, var aidsPattern: List<String>, var binsPattern: List<String>, var strengthMatches: Int)