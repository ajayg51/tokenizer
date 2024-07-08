package com.example.tokenizer.tokenizer.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tokenizer.tokenizer.models.FileModel;
import com.example.tokenizer.tokenizer.service.App;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/parse")
public class ApiController {
    

    @Autowired
    App apk;

    @PostMapping("/change-file-text")
    public ResponseEntity<String> postMethodName(@RequestBody FileModel fileModel) {
        String excelFilePath = fileModel.getExcel_file_path();
        String directoryPath = fileModel.getDirectory_path();
        String fileTypeToChange = fileModel.getFile_type_to_change();


        // System.out.println("Excel file path"+excelFilePath);
        // System.out.println("directory path"+directoryPath);
        // System.out.println("fileTypeToChange"+fileTypeToChange);

        if(!fileTypeToChange.equals("java")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("File type to change should be java");
        }


        boolean readExcelFileSuccess = App.readExcelFile(apk, excelFilePath);
        
        if(readExcelFileSuccess == false){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Something wrong while reading excel file");
        }

        boolean readDirectorySuccess = App.readDirectory(apk, directoryPath, fileTypeToChange);

        if(readDirectorySuccess == false ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Something went wrong while reading directory or writing files to directory");
        
        }

        return ResponseEntity.status(HttpStatus.OK).body("Files changed successfully");
    }
    
}
