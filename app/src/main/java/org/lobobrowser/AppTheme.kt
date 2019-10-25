package org.lobobrowser

import org.lobobrowser.preferences.IntEnum;

/**
 * The available app themes.
 */
enum class AppTheme(override val value: Int) : IntEnum {
    DARK(0),
    LIGHT(1)
}