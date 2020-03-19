package com.jackthenewest.businessrulepattern.domain.model.discount

import com.jackthenewest.businessrulepattern.application.exception.NotFoundMapKeyException
import com.jackthenewest.businessrulepattern.domain.model.customer.CustomerType
import com.jackthenewest.businessrulepattern.domain.type.yen.Yen
import java.util.*

/**
 * 料金値引き額
 */
class DiscountYen {
  private val value: EnumMap<CustomerType, EnumMap<DiscountType, Yen>>

  init {
    val seniorDiscountYen = EnumMap(mapOf(
      DiscountType.EARLY_MORNING to Yen(100),
      DiscountType.LATE_NIGHT to Yen(200)
    ))

    val adultDiscountYen = EnumMap(mapOf(
      DiscountType.EARLY_MORNING to Yen(50),
      DiscountType.LATE_NIGHT to Yen(100))
    )

    val childDiscountYen = EnumMap(mapOf(
      DiscountType.EARLY_MORNING to Yen(150),
      DiscountType.LATE_NIGHT to Yen(300)
    ))

    this.value = EnumMap(mapOf(
      CustomerType.SENIOR to seniorDiscountYen,
      CustomerType.ADULT to adultDiscountYen,
      CustomerType.CHILD to childDiscountYen
    ))
  }

  fun yen(customerType: CustomerType, discountType: DiscountType): Yen {
    val row = value[customerType]
      ?: throw NotFoundMapKeyException("Assigned customerType(CustomerType.${customerType.name}) is not found in ${this.javaClass} value.")

    return row[discountType]
      ?: throw NotFoundMapKeyException("Assigned customerType(CustomerType.${discountType.name}) is not found in ${this.javaClass} value.")
  }
}
