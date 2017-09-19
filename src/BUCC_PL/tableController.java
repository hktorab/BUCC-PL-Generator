package BUCC_PL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class tableController implements Initializable {



    //Table Input ID

    @FXML
    TextField textFieldCategory;
    @FXML
    TextField textFieldItem;
    @FXML
    TextField textFieldQuantity;
    @FXML
    TextField textFieldRate;
    @FXML
    TextField textFieldCost;



    //table initialize

    public TableView <ProductClass> table;

    public TableColumn<ProductClass,String> tableColumnCategory;

    public TableColumn<ProductClass,String> tableColumnItem;

    public TableColumn<ProductClass,String> tableColumnQuantity;

    public TableColumn<ProductClass,String> tableColumnRate;

    public TableColumn<ProductClass,String> tableColumnCost;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tableColumnCategory.setCellValueFactory(new PropertyValueFactory<>("cat"));
        tableColumnItem.setCellValueFactory(new PropertyValueFactory<>("item"));
        tableColumnQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tableColumnRate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        tableColumnCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        table.setItems(getItem());



        table.setEditable(true);
        tableColumnCategory.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnItem.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnRate.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnQuantity.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnCost.setCellFactory(TextFieldTableCell.forTableColumn());





//validating Edit Calculation


        //TextValidation

        //quantityField
        textFieldQuantity.textProperty().addListener((obs, oldText, newText) -> {
            if (!newText.matches("\\d*")) {
                textFieldQuantity.setText(newText.replaceAll("[^\\d]", ""));
            }

            CostCalculation();



        });

        //RateField


        textFieldRate.textProperty().addListener((obs, oldText, newText) -> {
            if (!newText.matches("\\d*")) {
                textFieldRate.setText(newText.replaceAll("[^\\d]", ""));
            }
            CostCalculation();

        });



        textFieldCost.setDisable(true);

    }



    //this method will collect data from rate and quantity and it will multiply it and save it
    void CostCalculation (){
        if (!(textFieldQuantity.getText().isEmpty() || textFieldRate.getText().isEmpty())){

            double tk=Double.parseDouble(textFieldQuantity.getText())*Double.parseDouble(textFieldRate.getText());

            textFieldCost.setText(""+tk);
        }
    }



    public ObservableList<ProductClass>  getItem()
    {
        ObservableList<ProductClass> item = FXCollections.observableArrayList();

        return item;
    }





    public void AddProductBudget(ActionEvent actionEvent) {
        ProductClass newProduct = new ProductClass();


        newProduct.setCat(textFieldCategory.getText());
        newProduct.setItem(textFieldItem.getText());
        newProduct.setQuantity(textFieldQuantity.getText());
        newProduct.setRate(textFieldRate.getText());
        newProduct.setCost(textFieldCost.getText());
        table.getItems().add(newProduct);

        textFieldCategory.clear();
        textFieldItem.clear();
        textFieldQuantity.clear();
        textFieldRate.clear();
        textFieldCost.clear();
    }

    public void buttonRemove(ActionEvent actionEvent) {
        ObservableList <ProductClass> productSelected,allProducts;
        allProducts=table.getItems();
        productSelected=table.getSelectionModel().getSelectedItems();
        productSelected.forEach(allProducts::remove);
    }

    public void buttonDone(ActionEvent actionEvent) {
        ProductClass nameCol;


        List<List<String>> array = new ArrayList<>();



        for (int i = 0; i <table.getItems().size(); i++) {
            nameCol=table.getItems().get(i);
            array.add(new ArrayList<>());
            array.get(i).add(nameCol.cat.get());
            array.get(i).add(""+ nameCol.item.get());
            array.get(i).add(""+nameCol.quantity.get());
            array.get(i).add(""+nameCol.rate.get());
            array.get(i).add(""+nameCol.cost.get());
        }
        Controller.budgetArray=array;
        stage.close();

        //for showing

        /*for (int i = 0; i < array.size(); i++) {
            for (int j = 0; j < array.get(i).size(); j++) {
                System.out.print(array.get(i).get(j)+" ");
            }
            System.out.println();
        }
*/
       /* for (int i = 0; i < 50; i++) {
            System.out.print(".");
        }
        System.out.println();*/
    }

//making the table cell editable

    public void CategoryChangeData(TableColumn.CellEditEvent<ProductClass, String> productClassStringCellEditEvent) {
        ProductClass pp = table.getSelectionModel().getSelectedItem();
        pp.setCat(productClassStringCellEditEvent.getNewValue());
    }

    public void ItemChangeData(TableColumn.CellEditEvent<ProductClass, String> productClassStringCellEditEvent) {
        ProductClass pp = table.getSelectionModel().getSelectedItem();
        pp.setItem(productClassStringCellEditEvent.getNewValue());
    }

    public void QuantityChangeData(TableColumn.CellEditEvent<ProductClass, String> productClassStringCellEditEvent) {
        ProductClass pp = table.getSelectionModel().getSelectedItem();
        pp.setQuantity(productClassStringCellEditEvent.getNewValue());
    }

    public void RateChangeData(TableColumn.CellEditEvent<ProductClass, String> productClassStringCellEditEvent) {
        ProductClass pp = table.getSelectionModel().getSelectedItem();
        pp.setRate(productClassStringCellEditEvent.getNewValue());
    }

    public void Total_CostChangeData(TableColumn.CellEditEvent<ProductClass, String> productClassStringCellEditEvent) {
        ProductClass pp = table.getSelectionModel().getSelectedItem();
        pp.setCost(productClassStringCellEditEvent.getNewValue());
    }


    public void closePlTable(ActionEvent actionEvent) {
        List<List<String>> array = new ArrayList<>();
        Controller.budgetArray=array;
        stage.close();
    }

    static Stage stage;
    static void getStage(Stage s){
        stage=s;

    }




}
