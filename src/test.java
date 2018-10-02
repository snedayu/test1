import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class test 
{
    public static void main (String[] args) throws IOException
    {
        String fileName="A.csv";
        String newFileTxt="B.txt";
        String binFile="C.BIN";
        File file = new File(fileName);
        File fileBtxt=new File(newFileTxt);
        
        Scanner inputStream;
        Scanner readB;
        FileWriter fileB;
        PrintWriter print;
        FileOutputStream bin;
        ObjectOutputStream save;
        
        //csv to txt
        try {
            inputStream = new Scanner(file);
            fileB=new FileWriter(newFileTxt);
            print=new PrintWriter(fileB);
            
            while(inputStream.hasNext())
             {
                 String data=inputStream.next();
                 String[] values=data.split(",");
                 print.println(values[2]+" "+values[1]+" "+values[0]);
             }
             inputStream.close();
             print.close();
             
        } catch (FileNotFoundException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //txt to bin
        try {
            readB=new Scanner(fileBtxt);
            bin=new FileOutputStream(binFile);
            save=new ObjectOutputStream(bin);
          
            System.out.println("bellow are the data that will be save to c.bin");
            
            while(readB.hasNext())
             {
                 String data=readB.nextLine();
                 System.out.println(data); //to see what data obtain from B.txt
                 String saveC="";
                 char[] charArray=data.toCharArray();// string to array char
                 
                 for(int i=0;i<data.length();i++)
                 {
                 saveC=saveC+Integer.toBinaryString(charArray[i])+" ";//array char to binary , this include the space bar which is 100000
                 }
                 System.out.println(saveC);// to show what data will be save to c.bin
                 save.writeUTF(saveC);//save the data to c.bin
             }
             
             readB.close();
             save.close();
             
        } catch (FileNotFoundException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("\ndone writing to c.bin bellow are the reading from c.bin");
       
        FileInputStream fileInBin = new FileInputStream(binFile);
        ObjectInputStream fileInSave= new ObjectInputStream(fileInBin);
        
        //to read the data from c.bin this is to ensure that the the data have 
        //been insert to c.bin and to open the c.bin using texteditor the result will be shown differently.
        String saveC=fileInSave.readUTF();
        String saveCC=fileInSave.readUTF();
        String saveCCC=fileInSave.readUTF();
        System.out.println(saveC);
        System.out.println(saveCC);
        System.out.println(saveCCC);
        fileInSave.close();
       
    }
}
