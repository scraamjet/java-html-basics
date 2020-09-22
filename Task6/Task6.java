import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Task6 {
    public static void main(String[] args) throws IOException {
        Helper.receivesCommands();
        int idCount = 11;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String request = bufferedReader.readLine();
        bufferedReader.close();
        Pattern idPattern = Pattern.compile("^/id/\\d++$|^/id/ALL$");
        Matcher idMatcher = idPattern.matcher(request);
        if (idMatcher.find()) {
            idPattern = Pattern.compile("^\\d++$");
            Matcher digitMatcher = idPattern.matcher(idMatcher.group().substring(4));
            if (digitMatcher.find()) {
                if(Integer.parseInt((digitMatcher.group()))<=idCount) {
                    User.userID(digitMatcher.group());
                }
                else {
                    Helper.ErrorID();
                }
            } else {
                if (idMatcher.group().substring(4).equals("ALL")) {
                    User.userID(idMatcher.group().substring(4));
                } else {
                    Helper.Error();
                }
            }
        } else {
            switch (request) {

                case "/feed": {
                    Post.feed();
                    break;
                }
                case "/messages": {
                    Message.messages();
                    break;
                }
                default:{
                    Helper.Error();
                    break;
                }
            }
        }
    }
}
