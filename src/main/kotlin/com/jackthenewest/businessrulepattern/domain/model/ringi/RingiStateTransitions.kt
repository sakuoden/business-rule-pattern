package com.jackthenewest.businessrulepattern.domain.model.ringi

import java.util.*

class RingiStateTransitions {
  private val value: EnumMap<RingiState, EnumSet<RingiState>> = EnumMap(mapOf(
    RingiState.UNDER to EnumSet.of(RingiState.SEND_BACK, RingiState.DECIDED, RingiState.REJECTION),

    RingiState.SEND_BACK to EnumSet.of(RingiState.UNDER)
  ))

  fun canTransit(from: RingiState, to: RingiState): Boolean {
    val allowedStates: EnumSet<RingiState> = this.value[from]
      ?: return false
    return allowedStates.contains(to)
  }
}
