package org.lobobrowser.utils

object Preconditions {

    /**
     * Ensure that an object is not null
     * and throw a RuntimeException if it
     * is null.
     *
     * @param obj check nullness on this object.
     */
    @JvmStatic
    fun checkNonNull(obj: Any?) {
        if (obj == null) {
            throw RuntimeException("Object must not be null")
        }
    }
}
