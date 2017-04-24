/*
 * Copyright (c) 2017 DevJake
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.Salt.Command.Container;

import me.Salt.Handler.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class parses raw text into a range of details about a command
 */
public class CommandParser {
    /**
     * This method takes a String of raw text, and attempts to parse it into a command.
     *
     * @param raw String - The raw text to be put through the parsing algorithm.
     *
     * @return ParsedCommandContainer - A container class that stores details about the parsed command.
     */
    public ParsedCommandContainer parse(String raw) {
        String beheaded = raw.replaceFirst(Main.salt.getCmdPrefix(), "");
        List<String> splitBeheaded = Arrays.asList(beheaded.split("\\s+"));
        List<String> argsUpper = splitBeheaded.subList(1, splitBeheaded.size());
        List<String> argsLower = new ArrayList<>();
        argsUpper.forEach(s -> argsLower.add(s.toLowerCase()));
        String cmd = splitBeheaded.get(0).toLowerCase();
        String cmdUpper = splitBeheaded.get(0);

        return new ParsedCommandContainer(raw, argsLower, argsUpper, beheaded, splitBeheaded, cmd, cmdUpper);
    }

    /**
     * A container class for details about a parsed command.
     */
    public class ParsedCommandContainer {
        private String rawText;
        private List<String> argsLower;
        private List<String> argsUpper;
        private String beheaded;
        private List<String> splitBeheaded;
        private String cmd;
        private String cmdUpper;
//TODO add List<String> definers, then sort args from definers. Definer often starts with a '-', but add a variable to SaltAPI to get and set the definer.

        /**
         * A constructor for details about the parsed command.
         *
         * @param rawText       String - The raw text of the command
         * @param argsLower     List - A list of the arguments, in lowercase
         * @param argsUpper     List - A list of the arguments, in the original case they were initially entered in
         * @param beheaded      String - The raw text, with the command prefix removed
         * @param splitBeheaded List - The raw text processed through a beheading (removal of the command prefix), then separated by a space
         * @param cmd           String - The command itself, in lowercase
         * @param cmdUpper      String - The command itself, in the original case it was initially entered in
         */
        public ParsedCommandContainer(String rawText, List<String> argsLower, List<String> argsUpper, String beheaded, List<String> splitBeheaded, String cmd, String cmdUpper) {
            this.rawText = rawText;
            this.argsLower = argsLower;
            this.argsUpper = argsUpper;
            this.beheaded = beheaded;
            this.splitBeheaded = splitBeheaded;
            this.cmd = cmd;
            this.cmdUpper = cmdUpper;
        }

        @Override
        public String toString() {
            return "ParsedCommandContainer{" +
                    "rawText='" + rawText + '\'' +
                    ", argsLower=" + argsLower +
                    ", argsUpper=" + argsUpper +
                    ", beheaded='" + beheaded + '\'' +
                    ", splitBeheaded=" + splitBeheaded +
                    ", cmd='" + cmd + '\'' +
                    ", cmdUpper='" + cmdUpper + '\'' +
                    '}';
        }

        /**
         * @return String - The raw text of the command.
         */
        public String getRawText() {
            return rawText;
        }

        /**
         * @return List - A list of the command's arguments, in lowercase.
         */
        public List<String> getArgsLower() {
            return argsLower;
        }

        /**
         * @return List - A list of the command's arguments, in the original casing they were entered in.
         */
        public List<String> getArgsUpper() {
            return argsUpper;
        }

        /**
         * @return String - The raw command, without the command prefix at the beginning.
         */
        public String getBeheaded() {
            return beheaded;
        }

        /**
         * @return List - The beheaded string, split by a blank space into a command and its following arguments.
         */
        public List<String> getSplitBeheaded() {
            return splitBeheaded;
        }

        /**
         * @return String - The command that was entered, such as "help". This string is in lowercase.
         */
        public String getCmd() {
            return cmd;
        }

        /**
         * @return String - The command that was entered, such as "help". This string is in its original casing.
         */
        public String getCmdUpper() {
            return cmdUpper;
        }
    }
}

