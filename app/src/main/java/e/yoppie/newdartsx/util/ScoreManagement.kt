package e.yoppie.newdartsx.util

import android.view.KeyEvent

class ScoreManagement(keyEvent: KeyEvent) {
    private val keyCode = keyEvent.keyCode
    private val preCode = 59

    fun isPreCode() = keyCode == preCode

    fun convert() = when (keyCode) {
        9, 75 -> 1
        7, 47 -> 2
        31, 36 -> 3
        48 -> 4
        72, 55, 33 -> 5
        43, 56, 30, 39 -> 6
        45, 73, 46 -> 7
        41, 71 -> 8
        29 -> 9
        34 -> 10
        40 -> 11
        44, 70 -> 12
        35, 74, 37 -> 13
        10 -> 14
        13, 11 -> 15
        8 -> 16
        69, 12 -> 17
        42, 16 -> 18
        14, 76 -> 19
        15, 38 -> 20
        32 -> 50
        else -> 0
    }

    fun convertPreCode() = when (keyCode) {
        35, 50 -> 1
        10, 76 -> 2
        11, 40 -> 3
        49, 31, 37 -> 4
        43 -> 5
        12 -> 7
        74, 34 -> 8
        70, 8, 32 -> 9
        36, 52, 56 -> 10
        47, 33, 16 -> 11
        69, 46 -> 12
        53 -> 13
        15, 14, 41 -> 14
        30, 9 -> 15
        38, 13, 42 -> 16
        45, 51 -> 17
        55, 44 -> 18
        48, 39 -> 19
        29, 75 -> 20
        54 -> 50
        else -> 0
    }
}