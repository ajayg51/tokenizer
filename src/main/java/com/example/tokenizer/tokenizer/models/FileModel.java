package com.example.tokenizer.tokenizer.models;


public class FileModel {
    private String excel_file_path;
    private String directory_path;
    private String file_type_to_change;



    public String getExcel_file_path() {
        return excel_file_path;
    }

    public void setExcel_file_path(String excelFilePath) {
        this.excel_file_path = excelFilePath;
    }

    public String getDirectory_path() {
        return directory_path;
    }

    public void setDirectory_path(String directoryPath) {
        this.directory_path = directoryPath;
    }

    public String getFile_type_to_change() {
        return file_type_to_change;
    }

    public void setFile_type_to_change(String fileType) {
        this.file_type_to_change = fileType;
    }
}
