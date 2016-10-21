package org.sbx.managers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sbx.enums.Mode;
import org.sbx.exceptions.FilesException;
import org.sbx.interfaces.DataManager;
import org.sbx.interfaces.LoggingMessenger;
import org.sbx.messages.ApplicationDebugMessages;
import org.sbx.messages.FileErrorMessages;
import org.sbx.messages.FileInfoMessages;
import org.sbx.objects.DBRecord;
import org.sbx.objects.Record;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by aloginov on 19.10.16.
 */
public class FileManager extends DataManager {
    private static final Logger logger = LogManager.getLogger(FileManager.class);

    private File file;

    private FileInputStream inputStream;
    private Scanner scanner;

    private Mode mode;

    List<String> list = new ArrayList<String>();

    private boolean success;

    public FileManager(){

    }

    public void setFile(String fileName){
        try {
            initFile(fileName);
        } catch (FilesException ex){
            logger.debug(ex.getErrorMessage());
        }
    }

    private void initFile(String fileName) throws FilesException{
        this.file = new File(fileName);
        if (!isFileExists())
            throw new FilesException(FileErrorMessages.FILE_DOES_NOT_EXIST);
    }

    public void setMode(Mode mode){
        this.mode = mode;
    }

    public void initConnection(){
        switch (mode){
            case READ:
                openFileForInput();
                break;
            default:
                // TODO
        }
    }

    private void openFileForInput(){
        logger.debug(ApplicationDebugMessages.METHOD_STARTED);

        this.success = false;

        if (!isFileExists()) {
            logger.error(FileErrorMessages.FILE_DOES_NOT_EXIST.getMessage(), file.getAbsolutePath());
        } else {
            try {
                this.inputStream = new FileInputStream(file);
                this.scanner = new Scanner(inputStream);

                if (this.scanner != null) {
                    this.success = true;
                }
            } catch (IOException ex){
                logger.error(ex);
            } finally {
                if (success)
                    logger.debug(FileInfoMessages.FILE_OPENED.getMessage(), file.getAbsolutePath(), FileInfoMessages.READ_MODE);
                else
                    logger.debug(FileErrorMessages.CANNOT_OPEN_FILE.getMessage(), file.getAbsolutePath(), FileInfoMessages.READ_MODE);
            }
        }

        logger.debug(ApplicationDebugMessages.METHOD_FINISHED);
    }

    public List<String> getDataList(){
        return list;
    }

    public Record getRecord() {
        return null;
    }

    private boolean isFileExists() {
        boolean f = false;
        if (this.file != null)
            if (this.file.exists())
                f = true;

        return f;
    }

    public void saveData() {

    }

    public void loadData() {
        try {
            while (scanner.hasNextLine()){
                list.add(scanner.nextLine());
            }
        } finally {
            if (scanner != null){
                scanner.close();
            }
        }
    }
}
