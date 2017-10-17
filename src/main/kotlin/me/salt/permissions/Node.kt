/*
 * Copyright 2017 DevJake
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.salt.permissions

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import me.salt.exception.ConfigMissingValueException
import me.salt.objects.Interaction
import java.util.regex.Pattern

@JsonInclude(JsonInclude.Include.NON_NULL)
class Node {
    var node = ""
        get() = makeFinalNode(field)
    @JsonIgnore
    var type: NodeType? = null
    @JsonIgnore
    var negate: Boolean? = null
    @JsonIgnore
    var segments: MutableList<NodeSegment>? = null
        get() = calcSegments()
    var authority: Authority.NodeAuthority? = null

    constructor(node: String) {
        this.node = node
    }

//    constructor(node: String, type: NodeType, negate: Boolean, segments: MutableList<NodeSegment>, authority: Authority.NodeAuthority) {
//        this.node = node
//        this.type = type
//        this.negate = negate
//        this.segments = segments
//        this.authority = authority
//    }
    @JsonCreator(mode = JsonCreator.Mode.DEFAULT)
    constructor(node: String, type: NodeType?, negate: Boolean?, segments: MutableList<NodeSegment>?, authority: Authority.NodeAuthority?) {
        this.node = node
        this.type = type ?: NodeType.PERMISSION
        this.negate = negate
        this.segments = segments
        this.authority = authority ?: Authority.None()
    }

    @JsonProperty("authority")
    private fun getJacksonAuthority() =
            if (!(authority?.levels?.contains(Authority.Level.NONE) ?: true && authority?.interactions?.contains(Interaction.ALL) ?: true)) authority else null

//    PermUtils.registerPermission(this) //Inform the bot of this permission's existence

    enum class NodeType {
        PERMISSION,
        ENFORCEMENT
    }

    private fun parse(node: String): String {
        val matchIllegals = Pattern.compile("[^a-zA-Z*.\\s]")
        var n0 = node
        if (node.startsWith("-")) {
            negate = true
            n0 = node.removePrefix("-")
        }
        if (matchIllegals.matcher(n0).find())
            throw ConfigMissingValueException(
                    "Illegal characters were specified for node parsing! Allowed = a-z, A-Z, '.' and '*'")
        var parsed = n0.toLowerCase()
        parsed = parsed.replace("\\s+", "")
        parsed = parsed.replace("[.]+", ".")
        parsed = parsed.replace("[*]+", "*")

        parsed = parsed.removePrefix(".")
        return parsed
    }

    private fun calcSegments() =
            node.split(".").toList()
                    .mapTo(mutableListOf(),
                            { NodeSegment(it, it == "*") })

    private fun makeFinalNode(node: String) =
            "${if (negate ?: false && type == NodeType.PERMISSION) "-" else ""}$node"

    fun addSegment(segment: String) {
        //TODO block if last segment is a single asterisk
    }

    fun addBlanketSegment() {
        //TODO add asterisk to current chain
    }

    override fun toString(): String {
        return "Node(node=$node, type=$type, negate=$negate, segments=$segments), authority=$authority"
    }

    data class NodeSegment(val segment: String, val isBlanket: Boolean = false)
}

class NodeBuilder(node: String) {
    private var node = node
    private var type = Node.NodeType.PERMISSION
    private var negate = false
    private var segments = mutableListOf<Node.NodeSegment>()
    private var authority = Authority.None()

    constructor(node: String, authority: Authority.NodeAuthority) : this(node) {
        this.node = node
        this.authority = authority
    }

    constructor(node: String, negate: Boolean) : this(node) {
        this.node = node
        this.negate = negate
    }

    constructor(node: String, type: Node.NodeType) : this(node) {
        this.node = node
        this.type = type
    }

    constructor(node: String, type: Node.NodeType, negate: Boolean) : this(node) {
        this.node = node
        this.type = type
        this.negate = negate
    }

    constructor(node: String, type: Node.NodeType, authority: Authority.NodeAuthority) : this(node) {
        this.node = node
        this.type = type
        this.authority = authority
    }

    constructor(node: String, negate: Boolean, authority: Authority.NodeAuthority) : this(node) {
        this.node = node
        this.negate = negate
        this.authority = authority
    }

    constructor(node: String, type: Node.NodeType, negate: Boolean, authority: Authority.NodeAuthority) : this(node) {
        this.node = node
        this.type = type
        this.negate = negate
        this.authority = authority
    }

    fun build() = Node(node, type, negate, segments, authority)

    fun setNode(node: String) = apply { this.node = node }
    fun setType(type: Node.NodeType) = apply { this.type = type }
    fun setNegate(negate: Boolean) = apply { this.negate = negate }
    fun setAuthority(authority: Authority.NodeAuthority) = apply { this.authority = authority }
}