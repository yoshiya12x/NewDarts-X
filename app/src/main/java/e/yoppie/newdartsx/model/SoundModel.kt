package e.yoppie.newdartsx.model

import e.yoppie.newdartsx.R

enum class SoundModel(
    val id: Int,
    val bullButtonId: Int,
    val inBullButtonId: Int,
    val backGroundId: Int,
    val soundId: Int
) {
    EFFECT1(
        id = 1,
        bullButtonId = R.id.bull_sound_button_1,
        inBullButtonId = R.id.in_bull_sound_button_1,
        backGroundId = R.drawable.square_button_selector,
        soundId = R.raw.bull_sound1
    ),
    EFFECT2(
        id = 2,
        bullButtonId = R.id.bull_sound_button_2,
        inBullButtonId = R.id.in_bull_sound_button_2,
        backGroundId = R.drawable.square_button_selector,
        soundId = R.raw.bull_sound2
    ),
    EFFECT3(
        id = 3,
        bullButtonId = R.id.bull_sound_button_3,
        inBullButtonId = R.id.in_bull_sound_button_3,
        backGroundId = R.drawable.square_button_selector,
        soundId = R.raw.bull_sound3
    ),
    EFFECT4(
        id = 4,
        bullButtonId = R.id.bull_sound_button_4,
        inBullButtonId = R.id.in_bull_sound_button_4,
        backGroundId = R.drawable.square_button_selector,
        soundId = R.raw.bull_sound1
    ),
    EFFECT5(
        id = 5,
        bullButtonId = R.id.bull_sound_button_5,
        inBullButtonId = R.id.in_bull_sound_button_5,
        backGroundId = R.drawable.square_button_selector,
        soundId = R.raw.bull_sound1
    ),
    EFFECT6(
        id = 6,
        bullButtonId = R.id.bull_sound_button_6,
        inBullButtonId = R.id.in_bull_sound_button_6,
        backGroundId = R.drawable.square_button_selector,
        soundId = R.raw.bull_sound1
    ),
    EFFECT7(
        id = 7,
        bullButtonId = R.id.bull_sound_button_7,
        inBullButtonId = R.id.in_bull_sound_button_7,
        backGroundId = R.drawable.square_button_selector,
        soundId = R.raw.bull_sound1
    ),
    EFFECT8(
        id = 8,
        bullButtonId = R.id.bull_sound_button_8,
        inBullButtonId = R.id.in_bull_sound_button_8,
        backGroundId = R.drawable.square_button_selector,
        soundId = R.raw.bull_sound1
    ),
    EFFECT9(
        id = 9,
        bullButtonId = R.id.bull_sound_button_9,
        inBullButtonId = R.id.in_bull_sound_button_9,
        backGroundId = R.drawable.square_button_selector,
        soundId = R.raw.bull_sound1
    ),
    EFFECT10(
        id = 10,
        bullButtonId = R.id.bull_sound_button_10,
        inBullButtonId = R.id.in_bull_sound_button_10,
        backGroundId = R.drawable.square_button_selector,
        soundId = R.raw.bull_sound1
    );

    companion object {
        fun forId(id: Int): SoundModel {
            return values().first { it.id == id }
        }
        fun forBullButtonId(bullButtonId: Int): SoundModel {
            return values().first { it.bullButtonId == bullButtonId }
        }
        fun forInBullButtonId(inBullButtonId: Int): SoundModel {
            return values().first { it.inBullButtonId == inBullButtonId }
        }
        fun forSoundId(soundId: Int): SoundModel {
            return values().first { it.soundId == soundId }
        }
        fun getAll(): Array<SoundModel> {
            return values()
        }
    }
}