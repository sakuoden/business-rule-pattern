package com.jackthenewest.businessrulepattern.domain.model.ringi

import java.util.*

class RingiStateTransitions {
  private val value: EnumMap<RingiStatus, EnumSet<RingiStatus>>

  init {
    val valueForUnder: EnumSet<RingiStatus> =
      EnumSet.of(
        RingiStatus.SEND_BACK,
        RingiStatus.DECIDED,
        RingiStatus.REJECTION
      )

    val valueForSendBack: EnumSet<RingiStatus> =
      EnumSet.of(
        RingiStatus.UNDER
      )

    this.value = EnumMap(mapOf(
      RingiStatus.UNDER to valueForUnder,
      RingiStatus.SEND_BACK to valueForSendBack
    ))
  }

  fun canTransit(from: RingiStatus, to: RingiStatus): Boolean {
    val allowedStates: EnumSet<RingiStatus> = this.value[from]
      ?: return false
    return allowedStates.contains(to)
  }
}
