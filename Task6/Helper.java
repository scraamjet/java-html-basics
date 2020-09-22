import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

class Helper {
     static void receivesCommands(){
         System.out.println("/id/натуральное число - данные пользователя по id(всего 12 ID[0;11])" +
                 "\n/id/ALL - данные всех пользователей" +
                 "\n/feed - все посты пользователей" +
                 "\n/messages - все сообщения пользователей друг другу");
     }
     static void makeHTMLFile(String text, String filename) throws IOException {
        String a  = "";
        File file = new File(filename);
        String begin  = "<!DOCTYPE html>\n<html lang = \"rus\">\n<meta charset = \"UTF-8\">\n<body>\n";
        String end = "\n</body>\n</html>";
        a+= begin + text + end;
        FileWriter fileWriter = new FileWriter(filename);
        fileWriter.write(a);
        fileWriter.close();
    }

     static void browserOpen(String filename){
        Desktop desktop = Desktop.getDesktop();
        try{
            URI uri = new URI(filename);
            desktop.browse(uri);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
    static void Error() throws IOException {
        makeHTMLFile("<h1 style = \"font-size:500\">\nHTTP ERROR 404</h1>\n<br>Not Found 404","404.html");
        browserOpen("404.html");
    }
    static void ErrorID() throws IOException {
        makeHTMLFile("<h1 style = \"font-size:500\">\nHTTP ERROR 404</h1>\n<br>Пользователя с таким ID не существует в базе данных!","404.html");
        browserOpen("404.html");
    }
}
