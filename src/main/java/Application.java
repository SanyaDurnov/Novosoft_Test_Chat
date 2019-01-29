import Source.Chat;
import Source.ConfigReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) throws IOException {
        new ConfigReader();
        List<Thread> threadList = new ArrayList<>();
        Chat chat = new Chat();
        chat.fileClean();
        chat.setChatConfig();
        for (int i = 0; i < chat.getChatUserList().size(); i++){
            threadList.add(new Thread(chat.getChatUserList().get(i)));
        }

        for (Thread th:threadList) {
            th.start();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            String chars = reader.readLine();
            if (chars.equals("q")){
                for (Thread th:threadList) {
                    th.interrupt();
                }
            }
        }
    }
}
