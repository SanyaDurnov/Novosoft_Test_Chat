package Source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatUser implements Runnable,NovosoftChat {
    private static final Logger logger = LoggerFactory.getLogger(ChatUser.class);
    int seconds;
    long index;
    String message;
    boolean isCancel = false;

    public ChatUser(int seconds, long index, String message) {
        this.seconds = seconds;
        this.index = index;
        this.message = message;
    }

    public void setCancel(boolean cancel) {
        isCancel = cancel;
    }

    @Override
    public String toString() {
        Date time = new Date();
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss;");
        return format.format(time) + " " + this.index + ": " + this.message;
    }

    @Override
    public void run() {
        try{
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(this.seconds * 1000);
                Singleton.getInstance().writeToFile(CHAT_PATH,this.toString());
             //   System.out.println(this.toString());
            }
    } catch (InterruptedException e) {
      //  e.printStackTrace();
        System.out.println(Thread.currentThread().getName() + " -closed!");
    }catch (IOException e){
            e.printStackTrace();
        }
    }
}
