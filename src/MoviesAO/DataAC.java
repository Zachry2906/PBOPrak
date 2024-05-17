package MoviesAO;
import java.sql.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.logging.Level;

import Koneksi.Connector;
import Model.*; 
import AOInterface.Mimplement;

public class DataAC implements Mimplement{
    Connection connection;
    final String select = "select * from movie";
    final String insert = "INSERT INTO movie (judul, alur, penokohan, akting, nilai) VALUES (?, ?, ? ,?, ?);";
    final String update = "update movie set judul=?, alur=?, penokohan=?, akting=?,nilai=? where judul=?";
    final String delete = "delete from movie where judul=?";
    final String clear = "TRUNCATE TABLE movie";

    public DataAC(){
        connection = Connector.getConnection();
    }

    @Override
    public void insert(DataMovies d) {
        PreparedStatement s = null;
        try {
            s = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            s.setString(1, d.getJudul());
            s.setDouble(2, d.getAlur());
            s.setDouble(3, d.getPenokohan());
            s.setDouble(4, d.getAkting());
            double rata_rata = (d.getAlur() + d.getPenokohan() + d.getAkting()) / 3;
              s.setDouble(5, rata_rata);

        s.executeUpdate();
        } catch (SQLException x){
              x.printStackTrace();
        } finally {
            try {
                s.close();
            } catch (SQLException x){
                x.printStackTrace();
            }
        }
    }

    @Override
    public void update(DataMovies d) {
              PreparedStatement s = null;
        try {
            s = connection.prepareStatement(update);
            s.setString(1, d.getJudul());
            s.setDouble(2, d.getAlur());
            s.setDouble(3, d.getPenokohan());
            s.setDouble(4, d.getAkting());
            double rata_rata = (d.getAlur() + d.getPenokohan() + d.getAkting()) / 3;
              s.setDouble(5, rata_rata);
              s.setString(6, d.getJudul());

        s.executeUpdate();
        } catch (SQLException x){
              x.printStackTrace();
        } finally {
            try {
                s.close();
            } catch (SQLException x){
                x.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String d) {
        System.out.print(d);
         PreparedStatement s = null;
              try {
            s = connection.prepareStatement(delete);
            s.setString(1, d);
            s.executeUpdate();
            System.out.print(d);
              } catch (SQLException x){
                  x.printStackTrace();
              }finally {
            try {
                s.close();
            } catch (SQLException x){
                x.printStackTrace();
            }
        }
    }

    @Override
    public List<DataMovies> getAll() {
        List<DataMovies> dm = null;
        try {
            dm = new ArrayList<DataMovies>();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(select);
            while (rs.next()) {
                DataMovies d = new DataMovies();
                d.setJudul(rs.getString("judul"));
                d.setAlur(rs.getInt("alur"));
                d.setPenokohan(rs.getInt("penokohan"));
                d.setAkting(rs.getInt("akting"));
                d.setNilai((rs.getInt("akting") + rs.getInt("alur") + rs.getInt("penokohan"))/3);
                dm.add(d);
            }
        } catch (SQLException x){
            Logger.getLogger(DataAC.class.getName()).log(Level.SEVERE, null, x);
        }
        return dm;
    }
    
    public void clear(){
         PreparedStatement s = null;
              try {
            s = connection.prepareStatement(clear);

            s.executeUpdate();
              } catch (SQLException x){
                  x.printStackTrace();
              }finally {
            try {
                s.close();
            } catch (SQLException x){
                x.printStackTrace();
            }
        }
    }

}
