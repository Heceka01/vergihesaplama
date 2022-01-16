/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proje;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Vatan-OEM
 */
public class VergiHesaplatmaController implements Initializable {

    @FXML
    private Label lbl;
    @FXML
    private ComboBox vergiler;
    @FXML
    private ComboBox ilturu;
    @FXML
    private ComboBox gayrimenkulturu;
    @FXML
    private ComboBox aractipi;
    @FXML
    private ComboBox motorhacmi;
    @FXML
    private GridPane kdvgrid;
    @FXML
    private GridPane gelirgrid;
    @FXML
    private GridPane kurumlargrid;
    @FXML
    private GridPane otvgrid;
    @FXML
    private GridPane emlakgrid;
    @FXML
    private TextField kdvhesaplanantutar;
    @FXML
    private TextField kdvtutar;
    @FXML
    private TextField gelirtutar;
    @FXML
    private TextField gelirhesaplanantutar;
    @FXML
    private TextField gelirnet;
    @FXML
    private TextField emlaktutar;
    @FXML
    private TextField emlakhesaplanantutar;
    @FXML
    private TextField otvtutar;
    @FXML
    private TextField otvhesaplanantutar;
    @FXML
    private TextField otvkdvtutar;
    @FXML
    private TextField otvtoplamtutar;
    @FXML
    private TextField kurumlartutar;
    @FXML
    private TextField kurumlarhesaplanantutar;
    @FXML
    private RadioButton kdv1;
    @FXML
    private RadioButton kdv8;
    @FXML
    private RadioButton kdv18;
    @FXML
    private Button returnn;
    @FXML
    private Button home;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        aractipi.getItems().addAll("Binek otomobil ve arazi taşıtları","Motosikletler","Minibüs","Otobüs");
        aractipi.setValue("Binek otomobil ve arazi taşıtları");
        motorhacmi.getItems().addAll("1600 ve aşağısı","1601-2000","2001 ve üstü");
        motorhacmi.setValue("1600 ve aşağısı");
        vergiler.getItems().addAll("KDV","Gelir Vergisi","Kurumlar Vergisi","ÖTV","Emlak Vergisi");
        vergiler.setValue("KDV");
        ilturu.getItems().addAll("Büyükşehir","Büyükşehir Değil");
        ilturu.setValue("Büyükşehir");
        gayrimenkulturu.getItems().addAll("Konut","İşyeri","Arsa","Arazi");
        gayrimenkulturu.setValue("Konut");
        Fonksiyonlar fonk=new Fonksiyonlar();
        final ToggleGroup group = new ToggleGroup();
        kdv1.setToggleGroup(group);
        kdv1.setSelected(true);
        kdv8.setToggleGroup(group);
        kdv18.setToggleGroup(group);
        fonk.buttoncss(home, returnn);
        vergiler.valueProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if(newValue != null){
            fonk.VergiGosterKapat(newValue, kdvgrid, gelirgrid, kurumlargrid, otvgrid, emlakgrid);
        }
            }
        });
        kdvtutar.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, 
        String newValue) {
        if (!newValue.matches("\\d*")) {
            kdvtutar.setText(newValue.replaceAll("[^\\d]", ""));
        }
        }
        });
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() 
        {
            @Override
            public void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n)
            {
                RadioButton rb = (RadioButton)group.getSelectedToggle();
                String deger=rb.getText().substring(1);
                if (rb != null) {
                    kdvhesaplanantutar.setText(String.valueOf(Float.valueOf(deger)*Float.valueOf(kdvtutar.getText())/100)+" ₺");   
                }
            }
        });
        kdvtutar.textProperty().addListener((observable, oldValue, newValue) -> {
            fonk.KDVHesapla(kdvtutar, kdvhesaplanantutar, kdv1, kdv8, kdv8);
        });
        
        gelirtutar.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, 
        String newValue) {
        if (!newValue.matches("\\d*")) {
            gelirtutar.setText(newValue.replaceAll("[^\\d]", ""));
        }
        }
        });
        gelirtutar.textProperty().addListener((observable, oldValue, newValue) -> {
            fonk.GelirHesapla(gelirtutar, gelirhesaplanantutar, gelirnet);
        });
        
        emlaktutar.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, 
        String newValue) {
        if (!newValue.matches("\\d*")) {
            emlaktutar.setText(newValue.replaceAll("[^\\d]", ""));
        }
        }
        });
        emlaktutar.textProperty().addListener((observable, oldValue, newValue) -> {
            fonk.EmlakHesapla(emlaktutar, ilturu, gayrimenkulturu, emlakhesaplanantutar);
        });
        gayrimenkulturu.valueProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if(newValue != null){
            fonk.EmlakHesapla(emlaktutar, ilturu, gayrimenkulturu, emlakhesaplanantutar);
        }
            }
        });
        ilturu.valueProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if(newValue != null){
            fonk.EmlakHesapla(emlaktutar, ilturu, gayrimenkulturu, emlakhesaplanantutar);
        }
            }
        });
        
        otvtutar.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, 
        String newValue) {
        if (!newValue.matches("\\d*")) {
            otvtutar.setText(newValue.replaceAll("[^\\d]", ""));
        }
        }
        });
        otvtutar.textProperty().addListener((observable, oldValue, newValue) -> {
            fonk.OTVHesapla(aractipi, motorhacmi, otvtutar, otvhesaplanantutar, lbl,otvkdvtutar,otvtoplamtutar);
        });
        aractipi.valueProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if(newValue != null){
            fonk.OTVHesapla(aractipi, motorhacmi, otvtutar, otvhesaplanantutar, lbl,otvkdvtutar,otvtoplamtutar);
            if(newValue=="Binek otomobil ve arazi taşıtları"){
                motorhacmi.getItems().clear();
                motorhacmi.getItems().addAll("1600 ve aşağısı","1601-2000","2001 ve üstü");
                motorhacmi.setValue("1600 ve aşağısı");
            }
            if(newValue=="Motosikletler")
            {
                motorhacmi.getItems().clear();
                motorhacmi.getItems().addAll("250 ve altı","250 üstü");
                motorhacmi.setValue("250 ve altı");
            }
           }
          }
        });
        motorhacmi.valueProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if(newValue != null){
            fonk.OTVHesapla(aractipi, motorhacmi, otvtutar, otvhesaplanantutar, lbl,otvkdvtutar,otvtoplamtutar);
        }
            }
        });
        kurumlartutar.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, 
        String newValue) {
        if (!newValue.matches("\\d*")) {
            kurumlartutar.setText(newValue.replaceAll("[^\\d]", ""));
        }
        }
        });
        kurumlartutar.textProperty().addListener((observable, oldValue, newValue) -> {
            fonk.KurumlarHesapla(kurumlartutar, kurumlarhesaplanantutar);
        });
        
        
        
        
        
        home.setOnAction(e -> {
            try {
                fonk.StageKapa(home);
                Stage stage=new Stage();
                fonk.GirisSayfasi(stage);
            } catch (Exception ex) {
                Logger.getLogger(GirisController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        returnn.setOnAction(e -> {
            try {
                fonk.StageKapa(returnn);
                Stage stage=new Stage();
                fonk.GirisSayfasi(stage);
            } catch (Exception ex) {
                Logger.getLogger(GirisController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
    
}
