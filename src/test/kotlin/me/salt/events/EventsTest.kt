/*
 * Copyright 2017 DevJake
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.salt.events

import com.winterbe.expekt.should
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.*
import java.io.File

class MyEvent(var testArg: String) : Event

class GeneralListener : ListenerAdapter {
    lateinit var lastEvent: Event
    override fun onEvent(e: Event) {
        lastEvent = e
    }
}

class SpecificListener : EventHandler() {
    lateinit var receivedEvent: FileCreateEvent

    override fun onFileCreate(e: FileCreateEvent) {
        receivedEvent = e
    }
}


class EventsTest : Spek({
        given("an event listener class interfaced to ListenerAdapter") {
            var subject = GeneralListener()
            describe("when registering and then firing an event to our event listener") {
                EventDistributor.registerListener(subject)
                EventDistributor.fireEvent(MyEvent("Test String to verify instance integrity"))
                EventDistributor.unregisterListener(subject)
                it("should relay an instance of our fired event to our listener") {
                    subject.lastEvent.should.be.instanceof(MyEvent::class.java)
                }

                it("should relay the exact event instance to our listener") {
                    (subject.lastEvent as MyEvent).testArg.should.equal("Test String to verify instance integrity")
                }
            }
    }

        given("an event listener extending EventHandler") {
            describe("when firing an event (FileCreateEvent)") {
                var subject = SpecificListener()
                EventDistributor.registerListener(subject)
                EventDistributor.fireEvent(FileCreateEvent(File("path\\to\\my\\file"), false))
                EventDistributor.unregisterListener(subject)
                it("should relay an instance of our fired event to our listener") {
                    subject.receivedEvent.should.be.an.instanceof(FileCreateEvent::class.java)
                }

                it("should relay the exact event instance to our listener") {
                    subject.receivedEvent.file.path.should.equal("path\\to\\my\\file")
                }
            }
        }

    on("firing an event by calling fireEvent()"){
        var subject = GeneralListener()
        EventDistributor.registerListener(subject)
        val p = MyEvent("text-p")
        fireEvent(p)
        EventDistributor.unregisterListener(subject)
        it("should fire an instance of our event"){
            subject.lastEvent.should.be.instanceof(p::class.java)
        }

        it("should fire our exact event instance"){
            subject.lastEvent.should.equal(p)
        }
    }
})
