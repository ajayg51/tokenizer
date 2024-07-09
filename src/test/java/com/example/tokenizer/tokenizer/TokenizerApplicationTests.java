package com.example.tokenizer.tokenizer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.tokenizer.tokenizer.service.AppService;

import static org.junit.Assert.assertTrue;




@SpringBootTest
class TokenizerApplicationTests {


	private final String excelFilePath = "C:\\Users\\Monocept\\Desktop\\desktop\\code\\java_tokenizer\\translation.xlsx";
        
    private final String directoryPath = "C:\\Users\\Monocept\\Desktop\\desktop\\code\\java_tokenizer\\java_files";
    private final String fileType = "java";


	@Autowired
	AppService apk;

    @Test
    public void TestReadExcelPath(){
        if(AppService.readExcelFile(apk, null) == true){
            assertTrue("Read excel file successfully",true);
        }else{
            assertTrue("Failure read excel file",false);
        }
        
    }


    @Test
    public void TestReadDirectory(){
        
        if(AppService.readDirectory(apk, null, null) == true){
            assertTrue("Directory non-empty",true);
        }else{
            assertTrue("Empty directory",false);
        }
        
    }

    @Test
    public void TestReplaceWordInText(){
         if(AppService.replaceWordInText(apk, "").length() == 0){
            assertTrue("Text found empty",true);
        }else{
            assertTrue("Text found non-empty",false);
        }
        
    }

}
