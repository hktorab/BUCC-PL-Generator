package BUCC_PL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    public Label todayDate;
    public TextField plName;
    public TextField eventName;
    public DatePicker eventDate;
    public TextField startTime;
    public TextField eventVenue;
    public TextArea eventAgenda;
    public TextArea eventAddText;
    public TextField endTime;

    public RadioButton signedByPresident;
    public RadioButton signedByVicePresident;
    public RadioButton noBudget;
    public RadioButton budget;

    public ComboBox<String> combo1;
    public ComboBox <String> combo2;
    public TextArea eventObjective;



    public RadioButton signedByDirector;
    public Label success;
    public  TextField showName;
    public  TextField showID;
    public  TextField showPhone;
    public  TextField showEmail;
    public Button reset;

    ObservableList<String> list = FXCollections.observableArrayList("AM","PM");
    ActionEvent event;

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        combo1.setItems(list);
        combo2.setItems(list);
        DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String dat=dateFormat.format(date).toString();

        todayDate.setText(dat);
        combo1.setValue("AM");
        combo2.setValue("AM");



//making Text field input only numeric
        numericTextField(endTime);
        numericTextField(startTime);
        //signature field initialization
        signature(event);

    }

    void numericTextField(TextField textField){
        textField.textProperty().addListener((obs, oldText, newText) -> {
            if (!newText.matches("\\d*")) {
                textField.setText(newText.replaceAll("[^\\d]", ""));
            }


        });
    }


    public void reset(ActionEvent actionEvent) {
        plName.setText("");
        eventName.setText("");
        startTime.setText("");
        endTime.setText("");
        eventVenue.setText("");
        eventAgenda.setText("");

        eventAddText.setText("");

        LocalDate localDate=null;
        eventObjective.setText("");
        eventDate.setValue(localDate);
        success.setText("");
        noBudget(actionEvent);
        noBudget.setSelected(true);




    }
    LocalDate localDate;
    static List<List<String>> budgetArray;


    public void extractToWord(ActionEvent actionEvent) {
        try {

            localDate = eventDate.getValue();

            //PlName
            if ((plName.getText()).isEmpty())
            {

                errorField(plName);
            }

            //Event Name
            else if((eventName.getText()).isEmpty())
            {
                errorRemoveField(plName);
                errorField(eventName);
            }

            //Start Time
            else if((startTime.getText()).isEmpty())
            {
                errorRemoveField(plName);
                errorRemoveField(eventName);
                errorRemoveField(eventDate);
                errorField(startTime);
            }


            //End Time

            else if((endTime.getText()).isEmpty())
            {
                errorRemoveField(plName);
                errorRemoveField(eventName);
                errorRemoveField(eventDate);
                errorRemoveField(startTime);
                errorField(endTime);
            }


            //venue

            else if((eventVenue.getText()).isEmpty())
            {
                errorRemoveField(plName);
                errorRemoveField(eventName);
                errorRemoveField(eventDate);
                errorRemoveField(startTime);
                errorRemoveField(endTime);
                errorField(eventVenue);
            }

            //agenda
            else if((eventAgenda.getText()).isEmpty())
            {
                errorRemoveField(plName);
                errorRemoveField(eventName);
                errorRemoveField(eventDate);
                errorRemoveField(startTime);
                errorRemoveField(eventVenue);
                errorField(eventAgenda);
            } else if (eventObjective.getText().isEmpty())
            {
                errorRemoveField(plName);
                errorRemoveField(eventName);
                errorRemoveField(eventDate);
                errorRemoveField(startTime);
                errorRemoveField(eventVenue);
                errorRemoveField(eventAgenda);
                errorField(eventObjective);
            }

            else {
                errorRemoveField(plName);
                errorRemoveField(eventName);
                errorRemoveField(eventDate);
                errorRemoveField(startTime);
                errorRemoveField(eventVenue);
                errorRemoveField(eventAgenda);
                errorRemoveField(eventObjective);

                String signature="";
                if (signedByPresident.isSelected())
                {
                    if (!(signatureName.equalsIgnoreCase(showName.getText()) && signatureId.equalsIgnoreCase(showID.getText()) &&
                            signatureMobile.equalsIgnoreCase(showPhone.getText()) && signatureEmail.equalsIgnoreCase(showEmail.getText()) )){
                        //hsetText();

                        JSON.updateJSONFile("President",showName.getText(),showID.getText(),showPhone.getText(),showEmail.getText());
                    }

                }
                else if(signedByVicePresident.isSelected()) {
                    if (!(signatureName.equalsIgnoreCase(showName.getText()) && signatureId.equalsIgnoreCase(showID.getText()) &&
                            signatureMobile.equalsIgnoreCase(showPhone.getText()) && signatureEmail.equalsIgnoreCase(showEmail.getText()) )){
                        //hsetText();

                        JSON.updateJSONFile("Vice-President",showName.getText(),showID.getText(),showPhone.getText(),showEmail.getText());
                    }

                }
                else {
                    if (!(signatureName.equalsIgnoreCase(showName.getText()) && signatureId.equalsIgnoreCase(showID.getText()) &&
                            signatureMobile.equalsIgnoreCase(showPhone.getText()) && signatureEmail.equalsIgnoreCase(showEmail.getText()) )){
                        //hsetText();

                        JSON.updateJSONFile("Director",showName.getText(),showID.getText(),showPhone.getText(),showEmail.getText());
                    }

                }

                if (noBudget.isSelected())
                {
                    budgetArray=new ArrayList<>();
                }
                WritePl writePl = new WritePl();

                String  sTime=startTime.getText()+" "+combo1.getValue();
                String  eTime=endTime.getText()+" "+combo2.getValue();
                writePl.variablesSet(plName.getText(),localDate,eventName.getText(),eventAgenda.getText(),
                        eventAddText.getText(),eventVenue.getText(),sTime,eTime,eventObjective.getText(),budgetArray,success);

            }
        }
        catch (Exception e){

            errorField(eventDate);
        }

    }

    static Stage  stage;


    public void budget(ActionEvent event) throws IOException {

        FXMLLoader loader=new FXMLLoader(getClass().getResource("PlTable.fxml"));
        Parent root = (Parent) loader.load();

        stage=new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                noBudget(event);

            }
        });
        tableController.getStage(stage);
        stage.showAndWait();
        //here we have to create a new window with dynamic textfield

    }


    public void noBudget(ActionEvent actionEvent){
        budgetArray=new ArrayList<>();

    }
    public void exit(ActionEvent actionEvent) {


        System.exit(0);
    }

    public void eventDateValidation(ActionEvent actionEvent) {
        eventDate.setConverter(new StringConverter<LocalDate>()
        {
            private DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            public String toString(LocalDate localDate)
            {
                if(localDate==null)
                    return "";
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString)
            {
                if(dateString==null || dateString.trim().isEmpty())
                {
                    return null;
                }
                return LocalDate.parse(dateString,dateTimeFormatter);
            }
        });

    }

    void errorField (TextField textField){
        textField.getStyleClass().add("errors");

    }


    void errorField (TextArea textArea){
        textArea.getStyleClass().add("errors");
    }

    void errorField (DatePicker datePicker){
        datePicker.getStyleClass().add("errors");
    }

    void errorRemoveField (TextField textField){
        textField.getStyleClass().remove("errors");

    }
    void errorRemoveField (TextArea textArea){
        textArea.getStyleClass().remove("errors");
    }
    void errorRemoveField (DatePicker datePicker){
        datePicker.getStyleClass().remove("errors");
    }




    //
    static  String  name;

    //signature
    static String signatureName="";
    static String signatureId="";
    static String signaturePost="";
    static String signatureMobile="";
    static String signatureEmail="";

    public void signature(ActionEvent actionEvent) {
        JSON json = new JSON();
        if (signedByPresident.isSelected())
        {
            json.checkingSignature("President");


        }else if(signedByVicePresident.isSelected())
        {
            json.checkingSignature("Vice-President");

        }
        else if (signedByDirector.isSelected()){
            json.checkingSignature("Director");


        }
        else {}

        showName.setText(signatureName);
        showID.setText(signatureId);
        showPhone.setText(signatureMobile);
        showEmail.setText(signatureEmail);
    }

    private void hsetText() {




        signatureName=showName.getText();
        signatureId=showID.getText();
        //signaturePost=show;
        signatureMobile=showPhone.getText();
        signatureEmail=showEmail.getText();
    }


}
