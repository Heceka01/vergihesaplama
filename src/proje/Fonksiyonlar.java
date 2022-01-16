/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package proje;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 *
 * @author Vatan-OEM
 */
public class Fonksiyonlar extends Application{

    public void buttoncss(Button home,Button returnn){
        final String IDLE_BUTTON_STYLE = "-fx-background-color: transparent;-fx-border-color:red";
        final String HOVERED_BUTTON_STYLE = "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;";
        ImageView geridon = new ImageView(new Image("/logo/return.png"));
        ImageView anaekran = new ImageView(new Image("/logo/home.png"));
        anaekran.setFitHeight(20);
        anaekran.setFitWidth(20);
        geridon.setFitHeight(20);
        geridon.setFitWidth(20);
        home.setGraphic(anaekran);
        returnn.setGraphic(geridon);
        home.setStyle(IDLE_BUTTON_STYLE);
        home.setOnMouseEntered(e -> home.setStyle(HOVERED_BUTTON_STYLE));
        home.setOnMouseExited(e -> home.setStyle(IDLE_BUTTON_STYLE));
        returnn.setStyle(IDLE_BUTTON_STYLE);
        returnn.setOnMouseEntered(e -> returnn.setStyle(HOVERED_BUTTON_STYLE));
        returnn.setOnMouseExited(e -> returnn.setStyle(IDLE_BUTTON_STYLE));
    }
    public void GirisSayfasi(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Giris.fxml"));     
        Scene scene = new Scene(root);   
        stage.setTitle("VERGI YONETIM SISTEMI");
        stage.getIcons().add(new Image("/logo/vergilogo.png"));
        stage.setScene(scene);
        stage.show();
    }
    
