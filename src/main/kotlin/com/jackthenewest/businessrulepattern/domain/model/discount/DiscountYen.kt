package com.jackthenewest.businessrulepattern.domain.model.discount

import com.jackthenewest.businessrulepattern.domain.model.customer.CustomerType
import com.jackthenewest.businessrulepattern.domain.type.yen.Yen
import java.util.*

/**
 * 料金値引き額
 */
class DiscountYen {
  val value: EnumMap<CustomerType, EnumMap<DiscountType, Yen>>

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


    val customerTypeMap = EnumMap(mapOf(
      CustomerType.SENIOR to seniorDiscountYen,
      CustomerType.ADULT to adultDiscountYen,
      CustomerType.CHILD to childDiscountYen
    ))

    this.value = customerTypeMap
  }

  fun yen(customerType: CustomerType, discountType: DiscountType): Yen {
    val row = value[customerType]
      ?: throw IllegalArgumentException("Assigned CustomerType does not exist")

    return row[discountType]
      ?: throw IllegalArgumentException("Assigned CustomerType does not exist")
  }
}
