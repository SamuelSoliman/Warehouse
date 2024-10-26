
import Classes.Categoria;
import Classes.Pallet;
import Classes.Prodotto;
import Classes.Scaffale;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class MagazzinoApp {

    static List<Prodotto> prodotti;
    static List<Categoria> categorie;
    static List<Scaffale> scafs;
    static List<Pallet> palls;

    public static void main(String[] args) {
        prodotti = new ArrayList<>();
        categorie = new ArrayList<>();
        scafs = new ArrayList<>();
        palls = new ArrayList<>();

        System.out.println("Benvenuto in Magazzino Manager 2000");

        String cmd = "";
        do {
            System.out.print("Inserire un comando(help per vedere la lista di comandi): ");
            cmd = readInput();

            switch (cmd) {
                case "help":
                    System.out.println("Questi sono i comandi disponibili");
                    System.out.println("add-product: Aggiungi un nuovo prodotto a sistema");
                    System.out.println("list-proudcts: per stampare tutti prodotti");
                    System.out.println("add-category: Aggiungi una nuova categoria a sistema");
                    System.out.println("list-categories: Visualizza categorie a sistema");
                    System.out.println("add-scaffale: Aggiungi un nuovo scaffale a sistema");
                    System.out.println("list-scaffale: Visualizza scaffale a sistema");
                    System.out.println("add-pallet: Aggiungi un nuovo pallet a sistema");
                    System.out.println("exit: Termina l'esecuzione");

                    break;
                case "exit":
                    System.out.println("Arrivederci!");
                    break;
                case "add-product":
                    addProduct();
                    break;
                case "list-products":
                    printProducts();
                    break;
                case "add-category":
                    addCategory();
                    break;
                case "list-categories":
                    printCategories();
                    break;
                case "add-scaffale":
                    addScaffale();
                    break;
                case "list-scaffale":
                    printScafs();
                    break;
                case "add-pallet":
                    addPallet();
                    break;
                default:
                    System.out
                            .println("Commando non trovato. Per la lista di commandi scrivere help o exit per uscire");
                    break;
            }
        } while (!cmd.equalsIgnoreCase("exit"));
    }

    private static void addProduct() {
        System.out.println("Inizio procedura inserimento prodotto");
        boolean addmoreProd = true;
        while (addmoreProd) {
            String idString;
            int id = 0;
            boolean acceptedId = false;
            while (!acceptedId) {
                System.out.print("Id: ");
                idString = readInput();
                try {

                    id = Integer.parseInt(idString);
                    acceptedId = true;
                } catch (Exception e) {
                    System.out.println("L'id è obbligatorio e deve essere numerico");
                }
            }
            boolean idStatues = checkId(id, prodotti);
            while (idStatues == true) {
              
              System.out.println("Questo id è utilizzato da un altro prodotto, scegliere un altro id.");
              acceptedId=false;
              while (!acceptedId){
              try{
                System.out.print("Id: ");
                idString = readInput();
                id = Integer.parseInt(idString);
              }
              catch(Exception e){
                System.out.println("L'id è obbligatorio e deve essere numerico");
              }
            }
                idStatues = checkId(id, prodotti);

            }
            System.out.print("Nome: ");
            String nome = readInput();
            System.out.print("Descrizione Breve: ");
            String descrBreve = readInput();
            if (categorie.size() > 0) {
                System.out.println("Vuoi inserire una Categoria per il prodotto (y/n)?");
                String catres = readInput();
                // char catresponse = catres.charAt(0);
                if (catres.equalsIgnoreCase("y")) {

                    System.out.println("Vuoi vedere la lista dei categorie (y/n)?");
                    String listres = readInput();
                    // char listresponse = listres.charAt(0);
                    if (listres.equalsIgnoreCase("y")) {
                        printCategories();
                    } // listresponse end
                    String categoriaUser = " Senza Categoria";
                    System.out.print("Categoria Nome: ");
                    categoriaUser = readInput();
                    // findCategoryByName
                    int i = findCategoryByName(categoriaUser);
                    if (i != -1) {
                        System.out.println("prodotto è agguinto sotto la categoria : " + categoriaUser);
                        Categoria resultCat = categorie.get(i);
                        Prodotto newProdotto = new Prodotto(id, nome, descrBreve, resultCat);
                        prodotti.add(newProdotto);
                    } else if (categorie.size() > 0 && i == -1) {
                        System.out.println("Cateogria Non esiste, prodotto è agguinto senza categoria ");
                        Prodotto newProdotto = new Prodotto(id, nome, descrBreve);
                        prodotti.add(newProdotto);
                    }

                } else {// catreponse end
                    System.out.println("prodotto è agguinto senza categoria ");
                    Prodotto newProdotto = new Prodotto(id, nome, descrBreve);
                    prodotti.add(newProdotto);
                }
            } // categore.size>0 end
            else {
                System.out.println(
                        " La lista delle categorie è vuota, aggunigi categorie nella lista, prodotto è agguinto senza categoria ");
                Prodotto newProdotto = new Prodotto(id, nome, descrBreve);
                prodotti.add(newProdotto);
            }
            System.out.println("Vuole mettre alro prodotto?(y/n)");
            String altroProdString = readInput();
            // char altroProd = altroProdString.charAt(0);
            if (altroProdString.equalsIgnoreCase("y")) {
                addmoreProd = true;
            } else {
                addmoreProd = false;
            }

        }
    }

    private static void addPallet() {
        System.out.println("Inizio procedura inserimento pallet");
        boolean addmorePall = true;
        if (!prodotti.isEmpty() && !scafs.isEmpty()) {
            while (addmorePall) {

                String idString;
                int id = 0;
                boolean acceptedId = false;
                while (!acceptedId) {
                    System.out.print("Id: ");
                    idString = readInput();
                    try {

                        id = Integer.parseInt(idString);
                        acceptedId = true;
                    } catch (Exception e) {
                        System.out.println("L'id è obbligatorio e deve essere numerico");
                    }
                }
                boolean idStatues = checkIdPall(id, palls);
                while (idStatues == true) {
                    System.out.println("Questo id è utilizzato da un altro pallet, sceglia un altro id.");
                    System.out.print("Id: ");
                    idString = readInput();
                    id = Integer.parseInt(idString);
                    idStatues = checkId(id, prodotti);

                }
                System.out.print("Nome: ");
                String nome = readInput();
                System.out.println("Vuole vedere la list di Scaffale prima di scgliere uno per il nuovo plalet (y/n)?");
                String scafResp = readInput();
                if (scafResp.equalsIgnoreCase("y")) {
                    printScafs();
                }
                System.out.print("Scaffale ID: ");
                String idScafStr = readInput();
                int idScaf = Integer.parseInt(idScafStr);
                boolean idScafCheck = checkIdScafs(idScaf, scafs);

                while (idScafCheck == false) {
                    System.out.println("Questo Scaffale non esistente, scegliere un altro id.");
                    System.out.print(" Scaffale ID: ");
                    idScafStr = readInput();
                    idScaf = Integer.parseInt(idScafStr);
                    idScafCheck = checkIdScafs(idScaf, scafs);

                }
                Scaffale scafUser = scafs.get(findScafbyId(idScaf));
                System.out.println("Vuole vedere la list di prodotti prima di scgliere uno per il nuovo plalet (y/n)?");
                String prodResp = readInput();
                if (prodResp.equalsIgnoreCase("y")) {
                    printProducts();
                }
                System.out.print("Prodotto ID: ");
                String idProdStr = readInput();
                int idProd = Integer.parseInt(idProdStr);
                boolean idProdStatus = checkId(idProd, prodotti);
                while (idProdStatus == false) {
                    System.out.println("Questo prodotto non esistente, scegliere un altro id.");
                    System.out.print("Prodotto ID: ");
                    idProdStr = readInput();
                    idProd = Integer.parseInt(idProdStr);
                    idProdStatus = checkId(idProd, prodotti);

                }
                Prodotto prodottoUser = prodotti.get(findProdbyId(idProd));

                Pallet newPall = new Pallet((palls.size() + 1), scafUser, prodottoUser);
                palls.add(newPall);
                // find prods and scaffales by id in arrays to met in the new pallet
                System.out.println("Vuole mettre alro pallet?(y/n)");
                String altroPallString = readInput();

                if (altroPallString.equalsIgnoreCase("y")) {
                    addmorePall = true;
                } else {
                    addmorePall = false;
                }
            } // addmorePall end
        } else {
            System.out.println(
                    "Le liste dei scaffale e prodotti sono vuote, devi metter almeno uno scaffale e un prodotto per mettere un nuovo pallet");
        }

    }

    private static void printProducts() {
        if (prodotti.isEmpty()) {
            System.out.println("La lista degli prodotti è vuota");
        } else {
            for (int i = 0; i < prodotti.size(); i++) {
                Prodotto prodotto = prodotti.get(i);
                System.out
                        .print(i + " Id: " + prodotto.Id + " product name: " + prodotto.Nome + " product descrpition: "
                                + prodotto.Descrizione);
                if (prodotto.CategoriaMerceologica == (null)) {
                    System.out.println(" :Senza Category ");
                    continue;
                } else {
                    System.out.print(" Product Category: " + prodotto.CategoriaMerceologica.Nome);
                    System.out.println(" ");

                }
            }
        }
    }

    private static void addCategory() {
        boolean addmoreCat = true;
        System.out.println("Inizio procedura inserimento categoria ");
        while (addmoreCat) {
            boolean acceptedId = false;
            String idString;
            int id = 0;
            while (!acceptedId) {
                System.out.print("Id: ");

                idString = readInput();
                try {
                    id = Integer.parseInt(idString);
                    acceptedId = true;

                } catch (Exception e) {
                    System.out.println("L'id è obbligatorio e deve essere numerico");
                }
            }

            boolean idStatues = checkIdcat(id, categorie);
            while (idStatues == true) {
                System.out.println("Questo id è utilizzato da un altro categoria, scegliere un altro id.");
                acceptedId = false;
                while (!acceptedId) {
                    try {
                        System.out.print("Id: ");
                        idString = readInput();
                        id = Integer.parseInt(idString);
                        acceptedId = true;

                    } catch (Exception e) {
                        System.out.println("L'id è obbligatorio e deve essere numerico");
                    }
                }
                idStatues = checkIdcat(id, categorie);

            }
            System.out.print("Nome: ");
            String nome = readInput();
            boolean nameStatues = checkNameCat(nome, categorie);
            boolean catEsiste = false;
            while (nameStatues) {
                System.out.println("Questa categoria è già esistente, vuole inserire un altro categoria(y/n)?");
                String res = readInput();
                if (res.charAt(0) == 'y') {
                    System.out.print("Nome: ");
                    nome = readInput();
                    nameStatues = checkNameCat(nome, categorie);
                } else if (res.charAt(0) != 'y') {

                    catEsiste = true;
                    break;
                }
            }
            if (!catEsiste) {
                System.out.print("Descrizione: ");
                String desc = readInput();
                Categoria newCategoria = new Categoria(id, nome, desc);
                categorie.add(newCategoria);
            }
            System.out.println("Voule inserire altra categoria (y/n)?");
            String altCat = readInput();
            if (altCat.charAt(0) == 'y') {
                addmoreCat = true;
                continue;
            } else if (altCat.charAt(0) != 'y') {
                addmoreCat = false;
                break;
            }
        }
    }

    private static void addScaffale() {
        System.out.println("Inizio procedura inserimento scaffale ");
        boolean addmoreScafs = true;
        while (addmoreScafs) {
            boolean acceptedId = false;
            String idString;
            int id = 0;
            while (!acceptedId) {
                System.out.print("Id: ");

                idString = readInput();
                try {
                    id = Integer.parseInt(idString);
                    acceptedId = true;

                } catch (Exception e) {
                    System.out.println("L'id è obbligatorio e deve essere numerico");
                }
            }
            // checkIdScafs
            boolean idStatues = checkIdScafs(id, scafs);
            while (idStatues == true) {
                System.out.println("Questo id è utilizzato da un altro Scaffale, scegliere un altro id.");
                System.out.print("Id: ");
                idString = readInput();
                id = Integer.parseInt(idString);
                idStatues = checkIdcat(id, categorie);

            }
            System.out.print("Nome: ");
            String nome = readInput();
            Scaffale newScaffale = new Scaffale(id, nome);
            scafs.add(newScaffale);
            System.out.println("Voule inserire altro Scaffale (y/n)?");
            String altScaf = readInput();
            if (altScaf.equalsIgnoreCase("y")) {
                addmoreScafs = true;
                continue;
            } else {
                addmoreScafs = false;
                break;
            }

        }
    }

    private static void printCategories() {
        if (categorie.isEmpty()) {
            System.out.println("La lista delle categorie è vuota");
        } else {
            for (int i = 0; i < categorie.size(); i++) {
                Categoria categoria = categorie.get(i);
                System.out.println(i + " Id: " + categoria.Id + " nome: " + categoria.Nome + " descrizione: "
                        + categoria.Descrizione);

            }
        }

    }

    private static void printScafs() {
        if (scafs.isEmpty()) {
            System.out.println("La lista delle Scafalle è vuota");
        } else {
            for (int i = 0; i < scafs.size(); i++) {
                Scaffale scaffale = scafs.get(i);
                System.out.println(i + " Id: " + scaffale.Id + " nome: " + scaffale.Nome);

            }
        }

    }

    private static int findCategoryByName(String catname) {
        int result = -1;

        for (int i = 0; i < categorie.size(); i++) {
            Categoria categoria = categorie.get(i);
            if (categoria.Nome.equals(catname)) {
                result = i;

            }

        }
        return result;
    }

    private static int findProdbyId(int id) {
        int result = -1;
        for (int i = 0; i < prodotti.size(); i++) {
            Prodotto prod = prodotti.get(i);
            if (prod.Id == id) {
                result = i;
            }
        }
        return result;
    }

    private static int findScafbyId(int id) {
        int result = -1;
        for (int i = 0; i < scafs.size(); i++) {
            Scaffale scaf = scafs.get(i);
            if (scaf.Id == id) {
                result = i;
            }
        }
        return result;
    }

    private static boolean checkId(int id, List<Prodotto> products) {
        boolean result = false;
        int i = 0;
        while (result == false && i < products.size()) {
            if (products.get(i).Id == id) {
                result = true;
            }
            i++;
        }

        return result;
    }

    private static boolean checkIdScafs(int id, List<Scaffale> scafs) {
        boolean result = false;
        int i = 0;
        while (result == false && i < scafs.size()) {
            if (scafs.get(i).Id == id) {
                result = true;
            }
            i++;
        }

        return result;
    }

    private static boolean checkIdcat(int id, List<Categoria> cats) {
        boolean result = false;
        int i = 0;
        while (result == false && i < cats.size()) {
            if (cats.get(i).Id == id) {
                result = true;
            }
            i++;
        }

        return result;
    }

    private static boolean checkIdPall(int id, List<Pallet> palls) {

        boolean result = false;
        int i = 0;
        while (result == false && i < palls.size()) {
            if (palls.get(i).Id == id) {
                result = true;
            }
            i++;
        }
        return result;
    }

    private static boolean checkNameCat(String name, List<Categoria> cats) {
        boolean result = false;
        int i = 0;
        while (result == false && i < cats.size()) {
            if (cats.get(i).Nome.equalsIgnoreCase(name)) {
                result = true;
            }
            i++;
        }

        return result;

    }

    private static String readInput() {
        try {
            // Per chi è curioso, qui viene istanziato un oggetto della classe di sistema
            // "BufferedReader" che si occupa di leggere il buffer in entrata da console e
            // tradurlo in stringa
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine().trim();
        } catch (Exception any) {
            return "error command";
        }
    }
}
