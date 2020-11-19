package dev.gvetri.player

enum class Orientation(val value: Int) {
    PORTRAIT(1),
    LANDSCAPE(2);

    companion object {
        fun from(findValue: Int): Orientation =
            values().firstOrNull { it.value == findValue } ?: PORTRAIT
    }

}