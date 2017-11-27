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

package me.salt.entities.config.entities

import com.winterbe.expekt.should
import me.salt.entities.permissions.GroupPermission
import me.salt.utilities.exception.ExceptionHandler
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class PermissionMapBuilderTest : Spek({
    ExceptionHandler.isTesting = true
    var subject = PermissionMapBuilder()
    val g0 = GroupPermission("group1", mutableListOf(), mutableListOf(), mutableListOf(), true, mutableListOf())
    val g1 = GroupPermission("group2", mutableListOf(), mutableListOf(), mutableListOf(), false, mutableListOf())
    val g2 = GroupPermission("group3", mutableListOf(), mutableListOf(), mutableListOf(), true, mutableListOf())

    beforeEachTest { subject = PermissionMapBuilder() }
    context("Group permissions") {
        on("adding a single group permission") {
            subject.addGroups(g0)
            it("should add our group permission to the list of group permissions") {
                (subject.build() as PermissionMap).groups.should.contain(g0)
            }

            it("should contain only one group permission") {
                (subject.build() as PermissionMap).groups?.size.should.equal(1)
            }
        }

        on("adding multiple group permission") {
            subject.addGroups(g0, g1)
            it("should add our group permission to the list of group permissions") {
                (subject.build() as PermissionMap).groups.should.contain(g0)
                (subject.build() as PermissionMap).groups.should.contain(g1)
            }

            it("should contain two group permissions") {
                (subject.build() as PermissionMap).groups?.size.should.equal(2)
            }
        }

        on("removing one existing group permission") {
            subject.addGroups(g0)
            it("should remove our specified group permission") {
                subject.removeGroups(g0)
                (subject.build() as PermissionMap).groups.should.not.contain(g0)
            }

            it("should contain zero group permissions") {
                (subject.build() as PermissionMap).groups?.size.should.equal(0)
            }
        }

        on("removing multiple existing group permissions where both are present") {
            subject.addGroups(g0, g1)
            it("should remove both of our specified group permissions") {
                subject.removeGroups(g0, g1)
                (subject.build() as PermissionMap).groups.should.not.contain(g0)
                (subject.build() as PermissionMap).groups.should.not.contain(g1)
            }

            it("should contain zero group permissions") {
                (subject.build() as PermissionMap).groups?.size.should.equal(0)
            }
        }

        on("removing multiple group permissions where only one is present") {
            subject.addGroups(g0, g2)
            it("should remove only the existing specified group permissions") {
                subject.removeGroups(g0, g1)
                (subject.build() as PermissionMap).groups.should.not.contain(g0)
                (subject.build() as PermissionMap).groups.should.contain(g2)
            }

            it("should contain zero group permissions") {
                (subject.build() as PermissionMap).groups?.size.should.equal(1)
            }
        }
    }
})