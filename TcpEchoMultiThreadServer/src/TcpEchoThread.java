import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

import static java.util.logging.Logger.getAnonymousLogger;

public class TcpEchoThread extends Thread {

    private final Socket SOCKET;

    public TcpEchoThread(Socket socket) {
        this.SOCKET = socket;
    }

    @Override
    public void run() {
        try {
            Logger logger = getAnonymousLogger();
            logger.info("Клиент подключен, принимаем данные");
            sleep(5000);
            InputStream inputStream = SOCKET.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            String acceptedInfo = dataInputStream.readUTF();
            logger.info("Принято: " + acceptedInfo);

            OutputStream outputStream = SOCKET.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            String toSend = "Отправляем: " + acceptedInfo;
            dataOutputStream.writeUTF(toSend);
            logger.info(toSend);

            inputStream.close();
            outputStream.close();
            logger.info("Сервер закончил свою работу");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
