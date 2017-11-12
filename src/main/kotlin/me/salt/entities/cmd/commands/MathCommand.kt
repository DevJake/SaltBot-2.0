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

package me.salt.entities.cmd.commands

/**
 * This command takes in a mathematical expression specified by a user and parses it, then solves it in accordance to how specified.
 *
 * Possible instructions that can be given to the parser include:
 * - Calculating for singular or multiple components
 * - Rearranging for singular or multiple components
 * - Factorising the given expression
 * - Repeatedly solve the expression for *n* to *z*, in increments of *i*
 * - Repeatedly rearrange the expression for *n* to *z*, in increments of *i*
 * - Simplifying the given expression
 *
 * It may not be possible for the request to be performed. If this is the case, the user shall be informed.
 *
 * A range of mathematical constants and inbuilt functions are also available for use. Below details their name and their usage:
 * - Pi : pi
 * - Sine(*x*) : sin(*x*)
 * - Cosine(*x*) : cos(*x*)
 * - Tangent(*x*) : tan(*x*)
 * - Square-root(*x*) : sqrt(*x*)
 * - *n*th-root(*x*) : (*n*)rt(*x*)
 */
class MathCommand