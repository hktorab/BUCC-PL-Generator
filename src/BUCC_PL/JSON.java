package BUCC_PL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;

public class JSON {
    File file = new File("plGenerator.json");
    JSONParser parser = new JSONParser();

    void checkingSignature(String sign)
    {
        try {
            //President
            if (sign.equals("President")){

                if (file.exists())
                {
                    Object obj = parser.parse(new FileReader("plGenerator.json"));
                    JSONObject jsonObject = (JSONObject) obj;
                    if (!jsonObject.containsKey("President"))
                    {
                        defaultPresident();
                    }else {
                        readInForSignature("President",jsonObject);
                    }
                }else {
                    defaultPresident();
                }
            }
            //Vice President
            else if(sign.equals("Vice-President"))
            {
                if (file.exists())
                {
                    Object obj = parser.parse(new FileReader("plGenerator.json"));
                    JSONObject jsonObject = (JSONObject) obj;
                    if (!jsonObject.containsKey("Vice-President"))
                    {
                        defaultVp();
                    }else {
                        readInForSignature("Vice-President",jsonObject);
                    }
                }else {
                    defaultVp();
                }
            }
            //Director
            else {
                if (file.exists())
                {
                    Object obj = parser.parse(new FileReader("plGenerator.json"));
                    JSONObject jsonObject = (JSONObject) obj;
                    if (!jsonObject.containsKey("Director"))
                    {
                        defaultDirector();
                    }else {
                        readInForSignature("Director",jsonObject);
                    }
                }else {
                    defaultDirector();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void defaultDirector() {
        Controller.signatureName="Humayun Kabir";
        Controller.signatureId="14141004";
        Controller.signaturePost="Director";
        Controller.signatureMobile="01521209545";
        Controller.signatureEmail="humayunkabirtorab@gmail.com";
    }



    private void defaultVp() {

        Controller.signatureName="Sakib Rahman";
        Controller.signatureId="13101279";
        Controller.signaturePost="Vice-President";
        Controller.signatureMobile="01521107994";
        Controller.signatureEmail="sakiblight@gmail.com";

    }

    private void defaultPresident() {
        Controller.signatureName="Hasin Raihan";
        Controller.signatureId="13301102";
        Controller.signaturePost="President";
        Controller.signatureMobile="016761709545";
        Controller.signatureEmail="anim.bracu@gmail.com";
    }



    private void readInForSignature(String position, JSONObject jsonObject) {
        JSONArray info = (JSONArray) jsonObject.get(position);
        Iterator<String> iterator = info.iterator();

        Controller.signatureName=iterator.next();
        Controller.signatureId=iterator.next();
        Controller.signaturePost=iterator.next();
        Controller.signatureMobile=iterator.next();
        Controller.signatureEmail=iterator.next();
        jsonObject.clear();


    }

    public static void updateJSONFile(String post, String nameText, String idText, String phoneText, String emailText) {






       JSONObject obj = new JSONObject();

        JSONArray list = new JSONArray();
        list.add(nameText);
        list.add(idText);
        list.add(post);
        list.add(phoneText);
        list.add(emailText);

        obj.put(post, list);

        try (FileWriter file = new FileWriter("plGenerator.json")) {

            file.write(obj.toJSONString());
            file.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Controller.signatureName=nameText;
        Controller.signatureId=idText;
        Controller.signaturePost=post;
        Controller.signatureMobile=phoneText;
        Controller.signatureEmail=emailText;

    }
}
