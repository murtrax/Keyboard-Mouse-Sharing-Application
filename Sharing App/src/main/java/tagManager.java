
public class tagManager {

    public String getTag(String line){
        String removedStr;
        removedStr = line.substring(0, line.indexOf(" "));
        return removedStr;
    }

    public  String removeTag(String line){
        String removedStr;
        removedStr = line.substring(line.indexOf(" ")+1);
        return  removedStr;
    }


}
