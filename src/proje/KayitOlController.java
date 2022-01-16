/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proje;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Vatan-OEM
 */

public class KayitOlController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button home,returnn,kayitol;
    @FXML
    private RadioButton kullanici,yonetici;
    @FXML
    private TextField adsoyad,adres,eposta;
    @FXML
    private PasswordField sifre;
    
    private String Kullanici_turu;
    
    Connection con = null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con=DbConnection.ConnectionDB();
        final ToggleGroup group = new ToggleGroup();
        kullanici.setToggleGroup(group);
        kullanici.setSelected(true);
        yonetici.setToggleGroup(group);
        Fonksiyonlar fonk = new Fonksiyonlar();
        fonk.buttoncss(home, returnn);
        kayitol.setOnAction(e -> {
            if(kullanici.isSelected())
                Kullanici_turu="Kullanici";
            if(yonetici.isSelected())
                Kullanici_turu="Yonetici";
            if(adsoyad.getText().trim().isEmpty() || adres.getText().trim().isEmpty() || eposta.getText().trim().isEmpty()|| sifre.getText().trim().isEmpty()){
            Alert girisalert =new Alert(Alert.AlertType.ERROR,"Boş Alanları Doldurunuz...",ButtonType.APPLY);
            girisalert.setTitle("Kayıt Durumu");
            girisalert.setHeaderText("Boş Alan");
            ((Button) girisalert.getDialogPane().lookupButton(ButtonType.APPLY)).setText("Tamam");
            girisalert.show();
            return;  
            }
            
            
            String sql="INSERT INTO Kayitlar(Adsoyad,Adres,Eposta,Sifre,Kayitturu) VALUES(?,?,?,?,?)";
            try {
                pst=con.prepareStatement(sql);
                pst.setString(1,adsoyad.getText());
                pst.setString(2, adres.getText());
                pst.setString(3, eposta.getText());
                pst.setString(4, sifre.getText());
                pst.setString(5,Kullanici_turu);
                String sql2 = "SELECT Eposta FROM Kayitlar";
                Statement statement2=con.createStatement();
                ResultSet rs2=statement2.executeQuery(sql2);
                while(rs2.next()){
                    if(eposta.getText().equals(rs2.getString("Eposta"))){
                        Alert hataalert =new Alert(Alert.AlertType.INFORMATION,"Girdiğiniz e posta zaten kayıtlıdır.Başka eposta giriniz.",ButtonType.APPLY);
                        hataalert.setTitle("Kayıt Durumu");
                        hataalert.setHeaderText("Kayıt Başarısız");
                        ((Button) hataalert.getDialogPane().lookupButton(ButtonType.APPLY)).setText("Devam Et");
                        hataalert.show();
                        return;
                    }
                        
                }
                pst.executeUpdate();
                Alert girisalert =new Alert(Alert.AlertType.INFORMATION,"Kayıt Olundu.Giriş Ekranına Yönlendiriliyorsunuz...",ButtonType.APPLY);
                girisalert.setTitle("Kayıt Durumu");
                girisalert.setHeaderText("Kayıt Başarılı");
                ((Button) girisalert.getDialogPane().lookupButton(ButtonType.APPLY)).setText("Devam Et");
                girisalert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.APPLY) {
                   try {
                     fonk.StageKapa(kayitol);
                     Stage stage=new Stage();
                     fonk.GirisSayfasi(stage);
                    } catch (Exception ex) {
                          Logger.getLogger(GirisController.class.getName()).log(Level.SEVERE, null, ex);
                    }
              }
             });   
            } catch (Exception ex) {
         }    
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
