/*
 * Copyright 2017 DevJake
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.salt.utilities.util

import me.salt.entities.objects.Days
import me.salt.entities.objects.Months
import me.salt.utilities.exception.ScheduleValueOutOfBoundsException
import me.salt.utilities.exception.exception
import java.time.OffsetDateTime
import java.time.OffsetTime

data class ScheduleHandler(val active: Boolean, val schedule: List<OffsetDateTime>, val operations: List<() -> Any>? = null) {
    fun isCurrentlyInSchedule(): Boolean {
        TODO()
    }

    fun nextScheduledPoint() {
        TODO()
    }

    fun lastScheduledPoint() {
        TODO()
    }
}

class ScheduleBuilder {
    private val repeatDays: MutableList<Days> = mutableListOf()
    private val repeatWeeks: MutableList<Int> = mutableListOf()
    private val repeatMonths: MutableList<Months> = mutableListOf()
    private var globalRepeatTimes: MutableList<OffsetTime>? = null
    private var specificRepeatTimes: MutableList<OffsetDateTime>? = null

    private var dailyInterval: Int = 0
    private var weeklyInterval: Int = 0
    private var MonthlyInterval: Int = 0

    // Operations to be run for each met schedule point
    private val operations: MutableList<() -> Any>? = null

    fun addRepeatDays(vararg days: Days) = apply { repeatDays.addAll(days) }
    fun removeRepeatDays(vararg days: Days) = apply { repeatDays.removeAll(days) }
    fun setRepeatDaily(value: Boolean) = apply { if (value) repeatDays.addAll(Days.values()) else repeatDays.removeAll(Days.values()) }

    fun addRepeatWeeks(vararg weeks: Int) = apply {
        if (weeks.any { it !in 1..4 }) {
            exception(ScheduleValueOutOfBoundsException("The specified weeks must be in the range of 1->4!"))
            return this
        } else repeatWeeks.addAll(weeks.toList())
    }

    fun removeRepeatWeeks(vararg weeks: Int) = apply { repeatWeeks.removeAll(weeks.toList()) }
    fun setRepeatWeekly(value: Boolean) = apply { if (value) repeatWeeks.addAll(listOf(1, 2, 3, 4)) else repeatWeeks.removeAll(listOf(1, 2, 3, 4)) }

    fun addRepeatMonths(vararg months: Months) = apply { repeatMonths.addAll(months) }
    fun removeRepeatMonths(vararg months: Months) = apply { repeatMonths.removeAll(months) }
    fun setRepeatMonthly(value: Boolean) = apply { if (value) repeatMonths.addAll(Months.values()) else repeatMonths.removeAll(Months.values()) }

    // Add a specific time to the schedule -- applies to all currently-held days
    fun addGlobalRepeatTime(vararg points: OffsetTime) = apply {
        //        if (hour !in 0..24) {
//            exception(ScheduleValueOutOfBoundsException("The specified value of $hour for field 'hour' must be between 0 and 24!"))
//            return this
//        }
//
//        if (minute !in 0..60) {
//            exception(ScheduleValueOutOfBoundsException("The specified value of $minute for field 'minute' must be between 0 and 60!"))
//            return this
//        }
//
//        if (second !in 0..60) {
//            exception(ScheduleValueOutOfBoundsException("The specified value of $second for field 'second' must be between 0 and 60!"))
//            return this
//        }

        globalRepeatTimes?.addAll(points) ?: mutableListOf(points)
    }

    fun addSpecificRepeatTime(vararg points: OffsetDateTime) = apply {
        specificRepeatTimes?.addAll(points) ?: mutableListOf(points)
    }

    // Add a specific time to the schedule -- applies to all specified days
    fun addRepeatTime(hour: Int, minute: Int, second: Int, vararg days: Days) {
        TODO()
    }

    // Repeat on 'x' weeks
    fun addRepeatWeeks(weekNumbers: List<Int>) {
        TODO()
    }

    // Repeat every week from now, skipping every x'th week
    fun addDailyInterval(interval: Int) {
        TODO()
    }

    // Repeat every week from now, skipping every x'th week
    fun addWeeklyInterval(interval: Int) {
        TODO()
    }

    // Repeat every week from now, skipping every x'th week
    fun addMonthlyInterval(interval: Int) {
        TODO()
    }

    // Add an exact date/time to the schedule
    fun addAtExact(exactTime: OffsetDateTime) {
        TODO()
    }

    fun build(active: Boolean = true): ScheduleHandler {
        val queue: MutableList<OffsetDateTime> = mutableListOf()
        val now = OffsetDateTime.now()

        return ScheduleHandler(active, listOf()) //TODO
    }
}