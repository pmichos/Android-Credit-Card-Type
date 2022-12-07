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

        CreditCardTypeManager.getInstance().loadTest()
    }

    @Test
    fun addition_isCorrectAmex() {
        assertEquals("AMEX", CreditCardTypeManager.getInstance().detect("346066395771607").cardName)
        assertEquals("AMEX", CreditCardTypeManager.getInstance().detect("379595897566750").cardName)
        assertEquals("AMEX", CreditCardTypeManager.getInstance().detect("379693054617670").cardName)
        assertEquals("AMEX", CreditCardTypeManager.getInstance().detect("374824710768640").cardName)
        assertEquals("AMEX", CreditCardTypeManager.getInstance().detect("343018715745392").cardName)
        assertEquals("AMEX", CreditCardTypeManager.getInstance().detect("375593000000000").cardName)
        assertEquals("AMEX", CreditCardTypeManager.getInstance().detect("375534900000000").cardName)
    }

    @Test
    fun addition_isCorrectCup() {
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("6200000000000000").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("6210945888010005").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("6210945888021").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("6210945888030003").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("6210945888040000007").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("6210946888053").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("6210946888060008").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("6210946888070000009").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("6210946888080006").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("6210946888090005").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("6210945888100004").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("6210946888110001").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("6210945888120002").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("6210945888130001").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("6210946888140008").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("6210946888150007").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("6210946888160006").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("6210946888170005").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("6210946888180004").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("6292600090881161").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("8171999900000018").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("8171999900000000021").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("8163999900010").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("6210947000000013").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("6210947100000012").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("6210947000000020011").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("6210947000000021").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("6210947100029").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("6210947000000039").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("6210947100000038").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("6210947000000047").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("6210947000000054").cardName)
        assertEquals("CUP", CreditCardTypeManager.getInstance().detect("6210947000000062").cardName)
    }

    @Test
    fun addition_isCorrectVisa() {
        assertEquals("VISA", CreditCardTypeManager.getInstance().detect("4444333322221111").cardName)
        assertEquals("VISA", CreditCardTypeManager.getInstance().detect("4911830000000").cardName)
        assertEquals("VISA", CreditCardTypeManager.getInstance().detect("4917610000000000").cardName)
        assertEquals("VISA", CreditCardTypeManager.getInstance().detect("4057840000000000").cardName)
        assertEquals("VISA", CreditCardTypeManager.getInstance().detect("4550390000000000").cardName)
        assertEquals("VISA", CreditCardTypeManager.getInstance().detect("4792730000000000").cardName)
        assertEquals("VISA", CreditCardTypeManager.getInstance().detect("4462030000000000").cardName)
        assertEquals("VISA", CreditCardTypeManager.getInstance().detect("4917610000000000003").cardName)
        assertEquals("VISA", CreditCardTypeManager.getInstance().detect("4917300800000000").cardName)
        assertEquals("VISA", CreditCardTypeManager.getInstance().detect("4484070000000000").cardName)
    }

    @Test
    fun addition_isCorrectMastercard() {
        assertEquals("MASTERCARD", CreditCardTypeManager.getInstance().detect("5555555555554444").cardName)
        assertEquals("MASTERCARD", CreditCardTypeManager.getInstance().detect("5454545454545454").cardName)
        assertEquals("MASTERCARD", CreditCardTypeManager.getInstance().detect("5167320000000000").cardName)
        assertEquals("MASTERCARD", CreditCardTypeManager.getInstance().detect("5458650000000000").cardName)
        assertEquals("MASTERCARD", CreditCardTypeManager.getInstance().detect("2720994753245108").cardName)
        assertEquals("MASTERCARD", CreditCardTypeManager.getInstance().detect("5299280211777880").cardName)
        assertEquals("MASTERCARD", CreditCardTypeManager.getInstance().detect("2221004151714145").cardName)
    }

    @Test
    fun addition_isCorrectElo() {
        assertEquals("ELO", CreditCardTypeManager.getInstance().detect("6505602939658753").cardName)
        assertEquals("ELO", CreditCardTypeManager.getInstance().detect("431274775578248").cardName)
        assertEquals("ELO", CreditCardTypeManager.getInstance().detect("5067211462821382").cardName)
    }
}