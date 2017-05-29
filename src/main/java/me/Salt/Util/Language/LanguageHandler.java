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
package me.Salt.Util.Language;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.Salt.Exception.Generic.MalformedJsonException;
import me.Salt.Exception.Generic.MissingDataException;
import me.Salt.Exception.Language.LanguageNotFoundException;
import me.Salt.Logging.LogUtils;
import me.Salt.Main;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;

/**
 * This class handles all information about the system's language files,
 * including the creation, modification and maintenance of the language files.
 */
public class LanguageHandler {
    private List<LanguageContainer> languages = new ArrayList<>();
    private HashMap<LangCode, LanguageContainer> lang = new HashMap<>();
    private Gson g = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                                      .serializeNulls()
                                      .setPrettyPrinting()
                                      .create();
    private boolean checked = false;

    /**
     * This parameter-less constructor automatically reads in system language files,
     * parsing their Json content to a LanguageContainer instance.
     * This instance is then cached into the internal system for later reference,
     * as well as being stored to a HashMap by the respective LangCode it holds.
     */
    public LanguageHandler() {
        //TODO move method contents to own method, then add a parameter for specifying the language-file type (guild/user/textchannel/system (by default))
        try {
            File f = new File(
                    URLDecoder.decode(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath(),
                            "UTF-8") + "/Data/Languages/System/");
            if (f.listFiles() == null)
                throw new LanguageNotFoundException("No system language files could be located!");
            StringBuilder sb;
            for (File file : f.listFiles()) {
                sb = new StringBuilder();
                BufferedReader br = new BufferedReader(new FileReader(file));
                String n;
                while ((n = br.readLine()) != null) {
                    sb.append(n).append("\n");
                }
                LanguageContainer lc = g.fromJson(sb.toString(), LanguageContainer.class);
                if (lc == null)
                    throw new MalformedJsonException("Language file (" + file.getName() + ") has malformed Json!");
                addLanguageNoWrite(lc);
            }
        } catch (IOException | LanguageNotFoundException | MalformedJsonException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void check(LanguageContainer languageContainer) {
        if (checked) return;
        // TODO Although every language is checked, the default language should be checked differently to other languages.
        // If a language is missing a value, it isn't majorly important; Simply log the missing value, but if the default
        // language is missing a value, the method should completely halt the program from finishing the start-up process.
        // This is because, if the system is looking for a value and it doesn't exist, it will refer to the superior language:
        // Say that the default language is English, but a guild has their language set to Russian.
        // If a user (or a guild) creates a language, they should be able to specify specific values, and not required to fill in every value.
        // If a user speaks, the bot should first attempt to retrieve the value for their custom, modified language.
        // If this doesn't exist, the system should then re-check for the same string, but with the guild's specified language.
        // If this also fails, then the value from the default language should be used. As a result, it is critically important that the default language is 100% complete.
        List<LangString> e = Arrays.asList(LangString.values());
        e.sort(Comparator.comparing(Enum::name));
        List<LangString> r = new ArrayList<>(languageContainer.getStrings().keySet());
        r.sort(Comparator.comparing(Enum::name));
        System.out.println(e + "\n" + r);
        if (e != r) try {
            throw new MissingDataException("Language " + languageContainer.getCode() + " is missing data!");
        } catch (MissingDataException e1) {
            // LogUtils.log(e1.getMessage(), LogUtils.Severity.SEVERE);
            // Cannot use as the language handler isn't currently existant to ConfigurationImpl.class. This creates a recursive call, which results in a NullPointerException
            //e1.printStackTrace();
            System.out.println(e1.getMessage());
            if (languageContainer.getCode().equals(Main.salt.getDefaultLangCode()))
                System.exit(-1); //Exits application if a language file exists, but is missing information
        }
        checked = true;
    }

    /**
     * This class queries the cached language files for the specified language.
     *
     * @param langCode LangCode - The LangCode to be queried
     * @return LanguageContainer - A LanguageContainer instance for the specified LangCode, or null if none was found
     */
    public LanguageContainer getLanguage(LangCode langCode) {
        return lang.getOrDefault(langCode, null);
        //TODO throw exception instead of returning null
    }
    //TODO add system that throws a warning if a LangCode value doesn't exist/doesn't have a value assigned

    /**
     * @return List - A List of all LangCode enum values
     */
    public List<LangCode> getAvailableLanguages() {
        return new ArrayList<>(lang.keySet());
    }

    /**
     * This method adds a new language to be recognised by the bot to both the internal cache, as well as calling upon the writeLang() method.
     * If an attempt is made to add a language that is already registered, the data shall be overwritten.
     *
     * @param languageContainer LanguageContainer - The instance of the LanguageContainer class that should be used for method operation
     */
    public void addLanguage(LanguageContainer languageContainer) {
        if (lang.containsKey(languageContainer.getCode())) {
            lang.replace(languageContainer.getCode(), languageContainer);
            writeLang(languageContainer);
        } else {
            lang.put(languageContainer.getCode(), languageContainer);
            languages.add(languageContainer);
            writeLang(languageContainer);
        }
    }

    /**
     * This method adds a new language to be recognised by the bot to both the internal cache.
     * Unlike with the addLanguage() method, this method does not call upon the writeLang() method.
     * <p>
     * This method exists so as to avoid wasting system resources.
     * Without this method, a language added from the system (through the initialisation stage)
     * would effectively be re-written with the same content,
     * when the initialisation method attempted to register the new LanguageContainer.
     * This method avoids such an issue.
     * <p>
     * If an attempt is made to add a language that is already registered, the data shall be overwritten.
     *
     * @param languageContainer LanguageContainer - The instance of the LanguageContainer class that should be used for method operation
     */
    private void addLanguageNoWrite(LanguageContainer languageContainer) {
        if (lang.containsKey(languageContainer.getCode())) {
            lang.replace(languageContainer.getCode(), languageContainer);
        } else {
            lang.put(languageContainer.getCode(), languageContainer);
            languages.add(languageContainer);
        }
    }

    /**
     * This method attempts to remove the LanguageContainer tied to this LangCode.
     *
     * @param langCode LangCode - The LangCode that this method should query
     */
    public void removeLanguage(LangCode langCode) {
        //TODO
    }

    /**
     * This method attempts to create a new .language file, and populate with details about a LanguageContainer.
     *
     * @param lc LanguageContainer - The LanguageContainer to use for file creation and population
     */
    private void writeLang(LanguageContainer lc) {
        //TODO add a type system, to allow for the generation of guild/textchannel/user language files.
        try {
            File f = new File(
                    URLDecoder.decode(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath(),
                            "UTF-8") + "/Data/Languages/System/", lc.getCode().name() + ".language");
            if (f.getParentFile().mkdirs())
                LogUtils.log("Created new file structure"); //TODO add LogUtils implementation
            else LogUtils.log("No new file structure created");
            if (f.createNewFile()) LogUtils.log("Created new file: " + f.getName());
            else LogUtils.log("No new file made: " + f.getName());
            f.setWritable(true);
            FileWriter fw = new FileWriter(f);
            fw.write(g.toJson(lc));
            fw.flush();
            fw.close();
            f.setWritable(false);
            f.setReadOnly();
        } catch (IOException e) {
            e.printStackTrace();
        }
        check(lc);
    }
}
//TODO consider a re-write of the language system. Idea of using Json is good(-ish), but might work better with a system of <name>xyz<value>xyz, to allow for non-enumerated strings to be added
