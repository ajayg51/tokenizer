// package com.example.tokenizer.tokenizer;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// import com.example.tokenizer.tokenizer.service.App;

// import static org.junit.Assert.assertTrue;

// import static org.junit.Assert.assertFalse;



// @SpringBootTest
// class TokenizerApplicationTests {


// 	private final String excelFilePath = "C:\\Users\\Monocept\\Desktop\\desktop\\code\\java_tokenizer\\translation.xlsx";
        
//     private final String directoryPath = "C:\\Users\\Monocept\\Desktop\\desktop\\code\\java_tokenizer\\java_files";
//     private final String fileType = "java";


// 	@Autowired
// 	App apk;

//     @Test
//     public void TestReadExcelPath(){
//         if(App.readExcelFile(apk, null) == true){
//             assertTrue("Read excel file successfully",true);
//         }else{
//             assertTrue("Failure read excel file",false);
//         }
        
//     }


//     @Test
//     public void TestReadDirectory(){
        
//         if(App.readDirectory(apk, null, null) == true){
//             assertTrue("Directory non-empty",true);
//         }else{
//             assertTrue("Empty directory",false);
//         }
        
//     }

//     @Test
//     public void TestReplaceWordInText(){
//          if(App.replaceWordInText(apk, "").length() == 0){
//             assertTrue("Text found empty",true);
//         }else{
//             assertTrue("Text found non-empty",false);
//         }
        
//     }

// }
