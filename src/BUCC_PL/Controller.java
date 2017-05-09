package BUCC_PL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    public Label eventTodaysDate;
    public TextField plName;
    public TextField eventName;
    public DatePicker eventDate;
    public TextField startTime;
    public TextField eventVenue;
    public TextArea eventAgenda;
    public TextArea eventAddText;
    public TextField endTime;
    public Label errorText;

    public RadioButton signedByPresident;
    public RadioButton signedByVicePresident;
    public RadioButton noBudget;
    public RadioButton budget;
    public TextField row;
    public ComboBox<String> combo1;
    public ComboBox <String> combo2;
    public TextArea eventObjective;
    public Label PLGenerated;

    ObservableList<String> list = FXCollections.observableArrayList("AM","PM");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        combo1.setItems(list);
        combo2.setItems(list);
        DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String dat=dateFormat.format(date).toString();
        // System.out.println(d);
        row.setEditable(false);
        eventTodaysDate.setText(dat);
    }



    public void reset(ActionEvent actionEvent) {
        plName.setText("");
        eventName.setText("");
        startTime.setText("");
        endTime.setText("");
        eventVenue.setText("");
        eventAgenda.setText("");
        errorText.setText("");
        eventAddText.setText("");
        LocalDate localDate=null;
        eventObjective.setText("");
        eventDate.setValue(localDate);


    }
    LocalDate localDate;

    public void extractToWord(ActionEvent actionEvent) {
        try {

            localDate = eventDate.getValue();
            String evenDateCheck=localDate.toString();


            //PlName
            if ((plName.getText()).isEmpty())
            {
                errorText.setText("You Must Fill all The Field");
            }

            //Event Name
            else if((eventName.getText()).isEmpty())
            {
                errorText.setText("You Must Fill all The Field");
            }

            //Event Date



            else if(evenDateCheck.isEmpty())
            {
                errorText.setText("You Must Fill all The Field");
            }


            //Start Time
            else if((startTime.getText()).isEmpty())
            {

                errorText.setText("You Must Fill all The Field");
            }
            else if(combo1.getValue().isEmpty()){
                errorText.setText("You Must Fill all The Field");
            }

            //End Time

            else if((endTime.getText()).isEmpty())
            {
                errorText.setText("You Must Fill all The Field");
            }

            else if(combo2.getValue().isEmpty()){
                errorText.setText("You Must Fill all The Field");
            }
            //venue

            else if((eventVenue.getText()).isEmpty())
            {
                errorText.setText("You Must Fill all The Field");
            }

            //agenda
            else if((eventAgenda.getText()).isEmpty())
            {
                errorText.setText("You Must Fill all The Field");
            } else if (eventObjective.getText().isEmpty())
            {
                errorText.setText("You Must Fill all The Field");

            }

            else {


                String signature;
                if (signedByPresident.isSelected())
                {
                    signature="anim";
                }
                else {
                    signature="asif";
                }

                int budget;
                if (noBudget.isSelected())
                {
                    budget=0;
                }else {
                    budget=Integer.parseInt(row.getText());
                }
                WritePl writePl = new WritePl();

                String  sTime=startTime.getText()+" "+combo1.getValue();
                String  eTime=endTime.getText()+" "+combo2.getValue();
                writePl.variablesSet(plName.getText(),localDate,eventName.getText(),eventAgenda.getText(),
                        eventAddText.getText(),signature,eventVenue.getText(),sTime,eTime,eventObjective.getText(),budget,PLGenerated,errorText);

            }
        }catch (Exception e){
            errorText.setText("You Must Fill all The Field");
        }
    }

    public void budget(ActionEvent event){

        row.setEditable(true);

    }
    public void noBudget(ActionEvent event){
        row.setText("");
        row.setEditable(false);

    }
    public void exit(ActionEvent actionEvent) {

        System.exit(0);

    }

}
