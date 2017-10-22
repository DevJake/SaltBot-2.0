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

package me.salt.cmd

import me.salt.exception.ExecuteCommandFailureException
import me.salt.exception.PostExecuteCommandFailureException
import me.salt.exception.PreExecuteCommandFailureException
import me.salt.permissions.Node


abstract class Command(val name: String, val description: String = "", val author: String = "", val perms: List<Node> = emptyList()) {

    @Throws(PreExecuteCommandFailureException::class)
    abstract fun preExecute(cmd: CommandParser.CommandContainer)

    @Throws(ExecuteCommandFailureException::class)
    abstract fun execute(cmd: CommandParser.CommandContainer)

    @Throws(PostExecuteCommandFailureException::class)
    abstract fun postExecute(cmd: CommandParser.CommandContainer)

}