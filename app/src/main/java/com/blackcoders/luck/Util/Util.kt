package com.blackcoders.luck.Util

object Util {
    fun rand(low: Int, high: Int): Int {
        require(low <= high) { "Invalid range" }
        return (Math.random() * (high - low + 1)).toInt() + low
    }
}