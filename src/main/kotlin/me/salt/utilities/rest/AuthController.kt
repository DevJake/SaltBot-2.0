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

package me.salt.utilities.rest

import io.javalin.Context

/**
 * This class handles REST endpoints relating to the REST API's Authorisation System.
 */
object AuthController {
    fun getTokenInfo(ctx: Context): () -> (Context) {
        TODO()
    }

    fun filterTokensByParam(ctx: Context): () -> (Context) {
        TODO()
    }

    fun deleteToken(ctx: Context): () -> (Context) {
        TODO()
    }

    fun updateToken(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getAllEndpoints(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getAllEndpointsByType(ctx: Context): () -> (Context) {
        TODO()
    }

    fun getAllEndpointsByParameter(ctx: Context): () -> (Context) {
        TODO()
    }
}