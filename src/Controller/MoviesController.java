package Controller;
import Model.*;
import AOInterface.Mimplement;
import  MoviesAO.DataAC;
import movies.MainView;

import java.util.List;

public class MoviesController {
    MainView frame;
    Mimplement imp;
    List<DataMovies> dm;
    static            String str;

    public MoviesController(MainView frame) {
        this.frame = frame;
        imp = new DataAC();
        dm = imp.getAll();
    }

    public void IsiTable(){
        dm = imp.getAll();
        ModelTabelMovies mp = new ModelTabelMovies(dm);
        frame.getShowTable().setModel(mp);
    }
    
    public void insert(){
        DataMovies dm = new DataMovies();
        dm.setJudul(frame.getTFJudul().getText());
        double alur = Double.parseDouble(frame.getTFAlur().getText());
        dm.setAlur(alur);
        double tokoh = Double.parseDouble(frame.getTFPenokohan().getText());
        dm.setPenokohan(tokoh);
        double akting = Double.parseDouble(frame.getTFAkting().getText());
        double nilai = ((akting + tokoh + alur) / 3);
        dm.setAkting(akting);
        dm.setNilai(nilai);
        imp.insert(dm);
    }
    
        public void update(){
        DataMovies dm = new DataMovies();
        dm.setJudul(frame.getTFJudul().getText());
        double alur = Double.parseDouble(frame.getTFAlur().getText());
        dm.setAlur(alur);
        double tokoh = Double.parseDouble(frame.getTFPenokohan().getText());
        dm.setPenokohan(tokoh);
        double akting = Double.parseDouble(frame.getTFAkting().getText());
        double nilai = ((akting + tokoh + alur) / 3);
        dm.setAkting(akting);
        dm.setNilai(nilai);
        imp.update(dm);
    }
        
       public void delete(){
        DataMovies dm = new DataMovies();
        String str = frame.getTFJudul().getText(); 
        System.out.print(str);// Mengambil nilai judul dari TextField
        imp.delete(str); // Memanggil metode delete dengan parameter String str
       }
       
       public void clear(){
           imp.clear();
       }
}
