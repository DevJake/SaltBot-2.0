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

package me.salt.util

import io.javalin.Context
import me.salt.entities.objects.containsAny
import java.time.OffsetDateTime

object SearchUtil {
    internal val searchables = mutableListOf<SearchElement>()

    abstract class SearchElement(val category: SearchCategory, val tags: List<String>, val description: String?) {
        val dateTime = OffsetDateTime.now()
    }

    enum class SearchCategory {
        EVENT,
        REST_ENDPOINT,
        CONFIG,
        LOG_TYPE,
        MODIFIED_MESSAGE,
    }

    fun addSearchables(vararg elements: SearchElement) = searchables.addAll(elements)
    fun removeSearchables(vararg elements: SearchElement) = searchables.removeAll(elements)
}

class SearchBuilder {
    //Must contain all given tags
    private val tagFilter = mutableListOf<String>()

    //Must either match exact date, or date range
    private val dateTimeFilter = mutableListOf<DateRange>()
    private val categoryFilter = mutableListOf<SearchUtil.SearchCategory>()

    fun withTags(vararg tags: String) = apply { tagFilter.addAll(tags) }

    fun withSetDateTime(vararg dateTimes: OffsetDateTime) = apply { dateTimes.forEach { dateTimeFilter.add(DateRange(it)) } }

    fun withSetDateTimeRange(startDate: OffsetDateTime, endDate: OffsetDateTime) = apply {
        dateTimeFilter.add(DateRange(startDate,
                endDate))
    }

    fun withCategories(vararg categories: SearchUtil.SearchCategory) = apply { categoryFilter.addAll(categories) }

    fun search(): List<SearchUtil.SearchElement> {
        val searched = SearchUtil.searchables
        when {
            tagFilter.isNotEmpty() -> searched.filter { it.tags.containsAny(tagFilter) }
            categoryFilter.isNotEmpty() -> searched.filter { categoryFilter.contains(it.category) }
            dateTimeFilter.isNotEmpty() -> {

                //Below calculates the upper and lower bounds of the dates; the furthest apart date ranges that the filter must match

                val doubleBounds = dateTimeFilter.filter { it.upperBound != null }
                val lBound: DateRange? = doubleBounds
                        .sortedWith(Comparator { o1, o2 ->
                            when {
                                o1.lowerBound.isBefore(o2.lowerBound) -> -1
                                o1.lowerBound.isEqual(o2.lowerBound) -> 0
                                else -> 1
                            }
                        }).firstOrNull()

                val uBound: DateRange? = doubleBounds
                        .sortedWith(Comparator { o1, o2 ->
                            when {
                                o1.upperBound?.isBefore(o2.upperBound) ?: false -> -1
                                o1.upperBound?.isEqual(o2.upperBound) ?: false -> 0
                                else -> 1
                            }
                        }).firstOrNull()

                searched.filter { se ->
                    val singleDt = dateTimeFilter.filter { it.upperBound == null }.map { it.lowerBound }
                    singleDt.any { it.isEqual(se.dateTime) } && (se.dateTime.isAfter(lBound?.lowerBound) && se.dateTime.isBefore(
                            uBound?.upperBound))
                }
            }
        }

        return searched
    }

    private data class DateRange(val lowerBound: OffsetDateTime, val upperBound: OffsetDateTime? = null)

}

class RestEndpointSearchElement(
        category: SearchUtil.SearchCategory,
        tags: List<String>,
        description: String?,
        private val context: (Context) -> () -> Context,
        private val endpoint: String
) : SearchUtil.SearchElement(category, tags, description)



class ModifiedSearchElement(
        category: SearchUtil.SearchCategory,
        tags: List<String>,
        description: String?
) : SearchUtil.SearchElement(category, tags, description)

