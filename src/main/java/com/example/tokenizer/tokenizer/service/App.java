package com.example.tokenizer.tokenizer.service;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.*;


/*
 *  @author Ajay kumar gond 
 *  
 * 
 */

@Service
public class App {
    private ArrayList<String> keyList, valueList;
    private Map<String, String> translationMap;
    private  String excelFilePath, directoryPath, fileTypeToChange;


    App(){

        this.keyList = new ArrayList<>();
        this.valueList = new ArrayList<>();
        this.translationMap = new LinkedHashMap<>();

    }

    void setExcelFilePath(String excelFilePath){
        this.excelFilePath = excelFilePath;
    }


    
    void setDirectoryPath(String directoryPath){
        this.directoryPath = directoryPath;
    }

    
    void setFileType(String fileTypeToChange){
        this.fileTypeToChange = fileTypeToChange;
    }




        /*
         * @Author Ajay kumar gond
         * @param App() : apk object, string : excel file path
         * @return boolean
         * 
         * Extracts out excel key values from translation.xsls file
         * and puts them in keyList, valueList and translation map
         * instance variables of apk object
         * 
         * 
         */

    public static boolean readExcelFile(App apk, String path) {
        
        
        
        System.out.println("readExcelFile() ::");

        

        try {

            FileInputStream fileInputStream = new FileInputStream(new File(path));
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                int i = 0;
                for (Cell cell : row) {
                    if (i == 0) {
                        apk.keyList.add(cell.toString());
                    }
                    i++;
                }
                i = 0;
                String value = "";
                for (Cell cell : row) {
                    if (i > 0) {
                        // value += cell.toString();
                        apk.valueList.add(cell.toString());
                    }
                    i++;
                }
            }

            for (int i = 0; i < apk.keyList.size(); i++) {
                final String key = apk.keyList.get(i);
                final String value = apk.valueList.get(i);
                // if(key != null && key != "" && value != null && value != ""){
                // }
                apk.translationMap.put(key, value);
            }



        } catch (Exception e) {
            System.out.println("Exception : " + e);
            return false;
        }

        
        return true;
    }

    private static boolean isAlphaDigit(char c) {
        return (c >= 'A' && c <= 'Z')
                || (c >= 'a' && c <= 'z')
                || (c >= '0' && c <= '9');
    }



     

         /*
         
         * @Author Ajay kumar gond
         * @param App() : apk object, string : text to be changed
         * @return string
         *
         * Takes out key from translationMap 1 by 1
         * finds it in text and replaces it with 
         * values of translation[key]
         * then returns changed text
         * 
         */


    public static String replaceWordInText(App apk, String text) {

        if(text.length() == 0){
            return "";
        }

        

        System.out.println("File after changes ::: ");
        for (String key : apk.translationMap.keySet()) {
            
            key = key.trim();

            int len = key.length();


            
            int prevFoundIdx = 0;
            
            System.out.println("key :::: " + key);
            
            while (true) {
                
                int idx = text.indexOf(key,prevFoundIdx);
                
                
                
                if(idx == -1){
                    break;
                }

                if(idx>0 && isAlphaDigit(text.charAt(idx-1)))
                    break;
                
                if(idx<text.length()-1 && isAlphaDigit(text.charAt(idx+len)))
                    break;

                
                prevFoundIdx = idx+len;

                String prefixText = text.substring(0, idx);
                String suffixText = text.substring(idx + len);
                text = prefixText +apk.translationMap.get(key)+suffixText;
    
                
            }

        }

        System.out.println(text);
        return text;
    }


         /*

         * @Author Ajay kumar gond
         * @param App() : apk object, string : directory path, 
         * @param string : fileType --> targeted file types
         
         
         * @return boolean
         *
         * 
         * 
         * Reads out file recursively and gets contents 1 by 1
         * and gets changes done
         * then writes back changed text in
         * respective file
         * 
         * 
         */





    public static boolean readDirectory(
            App apk,
            String directoryPath,
            String fileType) {

        


         try{
            
            File directory = new File(directoryPath);
            File fileList[] = directory.listFiles();


            for (File file : fileList) {
                if(file.isDirectory()){    
                    readDirectory(apk, file.toString(), fileType);
                }else{
                    String fileStr = file.toString();
    
                    if(!(fileStr.substring(fileStr.lastIndexOf(".")+1 ).equals(fileType))){
                        continue;
                    }
    
    
                    int i = 1;
    
                    System.out.println("Reading file :: "+(i++));
    
                    try(BufferedReader br = new BufferedReader(new FileReader(file))){
                        String line, text = "";
                
                        while((line=br.readLine() ) != null){
                            text += line;
                            text += "\n";
                        }
                        System.out.println(text);
                
                        text = replaceWordInText(apk,text);
                
                        System.out.println();
                        System.out.println("changed text :: "+text);
                        System.out.println();
                
                        try(BufferedWriter bw = new BufferedWriter( new FileWriter(file))){
                            bw.write(text);
                            System.out.println("Written successfully!!!");

                            return true;

                        }catch(Exception e){
                            System.out.println("Filewriter exception"+e);

                            return false;
                        }
                
                        }catch(Exception e){
                            System.out.println("Exception readFileTypeFile"+e);
                        }
                    }
    
                
                }

         }catch(Exception e){
            System.out.println("Exception :: "+e);
            return false;
         }

        

            return true;
        
    }

    /*
     * @author Ajay kumar gond
     * @param string array args
     * @return void return type
     * 
     * 
     */

    public static void main(String[] args) {
        // // input :: excel file path, directory path, file types

       
        // final String excelFilePath = "C:\\Users\\Monocept\\Desktop\\desktop\\code\\java_tokenizer\\translation.xlsx";
        
        // final String directoryPath = "C:\\Users\\Monocept\\Desktop\\desktop\\code\\java_tokenizer\\java_files";
       
        // final String fileTypeToChange = "java";


        // App apk = new App();

        // apk.setExcelFilePath(excelFilePath);
        // apk.setDirectoryPath(directoryPath);
        // apk.setFileType(fileTypeToChange);

        // readExcelFile(apk, excelFilePath);

        // System.out.println(apk.keyList.toString());
        // System.out.println();
        // System.out.println(apk.valueList.toString());
        // System.out.println();
        // System.out.println(apk.translationMap.toString());

        // readDirectory(apk, directoryPath, fileTypeToChange);

    }
}
