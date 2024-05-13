// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;
public class HellWorld {
    public static void main(String[] args) {
        System.out.println("Try programiz.pro");
        // returns 141 = 51 + 51 + 39;
        FileSystem f = new FileSystem();

//        System.out.println(f.createPath("/a", 1));
//        System.out.println(f.get("/a"));
//        System.out.println(f.createPath("/a/b", 2));
//        System.out.println(f.get("/a"));
//        System.out.println(f.get("/a/b"));



        System.out.println(f.createPath("/a", 1));
        System.out.println(f.createPath("/a/b/c", 2));
        System.out.println(f.get("/a"));
        System.out.println(f.get("/a/b"));

    }
}
class FileSystem {
    /**
     * @param path: the path to be created
     * @param val: path associated value
     * @return: the result of create
     */
    Map<String,Node> map;
    public boolean createPath(String path, int val) {
        String[] parts = extractComponents(path);

        if(map.containsKey(parts[0])){
            Node node = map.get(parts[0]);
            int i;
            for( i = 1 ; i < parts.length ; i++ ){
                if(node.childrenMap.containsKey(parts[i]))
                {
                    node = node.childrenMap.get(parts[i]);
                }else
                    break;
            }
            if( i == parts.length)
            {
                node.value = val;
            }
            else
            {

                for(int k = i ; k < parts.length; k++){

                    Node n = new Node(parts[i],-1);
                    node.childrenMap.put(parts[i],n);
                    node = n;
                }
                node.value = val;
            }
        }
        else
            create(parts,val);
        return true;
    }

    public void create(String[] parts,int val){
        Node node = new Node(parts[0],-1);
        this.map.put(parts[0],node);
        Node n = null;
        for(int i = 1; i < parts.length; i++){
            n = new Node(parts[i],-1);
            node.childrenMap.put(parts[i],n);
            node = n;
        }
        node.value = val;
    }

    /**
     * @param path: the path of retrieve
     * @return: path associated value
     */
    public int get(String path) {
        String[] parts = extractComponents(path);
        if(parts.length == 0 )
            return -1;
        Node node = this.map.get(parts[0]);
        if(node == null)
            return -1;
        for(int i  = 1 ; i < parts.length; i++){
            Node n = node.childrenMap.get(parts[i]);
            if( n == null)
                return -1;
            node = n;
        }
        return node.value;
    }
    public FileSystem(){
        this.map = new HashMap<>();
    }

    public static String[] extractComponents(String input) {
        // Check if the input string is "/"
        if (input.equals("/")) {
            // Return an array of length 0
            return new String[0];
        }

        // Split the string using "/"
        String[] parts = input.split("/");

        // Extract the components starting from index 1
        String[] extracted = new String[parts.length - 1];
        for (int i = 1; i < parts.length; i++) {
            extracted[i - 1] = parts[i];
        }

        return extracted;
    }

}
class Node{
    String name;
    int value = -1;
    Map<String,Node> childrenMap;
    public Node(String name, int value){
        this.name = name;
        this.value = value;
        this.childrenMap = new HashMap<>();
    }
}
