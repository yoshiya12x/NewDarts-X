package e.yoppie.newdartsx.model

import e.yoppie.newdartsx.R

enum class EffectModel(
    val id: Int,
    val bullButtonId: Int,
    val inBullButtonId: Int,
    val backGroundId: Int,
    val effectId: Int
) {
    EFFECT1(
        id = 1,
        bullButtonId = R.id.bull_button_1,
        inBullButtonId = R.id.in_bull_button_1,
        backGroundId = R.drawable.square_button_selector,
        effectId = R.raw.bull5
    ),
    EFFECT2(
        id = 2,
        bullButtonId = R.id.bull_button_2,
        inBullButtonId = R.id.in_bull_button_2,
        backGroundId = R.drawable.square_button_selector,
        effectId = R.raw.bull5
    ),
    EFFECT3(
        id = 3,
        bullButtonId = R.id.bull_button_3,
        inBullButtonId = R.id.in_bull_button_3,
        backGroundId = R.drawable.square_button_selector,
        effectId = R.raw.bull5
    ),
    EFFECT4(
        id = 4,
        bullButtonId = R.id.bull_button_4,
        inBullButtonId = R.id.in_bull_button_4,
        backGroundId = R.drawable.square_button_selector,
        effectId = R.raw.bull5
    ),
    EFFECT5(
        id = 5,
        bullButtonId = R.id.bull_button_5,
        inBullButtonId = R.id.in_bull_button_5,
        backGroundId = R.drawable.square_button_selector,
        effectId = R.raw.bull5
    ),
    EFFECT6(
        id = 6,
        bullButtonId = R.id.bull_button_6,
        inBullButtonId = R.id.in_bull_button_6,
        backGroundId = R.drawable.square_button_selector,
        effectId = R.raw.bull5
    ),
    EFFECT7(
        id = 7,
        bullButtonId = R.id.bull_button_7,
        inBullButtonId = R.id.in_bull_button_7,
        backGroundId = R.drawable.square_button_selector,
        effectId = R.raw.bull5
    ),
    EFFECT8(
        id = 8,
        bullButtonId = R.id.bull_button_8,
        inBullButtonId = R.id.in_bull_button_8,
        backGroundId = R.drawable.square_button_selector,
        effectId = R.raw.bull5
    ),
    EFFECT9(
        id = 9,
        bullButtonId = R.id.bull_button_9,
        inBullButtonId = R.id.in_bull_button_9,
        backGroundId = R.drawable.square_button_selector,
        effectId = R.raw.bull5
    ),
    EFFECT10(
        id = 10,
        bullButtonId = R.id.bull_button_10,
        inBullButtonId = R.id.in_bull_button_10,
        backGroundId = R.drawable.square_button_selector,
        effectId = R.raw.bull5
    );

    companion object {
        fun forId(id: Int): EffectModel {
            return values().first { it.id == id }
        }

        fun forBullButtonId(bullButtonId: Int): EffectModel {
            return values().first { it.bullButtonId == bullButtonId }
        }

        fun forInBullButtonId(inBullButtonId: Int): EffectModel {
            return values().first { it.inBullButtonId == inBullButtonId }
        }

        fun forEffectId(effectId: Int): EffectModel {
            return values().first { it.effectId == effectId }
        }

        fun getAll(): Array<EffectModel> {
            return values()
        }
    }
}