    public void KayitOlSayfasi(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("KayitOl.fxml"));     
        Scene scene = new Scene(root);   
        stage.setTitle("VERGI YONETIM SISTEMI");
        stage.getIcons().add(new Image("/logo/vergilogo.png"));
        stage.setScene(scene);
        stage.show();
    }
    
   
    public void VergiGuncelleme(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("VergiGuncelleme.fxml"));     
        Scene scene = new Scene(root);   
        stage.setTitle("VERGI YONETIM SISTEMI");
        stage.getIcons().add(new Image("/logo/vergilogo.png"));
        stage.setScene(scene);
        stage.show();
    }
    public void VergiHesaplatma(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("VergiHesaplatma.fxml"));     
        Scene scene = new Scene(root);   
        stage.setTitle("VERGI YONETIM SISTEMI");
        stage.getIcons().add(new Image("/logo/vergilogo.png"));
        stage.setScene(scene);
        stage.show();
    }
    public void VergiListeleme(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("VergiListeleme.fxml"));     
        Scene scene = new Scene(root);   
        stage.setTitle("VERGI YONETIM SISTEMI");
        stage.getIcons().add(new Image("/logo/vergilogo.png"));
        stage.setScene(scene);
        stage.show();
    }
    public void GeriDon(Stage stage,String oncekisayfa) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(oncekisayfa+".fxml"));     
        Scene scene = new Scene(root);   
        stage.setTitle("VERGI YONETIM SISTEMI");
        stage.getIcons().add(new Image("/logo/vergilogo.png"));
        stage.setScene(scene);
        stage.show();
    }
    public void StageKapa(Button btn){
        Stage kapancak = (Stage) btn.getScene().getWindow();
        kapancak.close();
    }
    public void StageKapa(TextField tf){
        Stage kapancak = (Stage) tf.getScene().getWindow();
        kapancak.close();
    }
    public void KDVHesapla(TextField tf1,TextField tf2,RadioButton r1,RadioButton r8,RadioButton r18){
            float tutar=Float.valueOf(tf1.getText());
            if(r1.isSelected()){
                tf2.setText(String.valueOf(tutar/100)+" ₺");
            }
            if(r8.isSelected()){
                tf2.setText(String.valueOf(tutar*8/100)+" ₺");
            }
            if(r18.isSelected()){
                tf2.setText(String.valueOf(tutar*18/100)+" ₺");
            }                   
    }
    public void GelirHesapla(TextField tf1,TextField tf2,TextField tf3){
        float tutar=Float.valueOf(tf1.getText());
        if(tutar<=32000 && 0<=tutar){
            float vergi=tutar*15/100;
            tf2.setText(String.valueOf(vergi)+" ₺");
            tf3.setText(String.valueOf(tutar-vergi)+" ₺");
        }
        if(tutar<=70000 && 32000<tutar){
            float vergi=4800;
            vergi+=(tutar-32000)*20/100;
            tf2.setText(String.valueOf(vergi)+" ₺");
            tf3.setText(String.valueOf(tutar-vergi)+" ₺");
        }
        if(tutar<=250000 && 70000<tutar){
            float vergi=12400;
            vergi+=(tutar-70000)*27/100;
            tf2.setText(String.valueOf(vergi)+" ₺");
            tf3.setText(String.valueOf(tutar-vergi)+" ₺");
        }
        if(tutar<=880000 && 250000<tutar){
            float vergi=61000;
            vergi+=(tutar-250000)*35/100;
            tf2.setText(String.valueOf(vergi)+" ₺");
            tf3.setText(String.valueOf(tutar-vergi)+" ₺");
        }
        if(880000<tutar){
            float vergi=281500;
            vergi+=(tutar-880000)*40/100;
            tf2.setText(String.valueOf(vergi)+" ₺");
            tf3.setText(String.valueOf(tutar-vergi)+" ₺");
        }
        
    }
    public void EmlakHesapla(TextField tf1,ComboBox il,ComboBox gayrimenkul,TextField tf2){
        float tutar=Float.valueOf(tf1.getText());
        float yuzde=0;
        float gayrimenkulyuzde=0;
        if(il.getValue()=="Büyükşehir"){
            yuzde= 2;
        }
        if(il.getValue()=="Büyükşehir Değil"){
            yuzde= 1;
        }
        if(gayrimenkul.getValue()=="Konut" || gayrimenkul.getValue()=="Arazi")
            gayrimenkulyuzde=(float) 0.2;
        if(gayrimenkul.getValue()=="İşyeri")
            gayrimenkulyuzde=(float) 0.4;
        if(gayrimenkul.getValue()=="Arsa")
            gayrimenkulyuzde=(float) 0.6;
        tf2.setText(String.valueOf(tutar*yuzde*gayrimenkulyuzde/200)+" ₺");
        
    }
    public void VergiGosterKapat(String newValue,GridPane kdvgrid,GridPane gelirgrid,GridPane kurumlargrid,GridPane otvgrid,GridPane emlakgrid){
        if(newValue!="KDV")
        kdvgrid.setVisible(false);
        if(newValue=="KDV")
        kdvgrid.setVisible(true);
        if(newValue!="Gelir Vergisi")
        gelirgrid.setVisible(false);
        if(newValue=="Gelir Vergisi")
        gelirgrid.setVisible(true);
        if(newValue!="Kurumlar Vergisi")
        kurumlargrid.setVisible(false);
        if(newValue=="Kurumlar Vergisi")
        kurumlargrid.setVisible(true);
        if(newValue!="ÖTV")
        otvgrid.setVisible(false);
        if(newValue=="ÖTV")
        otvgrid.setVisible(true);
        if(newValue!="Emlak Vergisi")
        emlakgrid.setVisible(false);
        if(newValue=="Emlak Vergisi")
        emlakgrid.setVisible(true);
                
    }
    public void OTVHesapla(ComboBox aractipi,ComboBox motorhacmi,TextField tf1,TextField tf2,Label lbl,TextField otvkdvtutar,TextField otvtoplamtutar){
        float tutar=Float.valueOf(tf1.getText());
        if(aractipi.getValue()=="Binek otomobil ve arazi taşıtları"){
            motorhacmi.setVisible(true);
            lbl.setVisible(true);
            if(motorhacmi.getValue()=="1600 ve aşağısı"){
                if(tutar<=92000){
                    tf2.setText(String.valueOf(tutar*45/100));
                    float vergilitoplam=Float.valueOf(tf2.getText())+tutar;
                    otvkdvtutar.setText(String.valueOf(vergilitoplam*18/100));
                    float kdv=Float.valueOf(otvkdvtutar.getText());
                    otvtoplamtutar.setText(String.valueOf(kdv+vergilitoplam)+" ₺");
                }
                    
                if(92000<tutar && tutar<=150000){
                    tf2.setText(String.valueOf(tutar*50/100));
                    float vergilitoplam=Float.valueOf(tf2.getText())+tutar;
                    otvkdvtutar.setText(String.valueOf(vergilitoplam*18/100));
                    float kdv=Float.valueOf(otvkdvtutar.getText());
                    otvtoplamtutar.setText(String.valueOf(kdv+vergilitoplam)+" ₺");
                }
                    
                if(150000<tutar){
                    tf2.setText(String.valueOf(tutar*80/100));
                    float vergilitoplam=Float.valueOf(tf2.getText())+tutar;
                    otvkdvtutar.setText(String.valueOf(vergilitoplam*18/100));
                    float kdv=Float.valueOf(otvkdvtutar.getText());
                    otvtoplamtutar.setText(String.valueOf(kdv+vergilitoplam)+" ₺");
                }
                    
            }
            if(motorhacmi.getValue()=="1601-2000"){
                if(tutar<170000){
                    tf2.setText(String.valueOf(tutar*130/100));
                    float vergilitoplam=Float.valueOf(tf2.getText())+tutar;
                    otvkdvtutar.setText(String.valueOf(vergilitoplam*18/100));
                    float kdv=Float.valueOf(otvkdvtutar.getText());
                    otvtoplamtutar.setText(String.valueOf(kdv+vergilitoplam)+" ₺");
                }
                    
                if(tutar>=170000){
                    tf2.setText(String.valueOf(tutar*150/100));
                    float vergilitoplam=Float.valueOf(tf2.getText())+tutar;
                    otvkdvtutar.setText(String.valueOf(vergilitoplam*18/100));
                    float kdv=Float.valueOf(otvkdvtutar.getText());
                    otvtoplamtutar.setText(String.valueOf(kdv+vergilitoplam)+" ₺");
                }
                    
            }
            if(motorhacmi.getValue()=="2001 ve üstü"){
                tf2.setText(String.valueOf(tutar*220/100));
                float vergilitoplam=Float.valueOf(tf2.getText())+tutar;
                otvkdvtutar.setText(String.valueOf(vergilitoplam*18/100));
                float kdv=Float.valueOf(otvkdvtutar.getText());
                otvtoplamtutar.setText(String.valueOf(kdv+vergilitoplam)+" ₺");
            }
        }
        if(aractipi.getValue()=="Motosikletler"){
            motorhacmi.setVisible(true);
            lbl.setVisible(true);
            if(motorhacmi.getValue()=="250 ve altı"){
                tf2.setText(String.valueOf(tutar*8/100));
                float vergilitoplam=Float.valueOf(tf2.getText())+tutar;
                otvkdvtutar.setText(String.valueOf(vergilitoplam*18/100));
                float kdv=Float.valueOf(otvkdvtutar.getText());
                otvtoplamtutar.setText(String.valueOf(kdv+vergilitoplam)+" ₺");
            }
            if(motorhacmi.getValue()=="250 üstü"){
                tf2.setText(String.valueOf(tutar*37/100));
                float vergilitoplam=Float.valueOf(tf2.getText())+tutar;
                otvkdvtutar.setText(String.valueOf(vergilitoplam*18/100));
                float kdv=Float.valueOf(otvkdvtutar.getText());
                otvtoplamtutar.setText(String.valueOf(kdv+vergilitoplam)+" ₺");
            }
        }
        if(aractipi.getValue()=="Minibüs" || aractipi.getValue()=="Otobüs"){
            motorhacmi.setVisible(false);
            lbl.setVisible(false);
            if(aractipi.getValue()=="Minibüs" ){
                tf2.setText(String.valueOf(tutar*9/100));
                float vergilitoplam=Float.valueOf(tf2.getText())+tutar;
                otvkdvtutar.setText(String.valueOf(vergilitoplam*18/100));
                float kdv=Float.valueOf(otvkdvtutar.getText());
                otvtoplamtutar.setText(String.valueOf(kdv+vergilitoplam)+" ₺");
            }
              
            
            if(aractipi.getValue()=="Otobüs"){
                tf2.setText(String.valueOf(tutar*1/100));
                float vergilitoplam=Float.valueOf(tf2.getText())+tutar;
                otvkdvtutar.setText(String.valueOf(vergilitoplam*18/100));
                float kdv=Float.valueOf(otvkdvtutar.getText());
                otvtoplamtutar.setText(String.valueOf(kdv+vergilitoplam)+" ₺");
            }
                
        }
    }
    public void KurumlarHesapla(TextField tf1,TextField tf2){
        float tutar=Float.valueOf(tf1.getText());
        tutar=tutar*23/100;
        tf2.setText(String.valueOf(tutar)+" ₺");
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
