import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class SearchEngineHandler implements URLHandler {
    ArrayList<String> list = new ArrayList<String>();
    ArrayList<String> searchList = new ArrayList<String>();
    public String handleRequest(URI url) {

        if (url.getPath().equals("/")) {
            return "We have these strings stored: " + list;
        } 

        else if (url.getPath().equals("/add")){
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                list.add(parameters[1]);
                return "" + list;
            }
            return "Invalid Operation";
        }
            else {
                if (url.getPath().contains("/search")){
                    String[] parameters = url.getQuery().split("=");
                    if (parameters[0].equals("s")) {
                        for (String word : list){
                            if (word.contains(parameters[1])){
                                searchList.add(word);
                            }       
                        }      
                    return "" + searchList;
                    }
                }
                return "404 not Found!";
            }
        
        }
    }


class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new SearchEngineHandler());
    }
}