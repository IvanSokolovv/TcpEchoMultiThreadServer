import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;
import static java.util.logging.Logger.getAnonymousLogger;

public class TcpEchoMultiThreadServer {

    public static void main(String[] args) throws IOException {
        Logger logger = getAnonymousLogger();

        final int port;
        if (args.length == 0) {
            logger.info("В аргументах не задан порт");
            port = 80;
        } else {
            port = parseInt(args[0]);
        }

        ServerSocket serverSocket = new ServerSocket(port);
        logger.info("Сервер запущен на порту " + port);

        while (true) {
            logger.info("Ждем подключения клиента");
            Socket acceptedSocket = serverSocket.accept();
            new TcpEchoThread(acceptedSocket).start();
        }
    }
}