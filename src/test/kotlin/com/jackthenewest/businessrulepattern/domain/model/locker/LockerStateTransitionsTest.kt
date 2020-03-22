package com.jackthenewest.businessrulepattern.domain.model.locker

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class LockerStateTransitionsTest {
  private lateinit var lockerStateTransitions: LockerStateTransitions

  @BeforeEach
  fun setUp() {
    lockerStateTransitions = LockerStateTransitions()
  }

  @Nested
  inner class CanRunEventMethod {

    /**
     * ロッカーが空の状態の場合、確保する前に預入は出来ない
     */
    @Test
    fun ifLockerIsEmptyCannotDepositBeforeReserve() {
      val actual: Boolean =
        lockerStateTransitions.canRunEvent(
          from = LockerState.EMPTY,
          event = LockerEvent.DEPOSIT
        )

      assertThat(actual).isFalse()
    }

    /**
     * ロッカーが空の状態の場合、確保できる
     */
    @Test
    fun ifLockerIsEmptyCanReserve() {
      val actual: Boolean =
        lockerStateTransitions.canRunEvent(
          from = LockerState.EMPTY,
          event = LockerEvent.RESERVE
        )

      assertThat(actual).isTrue()
    }

    /**
     * ロッカーが確保中の場合、預入できる
     */
    @Test
    fun ifLockerIsReservedCanDeposit() {
      val actual: Boolean =
        lockerStateTransitions.canRunEvent(
          from = LockerState.RESERVED,
          event = LockerEvent.DEPOSIT
        )

      assertThat(actual).isTrue()
    }

    /**
     * ロッカーが確保中の場合、確保キャンセルできる
     */
    @Test
    fun ifLockerIsReservedCanCancel() {
      val actual: Boolean =
        lockerStateTransitions.canRunEvent(
          from = LockerState.RESERVED,
          event = LockerEvent.CANCEL
        )

      assertThat(actual).isTrue()
    }

    /**
     * ロッカーが確保中の場合、受取は出来ない
     */
    @Test
    fun ifLockerIsReservedCannotReceive() {
      val actual: Boolean =
        lockerStateTransitions.canRunEvent(
          from = LockerState.RESERVED,
          event = LockerEvent.RECEIVE
        )

      assertThat(actual).isFalse()
    }

    /**
     * ロッカーが預入中の場合、確保は出来ない
     */
    @Test
    fun ifLockerIsFullCannotReserve() {
      val actual: Boolean =
        lockerStateTransitions.canRunEvent(
          from = LockerState.FULL,
          event = LockerEvent.RESERVE
        )

      assertThat(actual).isFalse()
    }

    /**
     * ロッカーが預入中の場合、預入は出来ない
     */
    @Test
    fun ifLockerIsFullCannotDeposit() {
      val actual: Boolean =
        lockerStateTransitions.canRunEvent(
          from = LockerState.FULL,
          event = LockerEvent.DEPOSIT
        )

      assertThat(actual).isFalse()
    }

    /**
     * ロッカーが預入中の場合、受取できる/
     */
    @Test
    fun ifLockerIsFullCanReceive() {
      val actual: Boolean =
        lockerStateTransitions.canRunEvent(
          from = LockerState.FULL,
          event = LockerEvent.RECEIVE
        )

      assertThat(actual).isTrue()
    }
  }
}