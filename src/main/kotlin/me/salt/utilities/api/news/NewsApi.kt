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

package me.salt.utilities.api.news

import me.salt.entities.lang.Country
import me.salt.entities.lang.Language
import me.salt.utilities.util.ScheduleHandler
import net.dv8tion.jda.core.entities.User
import java.time.OffsetDateTime

/**
 * This object aims to allow for the easy interaction with a 3rd party News article REST API.
 */
object NewsApi {
    /**
     * A private record of all previously-obtained [News sources][NewsSource] from the current runtime session
     */
    private val cache: MutableList<NewsSource> = mutableListOf()

    /**
     * Attempts to find any articles matching the given [searchCriteria].
     *
     * @param searchCriteria The [NewsSearchCriteria] to be used for performing the search
     * @param forceRefresh A [Boolean] reflecting if the bot should check for new sources. If false, the internal [cache] is checked. Defaults to false
     *
     * @return A potentially-null [List] of all [News sources][NewsSource] matching the given [searchCriteria]
     */
    fun find(searchCriteria: NewsSearchCriteria, forceRefresh: Boolean = false): List<NewsSource>? {
        TODO()
    }

    /**
     * Attempts to find any articles matching the given [searchCriteria], returning them sorted in accordance with the [sortCriteria].
     *
     * @param searchCriteria The [NewsSearchCriteria] to be used for performing the search
     * @param sortCriteria The [NewsSortCriteria] to be used for sorting the identified results
     * @param forceRefresh A [Boolean] reflecting if the bot should check for new sources. If false, the internal [cache] is checked. Defaults to false
     *
     * @return A potentially-null [List] of all [News sources][NewsSource] matching the given [searchCriteria], sorted in accordance to the [sortCriteria]
     */
    fun findAndSort(searchCriteria: NewsSearchCriteria, sortCriteria: NewsSortCriteria, forceRefresh: Boolean = false): List<NewsSource> {
        TODO()
    }

    /**
     * This method registers an instance of a [NewsSchedule] to a specified [User]. This allows for users to optionally receive automated reports on news headlines on a custom basis.
     *
     * @param user The [User] to register the [schedule] to
     * @param schedule The [NewsSchedule] to be registered to the given [user]
     */
    fun registerSchedule(user: User, schedule: NewsSchedule) {
        TODO()
    }

    /**
     * This method un-enrols the given [User] from receiving automated, scheduled news updated. This is the reverse of [registerSchedule]
     *
     * @param user The [User] to unregister the [schedule] from
     * @param schedule The [NewsSchedule] to be unregistered from the given [user]
     */
    fun unregisterSchedule(user: User, schedule: NewsSchedule) {
        TODO()
    }

    /**
     * This method un-enrols the given [User] from receiving automated, scheduled news updated. This is the reverse of [registerSchedule] and similar to [unregisterSchedule], except that it unregisters *any* [schedules][NewsSchedule] tied to their account.
     *
     * @param user The [User] to unregister [schedules][NewsSchedule] from
     */
    fun unregisterAllSchedules(user: User) {
        TODO()
    }
}

data class NewsSearchCriteria(val keyword: String?, val sourcePublishDate: OffsetDateTime?, val sourceName: String?, val sourceLanguage: Language?, val sourceCountry: Country?)

class NewsSource

/**
 * This enum contains different characteristics that can be used to order the results retrieved by the [NewsApi].
 */
enum class NewsSortCriteria {
    /**
     * Sort the results by the date they were [published][NewsSearchCriteria.sourcePublishDate] in ascending order
     */
    PUBLISH_DATE,
    /**
     * Sort the results by their relevance to the given [keyword][NewsSearchCriteria.keyword] in ascending order
     */
    KEYWORD_RELEVANCE,
    /**
     * Sort the results by their given popularity in ascending order
     */
    SOURCE_POPULARITY,
    /**
     * Sort the results by the date they were [published][NewsSearchCriteria.sourcePublishDate] in descending order
     */
    PUBLISH_DATE_REVERSED,
    /**
     * Sort the results by their relevance to the given [keyword][NewsSearchCriteria.keyword] in descending order
     */
    KEYWORD_RELEVANCE_REVERSED,
    /**
     * Sort the results by their given popularity in descending order
     */
    SOURCE_POPULARITY_REVERSED
}

class NewsSchedule(val userId: String, val searchCriteria: NewsSearchCriteria, val sortCriteria: NewsSortCriteria?, val schedule: ScheduleHandler)

