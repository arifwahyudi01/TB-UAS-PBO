public class WajibPajak extends Orang
{
    String id, noHP, alamat ;
    
    //constructor
    public WajibPajak()
    {

    }

    //constuctor
    public WajibPajak(String nama, String id, String alamat) 
    {
        this.nama = nama;
        this.id = id;
        this.alamat=alamat;
        System.out.println("Wajib Pajak "+this.nama+" telah terdaftar ");
    }   

    public void methodKosong()
    {

    }
}
