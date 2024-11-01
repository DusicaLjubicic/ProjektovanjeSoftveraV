/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Knjiga;

/**
 *
 * @author PC
 */
public class ModelTabeleKnjige extends AbstractTableModel {

    private List<Knjiga> listaKnjiga;
    private final String[] kolone={"NAslov", "Autor", "ISBN", "Godina izdanja"};
    //napravili niz stringova za kolone, final znaci da nam je konstantno, moze a i ne mora
    
    //konstruktor
    public ModelTabeleKnjige(List<Knjiga> listaKnjiga) {
        this.listaKnjiga = listaKnjiga;
    }
    
    
    @Override
    public int getRowCount() {
        return listaKnjiga.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Knjiga knjiga=listaKnjiga.get(rowIndex);
        switch(columnIndex){
            case 0:
                return knjiga.getNaslov();
            case 1:
                return knjiga.getAutor().getIme() + " " + knjiga.getAutor().getPrezime();
            case 2:
                return knjiga.getISBN();
            case 3:
                return knjiga.getGodinaIzdanja();
            default:
                return null; //ovde moze i prazan string da se vrati
        }
        
        //uzimamo i-ti red onaj prosledjeni za red a za kolone switchujemo indeks kolone u zavisnosti gde se sta upisuje, tj koji je indeks kolone prosledjen
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    void osveziPOdatke() {
        fireTableDataChanged(); //osvezava tabelu
    }
    
    
    
}
