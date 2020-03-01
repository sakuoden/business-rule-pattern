package com.jackthenewest.businessrulepattern.domain.model.fee

import com.jackthenewest.businessrulepattern.domain.type.yen.Yen

/**
 * 大人料金
 */
class AdultFee : Fee{
  companion object {
    private const val ADULT_FEE = 1000
  }

  private val value: Yen

  init {
    this.value = Yen(ADULT_FEE)
  }

  override fun yen(): Yen = this.value
}
