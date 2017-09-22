package BUCC_PL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class JSON {
    static File file = new File("plGenerator.json");

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



    private void readInForSignature(String post, JSONObject jsonObject) {
        JSONArray info = (JSONArray) jsonObject.get(post);
        Iterator<String> iterator = info.iterator();

        Controller.signatureName=iterator.next();
        Controller.signatureId=iterator.next();
        Controller.signaturePost=iterator.next();
        Controller.signatureMobile=iterator.next();
        Controller.signatureEmail=iterator.next();
        jsonObject.clear();


    }



    static void lol(){
        String s1 = "[{name: \"Bob\", car: \"Ford\"},{name: \"Mary\", car: \"Fiat\"}]";
        String s2 = "[{name: \"Mack\", car: \"VW\"},{name: \"Steve\", car: \"Mercedes Benz\"}]";
        String s3;
        //  s1=s1.s
        s1=s1.substring(s1.indexOf("[")+1, s1.indexOf("]"));
        System.out.println(s1);
        s2=s2.substring(s2.indexOf("[")+1, s2.indexOf("]"));
        System.out.println(s2);

        s3="["+s1+","+s2+"]";
        // System.out.println(s3);
    }



    static  String post;
    static  String nameText;
    static  String idText;
    static  String phoneText;
    static  String emailText;


    public static void updateJSONFile(String p, String name, String id, String phone, String email) {

        post=p;
        nameText=name;
        idText=id;
        phoneText=phone;
        emailText=email;

        String s1 = "[{name: \"Bob\", car: \"Ford\"},{name: \"Mary\", car: \"Fiat\"}]";
        String s2 = "[{name: \"Mack\", car: \"VW\"},{name: \"Steve\", car: \"Mercedes Benz\"}]";
        String s3=new String("");
        s1=s1.substring(s1.indexOf("[")+1, s1.indexOf("]"));
        s2=s2.substring(s2.indexOf("[")+1, s2.indexOf("]"));
        s3="["+s1+","+s2+"]";
        System.out.println(s3);


        try {

            if (!file.exists())
            {
                file.createNewFile();
                write();
            }
            else {
                JSONParser parser = new JSONParser();
                Object object = parser.parse(new FileReader("plGenerator.json"));
                JSONObject jsonObject = (JSONObject) object;

                boolean presidentObjectExist=false;
                boolean vpObjectExist=false;
                boolean directorObjectExist=false;

                if (jsonObject.containsKey("President")) {
                    presidentObjectExist=true;
                }else if (jsonObject.containsKey("Vice-President"))
                {
                    vpObjectExist=true;
                }else if (jsonObject.containsKey("Director"))
                {
                    directorObjectExist=true;
                }
                else {

                }

                if (post.equals("President")) {
                    presidentObjectExist=true;
                }else if (jsonObject.containsKey("Vice-President"))
                {
                    vpObjectExist=true;
                }else if (jsonObject.containsKey("Director"))
                {
                    directorObjectExist=true;
                }
                else {

                }

            }

            write();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void write() throws IOException {
        JSONObject obj = new JSONObject();
        JSONArray list = new JSONArray();
        list.add(nameText);
        list.add(idText);
        list.add(post);
        list.add(phoneText);
        list.add(emailText);
        //obj.put();
        obj.putIfAbsent(post, list);




        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(obj.toJSONString());
        fileWriter.flush();



        Controller.signatureName=nameText;
        Controller.signatureId=idText;
        Controller.signaturePost=post;
        Controller.signatureMobile=phoneText;
        Controller.signatureEmail=emailText;
    }
}
