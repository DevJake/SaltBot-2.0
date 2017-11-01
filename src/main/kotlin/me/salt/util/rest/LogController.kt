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


    internal fun getLogBySaltIdAndDateTime(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getLogByGuildIdAndDateTime(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getLogByVoiceChannelIdAndDateTime(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getLogByChannelIdAndDateTime(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getLogByUserIdAndDateTime(ctx: Context): () -> (Context) {
        TODO()
    }


    internal fun getLogBySaltIdAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getLogByGuildIdAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getLogByVoiceChannelIdAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getLogByChannelIdAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getLogByUserIdAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }


    internal fun getLogBySaltIdAndDateTimeAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getLogByGuildIdAndDateTimeAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getLogByVoiceChannelIdAndDateTimeAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getLogByChannelIdAndDateTimeAndEntryType(ctx: Context): () -> (Context) {
        TODO()
    }

    internal fun getLogByUserIdAndDateTimeAndEntryType(ctx: Context): () -> (Context) {
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


    internal fun getSaltAvailableDateTimes(ctx: Context): () -> (Context){
        TODO()
    }

    internal fun getGuildByIdAvailableDateTimes(ctx: Context): () -> (Context){
        TODO()
    }

    internal fun getVoiceChannelByIdAvailableDateTimes(ctx: Context): () -> (Context){
        TODO()
    }

    internal fun getChannelByIdAvailableDateTimes(ctx: Context): () -> (Context){
        TODO()
    }

    internal fun getUserByIdAvailableDateTimes(ctx: Context): () -> (Context){
        TODO()
    }


    internal fun deleteLogById(ctx: Context): () -> (Context){
        TODO()
    }


    internal fun deleteAllSaltLogsByIdRange(ctx: Context): () -> (Context){
        TODO()
    }

    internal fun deleteAllGuildLogsByIdRange(ctx: Context): () -> (Context){
        TODO()
    }

    internal fun deleteAllVoiceChannelLogsByIdRange(ctx: Context): () -> (Context){
        TODO()
    }

    internal fun deleteAllChannelLogsByIdRange(ctx: Context): () -> (Context){
        TODO()
    }

    internal fun deleteAllUserLogsByIdRange(ctx: Context): () -> (Context){
        TODO()
    }


    internal fun deleteAllSaltLogsByDateTimeRange(ctx: Context): () -> (Context){
        TODO()
    }

    internal fun deleteAllGuildLogsByDateTimeRange(ctx: Context): () -> (Context){
        TODO()
    }

    internal fun deleteAllVoiceChannelLogsByDateTimeRange(ctx: Context): () -> (Context){
        TODO()
    }

    internal fun deleteAllChannelLogsByDateTimeRange(ctx: Context): () -> (Context){
        TODO()
    }

    internal fun deleteAllUserLogsByDateTimeRange(ctx: Context): () -> (Context){
        TODO()
    }


    internal fun deleteAllSaltLogs(ctx: Context): () -> (Context){
        TODO()
    }

    internal fun deleteAllGuildByIdLogs(ctx: Context): () -> (Context){
        TODO()
    }

    internal fun deleteAllVoiceChannelByIdLogs(ctx: Context): () -> (Context){
        TODO()
    }

    internal fun deleteAllChannelByIdLogs(ctx: Context): () -> (Context){
        TODO()
    }

    internal fun deleteAllUserByIdLogs(ctx: Context): () -> (Context){
        TODO()
    }


    //TODO Ensure special perms exist
    internal fun deleteAllLogs(ctx: Context): () -> (Context){
        TODO()
    }


    internal fun deleteAllSaltLogEntriesByType(ctx: Context): () -> (Context){
        TODO()
    }

    internal fun deleteAllGuildLogEntriesByType(ctx: Context): () -> (Context){
        TODO()
    }

    internal fun deleteAllVoiceChannelLogEntriesByType(ctx: Context): () -> (Context){
        TODO()
    }

    internal fun deleteAllChannelLogEntriesByType(ctx: Context): () -> (Context){
        TODO()
    }

    internal fun deleteAllUserLogEntriesByType(ctx: Context): () -> (Context){
        TODO()
    }


}