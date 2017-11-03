package me.salt.util.rest

import io.javalin.Context

object SearchController {
    /*
    Each specifies a return type. Dissimilar to other
    Rest Controllers, but this allows for the internal API
    to use this methods as well, as opposed to just the REST API.
     */


    fun searchByTag(ctx: Context): () -> (Context) {
        TODO()
    }

    fun searchByDateTime(ctx: Context): () -> (Context) {
        TODO()
    }

    fun searchByCategory(ctx: Context): () -> (Context) {
        TODO()
    }

    fun searchByCategoryAndTag(ctx: Context): () -> (Context) {
        TODO()
    }

    fun searchByCategoryAndDateTime(ctx: Context): () -> (Context) {
        TODO()
    }

    fun searchByTagAndDateTime(ctx: Context): () -> (Context) {
        TODO()
    }

    fun searchByCategoryAndTagAndDateTime(ctx: Context): () -> (Context) {
        TODO()
    }

}