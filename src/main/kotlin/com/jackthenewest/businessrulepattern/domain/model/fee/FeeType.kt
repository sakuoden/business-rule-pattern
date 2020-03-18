package com.jackthenewest.businessrulepattern.domain.model.fee

import com.jackthenewest.businessrulepattern.domain.type.yen.Yen

/**
 * 料金種別
 */
enum class FeeType(
  private val fee: Fee
) {
  SENIOR(SeniorFee()),
  ADULT(AdultFee()),
  CHILD(ChildFee());

  fun yen(): Yen = fee.yen()

  fun feeName(): String = fee.feeName()
}
