/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;

import baza.DBBroker;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.Autor;
import model.Knjiga;
import model.User;
import model.Zanr;

/**
 *
 * @author PC
 */
public class Controller {
    
    private DBBroker dbb;
    private List<Knjiga> listaKnjiga=new ArrayList<>();
    private List<Autor> listaAutora=new ArrayList<>();
    private List<User> listaUsera=new ArrayList<>();

    private static Controller instance;
    public static Controller getInstance(){
        if(instance==null){
            instance = new Controller();
        }
        return instance;
    }

    private Controller() {
        dbb=new DBBroker();
        
        User u1=new User(1, "Duska", "duska");
        User u2=new User(2, "Suza", "suza");
        User u3=new User(3, "Stefa", "stefa");
        User u4=new User(4, "Miki", "miki");

        listaUsera.add(u1);
        listaUsera.add(u2);
        listaUsera.add(u3);
        listaUsera.add(u4);
        
        /*Autor autor1=new Autor("Ivo", "Andric", 1892, "Biografija autora Ive Andrica bla bla");
        Autor autor2=new Autor("Danilo", "Kis", 1935, "Biografija Danila Kisa bla bla bla bla");
        Autor autor3=new Autor("Mesa", "Selimovic", 1910, "Mesa Selimovic je rodjen u BiH");
        
        Knjiga knjiga1=new Knjiga("Na Drini cuprija", autor1, "1234567890", 1945, Zanr.DETEKTIVSKI);
        Knjiga knjiga2=new Knjiga("Basta, pepeo", autor2, "0987654321", 1982, Zanr.ISTORIJSKI);
        Knjiga knjiga3=new Knjiga("Tvrdjava", autor3, "1122334455", 1970, Zanr.NAUCNA_FANTASTIKA);

        
        //listaKnjiga=new ArrayList<>();
        //listaAutora=new ArrayList<>();
        //vec smo gore ali moze i ovako
        
        listaKnjiga.add(knjiga1);
        listaKnjiga.add(knjiga2);
        listaKnjiga.add(knjiga3);
        
        
        listaAutora.add(autor1);
        listaAutora.add(autor2);
        listaAutora.add(autor3);*/
        

    }
    
    //geteri i seteri za liste

    public List<User> getListaUsera() {
        return listaUsera;
    }

    public void setListaUsera(List<User> listaUsera) {
        this.listaUsera = listaUsera;
    }

    public List<Knjiga> getListaKnjiga() {
        return listaKnjiga;
    }

    public void setListaKnjiga(List<Knjiga> listaKnjiga) {
        this.listaKnjiga = listaKnjiga;
    }

    public List<Autor> getListaAutora() {
        return listaAutora;
    }

    public void setListaAutora(List<Autor> listaAutora) {
        this.listaAutora = listaAutora;
    }

    public void obrisiKnjigu(int id) {
        
        dbb.obrisiKnjigu(id);
        //listaKnjiga.remove(selektovaniRed);
    }

    public void dodajKnjigu(Knjiga novaKnjiga) {
        dbb.dodajKnjigu(novaKnjiga);
        //listaKnjiga.add(novaKnjiga);
        //System.out.println("Knjiga je dodata");
        //System.out.println(listaKnjiga);
    }

    public List<Knjiga> ucitajListuKnjigaIzBaze() {
        this.listaKnjiga=dbb.ucitajListuKnjigaIzBaze();
        return this.listaKnjiga;
    }

    public List<Autor> ucitajListuAutoraIzBaze() {
        return dbb.ucitajListuAutoraIzBaze();
    }

    public void azurirajKnjigu(Knjiga knjigaZaIzmenu) {
        dbb.azurirajKnjigu(knjigaZaIzmenu);
    }

    public boolean login1(String username, String pass) {
        //ako nadjemo jednog kome se poklapa prosledjeni username i password onda smo ulogovani, ako ne nismo ulogovani
        for (User u:listaUsera) {
            if(u.getUsername().equals(username) && u.getPassword().equals(pass)){
                return true;
            }
        }
        return false;
    }

    public boolean login2(String username, String pass) {
        return dbb.login(username, pass);
    }

    public List<Knjiga> filtriraj(String autor, String naziv) {
        List<Knjiga> rezultat=new ArrayList<>();
        
        if (autor!=null && naziv==null) {
            //ako je null radimo prvo filtriranje po nazivu i obrnto
            for (Knjiga k : listaKnjiga) {
                String autorKnjige=k.getAutor().getIme()+" "+ k.getAutor().getPrezime();
                if(autorKnjige.contains(autor)){
                    rezultat.add(k);
                }
            }
        }
        if (autor==null && naziv!=null) {
            for (Knjiga k : listaKnjiga) {
                if(k.getNaslov().contains(naziv)){
                    rezultat.add(k);
                }
            }
        }
        if(autor!=null && naziv!=null){
            for (Knjiga k : listaKnjiga) {
                String autorKnjige=k.getAutor().getIme()+" "+ k.getAutor().getPrezime();
                if(autorKnjige.contains(autor) && k.getNaslov().contains(naziv)){
                    rezultat.add(k);
                }
            }
        }
        //ok ovde smo imali samo dva parametra a sta ako imamo 5 ili vise necemo kucati za svaki to oduzima previse vremena
        
        /*List<Knjiga> rezultat2=new ArrayList<>();
        
        rezultat2=listaKnjiga.stream() //stream omogucava da nam listom pozovemo fju filter
                .filter(k->(naziv!=null && k.getNaslov().contains(naziv))&&
                        (autor!=null && (k.getAutor().getIme()+" "+k.getAutor().getPrezime()).contains(autor))
                ).collect(Collectors.toList());
        */
        return rezultat;
    }

    public List<Knjiga> filtriraj2(String autor, String naziv) {
        return dbb.filtriraj(autor, naziv);
    }
    
    
    
    
}
