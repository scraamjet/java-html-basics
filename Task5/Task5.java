import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Task5 {

    public static void main(String[] args) throws IOException {
        String URL = "https://www.youtube.com";
        HttpURLConnection httpURLConnection = null;
        HttpURLConnection httpURLConnection1 = null;
        try{
            URL url = new URL(URL);
            httpURLConnection =(HttpURLConnection)url.openConnection();//создаю соединение
            httpURLConnection.setRequestMethod("GET");//метод запроса
            httpURLConnection.connect();//выполняю подключение
            String str = "";
            if(HttpURLConnection.HTTP_OK == httpURLConnection.getResponseCode()) {//Получает код состояния из сообщения ответа HTTP(код состояния 200 - все хорошо)
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream()));//Возвращает входной поток для чтения URL ресурса
                String line;
                FileWriter fileWriter = new FileWriter("GET.html");
                while((line = bufferedReader.readLine())!=null){
                    str+=line;
                    str+="\n";
                    fileWriter.write(str);
                }
                System.out.println("GET-запрос выполнен! Смотрите GET.html файл!");
                fileWriter.close();
                bufferedReader.close();
            }
            else{
                System.out.println("Error: "+httpURLConnection.getResponseCode());
            }
          httpURLConnection1 = (HttpURLConnection)url.openConnection();
            httpURLConnection1.setRequestMethod("POST");
            httpURLConnection1.setDoOutput(true);
            httpURLConnection1.setRequestProperty("Content-Length", "0");
            httpURLConnection1.connect();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpURLConnection1.getOutputStream()));
                String smth = "";
                bufferedWriter.write(smth);
                bufferedWriter.close();
                InputRead(httpURLConnection1);
                httpURLConnection1.disconnect();

        }catch (Throwable e){
            e.printStackTrace();
        }
        finally {
            if(httpURLConnection!=null){
                httpURLConnection.disconnect();
            }
            if(httpURLConnection1!=null){
                httpURLConnection1.disconnect();
            }
        }
    }
    public static void InputRead(HttpURLConnection httpURLConnection) throws IOException {
        String returnstr = null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        FileWriter fileWriter = new FileWriter("POST.html");
        String str;
        while((str = bufferedReader.readLine())!=null){
            returnstr+=str;
            returnstr+="\n";
            fileWriter.write(returnstr);
        }
        System.out.println("POST запрос-выполнен! Смотрите POST.html файл!");
        fileWriter.close();
        bufferedReader.close();
    }
}
