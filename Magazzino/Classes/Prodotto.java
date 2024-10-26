package Classes;
public class Prodotto {
    public int Id;
    public String Nome;
    public String Descrizione;
    public Categoria CategoriaMerceologica;

public Prodotto(int id, String nome, String descrBreve){
    this.Id=id;
    this.Descrizione=descrBreve;
    this.Nome=nome;


}
public Prodotto(int id, String nome, String descrBreve, Categoria  category){
    this.Id=id;
    this.Descrizione=descrBreve;
    this.Nome=nome;
    this.CategoriaMerceologica=category;

}

}
