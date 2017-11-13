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

package me.salt.util.rest

import io.javalin.Context

object LogController {
    //TODO log entry files should generate an ID based upon the ID of the guild, channel, voicechannel or user related -- Allows for easier indexing and searching
    fun getLogById(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getSaltLogByIdAndDateTime(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getGuildLogByIdAndDateTime(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getVoiceChannelLogByIdAndDateTime(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getChannelLogByIdAndDateTime(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getUserLogByIdAndDateTime(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getSaltLogByIdAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getGuildLogByIdAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getVoiceChannelLogByIdAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getChannelLogByIdAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getUserLogByIdAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getSaltLogByIdAndDateTimeAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getGuildLogByIdAndDateTimeAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getVoiceChannelLogByIdAndDateTimeAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getChannelLogByIdAndDateTimeAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getUserLogByIdAndDateTimeAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getSaltLogByEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getGuildLogByEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getVoiceChannelLogByEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getChannelLogByEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getUserLogByEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getSaltLogByDateTimeAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getGuildLogByDateTimeAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getVoiceChannelLogByDateTimeAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getChannelLogByDateTimeAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getUserLogByDateTimeAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getLogByDateTimeAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getLogByDateTime(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getLogByEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getSaltAvailableDateTimes(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getGuildByIdAvailableDateTimes(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getVoiceChannelByIdAvailableDateTimes(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getChannelByIdAvailableDateTimes(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getUserByIdAvailableDateTimes(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteLogById(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteAllSaltLogsByIdRange(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteAllGuildLogsByIdRange(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteAllVoiceChannelLogsByIdRange(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteAllChannelLogsByIdRange(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteAllUserLogsByIdRange(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteAllSaltLogsByDateTimeRange(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteAllGuildLogsByDateTimeRange(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteAllVoiceChannelLogsByDateTimeRange(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteAllChannelLogsByDateTimeRange(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteAllUserLogsByDateTimeRange(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteAllSaltLogs(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteAllGuildByIdLogs(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteAllVoiceChannelByIdLogs(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteAllChannelByIdLogs(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteAllUserByIdLogs(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteAllSaltLogsById(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteAllGuildByIdLogsById(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteAllVoiceChannelByIdLogsById(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteAllChannelByIdLogsById(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteAllUserByIdLogsById(ctx: Context): () -> (Context) {
        TODO()
    }

    //TODO Ensure special perms exist
    fun deleteAllLogs(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteAllSaltLogEntriesByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteAllGuildLogEntriesByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteAllVoiceChannelLogEntriesByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteAllChannelLogEntriesByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteAllUserLogEntriesByType(ctx: Context): () -> (Context) {
        TODO()
    }

}