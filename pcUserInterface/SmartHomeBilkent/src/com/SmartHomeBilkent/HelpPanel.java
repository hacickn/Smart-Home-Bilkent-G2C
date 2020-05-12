package com.SmartHomeBilkent;

import com.SmartHomeBilkent.utilities.dataBase.Users;
import com.SmartHomeBilkent.utilities.dataBase.fields.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HelpPanel implements Initializable {
   @FXML
   private AnchorPane helpMainPane, helpExitPane,
         helpUserProfilePane, helpMenuPane,
         helpSettingsPane, homeSettingPane,
         modsSettingPane, usersSettingPane,
         applicationSettingPane;

   @FXML
   private JFXButton helpExitButton, helpMenuButton,
         helpUserProfileButton, helpSettingsButton,
         goBackFromLogout, goBackFromUserProfile,
         goBackFromMenu, goBackFromSettings,
         applicationSettingButton, usersSettingButton,
         modsSettingButton, homeSettingButton,
         goBackFromApplicationSetting, goBackFromUsersSetting,
         goBackFromModsSetting, goBackFromHomeSetting;

   @FXML
   private JFXTextArea menuTextField, userProfileTextField,
         logoutTextField, applicationSettingTextArea,
         usersSettingTextArea, modsSettingTextArea,
         homeSettingTextArea;
   private User loginUser;

   @Override
   public void initialize( URL location, ResourceBundle resources ) {
      getLoginUser();
      if( loginUser.getPreferredLanguage().equals( "TÜRKÇE" ) ) { // türkçe
         menuTextField.setText( "* Manuel olarak evdeki sistemleri kontrol edebilmek için;\n" +
                 "\n" +
                 "\t\tSol tarafta bulunan \n" +
                 "\t- 1. Düğmedeki şimşek simgesi ile \" ELEKTRİK \" valfi açılıp kapatılabilir. Aşağıda bulunan ayarlar" +
                 " butonuyla elektrik ayarlarına ulaşılabilir.\n" + "\t- 2. Düğmedeki vana simgesi ile evinizdeki " +
                 "\" GAZ \" valfi açılıp kapatılabilir. Aşağıda bulunan ayarlar butonuyla gaz ayarlarına ulaşılabilir.\n" +
                 "\t- 3. Düğmedeki su damlası simgesi ile evinizdeki \" SU \" valfi açılıp kapatılabilir.\n" +
                 "\n" + "\t\tAlt tarafta bulunan \n" + "\t- Soldan 1. sıradaki termometre ile birlikte \" EV SICAKLIĞI \"" +
                 " bilgisine ulaşılabilir.\n" + "\t- Soldan 2. sıradaki kapı simgesi ile birlikte \" EVİN KAPISI \" " +
                 "açılabilir.\n" + "\t- Soldan 3. sıradaki kapı simgesi ile istediğiniz ülkeninin ya da bölgenin " +
                 "\" HAVA DURUMU \" bilgisine ulaşılabilir. Üst tarafta bulunan metin kutusuna hava durumunu öğrenilmek" +
                 " istenilen ülkenin adını girip aşağıda bulunan güncelleştirme butonuna basarak aşağıdaki bilgilere" +
                 " ulaşılabilir.\n" + "\n" + "\t\tSağ tarafta bulunan \n" + "\t- 1. Düğmedeki akvaryum simgesi ile" +
                 " evdeki \" AKVARYUM AYARLARI \" bilgileri açılıp kapatılabilir. Aşağıda bulunan ayarlar butonuyla " +
                 "elektrik ayarlarına ulaşılabilir.\n" + "\t- 2. Düğmedeki sera simgesi ile evdeki \" SERA \" " +
                 "içerisindeki sıcaklık ve nem verilerine ulaşılabilir. Aşağıda bulunan ayarlar butonuyla elektrik " +
                 "ayarlarına ulaşılabilir.\n" + "\t- 3. Düğmedeki lamba simgesi ile evin bahçesindeki ışık açılıp " +
                 "kapatılabilir.\n" + "\n" + "\t\tÜst tarafta bulunan \n" + "\t- Sağdan 1. Düğmedeki kısayol simgesi " +
                 "ile otomatik olarak açmak ya da kapatmak  istenilen birimler açılıp kapatılabilir. Seçtikten sonra " +
                 "\"CALISTIR\" butonuna basılmasıyla beraber istenilen birimler aktif hale dönebilir. Aşağıda bulunan " +
                 "ayarlar butonuyla elektrik ayarlarına ulaşabilirsiniz.\n" + "\t- 2. Düğmedeki \"BAĞLANTI \"simgesi ile" +
                 " sayfanın sol üstteki ayarlar bölmesindeki bağlantılar bölmesine gidilebilir. \n" + "\t\t>> Sol " +
                 "tarafta bulunan \"BLUETOOTH\" bağlantısını aşağıda buluna 3 çıkışlı güncelleştirme simgesine sahip " +
                 "butonla aktif hale getirip \"ARDUINO\" ile Bluetooth bağlantısı sağlanılabilir. \n" + "\t\t>> Sağ " +
                 "tarafta bulunan port bağlantısından \"ARDUINO\" ile seri bağlantısını sağlayabilir.COM1 ve COM4" +
                 " seçeneğini seçip ardından altta bulunan güncelleştirme butonu ile aktif hale getirildikten sonra, " +
                 "\"ARDUNIO\" ve sensörler birebir arayüz üzerinden kontrol edilebilir.\n" + "\t\t\n" + "\t- 3. " +
                 "Düğmedeki saat simgesi ile \"KULLANICININ\" güncel zaman ayarını aktive etmesi beklenmektedir.\n" +
                 "\t\t>> Birinci text  alanının yanındaki takvim simgesinden gün seçilmelidir\n" + "\t\t>> İkinci text " +
                 "alanının yanındaki saat simgesinden saat, dakika simgesinden dakika seçilmelidir.\n" + " !!!!!!!! EN " +
                 "SON SEÇİM İŞLEMİNİ TAMAMLADIKTAN SONRA SEÇİLEN BİLGİLERİ KAYDETMEK İÇİN KAYDET BUTONUNA " +
                 "BASILMALIDIR!!!!!!!" );
         userProfileTextField.setText( "* Sağ üst köşede bulunan kullanıcı profili, akıllı evde bulunan kişilerin " +
                 "profillerinin eklenebileceği butondur. Ad ,soyad , doğum günü tarihi ve cinsiyet bilgileri sol " +
                 "taraftaki alana eklenebilir ve ayrıca altakki oklar butonundan istenilen zamanda değişiklik " +
                 "yapılabilir. Değişiklik yaptıktan sonra çıkmak için sola doğru olan ok tuşuna basılması yeterlidir.\n" +
                 "\t * Sağ tarafta bulunan bölmeden kullanıcı adı ve parola girilebilir ve ayrıca altakki oklar " +
                 "butonundan istenilen zamanda değişiklik yapılabilir. Değişiklik yaptıktan sonra çıkmak için sola " +
                 "doğru" + " olan ok tuşuna basılması yeterlidir." );
         logoutTextField.setText( "Her sayfanın en altında ortada bulunan kapı simgesi kullanıcı adı ve parolayı " +
                 "girdiğiniz sekmeye geri dönebilmeyi sağlar. İstenilen her an kullanıcı çıkış yapabilmektedir." );
         applicationSettingTextArea.setText( "> Sağdan 1. Düğmedeki \"UYGULAMA\" simgesi\n" +
                 "\t\t>> Altta bulunan sağdan birinci \"TEMA \" butonu ile bulunan 4 farklı arka plandan; \n" +
                 "\t(1) GECE\n" + "\t(2) UZAY \n" + "\t(3) PİRAMİT \n" + "\t(4) SOYUT \n" + "\t(5) AYDINLIK\n" +
                 "\t(6) NEON\n" + "\t(7) ÇİZGİ FİLM\n" + "\t(8) ORMAN\n" + "\t(9) CYBERPUNK \n" + "\t(10) AKILLI " +
                 "ŞEHİR \n" + "\t(11) PÜRÜZSÜZ \n" +"\t(12) YILDIZLARARASI \n" +"\t\tistenilen fonu" +
                 " seçip yanındaki boşluğa basılıp, boşluğun yeşil olmasıyla beraber istenilen yeni " + "tema aktif hale" +
                 " dönebilir.  \n" + "\t\t>> Altta bulunan sağdan ikinci \"DİL \" butonu ile bulunan 3 farklı dilden; " +
                 "\n" + "\t(1) İNGİLİZCE\n" + "\t(2) ALMANCA\n" + "\t(3) TÜRKÇE\n" + "\t\tistenilen dil seçilip " +
                 "istenilen yeni dil modu aktif hale dönebilir.   \n" + "\t\t>> Altta bulunan sağdan üçüncü" +
                 " \"ACİL DURUM \" butonu ile bulunan 2 farklı sirenden; \n" + "\t(1) İç Siren\n" + "\t(2) Dış Siren\n"+
                 "\t\tevde gaz, yangın, duman sensörlerinin uyarı vermesiyle ya da yanlış şifre alarmının girilmesi ile " +
                 "sirenler\n" + "\t\taktive hale dönüştürülebilir. Aynı zamanda kişi kendisi de manuel olarak iç ve dış" +
                 " sireni aktive edebilir.\n" + "\t\t>> Altta bulunan sağdan dördüncü \"BİLDİRİM \" butonu ile bulunan 3" +
                 " farklı sensörden; \n" + "\t(1) Yangın Sensörü\n" + "\t(2) Gaz Sensörü\n" + "\t(3) Duman Sensörü\n" +
                 "\t\tiki ayrı uyarı bildirimi gelebilmektedir;\n" + "\t(1) Görüntüsel Uyarı Bildirimi \n" + "\t(2) " +
                 "Sessel Uyarı Bildirimi\n" + "\t\tevde gaz, yangın, duman ya da yalnış şifre alarmının girilmesiyle " +
                 "uyarı bildirimi gelmesi kişinin isteğine göre ses, yazı ya da her iki türde geri dönüş sağlayabilir. " +
                 "Kişi kendisi de manuel olarak elde etmeyi istediği bildirim türlerini butonlara basarak aktif hale " +
                 "dönüştürebilir.\n" + "\t\t>> Altta bulunan sağdan üçüncü \"BAĞLANTILAR\" butonu ile bulunan 2 bağlantı" +
                 " türünden; \n" + "\t(1) Bluetooth\n" + "\t(2) Seriport\n" + "\t\t\t>>> Sol tarafta bulunan Bluetooth" +
                 " bağlantısını aşağıda bulunann 3 çıkışlı güncelleştirme butonuyla aktif hale getirip \"ARDUINO\" ile" +
                 " bluetooth bağlantısı sağlanılabilir. \n" + "\t\t\t>>> Sağ tarafta bulunan port bağlantısından " +
                 "\"ARDUINO\" ile seri bağlantısını sağlayabilir. COM1 ve COM4 seçeneğini seçip ardından altta bulunan" +
                 " güncelleştirme butonuyla aktif hale getirip aktif hale getirip \"ARDUNIO\" ve sensörlerini birebir" +
                 " arayüz üzerinden kontrol edebiliriz." );
         usersSettingTextArea.setText( "> Sağdan 2. Düğmedeki \"KULLANICILAR\" simgesi ile akıllı evde bulunan tüm" +
                 " kullanıcılar toplu olarak görülebilir.\n" + "\t\t>> Aşağıda solda bulunan kişi ekle butonuyla beraber" +
                 " eklemek istediğiniz kullanıcının bilgilerini ekledikten sonra aşağıda bulunan \"ARTI\" Butonuna " +
                 "basarak kullanıcı eklenilebilir. Çıkmak için Sola doğru olan oka basmanız gerekmektedir. \n" +
                 "\t\t>> Aşağıda ortada izin butonuyla yetişkin bireyler tarafından çocukların akıllı evdeki bulunan" +
                 " bazı birimlere erişim kısıtlaması yapılabilmektedir.\n" + "\t\t>> Aşağıda en sağda bulunan kişi sil" +
                 " butonuyla birlikte silmek istediğiniz kişiyi/leri seçip çöp kutusu simgesine basmanızla beraber " +
                 "\"KULLANICI SİL\" bilgisini gördükten sonra \"UYARI\" yazısını göreceksiniz. İşleme devam etmek " +
                 "istiyorsanız bulunan yazı bölümüne şifrenizi girip çarpı butonuna basmanız gerekmektedir. İşleme" +
                 " devam etmek istemiyor ya da kişi menüsüne dönmek istiyorsanız sola doğru olan oka basmanız " +
                 "gerekmektedir. " );
         modsSettingTextArea.setText( "> Sağdan 3. Düğmedeki \" MODLAR \" simgesi ile kullanıcı;\n" +
                 "\t\t>> \"ETKİLEŞİMLİ YAZI MODU\" ile arayüzde bulunan butonların altında bulunan bilgi yazılarına " +
                 "ulaşabilmektedir. \n" + "\t\t>> \" ETKİLEŞİMLİ SES MODU \" ile arayüzde bulunan butonların" +
                 " görevlerini ses yardımıyla ulaşabilmektedir. Panelde en altta bulunan \"SES DÜZEYİ\" butonu ile Ses" +
                 " ayarı yapılabilmektedir." );
         homeSettingTextArea.setText( "> 4. Düğmedeki \" EV AYARLARI \" alt bölümde bulunan birimlerin ayarlarına geçiş " +
                 "yapılmaktadır,\n" + "\t aynı zamanda bu panale menüden, aşağıda bulunan birimlerin ayarlar butonu ile " +
                 "dönülebilir. \n" + "\t\t>> Sağdan 1. \" ELEKTRİK AYARLARI \" ile günlük ve haftalık elektrik kullanım" +
                 " saati bilgisine ulaşılabilir.\n" + "\t\t>> Sağdan 2. \" GAZ AYARLARI \" ile günlük ve haftalık gaz " +
                 "kullanım saati bilgisine ulaşılabilir. \n" + "\t\t>> Sağdan 3. \" SU AYARLARI \" simgesi ile;" +
                 "\t\t\t\n" + "\t\t\t**  Birinci \"BALIK TÜRÜ\"  kutusundan akvaryumda bulunması önerilen,birbirleri " +
                 "içerisinde mutalist yaşayan balık türleri yazılmıştır. Seçilen, üretici gruğğ tarafından önerilen " +
                 "balık türlerine uygun hava motorunun çalışması için \"ÖNERİLEN SÜRE 10 SAAT\" olarak verilmiştir. \n" +
                 " \t\t\t**  İkinci \"YEMLEME BAŞLAMA ZAMANI\" kullanıcı tarafından seçilmelidir. Bir kere seçildiği " +
                 "taktirde her gün aynı saatte çalışmaya devam edecektir.\n" + "\t\t\t**  Üçüncü \"SU DEĞİŞİM GÜNÜ VE" +
                 " ZAMANI\" kullanıcı tarafından seçilmelidir. Bir kere seçildiği taktirde haftanın seçilen gününde ve " +
                 "saatinde çalışmaya devam edecektir.\n" + " \t\t\t**  Dördüncü \"HAVA MOTORU ÇALIŞMA MİKTARI VE " +
                 "BAŞLANGIÇ ZAMANI\" kullanıcı tarafından seçilmelidir. Bir kere seçildiği taktirde haftanın seçilen " +
                 "gününde ve saatinde çalışmaya devam edecektir. İlk butondan sürükleyerek hava motorunun çalışacağı " +
                 "süre belirlenmektedir. İkinci buton ile hava motorunun çalışmaya başlayacağı zaman seçilmektedir.\n" +
                 "!!!!!!!!EN SON SEÇİM İŞLEMİNİ TAMAMLADIKTAN SONRA SEÇİLEN BİLGİLERİ KAYDETMEK İÇİN KAYDET BUTONUNA " +
                 "BASILMALIDIR!!!!!!!\n" + "\t\t>> Sağdan 4. \"SERA AYARLARI\" simgesi ile günlük ve haftalık gaz " +
                 "kullanım saati bilgisine ulaşılabilir. Grafikte gün içerisinde ölçülen ortalama sıcaklık ve nem " +
                 "bilgilerine ulaşılabilir. Aşağıda da belirtildiği gibi kırmızı çizgiler sıcaklığı, turuncu çizgiler " +
                 "nemi temsil etmektedir. Akvaryumdan sera' nın deposuna gelen su, seranın ihtiyacı olduğu zaman" +
                 " damlama yöntemiyle alınmaktadır. Alınan suyun saatine ve gününe \"AKVARYUMDAN GELEN SON SU\" adı " +
                 "altında ulaşılabilir.\n" + "\t\t>> Sağdan 5. \"HAVA DURUMU AYARLARI\" simgesi ile menüden de " +
                 "ulabildiğimiz hava durumu paneli sayesinde hava durmunu öğrenmek istediğiniz ülkeninin ya da bölgenin" +
                 " \" HAVA DURUMU \" bilgisine ulaşılabilirsiniz. Üst tarafta bulunan metin kutusuna hava durumunu " +
                 "öğrenmek istediğiniz ülkenin adını girip aşağıda bulunan güncelleştirme butonuna basarak aşağıdaki" +
                 " bilgilere ulaşabilirsiniz. " );
      } else if( loginUser.getPreferredLanguage().equals( "DEUTSCH" ) ) { //almanca
         menuTextField.setText( "(3) MENÜ\n" + "\n" + "* Manuelle Steuerung der Systeme im Smart Home;\n" + "\n" +
                 "> Befindet sich auf der linken Seite\n" + ">> 1. Das Ventil \"ELECTRIC\" kann mit dem Blitzsymbol auf" +
                 " der Taste geöffnet und geschlossen werden. Auf die elektrischen Einstellungen kann über die " +
                 "Schaltfläche Einstellungen unten zugegriffen werden.\n" + ">> 2. Das \"GAS\" -Ventil in Ihrem Haus" +
                 " kann mit dem Ventilsymbol auf der Schaltfläche geöffnet und geschlossen werden. Auf die" +
                 " Gaseinstellungen kann über die Schaltfläche Einstellungen unten zugegriffen werden.\n" + ">> 3. Mit " +
                 "dem Wassertropfensymbol auf der Schaltfläche kann das \"WASSER\" -Ventil in Ihrem Haus geöffnet und" +
                 " geschlossen werden.\n" + "\n" + "> Befindet sich unten\n" + ">> In der ersten Zeile links finden Sie " +
                 "die Informationen zur \"HEIMTEMPERATUR\" mit dem Thermometer.\n" + ">> In der 2. Reihe links kann die" +
                 " \"HAUSTÜR\" mit dem Türsymbol geöffnet werden.\n" + ">> In der 3. Zeile links können Sie mit dem " +
                 "Türsymbol auf die \"WETTER\" -Informationen Ihres Landes oder Ihrer Region zugreifen. Auf die" +
                 " folgenden Informationen kann zugegriffen werden, indem Sie im Text den Namen des Landes eingeben, " +
                 "in dem Sie das Wetter erfahren möchten Kästchen oben und klicken Sie auf die Schaltfläche " +
                 "Aktualisieren unten.\n" + "\n" + "> Befindet sich auf der rechten Seite\n" + ">> Mit dem " +
                 "Aquarium-Symbol auf der 1. Schaltfläche können die Informationen zu \"AQUARIUM-EINSTELLUNGEN\" zu " +
                 "Hause ein- und ausgeschaltet werden. Auf die elektrischen Einstellungen kann über die Schaltfläche " +
                 "Einstellungen unten zugegriffen werden\n" + ">> Mit dem Gewächshaussymbol in der 2. Taste können " +
                 "Temperatur- und Feuchtigkeitsdaten im \"GEWÄCHSHAUS\" zu Hause erfasst werden zugegriffen werden. " +
                 "Auf die elektrischen Einstellungen kann über die Schaltfläche Einstellungen unten zugegriffen" +
                 " werden.\n" + ">> Mit dem Lampensymbol ab der 3. Taste kann das Licht im Garten des Hauses ein- und " +
                 "ausgeschaltet werden.\n" + "\n" + "Befindet sich oben\n" + "- Mit dem Verknüpfungssymbol auf der 1. " +
                 "Schaltfläche rechts können die Einheiten, die automatisch ein- oder ausgeschaltet werden, geöffnet " +
                 "und geschlossen werden.Nach Auswahl der Schaltfläche \"RUN\" können die gewünschten Einheiten " +
                 "aktiviert werden. Sie können auf die elektrischen Einstellungen mit der Schaltfläche Einstellungen " +
                 "unten zugreifen.\n" + "- Mit dem Symbol \"VERBINDUNG\" auf der 2. Schaltfläche können Sie in den" +
                 " Einstellungen zum Anschlussfeld wechseln Panel oben links auf der Seite.\n" + ">> Der Link " +
                 "\"BLUETOOTH\" auf der linken Seite kann mit der Schaltfläche mit dem Update-Symbol mit 3 Eingängen " +
                 "unten aktiviert werden und Bluetooth-Verbindung kann mit \"ARDUINO\" bereitgestellt werden.\n" +
                 ">> Es kann eine serielle Verbindung mit \"ARDUINO\" über die Anschlussverbindung auf der rechten " +
                 "Seite herstellen. Nachdem Sie die Optionen COM1 und COM4 ausgewählt und dann mit der" +
                 " Update-Schaltfläche unten aktiviert haben, \"ARDUNIO\" und Sensoren können über die Schnittstelle" +
                 " gesteuert werden.\n" + "\n" + "- Mit dem Uhrensymbol auf der 3. Taste soll der BENUTZER die" +
                 " aktuelle Zeiteinstellung aktivieren.\n" + ">> Der Tag sollte über das Kalendersymbol neben dem " +
                 "ersten Textfeld ausgewählt werden.\n" + ">> Stunde sollte über das Uhrensymbol ausgewählt werden " +
                 "und Minuten sollten aus ausgewählt werden das Minutensymbol neben dem zweiten Textfeld.\n" +
                 "!!!!!!!! Nach Abschluss des letzten Auswahlprozesses muss die Speichertaste gedrückt werden, um" +
                 " die ausgewählten Informationen zu speichern !!!!!!!" );
         userProfileTextField.setText( "* Das Benutzerprofil in der oberen rechten Ecke ist die Schaltfläche, über die" +
                 " die Profile der Personen im Smart Home hinzugefügt werden können. Name, Nachname, Geburtsdatum und " +
                 "Geschlechtsinformationen können dem Feld links hinzugefügt werden. Änderungen können jederzeit " +
                 "vorgenommen werden von der Pfeiltaste. Um nach Änderungen Änderungen zu beenden, müssen Sie die" +
                 " Pfeiltaste nach links drücken.\n" + "* Benutzername und Passwort können im rechten Bereich" +
                 " eingegeben werden. Änderungen können jederzeit von vorgenommen werden mit der Pfeiltaste." +
                 " Um nach Änderungen Änderungen zu beenden, müssen Sie die Pfeiltaste nach links drücken." );
         logoutTextField.setText( "Mit dem Türsymbol in der Mitte unten auf jeder Seite können Sie zu der " +
                 "Registerkarte zurückkehren, auf der Sie den Benutzernamen und das Passwort eingegeben haben.Der " +
                 "Benutzer kann sich jederzeit abmelden." );
         applicationSettingTextArea.setText( "Während Sie oben auf die Schaltfläche ANWENDUNGSEINSTELLUNGEN drücken\n" +
                 "> Symbol \"ANWENDUNG\" auf der 1. Schaltfläche von rechts\n" +
                 ">> 4 verschiedene Hintergründe gefunden durch den 1. Button \"THEMEN\" rechts am Hintern,\n" +
                 "(1) NACHT \n" + "(2) RAUM \n" + "(3) PYRAMID \n" + "(4) ZUSAMMENFASSUNG \n" + "(5) BELEUCHTUNG \n" +
                 "(6) NEON \n" + "(7) CARTOON\n" + "(8) WALD\n" + "(9) CYBERPUNK\n" + "(10) INTELLIGENTE STADT\n" +
                 "(11) GLATT\n" + "(12) INTERSTELLAR\n" + "Durch Auswahl des gewünschten Themas und Drücken des Feldes" +
                 " daneben kann das neue Thema aktiviert werden, wobei das Feld grün wird.\n" + ">> 3 verschiedene " +
                 "Sprachen finden Sie mit der 2. Schaltfläche \"SPRACHE\" unten rechts unten;\n" + "(1) ENGLISCH\n" +
                 "(2) DEUTSCH\n" + "(3) TÜRKISCH\n" + "Die gewünschte Sprache kann ausgewählt und der neue Sprachmodus " +
                 "aktiviert werden.\n" + ">> 2 verschiedene Sirenen, die mit dem 3. Knopf \"NOTFALL\" rechts am " +
                 "Hintern gefunden wurden;\n" + "(1) INTERNE SIRENE\n" + "(2) EXTERNE SIRENE\n" + "Sirenen können durch" +
                 " Warnung vor Gas-, Feuer- und Rauchsensoren zu Hause oder von aktiviert werden Eingabe eines falschen" +
                 " Passwortalarms. Gleichzeitig kann die Person die interne und externe Sirene manuell aktivieren.\n" +
                 "\n" + ">> Von den 3 verschiedenen Sensoren rechts mit der 4. Taste \"NOTIFICATION\" rechts unten;\n" +
                 "(1) FEUERSENSOR\n" + "(2) Gassensor\n" + "(3) RAUCHSENSOR\n" + "\n" + "Es können zwei separate " +
                 "Warnmeldungen empfangen werden.\n" + "\n" + "(1) VISUELLE WARNUNG\n" + "(2) PRÜFUNGSWARNUNG\n" + "\n" +
                 "Das Eintreffen einer Warnmeldung durch Eingabe von Gas-, Feuer-, Rauch- oder falschem Passwortalarm " +
                 "zu Hause kann vorsehen Ton, Text oder beide Arten der Rücksendung je nach Wunsch der Person. Die" +
                 " Person kann auch die Arten von Benachrichtigungen aktivieren Benutzer möchte manuell durch Drücken" +
                 " der Tasten erhalten.\n" + "\n" + ">> 2 Verbindungstypen, die über die 3. Schaltfläche" +
                 " \"VERBINDUNGEN\" unten rechts gefunden werden;\n" + "\n" + "(1) Bluetooth\n" + "(2) Serielle " +
                 "Schnittstelle\n" + ">>> Der Link \"BLUETOOTH\" auf der linken Seite kann mit der Schaltfläche mit" +
                 " dem Update-Symbol aktiviert werden 3 Eingänge unten und Bluetooth-Verbindung können mit \"ARDUINO\"" +
                 " bereitgestellt werden.\n" + ">>> Es kann eine serielle Verbindung mit \"ARDUINO\" über die " +
                 "Portverbindung auf der rechten Seite herstellen. Nachdem Sie die Optionen COM1 und COM4 ausgewählt " +
                 "und anschließend mit der Update-Schaltfläche unten aktiviert haben, \"ARDUNIO\" und Sensoren können " +
                 "über die Schnittstelle gesteuert werden.\n" + "\n" );
         usersSettingTextArea.setText( "> Mit dem Symbol \"BENUTZER\" auf der 2. Schaltfläche von rechts können alle " +
                 "Benutzer im Smart Home als Ganzes angezeigt werden.\n" + "\n" + ">> Nachdem Sie die Informationen des" +
                 " Benutzers hinzugefügt haben, den Sie hinzufügen möchten, klicken Sie links unten auf die Schaltfläche" +
                 " Person hinzufügen. Der Benutzer kann durch Drücken der Taste \"PLUS\" unten hinzugefügt werden. Zum" +
                 " Beenden müssen Sie den Pfeil nach links drücken.\n" + "\n" + ">> Unten, mit der Schaltfläche " +
                 "\"ERLAUBNIS\" in der Mitte, können erwachsene Personen Zugriffsbeschränkungen vornehmen zu einigen" +
                 " Einheiten im Smart-Home-Konto der Kinder.\n" + "\n" + ">> Sie sehen den Text \"WARNUNG\", nachdem" +
                 " Sie die Informationen \"BENUTZER LÖSCHEN\" gesehen haben, nachdem Sie die ausgewählt haben Personen," +
                 " die Sie löschen möchten, klicken Sie ganz rechts auf die Schaltfläche \"Löschen\" und drücken Sie " +
                 "auf das Papierkorbsymbol.\n" + "\n" + "Wenn Sie den Vorgang fortsetzen möchten, müssen Sie Ihr" +
                 " Passwort in den gefundenen Textbereich eingeben und auf die Schaltfläche \"CROSS\" klicken. Wenn" +
                 " Sie den Vorgang nicht fortsetzen oder zum Kontaktmenü zurückkehren möchten, müssen Sie den Pfeil " +
                 "nach links drücken." );
         modsSettingTextArea.setText( "> Mit dem Symbol \"MODES\" auf der 3. Schaltfläche von rechts der Benutzer;\n" +
                 ">> Mit dem \"INTERAKTIVEN TEXTMODUS\" ist es möglich, auf den Informationstext unter den Schaltflächen" +
                 " auf der Oberfläche zuzugreifen.\n" + ">> Mit dem \"INTERACTIVE SOUND MODE\" ist es möglich, die" +
                 " Aufgaben der Tasten auf der Oberfläche mit Hilfe von Sound zu erreichen.Die Lautstärke kann mit der" +
                 " Taste \"VOLUME\" am unteren Rand des Bedienfelds eingestellt werden.\n" );
         homeSettingTextArea.setText( "> In der vierten Schaltfläche \"HOME SETTINGS\" können Sie gleichzeitig zu" +
                 " den Einstellungen der Geräte im unteren Bereich wechseln. Sie können mit der Einstellungsschaltfläche" +
                 " der folgenden Einheiten zu diesem Bereich zurückkehren.\n" + "\n" +
                 ">> Mit der ersten Schaltfläche rechts \"ELEKTRISCHE EINSTELLUNGEN\", tägliche und wöchentliche " +
                 "Stromverbrauchsstunden Informationen können abgerufen werden.\n" + "\n" + ">> Mit der zweiten " +
                 "Schaltfläche \"GASEINSTELLUNGEN\" rechts können Informationen zu den täglichen und wöchentlichen " +
                 "Gasverbrauchsstunden abgerufen werden.\n" + "\n" + ">> Mit der dritten Taste \"WASSEREINSTELLUNGEN\" " +
                 "rechts;\n" + "\n" + "** Aus dem 1. Abschnitt \"Arten von Fischen\", die Fischarten, die im Aquarium " +
                 "gefunden werden sollen,blebende Mutalisten untereinander wurden geschrieben. Es wird als " +
                 "\"EMPFOHLENE ZEIT 10 STUNDEN\" für den Betrieb der Luft angegeben Motor geeignet für die von der " +
                 "Erzeugergruppe ausgewählten Fischarten.\n" + "** Der 2. Abschnitt \"FEEDING START TIME (JEDER TAG)\" " +
                 "muss vom Benutzer ausgewählt werden. Nach der Auswahl wird es jeden Tag zur gleichen Zeit ausgeführt.\n" +
                 "** Der dritte Abschnitt \"WASSERAUSTAUSCHTAG UND -ZEIT\" muss vom Benutzer ausgewählt werden. Nach der" +
                 " Auswahl wird der Betrieb an dem ausgewählten Wochentag und der ausgewählten Uhrzeit fortgesetzt.\n" +
                 "** ** Der vierte \"AIR MOTOR WORK AMOUNT AND START TIME\" sollte vom Benutzer ausgewählt werden. " +
                 "Einmal ausgewählt, Es wird an dem ausgewählten Tag und der ausgewählten Uhrzeit weiter betrieben." +
                 " Die Betriebsdauer des Luftmotors wird durch Ziehen von der ersten Schaltfläche bestimmt. Die zweite" +
                 " Taste wird ausgewählt, wenn der Luftmotor seinen Betrieb aufnimmt.\n" + "!!!!!!!! Drücken Sie die " +
                 "Taste SPEICHERN, um die ausgewählten Informationen nach Abschluss der letzten Auswahl zu speichern " +
                 "!!!!!!!\n" + "\n" + ">> Mit der vierten Schaltfläche rechts, \"GREENHOUSE SETTINGS\", " +
                 "können Informationen zu den täglichen und wöchentlichen Gasverbrauchsstunden abgerufen werden.\n" +
                 ">>> Auf die während des Tages gemessenen Informationen zu Durchschnittstemperatur und " +
                 "Luftfeuchtigkeit kann in der Grafik zugegriffen werden.\n" + ">>> Wie unten erwähnt, stehen rote " +
                 "Linien für Temperatur und orange Linien für Feuchtigkeit.\n" + ">>> Wasser, das aus dem Aquarium in " +
                 "den Tank des Gewächshauses kommt, wird nach der Tropfmethode entnommen, wenn das Gewächshaus es " +
                 "benötigt.\n" + ">>> Die Stunde und der Tag der Aufnahme können unter dem Namen \"LETZTES WASSER AUS" +
                 " DEM AQUARIUM\" erreicht werden.\n" + "\n" + ">> Mit der fünften Schaltfläche \"WETTEREINSTELLUNGEN\"" +
                 " rechts erreichen Sie die \"WETTER\" -Informationen des Landes oder der Region Hier möchten Sie die" +
                 " Wettersituation dank des Wetterfelds erfahren, auf das wir auch über das Menü zugreifen können.\n" +
                 ">>> Sie können auf die folgenden Informationen zugreifen, indem Sie den Namen des Landes eingeben, " +
                 "in dem Sie das Wetter erfahren möchten im Textfeld oben und klicken Sie auf die Schaltfläche " +
                 "Aktualisieren unten.\n" + "\n" + "\n" );
      } else { // ENGLISH
         menuTextField.setText( " * To manually control the systems in the smart home;\n" + "\t > Located on the left \n" +
                 "\t\t>> 1. The \" ELECTRIC \" valve can be opened and closed with the lightning bolt icon on the " +
                 "button. Electric settings can be accessed with the settings button below.\n" + "\t\t>> 2. The" +
                 " \" GAS \" valve in your home can be opened and closed with the valve icon on the button. Gas settings" +
                 " can be accessed with the settings button below.\n" + "\t\t>> 3. With the water drop icon on the " +
                 "button, the \"WATER\" valve in your home can be opened and closed.\n" + "\n" + "\t>Located at the " +
                 "bottom\n" + "\t\t>> In the 1st row on the left, the \"HOME TEMPERATURE\" information can be found " +
                 "with the thermometer.\n" + "\t\t>> In the 2nd row on the left, the \"HOUSE DOOR\" can be opened with " +
                 "the door icon.\n" + "\t\t>> In the 3rd row on the left, The \"WEATHER\" information of your country" +
                 " or region can be accessed with the door symbol. The following information can be accessed by entering" +
                 " the name of the country where you want to learn the weather in the text box at the top and clicking " +
                 "the update button below.\n" + "\n" + "\t>Located on the right\n" + "\t\t>> With the aquarium icon on" +
                 " the 1st Button, \"AQUARIUM SETTINGS\" information can be turned on and off at home. Electric settings" +
                 " can be accessed with the settings button below\n" + "\t\t>> With the greenhouse icon in the 2nd " +
                 "Button, temperature and humidity data in the \"GREENHOUSE\" at home can be accessed. Electric settings" +
                 " can be accessed with the settings button below.\n" + "\t\t>> With the lamp icon from the 3rd button, " +
                 "the light in the garden of the house can be turned on and off.\n" + "\n" + "\t\tLocated at the top\n" +
                 "\t- With the shortcut icon on the 1st button on the right, the units that are automatically turned on " +
                 "or off can be opened and closed. After selecting \"RUN\" button, desired units can be activated. You " +
                 "can access the electrical settings with the settings button below.\n" + "\n" + "\t- With the " +
                 "\"CONNECTION\" icon on the 2nd button, you can go to the connection panel in the settings panel at " +
                 "the top left of the page. \n" + "\t\t>>The \"BLUETOOTH\" link on the left side can be activated with" +
                 " the button with the update icon having 3-inputs below and Bluetooth connection can be provided with" +
                 " \"ARDUINO\".\n" + "\t\t>> It can provide serial connection with \"ARDUINO\" from the port connection " +
                 "which is on the right. After selecting the COM1 and COM4 option and then activating with the update" +
                 " button at the bottom, \"ARDUNIO\" and sensors can be controlled via the interface.\n" + "\n" + "\t-" +
                 " With the clock icon on the 3rd button, the USER is expected to activate the current time setting.\n" +
                 "\t\t>> The day should be selected from the calendar icon next to the first text field,\n" +
                 "\t\t>> Hour should be selected from the clock icon  and minutes should be selected from the minute " +
                 "icon that are next to the second text field.\n" + "!!!!!!!! SAVE THE BUTTON TO SAVE THE SELECTED " +
                 "INFORMATION AFTER COMPLETING THE LATEST SELECTION\n!!!!!!!" );
         userProfileTextField.setText( "\n" +
                 "\t* The user profile in the upper right corner is the button where the profiles of the people in the " +
                 "smart home can be added. Name, surname, date of birth and gender information can be added to the field" +
                 " on the left and also changes can be made at any time from the arrows button. To exit after making " +
                 "changes, it is enough to press the arrow key to the left.\n" + "\t* User name and password can be " +
                 "entered from the right-hand pane and also changes can be made at any time by using the arrows button." +
                 " To exit after making changes, it is enough to press the arrow key to the left." );
         logoutTextField.setText( "The door icon in the middle at the bottom of each page allows you to return to the " +
                 "tab where you entered the username and password. The user can logout at any time." );
         applicationSettingTextArea.setText( "> \"APPLICATION\" icon on the 1st button from the right\n" + "\n" +
                 "\t\t>> 4 different backgrounds found by the 1st button \"THEMES\" on the right at the buttom ,\n" +
                 "\t(1) DARK\n" + "\t(2) SPACE\n" + "\t(3) PYRAMID\n" + "\t(4) ABSTRACT\n" + "\t(1) LIGHT\n" +
                 "\t(2) NEON\n" + "\t(3) CARTOON\n" + "\t(4) FOREST\n" + "\t(1) CYBERPUNK\n" + "\t(2) SMART CITY\n" +
                 "\t(3) SMOOTH\n" + "\t(4) INTERSTELLAR\n" + "\tby selecting the desired theme and pressing the space " +
                 "next to it, the new theme can be activated with the space turned into green.  \n" + "\n" + "\t\t>> 3 " +
                 "different languages \u200B\u200Bfound by the 2nd  button \"LANGUAGE\" on the bottom right at the buttom " +
                 "; \n" + "\t(1) ENGLISH\n" + "\t(2) GERMAN\n" + "\t(3) TURKISH\n" + "\tthe desired language can be " +
                 "selected and the new language mode can be activated.\n" + "\n" + "\t\t>> 2 different sirens found by" +
                 " the 3rd  button \"EMERGENCY\" on the right at the buttom; \n" + "\t(1) INTERNAL SIREN\n" +
                 "\t(2) EXTERNAL SIREN\n" + "\tSirens can be turned into activation by " + "warning of gas, fire and " +
                 "smoke sensors at home or by entering false password alarm. At the same time," + " the person can " +
                 "manually activate the internal and external siren.\t\n" + "\t\t\n" + "\t\t>> From the 3 different " +
                 "sensors on the right with the 4th button \"NOTIFICATION\" on the right at the bottom;\n" +
                 "\t(1) FIRE SENSOR\n" + "\t(2) GAS SENSOR\n" + "\t(3) SMOKE SENSOR\n" + "\n" + "\ttwo separate warning" +
                 " notifications can be received;\n" + "\n" + "\t(1) VISUAL WARNING\n" + "\t(2) AUDITORY WARNING\n" +
                 "\n" + "\tThe arrival of a warning notification by entering gas, fire, smoke or false password alarm " +
                 "at home may provide sound, text or both types of return according to the person's request. The person " +
                 "can also activate the types of notifications user wants to obtain manually by pressing the buttons.\n" +
                 "\t\t>> 2 connection types found by the 3rd button  \"CONNECTIONS\" on the bottom right;\n" + "\n" +
                 "\t(1) Bluetooth\n" + "\t(2) Serial Port\n" + "\t\t\t>>> The \"BLUETOOTH\" link on the left side can" +
                 " be activated with the button with the update icon having 3-inputs below and Bluetooth connection can" +
                 " be provided with \"ARDUINO\".\n" + "\t\t\t>>> It can provide serial connection with \"ARDUINO\" from " +
                 "the port connection which is on the right. After selecting the COM1 and COM4 option and then " +
                 "activating with the update button at the bottom, \"ARDUNIO\" and sensors can be controlled via " +
                 "the interface.\n" );
         usersSettingTextArea.setText( "> With the \"USERS\" icon on the 2nd button from the right, all users in the" +
                 " smart home can be viewed as a whole.\n" + "\n" + "\t\t>> After adding the information of the user " +
                 "you want to add with the add person button on the left below, the user can be added by pressing the " +
                 "\"PLUS\" Button below. To exit, you must press the arrow to the left. \n" + "\n" + "\t\t>> Below, " +
                 "with the \"PERMISSION\" button in the middle, access restrictions can be made by adult individuals to " +
                 "some units in the smart home account of the children.\n" + "\t\n" + "\t\t>> You will see the text " +
                 "\"WARNING\" after seeing the \"DELETE USER\" information after selecting the person/'s you want to " +
                 "delete with the delete button on the far right, and press the trash icon.\n" + "\t\n" + "\tIf you " +
                 "want to continue the process, you need to enter your password in the text section found and press the" +
                 " \"CROSS\" button.\n" + "\tIf you do not want to continue the operation or want to return to the " +
                 "contact menu, you must press the arrow to the left." );
         modsSettingTextArea.setText( "> With the \"MODES\" icon on the 3rd button from the right, the user;\n" +
                 "\n" +
                 "\t\t>> With the \"INTERACTIVE TEXT MODE\", it is possible to access the information text under the " +
                 "buttons on the interface.\n" + "\t\t>> With the \"INTERACTIVE SOUND MODE\", it is possible to reach " +
                 "the tasks of the buttons on the interface with the help of sound. The volume can be adjusted with" +
                 " the \"VOLUME\" button at the bottom of the panel.\n" );
         homeSettingTextArea.setText( "> In the fourth button, \"HOME SETTINGS\", you can go to the settings of the " +
                 "units in the lower section, at the same time, you can return to this panel with the settings button" +
                 " of the units below.\n" + "\n" + "\t\t>> With the first button on the right, \"ELECTRICAL SETTINGS\"," +
                 " daily and weekly electricity usage hours information can be accessed.\n" + "\n" + "\t\t>> With the " +
                 "second button \"GAS SETTINGS\" on the right, daily and weekly gas usage hours information can be " +
                 "accessed.\n" + "\n" + "\t\t>> With the third button \"WATER SETTINGS\" on the right;\n" + "\t\t\t\n" +
                 "\t\t\t**  From the 1st section \"SPECIES OF FISH\" box, the species of fish that are proposed to be " +
                 "found in the aquarium, living mutalist among each other were written. It is given as \"RECOMMENDED " +
                 "TIME 10 HOURS\" for the operation of the air motor suitable for the fish species selected by the " +
                 "producer group. \n" + " \t\t\t**  The 2nd section \"FEEDING START TIME(EACH DAY)\" must be selected" +
                 " by the user. Once selected, it will continue to run at the same time every day.\n" +
                 "\t\t\t**  The third section \"WATER EXCHANGE DAY AND TIME\" must be selected by the user. Once " +
                 "selected, it will continue to operate on the selected day and time of the week.\n" +
                 "\t\t\t** The fourth \"AIR MOTOR WORK AMOUNT AND START TIME \" should be selected by the user." +
                 " Once selected, it will continue to operate on the selected day and time of the week. The duration of " +
                 "the air motor's operation is determined by dragging from the first button. The second button is " +
                 "selected when the air motor will start operating.\n" + "\n" + " !!!!!!!! PRESS SAVE BUTTON TO SAVE " +
                 "THE SELECTED INFORMATION AFTER COMPLETING THE LATEST SELECTION !!!!!!!\n" + "\t\t\n" + "\t\t>> With" +
                 " the fourth button on the right, \"GREENHOUSE SETTINGS\", daily and weekly gas usage hours information" +
                 " can be accessed. \n" + "\t\t\t>>> The average temperature and humidity information measured during " +
                 "the day can be accessed in the graph.\n" + "\t\t\t>>> As mentioned below, red lines represent " +
                 "temperature and orange lines represent humidity. \n" + "\t\t\t>>> Water coming from the aquarium to " +
                 "the greenhouse's tank is taken by the drip method when the greenhouse needs it. \n" + "\t\t\t>>> The " +
                 "hour and day of the intake can be reached under the name \"LAST WATER FROM THE AQUARIUM\".\n" + "\t\t\n" +
                 "\t\t>> With the fifth button \"WEATHER SETTINGS\" on the right, you can reach the \"WEATHER\" " +
                 "information of the country or region where you want to learn the weather situation, thanks to the " +
                 "weather panel, which we can also access from the menu. \n" + "\t\t\t>>> You can access the" +
                 " information" + " below by entering the name of the country you want to learn the weather in the text " +
                 "box at the top and clicking the update button below. \n" );
      }

   }

   public User getLoginUser() {
      for( User u : Users.getInstance().getUserList() ) {
         if( u.getEnter().equals( "true" ) ) {
            loginUser = u;
            return u;
         }
      }
      return null;
   }

   @FXML
   void mainButtonOnAction( ActionEvent event ) {
      if( event.getSource() == helpExitButton ) {
         closeAllMainPanes();
         helpExitPane.setVisible( true );
      } else if( event.getSource() == helpMenuButton ) {
         closeAllMainPanes();
         helpMenuPane.setVisible( true );
      } else if( event.getSource() == helpUserProfileButton ) {
         closeAllMainPanes();
         helpUserProfilePane.setVisible( true );
      } else if( event.getSource() == helpSettingsButton ) {
         closeAllMainPanes();
         helpSettingsPane.setVisible( true );
      } else if( event.getSource() == goBackFromLogout || event.getSource() == goBackFromUserProfile || event.getSource() == goBackFromMenu || event.getSource() == goBackFromSettings ) {
         closeAllMainPanes();
         helpMainPane.setVisible( true );
      }
   }

   private void closeAllMainPanes() {
      helpMainPane.setVisible( false );
      helpExitPane.setVisible( false );
      helpUserProfilePane.setVisible( false );
      helpMenuPane.setVisible( false );
      helpSettingsPane.setVisible( false );
   }

   @FXML
   void settingButtonOnAction( ActionEvent event ) {
      if( event.getSource() == applicationSettingButton ) {
         closeAllMainPanes();
         applicationSettingPane.setVisible( true );
      } else if( event.getSource() == modsSettingButton ) {
         closeAllMainPanes();
         usersSettingPane.setVisible( true );
      } else if( event.getSource() == usersSettingButton ) {
         closeAllMainPanes();
         modsSettingPane.setVisible( true );
      } else if( event.getSource() == homeSettingButton ) {
         closeAllMainPanes();
         homeSettingPane.setVisible( true );
      } else if( event.getSource() == goBackFromApplicationSetting ) {
         applicationSettingPane.setVisible( false );
         helpSettingsPane.setVisible( true );
      } else if( event.getSource() == goBackFromUsersSetting ) {
         usersSettingPane.setVisible( false );
         helpSettingsPane.setVisible( true );
      } else if( event.getSource() == goBackFromModsSetting ) {
         modsSettingPane.setVisible( false );
         helpSettingsPane.setVisible( true );
      } else if( event.getSource() == goBackFromHomeSetting ) {
         homeSettingPane.setVisible( false );
         helpSettingsPane.setVisible( true );
      }
   }

}
