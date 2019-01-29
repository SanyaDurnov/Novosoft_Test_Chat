package Source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Chat implements NovosoftChat {
    private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);
    ArrayList<String> fileStringList  = new ArrayList<>();
    Path chatPath;
    int clientsNumber;
    ArrayList<ChatUser> chatUserList = new ArrayList<>();

    private void readFromFile() throws IOException {
        FileReader fileReader = new FileReader(CHAT_CONFIG_PATH);
        BufferedReader reader = new BufferedReader(fileReader);
        for(String line= reader.readLine(); line != null; line = reader.readLine()) {
            fileStringList.add(line);
        }
        fileReader.close();
        reader.close();
    }

    public void fileClean() throws IOException{
        PrintWriter printWriter = new PrintWriter(CHAT_PATH);
        printWriter.write("");
        printWriter.close();
    }

    public Boolean setChatConfig() throws IOException{
        String[] inputValues;
        int seconds = 0;
        long index;
        String message;
        this.readFromFile();
        if( fileStringList.get(PATH_INDEX)!=null && !fileStringList.get(PATH_INDEX).isEmpty() ){
            chatPath = Paths.get(fileStringList.get(PATH_INDEX));
        }
        else {
            logger.error("There are no path to chat file found in chat config file!");
            return false;
        }
        if( fileStringList.get(CLIENTS_NUMBER_INDEX)!=null && !fileStringList.get(CLIENTS_NUMBER_INDEX).isEmpty() ){
            try {
                clientsNumber = Integer.parseInt(fileStringList.get(CLIENTS_NUMBER_INDEX));
            } catch (NumberFormatException e){
                logger.error("Wrong format for number of clients in chat config file");
                return false;
            }
        }
        else {
            logger.error("Number of clients not found!");
            return false;
        }
        for (int i = CLIENTS_START_INDEX; i<=fileStringList.size()-1; i++){
            inputValues = fileStringList.get(i).split("[ ]");
            try {
                seconds = Integer.parseInt(inputValues[0]);
                message = inputValues[1];
                index = i - CLIENTS_START_INDEX;

            }catch (NumberFormatException e){
                logger.info("Wrong line format in file");
                break;
            }catch (ArrayIndexOutOfBoundsException e){logger.error("Not enough parameters in file"); break;}
            chatUserList.add(new ChatUser(seconds,index,message));
        }
        if(!chatUserList.isEmpty()){
            return true;
        }else {
            return false;
        }
    }

    public Chat() {

    }

    public Path getChatPath() {
        return chatPath;
    }

    public int getClientsNumber() {
        return clientsNumber;
    }

    public ArrayList<ChatUser> getChatUserList() {
        return chatUserList;
    }
}
