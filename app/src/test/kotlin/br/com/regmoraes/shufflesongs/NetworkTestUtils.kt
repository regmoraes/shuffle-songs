package br.com.regmoraes.shufflesongs


/**
 *   Copyright {2019} {Rômulo Eduardo G. Moraes}
 **/
internal object NetworkTestUtils {

    fun getStringContentFromFile(filePath: String): String {

        val inputStream = this::class.java.classLoader?.getResourceAsStream(filePath)

        return inputStream?.bufferedReader().use { it?.readText() ?: "" }
    }
}