import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Pajak extends WajibPajak implements Interdb //class pajak yang mengimplementasikan interface Database dan mengekstends Orang
{   int pajakPenghasilan, pajakKendaraan, pajakTotal, a;
    int penghasilan, bonus, tunjangan, total;
    int roda2, roda4, pajakRoda2, pajakRoda4;
    boolean idTersedia;

    public void nama()
    {
        System.out.print("    Nama\t: ");
        this.nama = input.nextLine();
    }

    public void id()
    {
        System.out.print("    ID\t: ");
        this.id= input.nextLine();
    }

    public void alamat()
    {
        System.out.println("    Alamat\t: ");
        this.alamat=input.nextLine();
    }

    // public void hitungPajakPenghasilan()
    // {   System.out.println("test");
    //     this.total = this.penghasilan + this.tunjangan + this.bonus;
        
    //     if (this.total <10000000)
    //     {   System.out.println("test3");
    //         this.pajakPenghasilan = this.total/30;
    //         System.out.println(this.pajakPenghasilan);
    //     }
    //     else if(this.total >10000000 && this.total <25000000)
    //     {
    //         this.pajakPenghasilan = this.total/20;
    //     }
    //     else if(this.total >25000000 && this.total <50000000)
    //     {
    //         this.pajakPenghasilan = this.total/15;
    //     }
    //     else
    //     {
    //         this.pajakPenghasilan = this.total/10;
    //     }
    // }

    public void hitungRoda2() throws SQLException
    {
       this.pajakRoda2 = this.roda2 * 500000;
       System.out.println(this.pajakRoda2);
    }

    public void hitungRoda4() 
    {
       this.pajakRoda4 = this.roda4 *1000000;
       System.out.println(this.pajakRoda4);
    }

    public void hitungPajakKendaraan()
    {
        this.pajakKendaraan= this.pajakRoda2+this.pajakRoda4;
        System.out.println(this.pajakKendaraan);
    }

    //koneksi database
    Connection conn;
    String link = "jdbc:mysql://localhost:3306/hitung_pajak";
    Scanner input = new Scanner(System.in);

    @Override
    public void view() throws SQLException 
    {
         //mengakses data yang berada di database hitung_pajak tabel wajib_pajak
         String sql = "SELECT * FROM wajib_pajak";
         conn = DriverManager.getConnection(link,"root","");
         Statement statement = conn.createStatement();
         ResultSet result = statement.executeQuery(sql);
 
         //percabangan while
         while (result.next())
         {
             //block program untuk mengakses data di tabel wahib_pajak dan mencetaknya di console
             System.out.print("\nNama\t  : ");
             System.out.println(result.getString("nama"));
             System.out.print("ID\t : ");
             System.out.println(result.getString("id"));
             System.out.print("Total Pajak\t  : ");
             System.out.println(result.getInt("total_pajak"));
             System.out.print("Alamat\t  : ");
             System.out.println(result.getString("alamat"));
         }
         statement.close();
        
    }

    @Override
    public void update() throws SQLException 
    {
        //try
        try
        {
            view();
            Integer pil;
            String text = "\n Ubah Data";
            System.out.println(text.toLowerCase());
            System.out.print("ID yang hendak diubah : ");
            String tukar = input.nextLine();

            //mengakses data database hitung_pajak tabel wajib_pajak
            String sql = "SELECT * FROM wajib_pajak WHERE id ='"+tukar+"'";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            //percabangan  if
            if (result.next())
            {
                System.out.println("Data yang hendak diubah\n");
                System.out.println("Nama ["+result.getString("nama")+"] : ");
                String x = input.nextLine();
                System.out.println("Alamat ["+result.getString("alamat")+"] : ");
                String y = input.nextLine();
                System.out.println("Total Pajak ["+result.getInt("total_pajak")+"] : ");
                int z = input.nextInt();

                sql = "UPDATE wajib_pajak SET nama = '"+x+"', alamat = '"+y+"', total_pajak = "+z+" WHERE id = '"+tukar+"' ";
                    if(statement.executeUpdate(sql) > 0)
                    {
                        System.out.println("Berhasil memperbaharui data (id "+tukar+")");
                    }
                
            }   
            else
            {
                System.out.println("id tidak ditemukan");
            }
        }

        // exeption SQL 
        catch (SQLException e)
        {
            System.err.println("Kesalahan update data");
        }
        //exception input tidak sesuai jenis data
        catch (InputMismatchException e)
        {
            System.err.println("Inputan harus berupa angka!");
        }
  
    }

    @Override
    public void delete() throws SQLException 
    {
        
        try{
	        view();
	        System.out.print("Ketik id yang akan Anda Hapus : ");
	        String idd= input.nextLine();
	        
	        String sql = "DELETE FROM wajib_pajak WHERE id = '"+ idd+"'";
	        Statement statement = conn.createStatement();
	        //ResultSet result = statement.executeQuery(sql);
	        
	        if(statement.executeUpdate(sql) > 0){
	            System.out.println("Berhasil menghapus data wajib pajak (ID "+idd+")");
	        }
	   }catch(SQLException e){
	        System.out.println("Terjadi kesalahan dalam menghapus data wajib_pajak");
	        }
    }

    @Override
    public void save() throws SQLException 
    {
        
    }

    @Override
    public void search() throws SQLException 
    {
 
        
    }


    
}
