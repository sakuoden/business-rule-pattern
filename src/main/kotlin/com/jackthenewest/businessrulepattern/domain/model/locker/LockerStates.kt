package com.jackthenewest.businessrulepattern.domain.model.locker

class LockerStates(
  private val value : Set<LockerState>
) {
  fun contains(state: LockerState): Boolean = this.value.contains(state)

  fun union(lockerStates: Set<LockerState>): LockerStates {
    val result = this.value.union(lockerStates)
    return LockerStates(result)
  }

  fun intersect(lockerStates: Set<LockerState>): LockerStates {
    val result = this.value.intersect(lockerStates)
    return LockerStates(result)
  }

  fun minus(lockerStates: Set<LockerState>): LockerStates {
    val result = this.value.minus(lockerStates)
    return LockerStates(result)
  }

  fun isSubset(lockerStates: Set<LockerState>): Boolean =
    lockerStates.any { lockerState ->
      this.value.contains(lockerState)
    }

  fun isPureSubset(lockerStates: Set<LockerState>): Boolean = this.value.containsAll(lockerStates)

  fun same(lockerStates: Set<LockerState>): Boolean = this.value == lockerStates

  fun size(): Int = this.value.size
}
