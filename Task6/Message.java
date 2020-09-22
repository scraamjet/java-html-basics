import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Message {
    private static String txtFilename = "messages.txt";
    private String from;
    private String to;
    private String message;

    public Message() {}

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "From: "+getFrom() + " To: "+getTo()+"<br>\nMessage: " +getMessage()+"<br>\n";
    }
    static void messages() throws IOException {
        BufferedReader bufferedReader =  new BufferedReader(new FileReader(Message.class.getResource("/").getPath()+txtFilename));
        String information = "";
        String stringHelper = null;
        while ((stringHelper = bufferedReader.readLine())!=null){
            information+=stringHelper;
        }
        bufferedReader.close();
        String [] str = information.split("\\|");
        String id1="";
        String id2="";
        String sms;
        Pattern from = Pattern.compile("\\d+>");
        Pattern to = Pattern.compile("\\d+\\)");
        Pattern message = Pattern.compile("\\)\\$.+");
        ArrayList<Message> messageSet = new ArrayList<>();
        for(String b:str){
            Message SMS = new Message();
            Matcher matcher = from.matcher(b);
            if(matcher.find()){
                id1 = matcher.group().substring(0,matcher.group().length()-1);
                SMS.setFrom(id1);
            }
            matcher = to.matcher(b);
            if(matcher.find()){
                id2 = matcher.group().substring(0,matcher.group().length()-1);
                SMS.setTo(id2);
            }
            matcher = message.matcher(b);
            if(matcher.find()){
                sms = matcher.group().substring(2);
                SMS.setMessage(sms);
            }
            messageSet.add(SMS);
        }
        bufferedReader.close();
        str = User.usersReader();
        Pattern userID = Pattern.compile("ID:\\d+");
        Pattern userName = Pattern.compile("Name:[a-zA-Z]+\\s[a-zA-Z]+;");
        for(int i = 0;i<messageSet.size();i++){
            for (String b:str){
                Matcher matcher = userID.matcher(b);
                if(matcher.find()){
                    if(matcher.group().substring(3).equals(messageSet.get(i).getFrom())){
                        matcher = userName.matcher(b);
                        if(matcher.find())
                            messageSet.get(i).setFrom(matcher.group().substring(5,matcher.group().length()-1));
                    }
                    else
                    if(matcher.group().substring(3).equals(messageSet.get(i).getTo())){
                        matcher = userName.matcher(b);
                        if (matcher.find())
                            messageSet.get(i).setTo(matcher.group().substring(5,matcher.group().length()-1));
                    }
                }
            }
        }
        information = "";
        Iterator iterator = messageSet.iterator();
        while (iterator.hasNext()){
            information+=iterator.next();
        }
        Helper.makeHTMLFile(information,"messages.html");
        Helper.browserOpen("messages.html");
    }
}
