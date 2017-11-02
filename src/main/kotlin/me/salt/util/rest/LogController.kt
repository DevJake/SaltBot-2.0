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
    internal fun getLogById(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getSaltLogByIdAndDateTime(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getGuildLogByIdAndDateTime(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getVoiceChannelLogByIdAndDateTime(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getChannelLogByIdAndDateTime(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getUserLogByIdAndDateTime(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getSaltLogByIdAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getGuildLogByIdAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getVoiceChannelLogByIdAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getChannelLogByIdAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getUserLogByIdAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getSaltLogByIdAndDateTimeAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getGuildLogByIdAndDateTimeAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getVoiceChannelLogByIdAndDateTimeAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getChannelLogByIdAndDateTimeAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getUserLogByIdAndDateTimeAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getSaltLogByEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getGuildLogByEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getVoiceChannelLogByEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getChannelLogByEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getUserLogByEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getSaltLogByDateTimeAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getGuildLogByDateTimeAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getVoiceChannelLogByDateTimeAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getChannelLogByDateTimeAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getUserLogByDateTimeAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getLogByDateTimeAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getLogByDateTime(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getLogByEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getSaltAvailableDateTimes(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getGuildByIdAvailableDateTimes(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getVoiceChannelByIdAvailableDateTimes(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getChannelByIdAvailableDateTimes(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getUserByIdAvailableDateTimes(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteLogById(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteAllSaltLogsByIdRange(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteAllGuildLogsByIdRange(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteAllVoiceChannelLogsByIdRange(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteAllChannelLogsByIdRange(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteAllUserLogsByIdRange(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteAllSaltLogsByDateTimeRange(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteAllGuildLogsByDateTimeRange(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteAllVoiceChannelLogsByDateTimeRange(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteAllChannelLogsByDateTimeRange(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteAllUserLogsByDateTimeRange(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteAllSaltLogs(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteAllGuildByIdLogs(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteAllVoiceChannelByIdLogs(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteAllChannelByIdLogs(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteAllUserByIdLogs(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteAllSaltLogsById(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteAllGuildByIdLogsById(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteAllVoiceChannelByIdLogsById(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteAllChannelByIdLogsById(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteAllUserByIdLogsById(ctx: Context): () -> (Context) {
        TODO()
    }

    //TODO Ensure special perms exist
    internal fun deleteAllLogs(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteAllSaltLogEntriesByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteAllGuildLogEntriesByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteAllVoiceChannelLogEntriesByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteAllChannelLogEntriesByType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun deleteAllUserLogEntriesByType(ctx: Context): () -> (Context) {
        TODO()
    }

}