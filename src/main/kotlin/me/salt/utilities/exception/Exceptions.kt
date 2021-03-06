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

package me.salt.utilities.exception

class ColourValueException(message: String? = null) : RuntimeException(message)
class ConfigMissingValueException(message: String? = null) : RuntimeException(message)
class BuilderValueException(message: String? = null) : RuntimeException(message)
class ExcessiveValueException(message: String? = null) : RuntimeException(message)
class ConfigHandlerException(message: String? = null) : RuntimeException(message)
class ConfigWriteException(message: String? = null) : RuntimeException(message)
class AmorphousCommandException(message: String? = null) : RuntimeException(message)
class PrefixlessCommandException(message: String? = null) : RuntimeException(message)
class ConfigIllegalFieldException(message: String? = null) : RuntimeException(message)
class LogEntryIdGenMissingIdException(message: String? = null) : RuntimeException(message)
class ScheduleValueOutOfBoundsException(message: String? = null) : RuntimeException(message)

class LangConfigReadException(message: String? = null) : Exception(message)
class PreExecuteCommandFailureException(message: String? = null) : Exception(message)
class ExecuteCommandFailureException(message: String? = null) : Exception(message)
class PostExecuteCommandFailureException(message: String? = null) : Exception(message)
class RollbarInitException(message: String? = null) : Exception(message)
class DuplicateRestEndpointException(message: String? = null) : Exception(message)
class CommandBuilderFailureException(message: String? = null) : Exception(message)

