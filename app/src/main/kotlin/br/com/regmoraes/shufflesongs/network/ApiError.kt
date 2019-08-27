package br.com.regmoraes.shufflesongs.network

/**
 *   Copyright {2019} {Rômulo Eduardo G. Moraes}
 **/
data class ApiError(val statusMessage: String): Throwable(message = statusMessage)