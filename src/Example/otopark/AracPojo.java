package Example.otopark;

import java.util.LinkedList;

public class AracPojo {

    private String aracPlk;
    private Integer aracUcret;
    private String aracTip;
    private final Integer aracBeklemeSuresi;
    private final Integer aracHatchBackTarife = 10;
    private final Integer aracSedanTarife = 12;
    private final Integer aracSuvTarife = 15;
    private final Integer aracPanelVanTarife = 20;

    public static void main(String[] args) {
        System.out.println();

    }

    public AracPojo(String aracPlk, String aracTip, Integer aracBeklemeSuresi) {

        this.aracPlk = aracPlk;
        this.aracBeklemeSuresi = aracBeklemeSuresi;
        this.aracTip = layOut.totalLayOut(aracTip);

        //ücretin belirlenmesi için kullanılan komut kısmı.
        if (layOut.totalLayOut(aracTip).equals("HatchBack")) {
            this.aracUcret = aracHatchBackTarife * aracBeklemeSuresi;
        }
        if (layOut.totalLayOut(aracTip).equals("Sedan")) {
            this.aracUcret = aracSedanTarife * aracBeklemeSuresi;
        }
        if (layOut.totalLayOut(aracTip).equals("Suv")) {
            this.aracUcret = aracSuvTarife * aracBeklemeSuresi;
        }
        if (layOut.totalLayOut(aracTip).equals("PanelVan")) {
            this.aracUcret = aracPanelVanTarife * aracBeklemeSuresi;
        }
    }

    public String getAracPlk() {
        return aracPlk;
    }

    public void setAracPlk(String aracPlk) {
        this.aracPlk = aracPlk;
    }

    public Integer getAracUcret() {
        return aracUcret;
    }

    public String getAracTip() {
        return aracTip;
    }

    public Integer getAracBeklemeSuresi() {
        return aracBeklemeSuresi;
    }

    public void setAracTip(String aracTip) {

        this.aracTip = aracTip;
    }

    @Override
    public String toString() {

        return "Arac Plaka = " + this.aracPlk + " || Ödenecek Ücret = " + this.aracUcret + " || Bekleme Süresi = " + this.aracBeklemeSuresi + " || arac Tip = " + this.aracTip;
    }

    public String getWriterContent() {
        String string = getAracPlk() + "," + getAracTip() + "," + getAracBeklemeSuresi() + "&\n";
        return string;
    }

    public static class layOut {

        public static String totalLayOut(String aracTip) {

            //döndürülecek değerin tutulacağı değişken tanımlanıyor.
            String response = null;

            //kontrol edilcek doğru kelimeler array içine kaydediliyor.
            String[] HatchbackArray = {"hatchback", "Hatchback", "hatchBack", "HatchBack", "HATCHBACK"};
            String[] sedanArray = {"sedan", "Sedan", "SEDAN"};
            String[] suvArray = {"suv", "Suv", "SUV"};
            String[] panelvanArray = {"panelvan", "Panelvan", "PanelVan", "panelVan", "PANELVAN"};

            //aracTip hatchback ise bu düzenleme bloğuna giriyor.
            for (int i = 0; i < HatchbackArray.length; i++) {
                if (aracTip.equals(HatchbackArray[i])) {
                    response = "HatchBack";

                }
            }

            //aracTip sedan ise bu düzenleme bloğuna giriyor.
            for (int i = 0; i < sedanArray.length; i++) {
                if (aracTip.equals(sedanArray[i])) {
                    response = "Sedan";

                }
            }

            //aracTip suv ise bu düzenleme bloğuna giriyor.
            for (int i = 0; i < suvArray.length; i++) {
                if (aracTip.equals(suvArray[i])) {
                    response = "Suv";

                }
            }

            //aracTip tip panelvan ise bu düzenleme bloğuna giriyor.
            for (int i = 0; i < panelvanArray.length; i++) {
                if (aracTip.equals(panelvanArray[i])) {
                    response = "PanelVan";

                }
            }
            return response;
        }
    }

    public static class aracCheck {

        public static boolean TypeCheck(String aracTip) {

            boolean typePass = false;
            String[] gecerliAracTipArray = {"sedan", "Sedan", "SEDAN", "hatchback", "Hatchback", "hatchBack",
                "HatchHack", "HATCHBACK", "suv", "Suv", "SUV", "panelvan", "Panelvan", "PanelVan", "panelVan", "PANELVAN"};

            for (int i = 0; i < gecerliAracTipArray.length; i++) {
                if (aracTip.equals(gecerliAracTipArray[i])) {
                    typePass = true;
                }
            }
            return typePass;
        }

        public static boolean PlakaCheck(String aracPlaka) {

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
            boolean firstPassTotal = false;
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
                firstPassTotal = true;

            } else if (firstPass1 == 1 && firstPass2 == 1) {
                firstPassTotal = true;

            } else {
                firstPassTotal = false;

            }

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

            //üç bölümden dönen değerlere göre plakanın, geçerli veya geçersiz olduğu hesaplanıyor.
            if (firstPassTotal == true && midPassTotal == true && lastPassTotal == true) {
                return true;
            } else {
                return false;
            }
        }
    }
}
