import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PajakKendaraan extends Pajak
{
       

        Connection conn;
        String link = "jdbc:mysql://localhost:3306/hitung_pajak";
        
        Scanner input = new Scanner(System.in);
    
        public void roda2()
        {
            System.out.println("    byk roda 2\t :");
            this.roda2 = input.nextInt();
        }
        public void roda4()
        {
            System.out.println("    byk roda 4\t: ");
            this.roda4 = input.nextInt();
        }
    
       
        @Override
        public void save() throws SQLException 
        {
            //try
            try
            {
                nama();
                id();
                alamat();
                roda2();
                roda4();
                hitungRoda2();
                hitungRoda4();
                hitungPajakKendaraan();

                conn = DriverManager.getConnection(link,"root","");
            Statement statement = conn.createStatement();
            //input data ke database_gudang tabel barang
            String sql = "INSERT INTO wajib_pajak (nama, id, alamat, total_pajak) VALUES ('"+this.nama+"', '"+this.id+"', '"+this.alamat+"', '"+this.pajakKendaraan+"')";
            statement.execute(sql);
            System.out.println("Berhasil input data");

            sql = "INSERT INTO pajak_kendaraan (nama, id, roda2, roda4, total_pajakkendaraan) VALUES ('"+this.nama+"', '"+this.id+"', '"+this.roda2+"',  '"+this.roda4+"', '"+this.pajakKendaraan+"')";
                statement.execute(sql);
                System.out.println("Berhasil input data");
                
            }
                //exception SQL
                catch(SQLException e)
                {
                    System.err.println("Kesalahan dalam input data");
                }
                //excception input tidak sesuai dengan tipe data
                catch(InputMismatchException e)
                {
                    System.out.println("Inputan data dengan benar");
                }
                
        
    }
}
