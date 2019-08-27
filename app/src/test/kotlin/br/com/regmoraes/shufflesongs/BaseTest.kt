package br.com.regmoraes.shufflesongs

import io.mockk.MockKAnnotations
import org.junit.Before

abstract class BaseTest {

    @Before
    open fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }
}