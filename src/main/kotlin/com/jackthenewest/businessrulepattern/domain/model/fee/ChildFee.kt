package com.jackthenewest.businessrulepattern.domain.model.fee

import com.jackthenewest.businessrulepattern.domain.type.yen.Yen

/**
 * 子供料金
 */
class ChildFee : Fee {
  companion object {
    private const val CHILD_FEE = 500
  }

  private val value: Yen

  init {
    this.value = Yen(CHILD_FEE)
  }

  override fun yen(): Yen = this.value

  override fun feeName(): String = "子供料金"
}
