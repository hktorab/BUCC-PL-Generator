package BUCC_PL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.Iterator;

public class JSON {
    static File file = new File("plGenerator.json");
    JSONParser parser;
    void checkingSignature(String sign)
    {
        JSONParser parser = new JSONParser();

        try {
            //President
            if (sign.equals("President")){

                if (file.exists())
                {
                    Object obj = parser.parse(new FileReader("plGenerator.json"));
                    JSONObject jsonObject = (JSONObject) obj;
                    JSONArray info = (JSONArray) jsonObject.get("President");
                    Iterator<String> iterator = info.iterator();

                    if (iterator.next().isEmpty())
                    {
                        defaultPresident();
                        jsonObject.clear();
                        info.clear();
                        ((JSONObject) obj).clear();

                    }else {
                        readInForSignature("President",jsonObject);
                    }
                }else {
                    defaultPresident();
                }
            }
            //Vice President
            //Vice-President
            else if(sign.equals("Vice-President"))
            {
                if (file.exists())
                {
                    Object obj = parser.parse(new FileReader("plGenerator.json"));
                    JSONObject jsonObject = (JSONObject) obj;
                    JSONArray info = (JSONArray) jsonObject.get("Vice-President");
                    Iterator<String> iterator = info.iterator();

                    if (iterator.next().isEmpty())
                    {
                        defaultVp();
                        jsonObject.clear();
                        info.clear();
                        ((JSONObject) obj).clear();

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
                    JSONArray info = (JSONArray) jsonObject.get("Director");
                    Iterator<String> iterator = info.iterator();

                    if (iterator.next().isEmpty())
                    {
                        defaultDirector();
                        jsonObject.clear();
                        info.clear();
                        ((JSONObject) obj).clear();

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



    private  void readInForSignature(String post, JSONObject jsonObject) {
        JSONArray info = (JSONArray) jsonObject.get(post);
        Iterator<String> it = info.iterator();

        if (it.next().isEmpty())
        {
            defaultPresident();
            jsonObject.clear();
        }else {
            info = (JSONArray) jsonObject.get(post);
            Iterator<String> iterator = info.iterator();
            Controller.signatureName=iterator.next();
            Controller.signatureId=iterator.next();
            Controller.signaturePost=iterator.next();
            Controller.signatureMobile=iterator.next();
            Controller.signatureEmail=iterator.next();
            jsonObject.clear();
        }

    }








    public  void updateJSONFile(String post, String name, String id, String phone, String email) {




        try {

            if (!file.exists())
            {
                file.createNewFile();
                String dummyValue="{\"President\":[\"\",\"\",\"\",\"\",\"\"],\"Vice-President\":[\"\",\"\",\"\",\"\",\"\"],\"Director\":[\"\",\"\",\"\",\"\",\"\"]"+"}";
               // System.out.println(dummyValue);
                createJSON(dummyValue); // it will create a blank json file with empty value of president,vp and director

            }

            //Creating Json Object
            JSONObject obj = new JSONObject();
            JSONArray list = new JSONArray();
            list.add(name);
            list.add(id);
            list.add(post);
            list.add(phone);
            list.add(email);
            obj.put(post, list);
            String valueOfObject=obj.toJSONString();

            //Removing initial { and finishing } of json file
            valueOfObject=valueOfObject.substring(valueOfObject.indexOf("{")+1,valueOfObject.indexOf("}"));
           // System.out.println(valueOfObject);


            //Fetching Data of json File

            BufferedReader bufferedReader = new BufferedReader(new FileReader("plGenerator.json"));
            String storedValueInJson=bufferedReader.readLine();

            //Removing initial { and finishing } of json file
            storedValueInJson=storedValueInJson.substring(storedValueInJson.indexOf("{")+1,storedValueInJson.indexOf("}"));
            //System.out.println(storedValueInJson);

            String [] array =storedValueInJson.split("]");
            array[0]+="]";
            array[1]+="]";
            array[2]+="]";

            if (post.equals("President")) {
                array[0]=valueOfObject;
            }else if (post.equals("Vice-President"))
            {
                array[1]=valueOfObject;
            }else if (post.equals("Director"))
            {
                array[2]=valueOfObject;
            }
            else { }

            String newJSONValue="{"+array[0];

            for (int c=1;c<array.length;c++){
                newJSONValue=newJSONValue+array[c];
            }

            newJSONValue=newJSONValue+"}";
           // System.out.println(newJSONValue);
            createJSON(newJSONValue);


            //updating info from json file
            JSONParser parser = new JSONParser();
            Object  object = parser.parse(new FileReader("plGenerator.json"));
            JSONObject jsonObject = (JSONObject)  object;
            readInForSignature(post,jsonObject);



        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void createJSON(String value){
        try {

            FileWriter fileWriter = new FileWriter("plGenerator.json");
            fileWriter.write(value);

            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
