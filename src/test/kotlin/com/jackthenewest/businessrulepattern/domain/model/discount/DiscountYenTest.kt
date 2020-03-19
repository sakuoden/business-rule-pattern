package com.jackthenewest.businessrulepattern.domain.model.discount

import com.jackthenewest.businessrulepattern.application.exception.NotFoundMapKeyException
import com.jackthenewest.businessrulepattern.domain.model.customer.CustomerType
import com.jackthenewest.businessrulepattern.domain.type.yen.Yen
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class DiscountYenTest {
  private lateinit var discountYen: DiscountYen

  @BeforeEach
  fun setUp() {
    discountYen = DiscountYen()
  }

  @Nested
  inner class YenMethod {

    /**
     * シニアが早朝割引を受ける場合は100円割引
     */
    @Test
    fun seniorInEarlyMorningReturn100() {
      val expected = Yen(100)

      val customerType = CustomerType.SENIOR
      val discountType = DiscountType.EARLY_MORNING

      val actual = discountYen.yen(
        customerType = customerType,
        discountType = discountType
      )

      assertThat(actual)
        .usingRecursiveComparison()
        .isEqualTo(expected)
    }

    /**
     * シニアが深夜割引を受ける場合は200円割引
     */
    @Test
    fun seniorInLateNight200() {
      val expected = Yen(200)

      val customerType = CustomerType.SENIOR
      val discountType = DiscountType.LATE_NIGHT

      val actual = discountYen.yen(
        customerType = customerType,
        discountType = discountType
      )

      assertThat(actual)
        .usingRecursiveComparison()
        .isEqualTo(expected)
    }

    /**
     * 大人が早朝割引を受ける場合は50円割引
     */
    @Test
    fun adultInEarlyMorningReturn50() {
      val expected = Yen(50)

      val customerType = CustomerType.ADULT
      val discountType = DiscountType.EARLY_MORNING

      val actual = discountYen.yen(
        customerType = customerType,
        discountType = discountType
      )

      assertThat(actual)
        .usingRecursiveComparison()
        .isEqualTo(expected)
    }

    /**
     * 大人が深夜割引を受ける場合は100円割引
     */
    @Test
    fun adultInLateNight100() {
      val expected = Yen(100)

      val customerType = CustomerType.ADULT
      val discountType = DiscountType.LATE_NIGHT

      val actual = discountYen.yen(
        customerType = customerType,
        discountType = discountType
      )

      assertThat(actual)
        .usingRecursiveComparison()
        .isEqualTo(expected)
    }

    /**
     * 子供が早朝割引を受ける場合は150/円割引
     */
    @Test
    fun childInEarlyMorningReturn150() {
      val expected = Yen(150)

      val customerType = CustomerType.CHILD
      val discountType = DiscountType.EARLY_MORNING

      val actual = discountYen.yen(
        customerType = customerType,
        discountType = discountType
      )

      assertThat(actual)
        .usingRecursiveComparison()
        .isEqualTo(expected)
    }

    /**
     * 子供が深夜割引を受ける場合は300円割引
     */
    @Test
    fun childInLateNight100() {
      val expected = Yen(300)

      val customerType = CustomerType.CHILD
      val discountType = DiscountType.LATE_NIGHT

      val actual = discountYen.yen(
        customerType = customerType,
        discountType = discountType
      )

      assertThat(actual)
        .usingRecursiveComparison()
        .isEqualTo(expected)
    }

    /**
     * DiscountYenが想定していないCustomerTypeが入った場合はNotFoundMapKeyExceptionを投げる
     */
    @Test
    fun unexpectedCustomerType() {
      val customerType = CustomerType.SPECIAL
      val discountType = DiscountType.EARLY_MORNING

      assertThrows<NotFoundMapKeyException> {
        discountYen.yen(
          customerType = customerType,
          discountType = discountType
        )
      }.let {
        println(it.message)
      }
    }
  }
}
