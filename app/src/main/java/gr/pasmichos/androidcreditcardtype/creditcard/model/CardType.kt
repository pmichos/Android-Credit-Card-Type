package gr.pasmichos.androidcreditcardtype.creditcard.model

import kotlinx.serialization.Serializable

@Serializable
data class CardType(val id: Int,
                    val kernelId: Int,
                    val name: String,
                    val displayName: String,
                    val aids: List<String>,
                    val bins: List<String>)