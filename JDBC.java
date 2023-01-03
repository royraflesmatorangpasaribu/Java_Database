/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roy_jdbc;

import db.DBHelper;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Praktikum13_Roy_JDBC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        //TODO code application logic here
        //DBHelper.getConnection();
        MahasiswaModel mhs = new MahasiswaModel();
        
        Mahasiswa mhs1 = new Mahasiswa("2117051059", "Mery K.P");
        
        System.out.println("=======Operation=======");
        //mhs.addMahasiswa(mhs1);
        
        //mhs.updateMahasiswa(mhs1, "2117051059", "Mery Kristiyanti Pasaribu", "");
        
        mhs.deleteMahasiswa(mhs1, "2117051059");
        
        System.out.println("==========View==========");
        
        ArrayList<Mahasiswa> listMahasiswa = mhs.getMahasiswa();
        
        for (int i = 0; i < listMahasiswa.size(); i++){
            System.out.println("Nama : " + listMahasiswa.get(i).getNama());
            System.out.println("NPM : " + listMahasiswa.get(i).getNpm());
            System.out.println("");
        }
    }
    
}
