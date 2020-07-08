package com.jackthenewest.businessrulepattern.domain.model.locker

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class LockerStatesTest {
  private lateinit var lockerStates: LockerStates

  @BeforeEach
  fun setUp() {
    val value: Set<LockerState> =
      setOf(
        LockerState.EMPTY,
        LockerState.RESERVED,
        LockerState.FULL
      )
    lockerStates = LockerStates(value)
  }

  @Nested
  inner class ContainsMethod {
    val lockerStates: LockerStates =
      LockerStates(
        setOf(
          LockerState.EMPTY,
          LockerState.RESERVED
        )
      )

    /**
     * 集合に要素がある場合はtrue
     */
    @Test
    fun returnTrueIfLockerStatesContainsElements() {
      val actual = lockerStates.contains(LockerState.EMPTY)

      assertThat(actual).isTrue()
    }

    /**
     * 集合に要素がない場合はfalse
     */
    @Test
    fun returnFalseIfLockerStatesDoesNotContainsElements() {
      val actual = lockerStates.contains(LockerState.FULL)

      assertThat(actual).isFalse()
    }
  }
}