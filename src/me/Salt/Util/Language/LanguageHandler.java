package me.Salt.Util.Language;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.Salt.Exception.Generic.MalformedJsonException;
import me.Salt.Exception.Language.LanguageNotFoundException;

import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class handles all information about the system's language files,
 * including the creation, modification and maintenance of the language files.
 */
public class LanguageHandler {
    private List<LanguageContainer> languages = new ArrayList<>();
    private HashMap<LangCode, LanguageContainer> lang = new HashMap<>();
    private Gson g = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .serializeNulls()
            .setPrettyPrinting()
            .create();

    /**
     * This parameter-less constructor automatically reads in system language files,
     * parsing their Json content to a LanguageContainer instance.
     * This instance is then cached into the internal system for later reference,
     * as well as being stored to a HashMap by the respective LangCode it holds.
     */
    public LanguageHandler() {
        //TODO move method contents to own method, then add a parameter for specifying the language-file type (guild/user/textchannel/system (by default))
        try {
            File f = new File(URLDecoder.decode(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath(), "UTF-8") + "/Data/Languages/System/");

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

    /**
     * This class queries the cached language files for the specified language.
     *
     * @param langCode LangCode - The LangCode to be queried
     *
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
            File f = new File(URLDecoder.decode(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath(), "UTF-8") + "/Data/Languages/System/", lc.getCode().name() + ".language");
            if (f.getParentFile().mkdirs())
                System.out.println("Created new file structure"); //TODO add LogUtils implementation
            else System.out.println("No new file structure created");

            if (f.createNewFile())
                System.out.println("Created new file: " + f.getName());
            else System.out.println("No new file made: " + f.getName());

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
    }
}
