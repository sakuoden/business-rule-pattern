package com.jackthenewest.businessrulepattern.domain.model.ringi

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class RingiStatusTransitionsTest {
  private lateinit var ringiStateTransitions: RingiStateTransitions

  @BeforeEach
  fun setUp() {
    ringiStateTransitions = RingiStateTransitions()
  }

  @Nested
  inner class CanTransitMethod {

    /**
     * 稟議中 -> 稟議中 false
     */
    @Test
    fun fromUnderToUnderReturnFalse() {
      val actual: Boolean =
        ringiStateTransitions.canTransit(
          from = RingiStatus.UNDER,
          to = RingiStatus.UNDER
        )

      assertThat(actual).isFalse()
    }

    /**
     * 稟議中 -> 稟議差戻し中 true
     */
    @Test
    fun fromUnderToSendBackReturnTrue() {
      val actual: Boolean =
        ringiStateTransitions.canTransit(
          from = RingiStatus.UNDER,
          to = RingiStatus.SEND_BACK
        )

      assertThat(actual).isTrue()
    }

    /**
     * 稟議中 -> 稟議済み true
     */
    @Test
    fun fromUnderToDecidedReturnTrue() {
      val actual: Boolean =
        ringiStateTransitions.canTransit(
          from = RingiStatus.UNDER,
          to = RingiStatus.DECIDED
        )

      assertThat(actual).isTrue()
    }

    /**
     * 稟議中 -> 棄却 true
     */
    @Test
    fun fromUnderToRejectionReturnTrue() {
      val actual: Boolean =
        ringiStateTransitions.canTransit(
          from = RingiStatus.UNDER,
          to = RingiStatus.REJECTION
        )

      assertThat(actual).isTrue()
    }

    /**
     * 稟議差戻し中 -> 稟議中 true
     */
    @Test
    fun fromSendBackToUnderReturnTrue() {
      val actual: Boolean =
        ringiStateTransitions.canTransit(
          from = RingiStatus.SEND_BACK,
          to = RingiStatus.UNDER
        )

      assertThat(actual).isTrue()
    }

    /**
     * 稟議差戻し中 -> 稟議差戻し中 false
     */
    @Test
    fun fromSendBackToSendBackReturnFalse() {
      val actual: Boolean =
        ringiStateTransitions.canTransit(
          from = RingiStatus.SEND_BACK,
          to = RingiStatus.SEND_BACK
        )

      assertThat(actual).isFalse()
    }

    /**
     * 稟議差戻し中 -> 稟議済み false
     */
    @Test
    fun fromSendBackToDecidedReturnFalse() {
      val actual: Boolean =
        ringiStateTransitions.canTransit(
          from = RingiStatus.SEND_BACK,
          to = RingiStatus.DECIDED
        )

      assertThat(actual).isFalse()
    }

    /**
     * 稟議差戻し中 -> 棄却 false
     */
    @Test
    fun fromSendBackToRejectionReturnFalse() {
      val actual: Boolean =
        ringiStateTransitions.canTransit(
          from = RingiStatus.SEND_BACK,
          to = RingiStatus.REJECTION
        )

      assertThat(actual).isFalse()
    }

    /**
     * 稟議済み -> 稟議中 false
     */
    @Test
    fun fromDecidedToUnderReturnFalse() {
      val actual: Boolean =
        ringiStateTransitions.canTransit(
          from = RingiStatus.DECIDED,
          to = RingiStatus.UNDER
        )

      assertThat(actual).isFalse()
    }

    /**
     * 棄却 -> 稟議差戻し中 false
     */
    @Test
    fun fromRejectionToSendBackReturnFalse() {
      val actual: Boolean =
        ringiStateTransitions.canTransit(
          from = RingiStatus.REJECTION,
          to = RingiStatus.SEND_BACK
        )

      assertThat(actual).isFalse()
    }
  }
}