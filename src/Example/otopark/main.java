/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Example.otopark;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alper Tolak
 */
public class main {

    private static Scanner scanner = new Scanner(System.in);
    private static LinkedList<AracPojo> aracListe = new LinkedList<AracPojo>();

    public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException {

        aracIslem();
    }

    public static void aracIslem() throws InterruptedException, FileNotFoundException, IOException {

        aracListe = dataHandler.dataReader();

        getMesaj();

        String islem;

        while (true) {
            System.out.println("?------------------------------------------------");
            System.out.print("lütfen yapmak istediğiniz işlemi yazınız = ");
            islem = scanner.nextLine();
            System.out.println("------------------------------------------------?");

            if (islem.equals("q") || islem.equals("Q")) {
                System.out.println("sistem kapatılıyor...");
                Thread.sleep(1000);
                System.out.println("---^");
                System.out.println("Sistem Kapatıldı...");
                System.exit(0);
            } else if (islem.equals("1")) {
                aracListesi();
            } else if (islem.equals("2")) {
                aracSorgu();
            } else if (islem.equals("3")) {
                aracEkle();
            } else if (islem.equals("4")) {
                aracCikart();
            } else {
                System.out.println("geçersiz işlem girdiniz.");
            }
        }
    }
    
    private static void aracListesi() {

        
        int i = 1;
        System.out.println("#1-----------------------------------------------\n"
                + "Aktif kayıtlı araç listesi:");

        for (AracPojo a : aracListe) {

            System.out.println("---^");
            System.out.print(i + " ==> ");
            System.out.println(a);

            i++;
        }
        System.out.println("-----------------------------------------------#1");
    }

    private static void aracSorgu() throws InterruptedException {
        String aracPlaka;
        System.out.println("#2-----------------------------------------------");
        while (true) {
            System.out.print("sorgulamak istediğiniz aracın plakasını giriniz (iptal için q) = ");
            aracPlaka = scanner.nextLine();
            System.out.println("---^");

            if (aracPlaka.equals("q") || aracPlaka.equals("Q")) {
                System.out.println("işlem iptal ediliyor...");
                Thread.sleep(300);
                System.out.println("---^");
                System.out.println("işlem iptal edildi.");
                System.out.println("-----------------------------------------------#2");
                break;
            }
            try {
                if (AracPojo.aracCheck.PlakaCheck(aracPlaka)) {

                    for (AracPojo a : aracListe) {
                        if (a.getAracPlk().equals(aracPlaka)) {
                            System.out.println("? ==> " + a);
                            System.out.println("---^");
                            break;
                        }
                    }
                } else {
                    System.out.println("girdiğiniz plaka geçersiz.");
                    System.out.println("---^");
                }
            } catch (Exception e) {
                System.out.println("girdiğiniz plaka geçersiz.");
                System.out.println("---^");
            }
        }
    }

