package gr.pasmichos.androidcreditcardtype.creditcard.model

import kotlinx.serialization.Serializable

@Serializable
data class CardTypes(val cardTypes: List<CardType>)