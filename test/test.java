
import Example.otopark.AracPojo;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ali Tolak
 */
public class test {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
//        LinkedList<AracPojo> aracListe = new LinkedList<>();
        
//        AracPojo arac1 = new AracPojo("34 dju 64", "sedan", 3);
//        AracPojo arac2 = new AracPojo("33 dbs 1267", "hatchback", 2);
//        AracPojo arac3 = new AracPojo("34 hte 638", "suv", 1);
//        AracPojo arac4 = new AracPojo("34 ay 620", "panelvan", 4);
//        
//        aracListe.add(arac1);
//        aracListe.add(arac2);
//        aracListe.add(arac3);
//        aracListe.add(arac4);
//        
//        dataWriter(aracListe);
//        aracListe.remove(0);
//        dataRemower(aracListe);
//        aracListe.remove(1);
//        dataRemower(aracListe);
//        
        
        if (true) System.out.println("alpertolsak");
        
        
    }
    public static void dataRemower(LinkedList<AracPojo> YeniAracListe) throws IOException{
        
        FileWriter fw = new FileWriter("VeriTest.txt");
        for(AracPojo a: YeniAracListe){
            fw.write(a.getWriterContent());
        }
        fw.close();
    }
    public static void dataWriter(LinkedList<AracPojo> aracListe) throws IOException {

        FileWriter fw = new FileWriter("VeriTest.txt", true);
        for(AracPojo a: aracListe){
            fw.write(a.getWriterContent());
        }
        fw.close();
    }
    public static LinkedList<AracPojo> dataReader() throws FileNotFoundException {

        //açıklama yazılamayacak kadar karışık.
        AracPojo aracPojo;

        LinkedList<AracPojo> aracListe = new LinkedList<>();

        String crudeData = "";
        String[] aracData = {" ", " ", " "};
        String[] handledData;

        Scanner scanner = new Scanner(new BufferedReader(new FileReader("VeriTest.txt")));

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
    public static void aracPlakaCheck(String aracPlaka) {

        //kontrol amaçlı kullanılacak sayılar Character dizisine kaydediliyor.
        Character[] sayilar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        //plakanın orta kısmında geçen, geçerli Character dizisine kaydediliyor.
        Character[] gecerliPlkHarfleri = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k',
            'l', 'm', 'n', 'o', 'p', 'r', 's', 't', 'u', 'v', 'y', 'z'};

        //plaka bilgisi parçalarına ayrılıyor.
        String[] plaqueSections = aracPlaka.split(" ");

        //plaka bölümlerinin karakterinin tutulacağı Character tipinde bağlı listeler oluşturuluyor.
        LinkedList<Character> firstSection = new LinkedList<Character>();
        LinkedList<Character> midSection = new LinkedList<Character>();
        LinkedList<Character> lastSection = new LinkedList<Character>();

        //ilk kısımın bilgileri dizi içine alınıyor.
        for (int i = 0; i < plaqueSections[0].length(); i++) {
            firstSection.add(plaqueSections[0].charAt(i));
        }

        //orta kısımın bilgileri dizi içine alınıyor.
        for (int i = 0; i < plaqueSections[1].length(); i++) {
            midSection.add(plaqueSections[1].charAt(i));
        }

        //son bölümün boş olup olamadığı try bloğuyla kontrol ediliyor.
        boolean lastPassOut = false;
        try {
            //son kısımın bilgileri dizi içine alınıyor.
            for (int i = 0; i < plaqueSections[2].length(); i++) {
                lastSection.add(plaqueSections[2].charAt(i));

                //son kısım dolu ise 'lastPassOut = true' yapılıyor.
                lastPassOut = true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {

            //son kısım boş ise 'lastPassOut = false' yapılıyor.
            lastPassOut = false;
        }

        //İLK BÖLÜM
        //ilk bölüm için boolean tipinde kontrol anahtarları tanımlanıyor.
        int firstPass1 = 0;
        int firstPass2 = 0;
        boolean firtPassTotal = false;
        boolean limit;

        //ilk bölüm karakteri kontrol ediliyor.
        //ilk bölümün karakterleri sayıdan oluşup oluşmadığı kontrol ediliyor.
        try {

            //karakterler sayı ise 'limit = true' yapılıyor.
            Integer.parseInt(plaqueSections[0]);
            limit = true;
        } catch (NumberFormatException e) {

            //karakterler sayı değilde 'limit = false' yapılıyor.
            limit = false;
        }

        //ilk bölümün 81 den büyük olup olmadığı kontrol ediliyor.
        if (limit && Integer.parseInt(plaqueSections[0]) <= 81) {

            //ilk bölüm tek karakterse bu if bloğuna giriyor.
            if (firstSection.size() == 1) {
                for (int i = 0; i < sayilar.length; i++) {
                    if (firstSection.get(0) == sayilar[i]) {
                        firstPass1 = 1;
                        break;
                    } else {
                        firstPass1 = 0;
                        firstPass2 = -1;
                    }
                }
            }
            //ilk bölüm iki karaterse bu if bloğuna giriyor.
            if (firstSection.size() == 2) {
                for (int i = 0; i < sayilar.length; i++) {
                    firstPass2 = -1;
                    if (firstSection.get(0) == sayilar[i]) {
                        firstPass1 = 1;
                        break;
                    } else {
                        firstPass1 = 0;
                    }
                }
                for (int i = 0; i < sayilar.length; i++) {
                    firstPass2 = -1;
                    if (firstSection.get(1) == sayilar[i]) {
                        firstPass2 = 1;
                        break;
                    } else {
                        firstPass2 = 0;

                    }
                }
            }
            if (firstSection.size() == 0) {
                firstPass1 = -1;
                firstPass2 = -1;
            }

        } else {
            firstPass1 = 0;
            firstPass2 = 0;
        }

        //gelen anahtar bilgilerine göre hesap yapılıyor.
        if (firstPass1 == 1 && firstPass2 == -1) {
            firtPassTotal = true;

        } else if (firstPass1 == 1 && firstPass2 == 1) {
            firtPassTotal = true;

        } else {
            firtPassTotal = false;

        }

        System.out.println("firtPassTotal = " + firtPassTotal);

        //ORTA BÖLÜM
        //orta bölüm için boolean tipinde kontrol anahtarları tanımlanıyor.
        int midPass1 = 0;
        int midPass2 = 0;
        int midPass3 = 0;
        boolean midPassTotal = false;

        //orta bölümün karakteri kontrol ediliyor.
        //orta bölüm tek karakterse bu if bloğuna giriyor.
        if (midSection.size() == 1) {
            for (int i = 0; i < gecerliPlkHarfleri.length; i++) {
                if (midSection.get(0).equals(gecerliPlkHarfleri[i])) {
                    midPass1 = 1;
                    break;
                } else {
                    midPass1 = 0;
                    midPass2 = -1;
                    midPass3 = -1;

                }
            }
        }
        //orta bölüm iki karaterse bu if bloğuna giriyor.
        if (midSection.size() == 2) {
            for (int i = 0; i < gecerliPlkHarfleri.length; i++) {
                midPass3 = -1;
                if (midSection.get(0).equals(gecerliPlkHarfleri[i])) {
                    midPass1 = 1;
                    break;
                } else {
                    midPass1 = 0;
                }
            }
            for (int i = 0; i < gecerliPlkHarfleri.length; i++) {
                midPass3 = -1;
                if (midSection.get(1).equals(gecerliPlkHarfleri[i])) {
                    midPass2 = 1;
                    break;
                } else {
                    midPass2 = 0;

                }
            }
        }

        //orta bölüm üç karakterse bu if bloğuna giriyor.
        if (midSection.size() == 3) {
            for (int i = 0; i < gecerliPlkHarfleri.length; i++) {
                if (midSection.get(0).equals(gecerliPlkHarfleri[i])) {
                    midPass1 = 1;
                    break;
                } else {
                    midPass1 = 0;
                }
            }
            for (int i = 0; i < gecerliPlkHarfleri.length; i++) {
                if (midSection.get(1).equals(gecerliPlkHarfleri[i])) {
                    midPass2 = 1;
                    break;
                } else {
                    midPass2 = 0;
                }
            }
            for (int i = 0; i < gecerliPlkHarfleri.length; i++) {
                if (midSection.get(2).equals(gecerliPlkHarfleri[i])) {
                    midPass3 = 1;
                    break;
                } else {
                    midPass3 = 0;
                }
            }
        }
        if (midSection.size() == 0) {
            midPass1 = -1;
            midPass2 = -1;
            midPass3 = -1;
        }
        //gelen anahtar bilgilerine göre hesap yapılıyor.
        if (midPass1 == 1 && midPass2 == -1 && midPass3 == -1) {
            midPassTotal = true;

        } else if (midPass1 == 1 && midPass2 == 1 && midPass3 == -1) {
            midPassTotal = true;

        } else if (midPass1 == 1 && midPass2 == 1 && midPass3 == 1) {
            midPassTotal = true;

        } else {
            midPassTotal = false;

        }
        System.out.println("midPassTotal = " + midPassTotal);

        //SON BÖLÜM
        //son bölüm için boolean tipinde kontrol anahtarları tanımlanıyor.
        int lastPass1 = 0;
        int lastPass2 = 0;
        int lastPass3 = 0;
        int lastPass4 = 0;
        boolean lastPassTotal = false;

        //son bölümün karakteri kontrol ediliyor.
        //son bölümün karakter uzunluğunun 4 karakterden büyük olup olmadığı kontrol ediliyor.
        if (lastSection.size() <= 4) {

            //son bölümün boş olup olmadığı kontrol ediliyor.
            if (!lastPassOut) {
                lastPass1 = -1;
                lastPass2 = -1;
                lastPass3 = -1;
                lastPass4 = -1;
            } else {

                //son bölüm tek karakterse bu if bloğuna giriyor.
                if (lastSection.size() == 1) {
                    for (int i = 0; i < sayilar.length; i++) {
                        lastPass2 = -1;
                        lastPass3 = -1;
                        lastPass4 = -1;
                        if (lastSection.get(0) == sayilar[i]) {
                            lastPass1 = 1;
                            break;
                        } else {
                            lastPass1 = 0;

                        }
                    }
                }
                //son bölüm iki karaterse bu if bloğuna giriyor.
                if (lastSection.size() == 2) {
                    for (int i = 0; i < sayilar.length; i++) {
                        lastPass3 = -1;
                        lastPass4 = -1;
                        if (lastSection.get(0) == sayilar[i]) {
                            lastPass1 = 1;
                            break;
                        } else {
                            lastPass1 = 0;
                        }
                    }
                    for (int i = 0; i < sayilar.length; i++) {
                        lastPass3 = -1;
                        lastPass4 = -1;
                        if (lastSection.get(1) == sayilar[i]) {
                            lastPass2 = 1;
                            break;
                        } else {
                            lastPass2 = 0;

                        }
                    }
                }

                //orta bölüm üç karakterse bu if bloğuna giriyor.
                if (lastSection.size() == 3) {
                    for (int i = 0; i < sayilar.length; i++) {
                        lastPass4 = -1;
                        if (lastSection.get(0) == sayilar[i]) {
                            lastPass1 = 1;
                            break;
                        } else {
                            lastPass1 = 0;
                        }
                    }
                    for (int i = 0; i < sayilar.length; i++) {
                        lastPass4 = -1;
                        if (lastSection.get(1) == sayilar[i]) {
                            lastPass2 = 1;
                            break;
                        } else {
                            lastPass2 = 0;
                        }
                    }
                    for (int i = 0; i < sayilar.length; i++) {
                        if (lastSection.get(2) == sayilar[i]) {
                            lastPass3 = 1;
                            break;
                        } else {
                            lastPass3 = 0;
                        }
                    }
                }
                //orta bölüm dört karakterse bu if bloğuna giriyor.
                if (lastSection.size() == 4) {
                    for (int i = 0; i < sayilar.length; i++) {
                        if (lastSection.get(0) == sayilar[i]) {
                            lastPass1 = 1;
                            break;
                        } else {
                            lastPass1 = 0;
                        }
                    }
                    for (int i = 0; i < sayilar.length; i++) {
                        if (lastSection.get(1) == sayilar[i]) {
                            lastPass2 = 1;
                            break;
                        } else {
                            lastPass2 = 0;
                        }
                    }
                    for (int i = 0; i < sayilar.length; i++) {
                        if (lastSection.get(2) == sayilar[i]) {
                            lastPass3 = 1;
                            break;
                        } else {
                            lastPass3 = 0;
                        }
                    }
                    for (int i = 0; i < sayilar.length; i++) {
                        if (lastSection.get(3) == sayilar[i]) {
                            lastPass4 = 1;
                            break;
                        } else {
                            lastPass4 = 0;
                        }
                    }
                }
            }
        } else {
            lastPass1 = -1;
            lastPass2 = -1;
            lastPass3 = -1;
            lastPass4 = -1;
        }

        //gelen anahtar bilgilerine göre hesap yapılıyor.
        if (lastPass1 == 1 && lastPass2 == -1 && lastPass3 == -1 && lastPass4 == -1) {
            lastPassTotal = true;

        } else if (lastPass1 == 1 && lastPass2 == 1 && lastPass3 == -1 && lastPass4 == -1) {
            lastPassTotal = true;

        } else if (lastPass1 == 1 && lastPass2 == 1 && lastPass3 == 1 && lastPass4 == -1) {
            lastPassTotal = true;

        } else if (lastPass1 == 1 && lastPass2 == 1 && lastPass3 == 1 && lastPass4 == 1) {
            lastPassTotal = true;

        } else {
            lastPassTotal = false;

        }
        System.out.println("lastPassTotal = " + lastPassTotal);
    }
}
