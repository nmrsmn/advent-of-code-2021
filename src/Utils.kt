import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

import kotlin.math.abs
import kotlin.math.max

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

/**
 * Boolean helpers for converting boolean to Char or String
 */
val Boolean.char get() = if (this) '1' else '0'
val Boolean.string get() = if (this) "1" else "0"

/**
 *
 */

data class Point(val x: Int, val y: Int) {

    fun steps(other: Point) : IntRange
        = 0 .. max(abs(x - other.x), abs(y - other.y))
}