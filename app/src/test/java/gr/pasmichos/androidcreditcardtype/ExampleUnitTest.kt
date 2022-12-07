package gr.pasmichos.androidcreditcardtype

import gr.pasmichos.androidcreditcardtype.creditcard.CreditCardTypeManager
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(RobolectricTestRunner::class)
class ExampleUnitTest {

    @Before
    fun load() {
        val context = RuntimeEnvironment.application.applicationContext

        CreditCardTypeManager.getInstance().loadFromAssets(context)
    }

    @Test
    fun addition_isCorrectAmex() {
        assertEquals("AMEX", CreditCardTypeManager.getInstance().detect("346066395771607").name)
        assertEquals("AMEX", CreditCardTypeManager.getInstance().detect("379595897566750").name)
        assertEquals("AMEX", CreditCardTypeManager.getInstance().detect("379693054617670").name)
        assertEquals("AMEX", CreditCardTypeManager.getInstance().detect("374824710768640").name)
        assertEquals("AMEX", CreditCardTypeManager.getInstance().detect("343018715745392").name)
        assertEquals("AMEX", CreditCardTypeManager.getInstance().detect("375593000000000").name)
        assertEquals("AMEX", CreditCardTypeManager.getInstance().detect("375534900000000").name)
    }

    // Cards are from UL Tool documentation
    @Test
    fun addition_isCorrectCup() {

        // Test Cards (Quick Pass)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6200000000000000").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210945888010005").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210945888021").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210945888030003").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210945888040000007").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210946888053").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210946888060008").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210946888070000009").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210946888080006").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210946888090005").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210945888100004").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210946888110001").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210945888120002").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210945888130001").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210946888140008").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210946888150007").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210946888160006").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210946888170005").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210946888180004").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6292600090881161").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("8171999900000018").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("8171999900000000021").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("8163999900010").name)

        // Functional Test Cards
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210948000000011").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210948000000029").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210948000000052").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210948000000037").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210948000000045").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210948000000060").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210948000000078").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210948000000086").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210948000000094").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210948000000102").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210948000000110").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210948000000128").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210948000000136").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210948000000144").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210948000000000152").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210948000000169").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210948000000177").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210948000000185").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210948000000193").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210948000000201").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210948000000219").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210948000000227").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210948000000235").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210948000000243").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210948000010242").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210948000000250").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210948000000268").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210948000000037").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("8171999900000018").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("8171999900000000021").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("8163999900010").name)

        // Integration Test Cards
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210947000000013").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210947100000012").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210947000000020011").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210947000000021").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210947100029").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210947000000039").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210947100000038").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210947000000047").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210947000000054").name)
        assertEquals("UPI", CreditCardTypeManager.getInstance().detect("6210947000000062").name)
    }

    @Test
    fun addition_isCorrectVisa() {
        assertEquals("VISA", CreditCardTypeManager.getInstance().detect("4444333322221111").name)
        assertEquals("VISA", CreditCardTypeManager.getInstance().detect("4911830000000").name)
        assertEquals("VISA", CreditCardTypeManager.getInstance().detect("4917610000000000").name)
        assertEquals("VISA", CreditCardTypeManager.getInstance().detect("4057840000000000").name)
        assertEquals("VISA", CreditCardTypeManager.getInstance().detect("4550390000000000").name)
        assertEquals("VISA", CreditCardTypeManager.getInstance().detect("4792730000000000").name)
        assertEquals("VISA", CreditCardTypeManager.getInstance().detect("4462030000000000").name)
        assertEquals("VISA", CreditCardTypeManager.getInstance().detect("4917610000000000003").name)
        assertEquals("VISA ELECTRON", CreditCardTypeManager.getInstance().detect("4917300800000000").name)
        assertEquals("VISA", CreditCardTypeManager.getInstance().detect("4484070000000000").name)
    }

    @Test
    fun addition_isCorrectMastercard() {
        assertEquals("MASTERCARD", CreditCardTypeManager.getInstance().detect("5555555555554444").name)
        assertEquals("MASTERCARD", CreditCardTypeManager.getInstance().detect("5454545454545454").name)
        assertEquals("MASTERCARD", CreditCardTypeManager.getInstance().detect("5167320000000000").name)
        assertEquals("MASTERCARD", CreditCardTypeManager.getInstance().detect("5458650000000000").name)
        assertEquals("MASTERCARD", CreditCardTypeManager.getInstance().detect("2720994753245108").name)
        assertEquals("MASTERCARD", CreditCardTypeManager.getInstance().detect("5299280211777880").name)
        assertEquals("MASTERCARD", CreditCardTypeManager.getInstance().detect("2221004151714145").name)
    }

    @Test
    fun addition_isCorrectElo() {
        assertEquals("ELO", CreditCardTypeManager.getInstance().detect("6505602939658753").name)
        assertEquals("ELO", CreditCardTypeManager.getInstance().detect("431274775578248").name)
        assertEquals("ELO", CreditCardTypeManager.getInstance().detect("5067211462821382").name)
    }
}