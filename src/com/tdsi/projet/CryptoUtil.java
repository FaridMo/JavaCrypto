package com.tdsi.projet;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFileChooser;

public class CryptoUtil {

	
		static SecretKey genkey(String algo,int taille){
		try{
			
			KeyGenerator kg = KeyGenerator.getInstance(algo);
                        SecureRandom secureRandom = new SecureRandom();

                        kg.init(taille,secureRandom);
        		//kg.init(taille);
                        //byte[] b = kg.generateKey().getEncoded();
                        //SecretKey k = new SecretKeySpec(b,algo);
                        //SecretKey key = kg.generateKey();
                        
                        SecretKey k = kg.generateKey();
			
			return k;
                        
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
			
		}
		
		
		
		static boolean savekey(SecretKey k,String chemin){
			try{
				FileOutputStream fos = new FileOutputStream(chemin);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(k);
				
				oos.close();
				fos.close();
				return true;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
			
			
		}
		
		
                       
		static SecretKey receive_key(String chemin){
			try {
				FileInputStream fis = new FileInputStream(chemin);
				ObjectInputStream ois = new ObjectInputStream(fis);
//				ois.close();  fis.close();
				return (SecretKey) ois.readObject();
		} catch (Exception e) {
				return null;
			}
		
		}
                
                
                
                static void fileProcessor(int cipherMode,String key,String algo,File inputFile,File outputFile) throws FileNotFoundException, IOException, NoSuchAlgorithmException, NoSuchPaddingException{
                        try {
                              Key secretKey = new SecretKeySpec(key.getBytes(),"AES");
                              
                              Cipher cipher = Cipher.getInstance(algo);
                              cipher.init(cipherMode, secretKey);

                              FileInputStream inputStream = new FileInputStream(inputFile);
                              byte[] inputBytes = new byte[(int) inputFile.length()];
                              inputStream.read(inputBytes);

                              byte[] outputBytes = cipher.doFinal(inputBytes);

                              FileOutputStream outputStream = new FileOutputStream(outputFile);
                              outputStream.write(outputBytes);

                              inputStream.close();
                              outputStream.close();
                              
                              

                           } catch (InvalidKeyException | BadPaddingException
                                    | IllegalBlockSizeException | IOException e) {
                               e.printStackTrace();
                           }
     }
                
               static boolean saveText(String hache,String chemin){
			try{
				FileOutputStream fos = new FileOutputStream(chemin);
				//ObjectOutputStream oos = new ObjectOutputStream(fos);
                                byte[] byt = hache.getBytes();
				fos.write(byt);
                                
                                fos.toString();
				//oos.close();
				fos.close();
				return true;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
			
			
		}
                
                
      }
                
                
          /*      public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException, NoSuchPaddingException{
                            String key = "This is a secret";
                            File inputFile = new File("C:\\Users\\FaridMo\\Desktop\\farid.txt");
                           File encryptedFile=null;
                           File decryptedFile=null;
                         
                            if(inputFile.getName().endsWith(".txt")){
                               encryptedFile = new File(inputFile.getPath().replaceAll(".txt", ".crypt")); */
                      /*         try {
                                 fileProcessor(Cipher.ENCRYPT_MODE,key,inputFile,encryptedFile);
                                 //inputFile.delete();
                                 
                                 System.out.println("Sucess");
                             } catch (IOException ex) {
                                 System.out.println(ex.getMessage());
                             }
                           
                            
                            } */ 
                /*         
                           if(encryptedFile.getPath().endsWith(".crypt")){
                               decryptedFile = new File(encryptedFile.getPath().replaceAll(".crypt", ".txt"));
                                try {
                                 fileProcessor(Cipher.DECRYPT_MODE,key,encryptedFile,decryptedFile);
                                 //encryptedFile.delete();
                                 System.out.println("Sucess");
                             } catch (IOException ex) {
                                 System.out.println(ex.getMessage());
                             }
                            }
                                
                           
                         
                            //File decryptedFile = new File(encryptedFile+".txt");
                                
                            
                } */
            
		

