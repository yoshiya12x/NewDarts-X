package e.yoppie.newdartsx.util

import android.view.KeyEvent
import e.yoppie.newdartsx.model.ScoreModel

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
        54 -> 100 // InBull
        else -> 0
    }

    fun convertNob() = when (keyCode) {
        7 -> ScoreModel(2, false)
        31 -> ScoreModel(3, false)
        48 -> ScoreModel(4, false)
        55, 72 -> ScoreModel(5, false)
        43, 56 -> ScoreModel(6, false)
        45, 73 -> ScoreModel(7, false)
        41 -> ScoreModel(8, false)
        29 -> ScoreModel(9, false)
        40 -> ScoreModel(11, false)
        35, 74 -> ScoreModel(13, false)
        10 -> ScoreModel(14, false)
        69 -> ScoreModel(17, false)
        42 -> ScoreModel(18, false)
        14 -> ScoreModel(19, false)
        15 -> ScoreModel(20, false)
        9 -> ScoreModel(2, true)
        47 -> ScoreModel(4, true)
        36 -> ScoreModel(6, true)
        33 -> ScoreModel(10, true)
        30 -> ScoreModel(12, true)
        44 -> ScoreModel(24, true)
        37 -> ScoreModel(26, true)
        13 -> ScoreModel(30, true)
        8 -> ScoreModel(32, true)
        12 -> ScoreModel(34, true)
        16 -> ScoreModel(36, true)
        75 -> ScoreModel(3, false)
        39 -> ScoreModel(18, false)
        46 -> ScoreModel(21, false)
        71 -> ScoreModel(24, false)
        34 -> ScoreModel(30, false)
        70 -> ScoreModel(36, false)
        11 -> ScoreModel(45, false)
        76 -> ScoreModel(57, false)
        38 -> ScoreModel(60, false)
        32 -> ScoreModel(50, false)

        else -> ScoreModel(0, false)

    }

    fun convertPreCodeNob() = when (keyCode) {
        35, 50 -> ScoreModel(1, false)
        10 -> ScoreModel(2, false)
        11 -> ScoreModel(3, false)
        49 -> ScoreModel(4, false)
        74 -> ScoreModel(8, false)
        70 -> ScoreModel(9, false)
        36, 52 -> ScoreModel(10, false)
        47 -> ScoreModel(11, false)
        46, 69 -> ScoreModel(12, false)
        15 -> ScoreModel(14, false)
        9, 30 -> ScoreModel(15, false)
        13, 38 -> ScoreModel(16, false)
        45 -> ScoreModel(17, false)
        55 -> ScoreModel(18, false)
        48 -> ScoreModel(19, false)
        29 -> ScoreModel(20, false)
        31 -> ScoreModel(8, true)
        12 -> ScoreModel(14, true)
        34 -> ScoreModel(16, true)
        8 -> ScoreModel(18, true)
        56 -> ScoreModel(20, true)
        33 -> ScoreModel(22, true)
        14 -> ScoreModel(28, true)
        39 -> ScoreModel(38, true)
        75 -> ScoreModel(40, true)
        76 -> ScoreModel(6, false)
        40 -> ScoreModel(9, false)
        37 -> ScoreModel(12, false)
        43 -> ScoreModel(15, false)
        32 -> ScoreModel(27, false)
        16 -> ScoreModel(33, false)
        53 -> ScoreModel(39, false)
        41 -> ScoreModel(42, false)
        42 -> ScoreModel(48, false)
        51 -> ScoreModel(51, false)
        44 -> ScoreModel(54, false)
        54 -> ScoreModel(50, true)


        else -> ScoreModel(0, false)
    }
}