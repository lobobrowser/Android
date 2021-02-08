package org.lobobrowser

import org.lobobrowser.preferences.IntEnum;

/**
 * The available app themes.
 */
enum class AppTheme(override val value: Int) : IntEnum {
    LIGHT(0),
    DARK(1)
}