package com.jackthenewest.businessrulepattern.domain.model.fee

import com.jackthenewest.businessrulepattern.domain.type.yen.Yen

/**
 * 料金
 */
interface Fee {
  fun yen(): Yen

  fun feeName(): String
}
