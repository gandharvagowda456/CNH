package helpers.readFile;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import testdata.TestDatas;

@Slf4j
public class ParseJsonData {

    public void getSiteConfigData(String envConfigName) throws Exception {

        ReadJsonFile readJson = new ReadJsonFile();
        try{
            JsonObject confInfo = readJson.readJsonFromResources(TestDatas.ENV_CONFIG_FILENAME);
            log.info("Reading "+envConfigName + " data");
            TestDatas.LOGIN_USERNAME = confInfo.getAsJsonObject("environments").getAsJsonObject(envConfigName).getAsJsonObject("logins").getAsJsonObject("firstLogin").get("userName").getAsString().trim();
            TestDatas.LOGIN_PASSWORD = confInfo.getAsJsonObject("environments").getAsJsonObject(envConfigName).getAsJsonObject("logins").getAsJsonObject("firstLogin").get("password").getAsString().trim();
            TestDatas.URL = confInfo.getAsJsonObject("environments").getAsJsonObject(envConfigName).getAsJsonObject("siteInfo").get("url").getAsString().trim();

        }catch (Exception error){
            throw new Exception("Error on reading "+TestDatas.ENV_CONFIG_FILENAME+" : " + error.getMessage());
        }
    }
}
