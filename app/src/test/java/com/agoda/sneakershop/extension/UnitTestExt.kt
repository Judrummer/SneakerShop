package com.agoda.sneakershop.extension

import org.junit.Assert

//Credit https://github.com/MarkusAmshove/Kluent
infix fun Any?.shouldEqual(expected: Any?) {
    Assert.assertEquals(expected, this)
}

fun test(){
    "10".shouldEqual("10")
    "10" shouldEqual "10"
}