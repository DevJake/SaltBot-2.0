package me.Salt.Container;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Salt001 on 10/04/2017.
 */


public class CommandParser {
    public CommandContainer parse(String raw, String cmdPrefix) {
        String beheaded = raw.replaceFirst(cmdPrefix, "");
        List<String> splitBeheaded = Arrays.asList(beheaded.split(" "));
        List<String> argsUpper = splitBeheaded.subList(1, splitBeheaded.size());
        List<String> argsLower = new ArrayList<>();
        argsUpper.forEach(s -> argsLower.add(s.toLowerCase()));
        String cmd = splitBeheaded.get(0).toLowerCase();
        String cmdUpper = splitBeheaded.get(0);

        return new CommandContainer(raw, argsLower, argsUpper, beheaded, splitBeheaded, cmd, cmdUpper);
    }


    public class CommandContainer {
        private String rawText;
        private List<String> argsLower;
        private List<String> argsUpper;
        private String beheaded;
        private List<String> splitBeheaded;
        private String cmd;
        private String cmdUpper;

        /**
         * @param rawText       String - The raw text of the command
         * @param argsLower     List - A list of the arguments, in lowercase
         * @param argsUpper     List - A list of the arguments, in the original case they were initially entered in
         * @param beheaded      String - The raw text, with the command prefix removed
         * @param splitBeheaded List - The raw text processed through a beheading (removal of the command prefix), then separated by a space
         * @param cmd           String - The command itself, in lowercase
         * @param cmdUpper      String - The command itself, in the original case it was initially entered in
         */
        public CommandContainer(String rawText, List<String> argsLower, List<String> argsUpper, String beheaded, List<String> splitBeheaded, String cmd, String cmdUpper) {
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
            return "CommandContainer{" +
                    "rawText='" + rawText + '\'' +
                    ", argsLower=" + argsLower +
                    ", argsUpper=" + argsUpper +
                    ", beheaded='" + beheaded + '\'' +
                    ", splitBeheaded=" + splitBeheaded +
                    ", cmd='" + cmd + '\'' +
                    ", cmdUpper='" + cmdUpper + '\'' +
                    '}';
        }

        public String getRawText() {
            return rawText;
        }

        public List<String> getArgsLower() {
            return argsLower;
        }

        public List<String> getArgsUpper() {
            return argsUpper;
        }

        public String getBeheaded() {
            return beheaded;
        }

        public List<String> getSplitBeheaded() {
            return splitBeheaded;
        }

        public String getCmd() {
            return cmd;
        }

        public String getCmdUpper() {
            return cmdUpper;
        }
    }
}

