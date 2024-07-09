package com.example.tokenizer.tokenizer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.tokenizer.tokenizer.service.AppService;

import static org.junit.Assert.assertTrue;




@SpringBootTest
class TokenizerApplicationTests {


	private final String excelFilePath = "C:\\Users\\Monocept\\Desktop\\desktop\\code\\tokenizer\\translation.xlsx";
        
    private final String directoryPath = "C:\\Users\\Monocept\\Desktop\\desktop\\code\\tokenizer\\java_files";
    
    private final String fileType = "java";


	@Autowired
	AppService apk;

    @Test
    public void TestReadExcelPath(){
        if(AppService.readExcelFile(apk, excelFilePath) == true){
            assertTrue("Read excel file successfully",true);
        }else{
            assertTrue("Failure read excel file",false);
        }
        
    }


    @Test
    public void TestReadDirectory(){
        
        if(fileType != "java"){
            assertTrue("fileType should be java", false);
        }else{
            if(AppService.readDirectory(apk, directoryPath, fileType) == true){
                assertTrue("Directory non-empty and file rewritten successfully!!",true);
            }else{
                assertTrue("Empty directory or something went wrong while file re-writing",false);
            }
        }

        
        
    }

    @Test
    public void TestReplaceWordInText(){

        // test if key found or not found
         if(AppService.replaceWordInText(apk, "abcd").length() == 0){
            assertTrue("Text found empty",true);
        }else{
            assertTrue("Text found non-empty",false);
        }
        
    }

}
