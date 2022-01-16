/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package proje;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;

/**
 *
 * @author Vatan-OEM
 */

public class GirisController implements Initializable {
    Connection con = null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    
    @FXML
    private TextField kullaniciadi;
    @FXML
    private PasswordField sifre;
    @FXML
    private Button kayitol,giris;
   
    @Override
    public void initialize(URL url, ResourceBundle rb){
        con=DbConnection.ConnectionDB();
        Fonksiyonlar fonk= new Fonksiyonlar();
        giris.setOnAction(e -> {
            String sql="Select * from Kayitlar Where Eposta LIKE ? AND Sifre LIKE ?;";
            if(kullaniciadi.getText().trim().isEmpty() || sifre.getText().trim().isEmpty()){
                    Alert girisalert =new Alert(Alert.AlertType.ERROR,"Boş Alanları Doldurunuz...",ButtonType.APPLY);
                    girisalert.setTitle("Giriş Durumu");
                    girisalert.setHeaderText("Boş Alan");
                    ((Button) girisalert.getDialogPane().lookupButton(ButtonType.APPLY)).setText("Tamam");
                     girisalert.show();
                     return;
                }
            try {
                boolean girisdurumu;
                pst=con.prepareStatement(sql);
                pst.setString(1,kullaniciadi.getText());
                pst.setString(2, sifre.getText());
                rs=pst.executeQuery();
                if(rs.next()){
                    girisdurumu=true;
                }
                else
                    girisdurumu=false;
                if(girisdurumu==true){
                    Alert girisalert =new Alert(Alert.AlertType.INFORMATION,"Giriş doğrulandı.Yönlendiriliyorsunuz...",ButtonType.APPLY);
                    girisalert.setTitle("Giriş Durumu");
                    girisalert.setHeaderText("Giriş Başarılı");
                    ((Button) girisalert.getDialogPane().lookupButton(ButtonType.APPLY)).setText("Devam Et");
                    girisalert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.APPLY) {
                        try {
                            fonk.StageKapa(giris);
                            Stage stage=new Stage();
                            fonk.VergiHesaplatma(stage);
                        } catch (Exception ex) {
                            Logger.getLogger(GirisController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    });
                }
                else{
                    Alert girisalert =new Alert(Alert.AlertType.ERROR,"Kullanıcı adı veya şifre yanlış.",ButtonType.APPLY);
                    girisalert.setTitle("Giriş Durumu");
                    girisalert.setHeaderText("Giriş Yanlış");
                    ((Button) girisalert.getDialogPane().lookupButton(ButtonType.APPLY)).setText("Tamam");
                     girisalert.show();
                }
                
            } catch (Exception ex) {
                
            }
        });
        
        kayitol.setOnAction(e -> {
            try {
                fonk.StageKapa(kayitol);
                Stage stage=new Stage();
                fonk.KayitOlSayfasi(stage);
            } catch (Exception ex) {
                Logger.getLogger(GirisController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
    
    
}
