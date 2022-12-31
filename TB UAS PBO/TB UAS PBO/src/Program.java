import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

//class program
public class Program 
{
     //koneksi database
     static Connection conn;
     static String link = "jdbc:mysql://localhost:3306/hitung_pajak";
     static Scanner input = new Scanner(System.in);
 
    public static void main(String[] args) throws Exception 
    {

         //date
        DateFormat formatTanggal = new SimpleDateFormat("dd MMMM yyyy");
        DateFormat formatJam = new SimpleDateFormat("HH:mm:ss");
        Date tanggal = new Date();

        String salamSapa = "Selamat Pagi, Semoga Harimu Luar Biasa";
        String sapa = salamSapa.replace("Selamat Pagi", "\nHello");
        //method string
        System.out.println(sapa.toLowerCase());
        System.out.println("\nSelamat datang di program pergudangan");
        
        // Pajak pajak = new Pajak();

      

        //pajak.view();
        // pajak.update();

        menu();

        //date
        System.out.println("====================================");
        System.out.println("=Dibuat pada     : "+formatTanggal.format(tanggal)+"=");
        System.out.println("=Diupdate pada   : "+formatJam.format(tanggal)+" WIB    =");
        System.out.println("====================================");
    }

    private static void menu() throws SQLException 
    {
        boolean balikMenu = true;
        boolean balikTanya = true;
        Integer pilihan;
        String pertanyaan;

        //perulangan while
        while (balikMenu)
        {
            System.out.println("\n=============== M E N U ================");
            System.out.println("    1.\tLihat data");
            System.out.println("    2.\tUbah data");
            System.out.println("    3.\tTambah data");
            System.out.println("    4.\tHapus data");
            System.out.println("    5.\tKeluar program");
            System.out.print("\tPilihan Anda (1/2/3/4/5): ");
            
            pilihan = input.nextInt();
            input.nextLine();
            Pajak pajak = new Pajak();
            PajakKendaraan pajakkendaraan = new PajakKendaraan();
            //percabangan switch case
            switch (pilihan) 
            {
                case 1:
                    pajak.view();
                    balikTanya = true;
                break;
                        
                case 2:
                    admin();
                    pajak.update();
                    balikTanya = true;
                break;

                case 3:
                    //pajakpenghasilan.save();
                    pajakkendaraan.save();
                    //pajak.hitungPajakTotal();
                    balikTanya = true;
                break;

                case 4:
                    pajak.delete();
                    balikTanya = true;
                break;

                case 5:
                    balikTanya = false;
                    balikMenu = false;
                break;

                default :
                    System.out.println("Inputan harus berupa angka 1/2/3/4/5!");
                break;
            }
            
            //perulangan while
            while (balikTanya)
            {
                System.out.print("Ingin kembali ke menu utama [y/n]? ");
                pertanyaan = input.nextLine();
                //percabangan if else if
                if (pertanyaan.equalsIgnoreCase("n")) //method string 
                {
                    balikMenu = false;
                    balikTanya = false;
                }
                else if (pertanyaan.equalsIgnoreCase("y")) //method string
                {
                    balikMenu = true;
                    balikTanya = false;
                }
                else 
                {
                    System.out.println("Inputkan 'y' atau 'n' saja");
                }
            }
        }
        System.out.println("\n\n\t\tSelesai\n");
    }

    private static void admin() throws SQLException
    {
        //membuat objek HashMap baru
        Map<String, String> Login = new HashMap<String, String>();

        //membaca data di database hitung_pajak tabel admin
        String inputUsername, inputPassword;
        String sql = "SELECT * FROM login";
        boolean relogin = true;
        conn = DriverManager.getConnection(link,"root","");
        Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);

        //perulangan while
        while (result.next())
        {
            //mengambil nilai di database dan menyimpannya ke dalam variable
            String user = result.getString("user");
            String password = result.getString("password");

            //input key dan value 
            Login.put(user, password);
        }
        System.out.println("===Silakan login terlebih dahulu===");

        //perulangan while
        while (relogin)
        {
            System.out.println("Masukkan username dan password yang benar!");
            System.out.print("Username : ");
            inputUsername = input.nextLine();
            System.out.print("Password : ");
            inputPassword = input.nextLine();
            //percabangan if
            if (Login.containsValue(inputUsername)==true) //method bawaan HashMap
            {System.out.println("test");
                //percabangan if
                if (Login.get(inputUsername).equals(inputPassword)) //method bawaan HashMap n method string
                {
                    System.out.println("Berhasil login");
                    // relogin = false;
                }
                else
                {
                    System.out.println("test2");
                    // relogin = true;
                }
            }
            else
            { System.out.println("Login Berhasil");
                relogin=false;
                // relogin = true;
            }
        }
        statement.close();
    }
        
    
}
