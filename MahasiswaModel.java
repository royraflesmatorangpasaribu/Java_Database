/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roy_jdbc;

import db.DBHelper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class MahasiswaModel {
    private final Connection CONN;

    public MahasiswaModel() {
        this.CONN = DBHelper.getConnection();
    }
    
    public void addMahasiswa(Mahasiswa mhs) throws SQLException{
        String insert = "INSERT INTO mhs VALUES ('" 
                + mhs.getNpm() + "', '" + mhs.getNama() + "');";
        String query = "SELECT * FROM mhs WHERE npm ='" + mhs.getNpm() + "';";
        ResultSet rs = CONN.createStatement().executeQuery(query);
        if(!rs.next()){
            try {
                if(CONN.createStatement().executeUpdate(insert) > 0){
                System.out.println("Berhasil memasukkan data");
                }else{
                    System.out.println("Gagal memasukkan data");
                }
            } catch (SQLException ex) {
                Logger.getLogger(MahasiswaModel.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Gagal memasukkan data");
            }
        }
        else{
           System.out.println("NPM : "+ mhs.getNpm() + " Sudah Ada"); 
        }
    }
    public ArrayList<Mahasiswa> getMahasiswa(){
        String query = "SELECT * FROM mhs";
        ArrayList<Mahasiswa>mhs = new ArrayList<Mahasiswa>();
        
        try {
            ResultSet rs = CONN.createStatement().executeQuery(query);
            while(rs.next()){
                Mahasiswa temp = new Mahasiswa(rs.getString("npm"), rs.getString("nama"));
                mhs.add(temp);
            }
            System.out.println("Berhasil mengambil data");
        } catch (SQLException ex) {
            Logger.getLogger(MahasiswaModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Gagal mengambil data");
        }
        return mhs;
    } 
    public void updateMahasiswa(Mahasiswa mhs, String npm, String nama, String npmNew) throws SQLException{
        mhs.setNama(nama);
        mhs.setNpm(npm);
        String query = "SELECT * FROM mhs WHERE npm ='" + mhs.getNpm() + "';";
        ResultSet rs = CONN.createStatement().executeQuery(query);
        if(rs.next()){
            String NpmDiUpdate = rs.getString(1);
            String dataOld = rs.getString(2);
            String updateNama = "UPDATE mhs SET nama = '" + mhs.getNama() + "' WHERE npm = '" + mhs.getNpm() + "';";
            String updateNpm = "UPDATE mhs SET npm = '" + npmNew + "' WHERE npm = '" + NpmDiUpdate + "';";
            String updateNpmNama = "UPDATE mhs SET npm = '" + npmNew + "' , nama = '"
                    + mhs.getNama() + "' WHERE npm = '" + NpmDiUpdate + "';";
                try {
                    if(NpmDiUpdate.equals(mhs.getNpm()) && !dataOld.equals(mhs.getNama()) && "".equals(npmNew)){
                        CONN.createStatement().executeUpdate(updateNama);
                        System.out.println("Berhasil melakukan update data dengan NPM : "
                            + NpmDiUpdate + "\nData sebelum update : " + dataOld
                            + "\nData sesudah update : "+ mhs.getNama());
                    }
                    else if(!NpmDiUpdate.equals(mhs.getNpm()) && dataOld.equals(mhs.getNama()) && "".equals(npmNew)){
                            CONN.createStatement().executeUpdate(updateNpm);
                            System.out.println("Berhasil melakukan update data dengan NPM : "
                            + NpmDiUpdate + "\nData sebelum update : " + NpmDiUpdate
                            + "\nData sesudah update : "+ npmNew);
                    }
                    else{
                        CONN.createStatement().executeUpdate(updateNpmNama);
                        System.out.println("Berhasil melakukan update data dengan NPM : "
                        + NpmDiUpdate + "\nData sebelum update : " + NpmDiUpdate + ","+ dataOld
                        + "\nData sesudah update : " + npmNew + "," + mhs.getNama());
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MahasiswaModel.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Gagal melakukan update data");
                }
        }
        else{
           System.out.println("NPM tidak Ada!"); 
        }
    }
    
    public void deleteMahasiswa(Mahasiswa mhs, String npm) throws SQLException{
        mhs.setNpm(npm);
        String query = "SELECT * FROM mhs WHERE npm = '" + mhs.getNpm() + "';";
        ResultSet rs = CONN.createStatement().executeQuery(query);
        if(rs.next()){
        String NpmDiHapus = rs.getString(1);
        String delete = "DELETE FROM mhs WHERE npm = '" + mhs.getNpm() + "';";
            try {
                CONN.createStatement().executeUpdate(delete);
                System.out.println("Berhasil menghapus data dengan NPM : " + NpmDiHapus);
            } catch (SQLException ex) {
                Logger.getLogger(MahasiswaModel.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Gagal menghapus data");
            }
        }
        else{
            System.out.println("NPM tidak Ada!");
        }
    }
}
