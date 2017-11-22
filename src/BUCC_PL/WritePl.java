package BUCC_PL;

import javafx.scene.control.Label;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by Torab on 19-Apr-17.
 */
public class WritePl {
    private XWPFDocument document;
    private FileOutputStream out;
    private String docName;
    private String eventDate;
    private String eventDateWithDay;
    private  String eventName;
    private  String eventAgenda;
    private  String additionalText;
    private  String eventVenue;
    private  String eventObjective;
    private  String startTime;
    private String endTime;
    private  String eventDay;

    public Label success;
    List<List<String>> budgetArray;
    protected void variablesSet(String plName, LocalDate localDate,
                                String eName, String eventAgendas, String eventAdditionText,
                                String eventVenues, String sTime, String eTime,
                                String eventObjectives,  List<List<String>> budget, Label suc) {
        try {
            success=suc;
            docName=plName+".docx";
            document= new XWPFDocument();
            //Write the Document.docs
            out = new FileOutputStream( new File(docName));

            //Day
            eventDay=localDate.getDayOfWeek().toString();
            eventDay=(Character.toUpperCase(eventDay.charAt(0))) + eventDay.substring(1).toLowerCase();


            //Month
            String m=localDate.getMonth().toString();
            String  month=(Character.toUpperCase(m.charAt(0))) + m.substring(1).toLowerCase();


            //Date
            int  dateOfTheEvent=localDate.getDayOfMonth();


            //Year
            int year=localDate.getYear();

            eventDateWithDay=eventDay+","+dayChecker(dateOfTheEvent)+month+","+ year;
            eventDate=dayChecker(dateOfTheEvent)+month+","+ year;
            //  System.out.println(eventDateWithDay);


            eventName=eName;
            eventAgenda=eventAgendas;
            additionalText=eventAdditionText;




            //budget

            budgetArray=budget;

            eventVenue=eventVenues;
            eventObjective=eventObjectives;

            startTime=sTime;
            endTime=eTime;


            write(plName);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    protected  String dayChecker(int checkDate){
        String dat=""+checkDate;
        if (checkDate==1)
        {
            dat=dat+"st";
        }else if (checkDate==2)
        {
            dat=dat+"nd";
        }else if (checkDate==3)
        {
            dat=dat+"rd";
        }else if (checkDate==21)
        {
            dat=dat+"st";
        }else if (checkDate==22)
        {
            dat=dat+"nd";
        }else if (checkDate==23)
        {
            dat=dat+"rd";
        }else {
            dat=dat+"th";
        }



        return dat+" ";
    }



    protected void write(String plName){
        try {

            //first Page

            header();
            intro();
            subject(eventName);
            body(eventName,eventDateWithDay,eventAgenda,additionalText);
            signature();

            //Second Page(Budget)
            budget(budgetArray);

            //third Page Proposal outline

            proposalOutline(eventName,eventDateWithDay,eventVenue,eventObjective);

            detailsSchedule(eventName,eventDate,eventVenue,eventObjective,startTime,endTime,eventDay);


            //Fourth Page Participant Information

            participantInformation();
            //Page Number
            try {
                footer();
            } catch (IOException e) {
                e.printStackTrace();
            }
            document.write(out);
            out.close();
            System.out.println(plName +" written successully");
            success.setText("Pl Generated");

            File file = new File(docName);
            try {
                //Open the file using Desktop class
                Desktop.getDesktop().open(file);
                docName="";
            }catch (IOException exception){
                exception.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




//First Page: HEADER

  /*  private void addImage(){
        try {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run=paragraph.createRun();
            paragraph.setAlignment(ParagraphAlignment.LEFT);

            run.removeBreak();

          *//*  String imgFile= System.getProperty("user.dir")+"\\drawable\\bucc.png";
            System.out.println(imgFile);*//*

            //FileInputStream is = new FileInputStream(System.getProperty("user.dir")+"\\drawable\\bucc.png");
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("bucc.png");
            run.addPicture(is, XWPFDocument.PICTURE_TYPE_PNG,
                    null, Units.toEMU(80.52), Units.toEMU(85.08)); // 107x108 pixels

            run.removeBreak();

            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


    protected void header() {
        try {
            //Editing The HEADER TOP and BOTTOM


            //Write the Document.docs



            CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
            CTPageMar pageMar = sectPr.addNewPgMar();
            pageMar.setLeft(BigInteger.valueOf(1440L));
            pageMar.setTop(BigInteger.valueOf(720L));
            pageMar.setRight(BigInteger.valueOf(1440L));
            pageMar.setBottom(BigInteger.valueOf(720L));

            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run=paragraph.createRun();
            paragraph.setAlignment(ParagraphAlignment.LEFT);
            run.removeBreak();
            run.removeBreak();
            run.removeBreak();
            run.removeBreak();


            InputStream is = this.getClass().getClassLoader().getResourceAsStream("mainBUCC.png");
            run.addPicture(is, XWPFDocument.PICTURE_TYPE_PNG,
                    null, Units.toEMU(80.52), Units.toEMU(85.08)); // 107x108 pixels

            run.removeBreak();
            run.removeBreak();
            run.removeBreak();

            is.close();




            //first line
            paragraph = document.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.CENTER);
            paragraph.setSpacingBefore(0);
            run=paragraph.createRun();

            run.setFontSize(16);
            run.setFontFamily("Times New Roman");
            run.setBold(true);
            run.setUnderline(UnderlinePatterns.SINGLE);
            run.setText( "COVER LETTER OF EVENT");
            run.addBreak();
            run.setText("BRAC UNIVERSITY COMPUTER CLUB (BUCC)");




        } catch (Exception e) {
            e.printStackTrace();
        }
    }



//FIRST PAGE: INTRO


    private void intro() {
        DateFormat dateFormat=new SimpleDateFormat("d");

        Date date = new Date();
        String dat=dateFormat.format(date);
        int checkDate= Integer.parseInt(dat);

        dat= dayChecker(checkDate);

        dateFormat=new SimpleDateFormat("MMMM");
        date = new Date();
        dat=dat+""+dateFormat.format(date)+",";
        //System.out.println(dat);


        dateFormat=new SimpleDateFormat("Y");
        date = new Date();
        dat=dat+" "+dateFormat.format(date);



        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);

        XWPFRun run=paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText(dat);
        run.addBreak();
        run.setText("To");

        run.addBreak();
        run.setText("The Director");


        run.addBreak();
        run.setText("Office of Co-curricular Activities");

        run.addBreak();
        run.setText("BRAC University,");

        run.addBreak();
        run.setText("Mohakhali, Dhaka-1212");

    }

    //FIRST PAGE: SUBJECT PART
    private void subject(String sub) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun run=paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setBold(true);
        run.setText("Subject:");


        run=paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setBold(true);
        run.setUnderline(UnderlinePatterns.SINGLE);
        run.setText(sub);


    }

    //FIRST PAGE BODY

    private void body(String eventName,String date,String agenda,String additionalText) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);

        XWPFRun run=paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");

        run.setText("Respected Sir, ");
        run.addBreak();
        run.setText("BRAC University Computer Club is planning to organize an event named "+eventName+" on "
                +date+"."+
                agenda+"." +additionalText+".Therefore, we need "+eventVenue+"" +
                " for conducting this event.");
        run.addBreak();

        run.setText("Therefore, BRAC University Computer Club (BUCC) requests you to approve this event.") ;
        run.addBreak();
        run.setText("Yours sincerely,");
    }



//FIRST PAGE: PRESIDENT OR VP SIGNATURE


    private void signature() {



        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setBorderBottom(Borders.SINGLE);//for single line border for sirs signature

        paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun run=paragraph.createRun();
        run.setFontSize(12);
        run.setBold(true);
        run.setFontFamily("Times New Roman");

        run.setText(Controller.signatureName+", ID: ");

        run=paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText(Controller.signatureId+", "+Controller.signaturePost+" , BUCC");
        run.addBreak();

        run=paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setBold(true);
        run.setText("Cell: ");


        run=paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText(Controller.signatureMobile+", ");


        run=paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setBold(true);
        run.setText("Email: ");



        run=paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText(Controller.signatureEmail);



        run.addBreak();
        adviserSignature();


    }




    //FIRST PAGE: SIR SIGNATURE

    private void adviserSignature(){
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setBorderBottom(Borders.SINGLE);


        paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun run=paragraph.createRun();
        run.setFontSize(12);
        run.setBold(true);
        run.setFontFamily("Times New Roman");


        run.setText("Advisor of the Club");
        run.addBreak();
        run.setText("Annajiat Alim Rasel");
        run.addBreak();


        run=paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText("Lecturer III, CSE Department,");
        run.addBreak();

        run=paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setBold(true);
        run.setText("RanksTel: ");


        run=paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText("+8809617445118 ");


        run=paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setBold(true);
        run.setText("Cell: ");



        run=paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText("01711935759 ");
        run.addBreak();


        run=paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setBold(true);
        run.setText("Email: ");



        run=paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText("annajiat@bracu.ac.bd, annajiat@gmail.com");
        run.addBreak();

        paragraph = document.createParagraph();
        paragraph.setBorderBottom(Borders.SINGLE);
        paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        run=paragraph.createRun();
        run.setFontSize(12);
        run.setBold(true);
        run.setFontFamily("Times New Roman");
        run.setText("Director of Office of Co-Curricular Activities");
    }


//:: :: PAGE TWO: BUDGET

    //This section is for Budget Page oF the Pl
    private void budget(  List<List<String>> budgetArray) {



        XWPFParagraph paragraph = document.createParagraph();

        //Creating new Page/second for the doc

        XWPFRun run=paragraph.createRun();
        run.addBreak(BreakType.PAGE);



        run=paragraph.createRun();
        run.setUnderline(UnderlinePatterns.SINGLE);
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        run.setFontSize(16);
        run.setBold(true);

        run.setFontFamily("Times New Roman");
        run.setText("BUDGET");
        run.addBreak();
        if (budgetArray.isEmpty())
        {
            run.addBreak();
            run.addBreak();
            run.addBreak();
            run.addBreak();
            run.addBreak();
            run.addBreak();
            run=paragraph.createRun();
            run.setFontSize(12);
            run.setFontFamily("Times New Roman");
            run.setText("No budget");

        }
        else {
            //create table
            int c=budgetArray.size()+2;
            XWPFTable table = document.createTable(c,7);
            run.setFontSize(12);
            run.setBold(true);

            formatTableText(table,0,"Category");
            formatTableText(table,1,"SL");
            formatTableText(table,2,"Item");
            formatTableText(table,3,"Quantity");
            formatTableText(table,4,"Rate(Taka)");
            formatTableText(table,5,"Total");
            formatTableText(table,6,"Allocated Budget");

            int row=0;
            for(int x = 0;x < table.getNumberOfRows(); x++){

                int col=0;
                XWPFTableRow tableRow = table.getRow(x);
                int numberOfCell = tableRow.getTableCells().size();
                if(x==c-1)
                {
                    XWPFTableCell cell = tableRow.getCell(4);
                    cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(3000));
                    //  System.out.println(row+"c:"+col);
                    cell.setText("Total");

                }
                for(int y = 0; y < numberOfCell ; y++){

                    if(y==1 ){
                        if (x==c-1){

                        }
                        else if (!(x==0)) {
                            XWPFTableCell cell = tableRow.getCell(y);
                            cell.setText("" + (row+1));
                        } else {

                        }
                    }
                    else if (!(y==6))
                    {
                        if (x==c-1){

                        }
                        else if (!(x==0)){

                            XWPFTableCell cell = tableRow.getCell(y);
                            cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(3000));
                            //  System.out.println(row+"c:"+col);
                            cell.setText(""+budgetArray.get(row).get(col));
                            col++;

                        } else {

                        }
                    }
                }
                if (!(x==0)){
                    row++;
                }
            }
        }
        paragraph = document.createParagraph();
        run=paragraph.createRun();
        run.addBreak();
        run.addBreak();
        run.addBreak();
        run.addBreak();
        run.addBreak();
        run.addBreak();

        adviserSignature();

    }


    //:: :: PAGE THREE - PROPOSAL OUTLINE AND DETAILS SCHEDULE

    private void proposalOutline(String eventName, String eventDateWithDay,
                                 String eventVenue, String eventObjective) {



        XWPFParagraph paragraph = document.createParagraph();

        //Creating new Page/third for the doc
        XWPFRun run = paragraph.createRun();
        run.addBreak(BreakType.PAGE);
        run.addBreak();
        run.addBreak();


//PROPOSAL OUTLINE AND EVENT NAME
        run = paragraph.createRun();
        run.setUnderline(UnderlinePatterns.SINGLE);
        run.setFontSize(18);
        run.setBold(true);
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        run.setFontFamily("Times New Roman");
        run.setText("PROPOSAL OUTLINE");
        run.addBreak();
        run.setText("Event: " + eventName);


//Club/Forum Name :

        XWPFTable table = document.createTable(5,3);/*
        table.getRow(0).getCell(0).setText("CLUB/Forum Name");
        table.getRow(0).getCell(1).setText(": ");
        table.getRow(0).getCell(2).setText("BRAC University Computer Club (BUCC)");
*/
        XWPFRun runs = table.getRow(0).getCell(0).addParagraph().createRun();
        runs.setBold(true);
        runs.setFontFamily("Times New Roman");
        runs.setFontSize(12);
        runs.setText("CLUB/Forum Name");
        table.getRow(0).getCell(0).removeParagraph(0);//This line deletes blank paraghaph

        table.getRow(0).getCell(1).setText(": ");

        runs = table.getRow(0).getCell(2).addParagraph().createRun();
        runs.setFontFamily("Times New Roman");
        runs.setFontSize(12);
        runs.setText("BRAC University Computer Club (BUCC)");
        table.getRow(0).getCell(2).removeParagraph(0);

        /*
        table.getRow(1).getCell(0).setText("Main Organizer/Organizing Committee");
        table.getRow(1).getCell(1).setText(": ");
        table.getRow(1).getCell(2).setText("BRAC University Computer Club (BUCC) ");

*/

        runs = table.getRow(1).getCell(0).addParagraph().createRun();
        runs.setBold(true);
        runs.setFontFamily("Times New Roman");
        runs.setFontSize(12);
        runs.setText("Main Organizer/Organizing Committee");
        table.getRow(1).getCell(0).removeParagraph(0);//This line deletes blank paraghaph

        table.getRow(1).getCell(1).setText(": ");

        runs = table.getRow(1).getCell(2).addParagraph().createRun();
        runs.setFontFamily("Times New Roman");
        runs.setFontSize(12);
        runs.setText("BRAC University Computer Club (BUCC)");
        table.getRow(1).getCell(2).removeParagraph(0);//This line deletes blank paraghaph




        /*

        table.getRow(2).getCell(0).setText("Place");
        table.getRow(2).getCell(1).setText(": ");
        table.getRow(2).getCell(2).setText(""+eventVenue);

*/



        runs = table.getRow(2).getCell(0).addParagraph().createRun();
        runs.setBold(true);
        runs.setFontFamily("Times New Roman");
        runs.setFontSize(12);
        runs.setText("Place");
        table.getRow(2).getCell(0).removeParagraph(0);//This line deletes blank paraghaph

        table.getRow(2).getCell(1).setText(": ");

        runs = table.getRow(2).getCell(2).addParagraph().createRun();
        runs.setFontFamily("Times New Roman");
        runs.setFontSize(12);
        runs.setText(""+eventVenue);
        table.getRow(2).getCell(2).removeParagraph(0);//This line deletes blank paraghaph


        /*




        table.getRow(3).getCell(0).setText("Date");
        table.getRow(3).getCell(1).setText(": ");
        table.getRow(3).getCell(2).setText(""+eventDateWithDay);

*/


        runs = table.getRow(3).getCell(0).addParagraph().createRun();
        runs.setBold(true);
        runs.setFontFamily("Times New Roman");
        runs.setFontSize(12);
        runs.setText("Date");
        table.getRow(3).getCell(0).removeParagraph(0);//This line deletes blank paraghaph

        table.getRow(3).getCell(1).setText(": ");

        runs = table.getRow(3).getCell(2).addParagraph().createRun();
        runs.setFontFamily("Times New Roman");
        runs.setFontSize(12);
        runs.setText(""+eventDateWithDay);
        table.getRow(3).getCell(2).removeParagraph(0);//This line deletes blank paraghaph

        /*
        table.getRow(4).getCell(0).setText("Objective");
        table.getRow(4).getCell(1).setText(": ");
        table.getRow(4).getCell(2).setText(""+eventObjective);
        */


        runs = table.getRow(4).getCell(0).addParagraph().createRun();
        runs.setBold(true);
        runs.setFontFamily("Times New Roman");
        runs.setFontSize(12);
        runs.setText("Objective");
        table.getRow(4).getCell(0).removeParagraph(0);//This line deletes blank paraghaph

        table.getRow(4).getCell(1).setText(": ");

        runs = table.getRow(4).getCell(2).addParagraph().createRun();
        runs.setFontFamily("Times New Roman");
        runs.setFontSize(12);
        runs.setText(""+eventObjective);
        table.getRow(4).getCell(2).removeParagraph(0);//This line deletes blank paraghaph

        table.getCTTbl().getTblPr().unsetTblBorders();




        for(int x = 0;x < table.getNumberOfRows(); x++){

            XWPFTableRow row = table.getRow(x);

            XWPFTableCell cell = row.getCell(0);
            cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(5000));

            cell = row.getCell(1);
            cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(300));
            cell = row.getCell(2);
            cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(5900));

        }

        run.addBreak();

    }




    //Creating new Page/third for the doc
    private void detailsSchedule(String eventName, String eventDateWithDay,
                                 String eventVenue, String eventObjective, String startTime, String endTime,String eventDay){
        //Details Schedule
        XWPFParagraph paragraph ;


        XWPFRun run ;

        paragraph = document.createParagraph();
        run=paragraph.createRun();
        run.addBreak();
        run.addBreak();
        run.setUnderline(UnderlinePatterns.SINGLE);
        run.setFontSize(18);
        run.setBold(true);
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        run.setFontFamily("Times New Roman");
        run.setText("DETAILS SCHEDULE");
        run.addBreak();
        run.addBreak();



        //EVENT NAME
        paragraph = document.createParagraph();
        run=paragraph.createRun();
        run.setFontSize(14);
        run.setBold(true);
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        run.setFontFamily("Times New Roman");
        run.setText("Event :");

        run=paragraph.createRun();
        run.setFontSize(14);
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        run.setFontFamily("Times New Roman");
        run.setText(" "+eventName);
        run.addBreak();
        run.addBreak();




//EVENT DAY

        run=paragraph.createRun();
        run.setFontSize(14);
        run.setBold(true);
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        run.setFontFamily("Times New Roman");
        run.setText("Day :");

        run=paragraph.createRun();
        run.setFontSize(14);
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        run.setFontFamily("Times New Roman");
        run.setText(" "+eventDay);
        run.addBreak();
        run.addBreak();


//EVENT DATE
        run=paragraph.createRun();
        run.setFontSize(14);
        run.setBold(true);
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        run.setFontFamily("Times New Roman");
        run.setText("Date :");

        run=paragraph.createRun();
        run.setFontSize(14);
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        run.setFontFamily("Times New Roman");
        run.setText(" "+eventDateWithDay);
        run.addBreak();
        run.addBreak();


        //EVENT START TIME

        run=paragraph.createRun();
        run.setFontSize(14);
        run.setBold(true);
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        run.setFontFamily("Times New Roman");
        run.setText("Start Time :");

        run=paragraph.createRun();
        run.setFontSize(14);
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        run.setFontFamily("Times New Roman");
        run.setText(" "+startTime);
        run.addBreak();
        run.addBreak();




        //EVENT END TIME

        run=paragraph.createRun();
        run.setFontSize(14);
        run.setBold(true);
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        run.setFontFamily("Times New Roman");
        run.setText("End Time :");


        run=paragraph.createRun();
        run.setFontSize(14);
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        run.setFontFamily("Times New Roman");
        run.setText(" "+endTime);
        run.addBreak();
        run.addBreak();

        //EVENT VENUE
        run=paragraph.createRun();
        run.setFontSize(14);
        run.setBold(true);
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        run.setFontFamily("Times New Roman");
        run.setText("Venue :");

        run=paragraph.createRun();
        run.setFontSize(14);
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        run.setFontFamily("Times New Roman");
        run.setText(" "+eventVenue);
        run.addBreak();
        run.addBreak();


    }


    //:: :: PAGE 4 PARTICIPATION LIST
    private void participantInformation() {
        XWPFParagraph paragraph = document.createParagraph();

        //Creating new Page/third for the doc
        XWPFRun run=paragraph.createRun();
        run.addBreak(BreakType.PAGE);


        run.setUnderline(UnderlinePatterns.SINGLE);
        run.setFontSize(18);
        run.setBold(true);
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        run.setFontFamily("Times New Roman");
        run.setText("PARTICIPANT INFORMATION");
        run.addBreak();



        XWPFTable table =document.createTable(20,5);

        formatTableText(table,0,"SL");
        formatTableText(table,1,"Name");
        formatTableText(table,2,"ID");
        formatTableText(table,3,"Department Or Designation");
        formatTableText(table,4,"Institution");


//This is line text is fixing the length of the cell of the Column

        for(int x = 0;x < table.getNumberOfRows(); x++){
            XWPFTableRow row = table.getRow(x);
            int numberOfCell = row.getTableCells().size();
            for(int y = 1; y < numberOfCell ; y++){
                XWPFTableCell cell = row.getCell(y);

                cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2500));
            }
        }


    }
    private void formatTableText(XWPFTable table, int col, String text){
        XWPFTableRow rowOne = table.getRow(0);
        XWPFParagraph para = rowOne.getCell(col).addParagraph();

        para.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run=para.createRun();
        run.setFontFamily("times new roman");
        run.setFontSize(10);
        run.setText(text);

        run.setBold(false);

    }







    private void footer() throws IOException {


        CTP ctp = CTP.Factory.newInstance();
        CTR ctr = ctp.addNewR();
        CTRPr rpr = ctr.addNewRPr();
        CTText textt = ctr.addNewT();
        textt.setStringValue( "Page : " );


        /*CTP ctp = CTP.Factory.newInstance();
        */

        //this add page number incremental
        ctp.addNewR().addNewPgNum();


        XWPFParagraph codePara = new XWPFParagraph(ctp, document);
        XWPFParagraph[] paragraphs = new XWPFParagraph[1];
        paragraphs[0] = codePara;
        //position of number
        codePara.setAlignment(ParagraphAlignment.RIGHT);

        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        // textt.setStringValue( " out of 4" );

        try {
            XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(document, sectPr);
            headerFooterPolicy.createFooter(STHdrFtr.DEFAULT, paragraphs);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }












/*
// add style (s.th.)
        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);


// add everything from the footerXXX.xml you need
        CTP ctpFooter = CTP.Factory.newInstance();
        CTR ctrFooter = ctpFooter.addNewR();
        CTText ctFooter = ctrFooter.addNewT();
        String footerText = "This is footer";
        ctFooter.setStringValue(footerText);
        XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooter, document);
        XWPFParagraph[] parsFooter = new XWPFParagraph[1];
        parsFooter[0] = footerParagraph;
        policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsFooter);*/
    }



}
