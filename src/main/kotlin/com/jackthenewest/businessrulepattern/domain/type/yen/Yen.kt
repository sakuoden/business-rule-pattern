package com.jackthenewest.businessrulepattern.domain.type.yen

import java.lang.IllegalArgumentException

/**
 * 円
 */
class Yen(
  private val value: Int
) {
  companion object {
    const val MIN = 0
    const val MAX = 999999999
  }
  fun same(yen: Yen): Boolean = value == yen.value()

  fun value(): Int = this.value

  init {
    check(value)
  }

  private fun isMinOrMore(value: Int): Boolean = value >= MIN

  private fun isMaxOrLess(value: Int): Boolean = value <= MAX

  private fun check(value: Int) {
    if (!isMinOrMore(value)) throw IllegalArgumentException("Money must be $MIN or more")
    if (!isMaxOrLess(value)) throw IllegalArgumentException("Money must be $MAX or less")
  }
}
