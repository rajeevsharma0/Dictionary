import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class dictionary {
        // root of the dictionaryNode
        private final dictionaryNode root;

        public dictionary() {
            root = new dictionaryNode();   // root is empty
        }

        private class dictionaryNode {
            private dictionaryNode[] children;   // declaration
            boolean isEnd;
            String ans;

            // initialization
            public dictionaryNode() {
                this.children = new dictionaryNode[27];  // special char
                this.isEnd = false;
                this.ans = "";
            }
        }

        // Insertion
        public  void insert(String word){
            dictionaryNode current = root;
            for (int i = 0 ; i < word.length();i++) {
                char ch = word.charAt(i);
                if (ch >= 'a' && ch <= 'z') {
                    if (current.children[ch - 'a'] == null) {
                        current.children[ch - 'a'] = new dictionaryNode();
                        current = current.children[ch - 'a'];
                    } else {
                        current = current.children[ch - 'a'];
                    }
                    current.isEnd = true;
                } else if (ch == '\'') {  //--> to print special
                    // System.out.print("Character: "+ word);
                    if (current.children[26] == null) {
                        current.children[26] = new dictionaryNode();
                        current = current.children[26];
                    } else {
                        current = current.children[26];
                    }
                    current.isEnd = true;
                }
            }
        }



        public String found(String prefix){
            dictionaryNode current = root;
            for (int i = 0 ; i < prefix.length();i++){
                char ch = prefix.charAt(i);
                if(ch>='a' && ch<='z'){
                    if(current.children[ch-'a'] == null) {
                        return "Not Found";
                    }
                    current = current.children[ch-'a'];
                    current.ans = prefix;
                }
                else if(ch=='\''){
                    if(current.children[26] == null) {
                        return "Word Not Found";
                    }
                    current = current.children[26];
                    current.ans = prefix;
                }

            }
            return current.ans +"\t"+"found";
        }





    public static void main(String[] args) throws IOException {
            dictionary trie = new dictionary();
            File file = new File("C:\\Users\\ULtraman\\Downloads\\list.txt");

            BufferedReader br = new BufferedReader(new FileReader(file));
            // Scanner vs Bufferreader
            String st;

            ArrayList<String> words = new ArrayList<>();

            while ((st = br.readLine()) != null){
                words.add(st);   // adding
            }



            for(String s: words){
                // System.out.println(s);
                trie.insert(s.toLowerCase());
            }

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a Word ");
        String userWord = scan.next();
        System.out.println("Result:"+"\t"+trie.found(userWord));
//        System.out.println("Result:"+"\t"+trie.suggestedWord(userWord));






    }

    }

