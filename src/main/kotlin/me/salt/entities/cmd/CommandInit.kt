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

package me.salt.entities.cmd

fun initCommands() {
    //TODO init commands

    CommandBuilder("hello", "Hello World")
            .preExecute { cmd, event, instHandler -> println("Hello 1"); instHandler.accept() }
            .preExecute { cmd, event, instHandler -> println("Hello 2"); instHandler.accept() }

            .execute { cmd, event, instHandler -> println("Hello2 1"); instHandler.accept() }
            .execute { cmd, event, instHandler -> println("Hello2 2"); instHandler.accept() }

            .postExecute { cmd, event -> println("Hello3 1") }
            .postExecute { cmd, event -> println("Hello3 2") }
            .build()

}
