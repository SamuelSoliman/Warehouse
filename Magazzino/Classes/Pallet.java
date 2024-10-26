package Classes;
public class Pallet {
    public int Id;
    public Scaffale Scaffale;
    public Prodotto Prodotto;
    public Pallet(int id, Scaffale scaf, Prodotto prod){
        this.Id=id;
        this.Scaffale=scaf;
        this.Prodotto=prod;

    }
}
