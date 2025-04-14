package helpers.readFile;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadJsonFile {
    ClassLoader resourceFolder;

    public ReadJsonFile() {resourceFolder = this.getClass().getClassLoader();}

    public JsonObject readJsonFromResources(String fileName) throws Exception {
        System.out.println("File Path:" + fileName);
        try {
            InputStream file = resourceFolder.getResourceAsStream("config/" + fileName);
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = (JsonObject)jsonParser.parse(
                    new InputStreamReader(file, "UTF-8"));

            return jsonObject;
        } catch(Exception ex){
            throw new Exception("Error trying to read Json file " + ex.getMessage());
        }
    }

}
