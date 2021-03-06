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

package me.salt.utilities.util

import java.util.regex.Pattern

object MathUtil {
    private val xPlusY = Pattern.compile("(-?[a-zA-Z0-9]+)\\s*[+]\\s*(-?[a-zA-Z0-9]+)")
    private val xDivY = Pattern.compile("(-?[a-zA-Z0-9]+)\\s*[/]\\s*(-?[a-zA-Z0-9]+)")
    private val xMultY = Pattern.compile("(-?[a-zA-Z0-9]+)\\s*[*]\\s*(-?[a-zA-Z0-9]+)")
    private val xMinusY = Pattern.compile("(-?[a-zA-Z0-9]+)\\s*[-]\\s*(-?[a-zA-Z0-9]+)")
    private val xBracketsY = Pattern.compile("([a-zA-Z0-9]+)\\s*\\(\\s*([a-zA-Z0-9\\-*+/]+\\s*)\\)")
    private val xPowY = Pattern.compile("(-?[a-zA-Z0-9]+)\\s*\\^\\s*(-?[a-zA-Z0-9]+)")

    /*
    For solving equations
    -> split equation down to smaller chunks
    -> note relation between chunks (add, subtract, powerof, etc)
    -> calculate chunks separately, note results
    -> compile outcomes together

    For resolving equation for 'x':
    -> locate '=' operator, if present
    -> simplify both sides as much as possible
    -> substitute desired value from left-to-right, or right-to-left, depending on complexity
        -> pass equation through algorithm to determine complexity (?)

    Some example inputs aiming to be supported:
    -> (2^3)+4
    -> 2^3+4
    -> (x) 2x+3y=7y //(val) indicates the value to be found
    -> (x) 2x+3y=9y-2
    -> (x, y) 2x+3y=9y-2
    -> -s 3x+2y-x+y^2 //-s indicates to 'simplify'
    -> x:2 y:1 2x+3y=9y-2 //Calculate for given values
    -> x:2 2x+3y=9y-2 //Calculate for given values
    -> n:1->10:2 2n^x //Print out the values of 1->10 in steps of 2, assigning val to n. Equivalent of sigma notation
    -> n:1->10:2 x:3 2n^x //As above, but substituting in x as 3
    -> (2x+3y)(a-2b) //expand
    -> (x) (2x+3y)(a-2b) //expand and resolve for x (if possible)
    -> -s (2x+3y)(a-2b) //expand and simplify (if possible)

    Constants and operators
    -> pi as Pi
    -> sin(x) as Sine(x)
    -> cos(x) as Cosine(x)
    -> tan(x) as Tangent(x)
    -> sqrt(x) as Square-root(x)
    -> (n)rt(x) as n'th-root(x)


     */

    private fun parse(expression: String): List<Expression> {
        TODO()
    }

    infix fun solveFor(element: String): Expression {
        TODO()
    }

    fun simplify(expression: Expression): Expression {
        TODO()
    }

    fun evaluate(expression: Expression): Expression {
        TODO()
    }
}

data class MathElementContainer(val lhs: Expression, val rhs: Expression, val operator: MathOperator)

enum class MathOperator {
    MULT,
    DIV,
    SUB,
    ADD,

    POW,
    ROOT,

    SIN,
    COS,
    TAN
}

data class Expression(val value: Double, val expr: String)