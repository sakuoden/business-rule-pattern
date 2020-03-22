package com.jackthenewest.businessrulepattern.domain.model.locker

import java.util.*

class LockerStateTransitions {
  private val value: EnumMap<LockerState, EnumSet<LockerEvent>> = EnumMap(mapOf(
    LockerState.EMPTY to EnumSet.of(LockerEvent.RESERVE),

    LockerState.RESERVED to EnumSet.of(LockerEvent.DEPOSIT, LockerEvent.CANCEL),

    LockerState.FULL to EnumSet.of(LockerEvent.RECEIVE)
  ))

  fun canRunEvent(from: LockerState, event: LockerEvent): Boolean {
    val allowedEvents: EnumSet<LockerEvent> = this.value[from]
      ?: return false
    return allowedEvents.contains(event)
  }
}
