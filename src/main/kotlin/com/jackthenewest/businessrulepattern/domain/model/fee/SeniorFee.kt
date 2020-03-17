package com.jackthenewest.businessrulepattern.domain.model.fee

import com.jackthenewest.businessrulepattern.domain.type.yen.Yen

/**
 * シニア料金
 */
class SeniorFee : Fee {
  companion object {
    private const val SENIOR_FEE = 600
  }

  private val value: Yen

  init {
    this.value = Yen(SENIOR_FEE)
  }

  override fun yen(): Yen = this.value

  override fun feeName(): String = "シニア料金"
}