    private static void aracEkle() throws InterruptedException, IOException {

        //Kayıt işlemi için gerekli olan geçiş anahtarları tanımlanıyor.
        boolean kytPass = false;
        boolean aracPlakaPass = true;
        boolean aracTipPass = true;
        boolean aracBeklemeSuresiPass = true;

        //Kullanıcıdan girilen verilerin tutulması için değişkenler oluşturuluyor.
        Date AracGiris;
        AracPojo yeniArac = null;
        Integer aracBeklemeSuresi = 0;
        String aracPlaka = null;
        String aracTip = null;
        String kytislem = null;
        Boolean IslemIptal = false;

        System.out.println("#3-----------------------------------------------");
        System.out.println("Arac Eklemek için gerekli Bilgiler = (Araç Plakası, Araç Tipi, Araç Bekleme Süresi)(çıkmak için q)");
        System.out.println("---^");
        //Bütün anahtarlar true göstere kadar dönücek sonsuz döngü açılıyor.
        while (true) {

            //En son kayıt anahtarıda true dönerse sonsuz döngü kırılıyor.
            if (kytPass == true) break;
            
            //girilen araç plakasının geçerliliği kontrol ediliyor.
            while (aracPlakaPass) {

                System.out.print("Aracın Plakasını giriniz = ");
                aracPlaka = scanner.nextLine();
                System.out.println("---^");
                try {
                    if (aracPlaka.equals("q") || aracPlaka.equals("Q")) {
                        IslemIptal = true;
                        break;
                    } else {

                        boolean ayniPlakaPass = false;

                        if (AracPojo.aracCheck.PlakaCheck(aracPlaka) == true) {
                            for (AracPojo a : aracListe) {
                                for (int i = 0; i < aracListe.size(); i++) {

                                    if (aracPlaka.equals(a.getAracPlk())) {
                                        ayniPlakaPass = true;
                                    }
                                }
                            }
                            if (ayniPlakaPass == true) {
                                System.out.println("girdiğiniz plaka sistemden kayıtlıdır.");
                                System.out.println("---^");
                                ayniPlakaPass = false;
                            } else {
                                break;
                            }

                        } else {
                            System.out.println("girdiğiniz plaka geçersizdir...\n---^");
                        }
                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("girdiğiniz plaka geçersizdir...\n---^");
                }
            }
            if (IslemIptal == true) {
                System.out.println("işlem iptal ediliyor...");
                Thread.sleep(700);
                System.out.println("---^");
                System.out.println("işlem iptal edildi.");
                break;
            }

            //girilen bekleme suresinin sayı olup olmadığı kontrol ediliyor.
            while (aracBeklemeSuresiPass) {
                System.out.print("Aracın ne kadar bekleyeceğini giriniz(saat) = ");
                try {
                    String geciciString;
                    geciciString = scanner.nextLine();
                    if (geciciString.equals("q") || geciciString.equals("Q")) {
                        System.out.println("---^");
                        IslemIptal = true;
                        break;
                    } else {
                        aracBeklemeSuresi = Integer.parseInt(geciciString);
                        System.out.println("---^");
                        break;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("---^\nlütfen bir sayı giriniz...\n---^");
                }
            }
            if (IslemIptal == true) {
                System.out.println("işlem iptal ediliyor...");
                Thread.sleep(700);
                System.out.println("---^");
                System.out.println("işlem iptal edildi.");
                break;
            }
            //Girilen araç tipinin geçerli olup olmadığı kontrol ediliyor. 
            while (aracTipPass) {
                System.out.print("Aracın tipini giriniz = ");
                aracTip = scanner.nextLine();
                System.out.println("---^");

                if (aracTip.equals("q") || aracPlaka.equals("Q")) {
                    IslemIptal = true;
                    break;

                } else if (AracPojo.aracCheck.TypeCheck(aracTip)) {
                    break;

                } else {
                    System.out.println("lütfen geçerli bir araç tipi giriniz...\n---^");
                }
            }
            if (IslemIptal == true) {
                System.out.println("işlem iptal ediliyor...");
                Thread.sleep(700);
                System.out.println("---^");
                System.out.println("işlem iptal edildi.");
                break;
            }
            while (true) {
                //kayıt işlemini gerçekleştirmek için netleştirme alınıyor.
                System.out.print("kaydı gerçekleştirmek istiyor musunuz? (Y/N)");
                kytislem = scanner.nextLine();
                System.out.println("---^");

                //Gelen veriye göre gerekli işlem gerçekleştiriliyor.
                if (kytislem.equals("y") || kytislem.equals("Y")) {
                    System.out.println("araç kaydediliyor...");
                    Thread.sleep(700);
                    System.out.println("---^");
                    System.out.println("araç kaydedildi.");
                    System.out.println("-----------------------------------------------#3");

                    //alınan bütün bilgilerle bir AracPojo nesnesi üretiriliyor.
                    yeniArac = new AracPojo(aracPlaka, aracTip, aracBeklemeSuresi);

                    //gelen arac verileri dosyaya kaydediliyor.
                    dataHandler.dataWriter(yeniArac);

                    //üretilen AracPojo nesnesi aracListe<AracPojo> listesine ekleniyor.
                    aracListe.add(yeniArac);
                    kytPass = true;
                    break;

                } else if (kytislem.equals("n") || kytislem.equals("N")) {
                    System.out.println("işlem iptal ediliyor...");
                    Thread.sleep(700);
                    System.out.println("-----------------------------------------------#3");
                    kytPass = true;
                    break;
                } else {
                    System.out.println("geçersiz işlem.");
                    System.out.println("---^");
                }
            }
        }
    }

    private static void aracCikart() throws IOException, InterruptedException {

        boolean cikartPass = false;
        String aracPlaka;

        System.out.println("#4-----------------------------------------------");

        while (cikartPass == false) {

            System.out.print("çıkatmak istediğiniz aracın plakasını giriniz(iptal için q) = ");
            aracPlaka = scanner.nextLine();
            System.out.println("---^");
            if (aracPlaka.equals("q") || aracPlaka.equals("Q")) {
                System.out.println("işlem iptal ediliyor...");
                Thread.sleep(700);
                System.out.println("---^");
                System.out.println("işlem iptal edildi.");
                System.out.println("-----------------------------------------------#4");
                break;
            }
            try {
                if (AracPojo.aracCheck.PlakaCheck(aracPlaka)) {
                    for (AracPojo a : aracListe) {

                        if (a.getAracPlk().equals(aracPlaka)) {
                            System.out.println("? ==> " + a);
                            System.out.println("---^");
                            while (true) {
                                System.out.print("bu aracı çıkartmak istediğinize emin misiniz? (Y/N) = ");
                                String islem = scanner.nextLine();
                                System.out.println("---^");
                                if (islem.equals("y") || islem.equals("Y")) {
                                    System.out.println("araç çıkartılıyor...");
                                    aracListe.remove(a);
                                    dataHandler.dataRemowe(aracListe);
                                    Thread.sleep(600);
                                    System.out.println("---^");
                                    System.out.println("araç çıkartıldı.");
                                    System.out.println("-----------------------------------------------#4");
                                    cikartPass = true;
                                    break;

                                } else if (islem.equals("n") || islem.equals("N")) {
                                    System.out.println("işlem iptal ediliyor...");
                                    System.out.println("---^");
                                    Thread.sleep(500);
                                    System.out.println("işlem iptal edildi.");
                                    System.out.println("-----------------------------------------------#4");
                                    cikartPass = true;
                                    break;
                                } else {
                                    System.out.println("geçersiz işlem.");
                                    System.out.println("---^");
                                }
                            }
                        }
                    }
                } else {
                    System.out.println("girdiğiniz plaka geçersiz.");
                    System.out.println("---^");
                }
            } catch (ConcurrentModificationException ex) {
            } catch (InterruptedException ex) {
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("girdiğiniz plaka geçersiz.");
                System.out.println("---^");
            }
        }
    }

    private static void getMesaj() {
        String mesaj = "Otopark Sistemine Hoşgediniz.\n"
                + "yapabileceğiniz işlemler aşağıdaki gibidir: \n"
                + "!------------------------------------------------\n"
                + "Otoparktaki Kayıtlı Araç Listesi = 1\n"
                + "Araç sorgula = 2\n"
                + "Araç ekle = 3\n"
                + "Araç çıkart = 4\n"
                + "Sistemden çıkmak = q\n"
                + "------------------------------------------------!";
        System.out.println(mesaj);
    }

    public static class dataHandler {

        public static LinkedList<AracPojo> dataReader() throws FileNotFoundException {

            //açıklama yazılamayacak kadar karışık.
            AracPojo aracPojo;

            LinkedList<AracPojo> aracListe = new LinkedList<>();

            String crudeData = "";
            String[] aracData = {" ", " ", " "};
            String[] handledData;

            Scanner scanner = new Scanner(new BufferedReader(new FileReader("Veri.txt")));

            while (scanner.hasNextLine()) {
                crudeData += scanner.nextLine();
            }
            handledData = crudeData.split("&");
            for (int i = 0; i < handledData.length; i++) {
                aracData = handledData[i].split(",");
                aracPojo = new AracPojo(aracData[0], aracData[1], Integer.parseInt(aracData[2]));
                aracListe.add(aracPojo);
            }
            return aracListe;
        }

        public static void dataWriter(AracPojo arac) throws IOException {

            FileWriter fw = new FileWriter("Veri.txt", true);
            fw.write(arac.getWriterContent());
            fw.close();
        }

        public static void dataRemowe(LinkedList<AracPojo> YeniAracListe) throws IOException {

            FileWriter fw = new FileWriter("Veri.txt");
            for (AracPojo a : YeniAracListe) {
                fw.write(a.getWriterContent());
            }
            fw.close();
        }
    }
}
