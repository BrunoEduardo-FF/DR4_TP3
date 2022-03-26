package br.edu.infnet.dr4_tp3.util

class BoolConv() {

    fun boolToString(bool: Boolean): String {
        if (bool) return "sim"
        return "não"
    }

    fun boolToInt(bool: Boolean): Int {
        if (bool) return 1
        return 0
    }
}