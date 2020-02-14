/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operating.systems.project;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Khaled
 */
public class OperatingSystemsProject {
        private static Directory root = new Directory(null, "root", "~/root");
        private static Directory currentDir = root;
    /**
     * @param args the command line arguments
     */
    //hi VSCode!
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner kb = new Scanner(System.in);
        
        System.out.println("\nPlease enter one of these operations:"
                    + "\n1. mkdir [directory name]"
                    + "\n2. 'ls' to print the inside of the directoty or 'ls -l' to view the perrmisions also."
                    + "\n3. cd [.. or ~/.*]"
                    + "\n4. echo [text]"
                    + "\n5. cat [file name you want to view],, cat > [file name you want to create ends with .txt] \n\n");
        // I can make more commands like chmod, rm and more ,but unfortunately i have no time.
        
        while(true){
            System.out.print(currentDir.getPath() + "$: ");
            String input = kb.nextLine();
            String [] tokens = input.split(" ");
            try{
                if(tokens[0].equals("mkdir")){                
                    Directory newDir = new Directory(currentDir, tokens[1], currentDir.getPath() + "/" + tokens[1]);
                    currentDir.getChiledDirectory().add(newDir);
                }
                else if(tokens[0].equals("ls")){
                    if(tokens.length >= 2){
                        
                        if(tokens[1].equals("-l")){
                            currentDir.printPermition();
                        }
                        else{
                        System.out.println("Wrong command!");
                        }
                    }
                    else{
                        currentDir.printContent();
                    }
                }
                else if(tokens[0].equals("cd")){
                    if(tokens[1].equals("..")){
                        currentDir = currentDir.getParent();
                    }
                    else if(tokens[1].matches("~/.*")){
                        if(root.getPath().equals(tokens[1])){
                            currentDir = root;
                        }
                        else{
                            searchForDirectory(root, tokens[1]);
                            if(!currentDir.getPath().equals(tokens[1])){
                                System.out.println("Error! Wrong Path");
                            }
                        }
                    }
                    else{
                        Directory a = changeDirectory(currentDir, tokens[1]);
                        if(a != null){
                            currentDir = a;
                        }
                        else{
                            System.out.println("Error! no directory by this name: " + tokens[1]);
                        }
                    }
                }
                else if(tokens[0].equals("echo")){
                    for(int i = 1 ; i < tokens.length ; i++){
                        System.out.print(tokens[i]+" ");
                    }
                    System.out.println("");
                }
                else if(tokens[0].equals("cat")){
                    if(tokens[1].equals(">")){
                        File file = new File(tokens[2]);
                        PrintWriter writer = new PrintWriter(file);
                        String text = kb.nextLine();
                        writer.write(text);
                        currentDir.getFile().add(file);
                        writer.close();
                    }
                    else if(!tokens[1].equals(">")){
                        Scanner read = new Scanner(new File(tokens[1]));
                        String data;
                        String [] words;
                        while(read.hasNext()){
                            data = read.nextLine();
                            words = data.split(" ");
                            for(int i = 0 ; i < words.length ; i++){
                                System.out.print(words[i] + " ");
                            }
                            System.out.println("");
                        }
                        read.close();
                    }
                    else{
                        System.out.println("to use 'cat' command please enter the word 'cat'"
                                + " followed by the sign '>' to create new file,, if you want to view the data"
                                + "inside the file enter 'cat' followed by the file name.");
                    }
                }
                else if(tokens[0].equals("rm")){
                    if(tokens[1].matches(".*.txt")){
                        for(int i = 0 ; i <= currentDir.getFile().size() ; i++){
                            if(currentDir.getFile().get(i).getName().equals(tokens[1])){
                                currentDir.getFile().remove(i);
                            }
                        }
                    }
                    else{
                        for(int i = 0 ; i <= currentDir.getChiledDirectory().size() ; i++){
                            if(currentDir.getChiledDirectory().get(i).getName().equals(tokens[1])){
                                currentDir.getChiledDirectory().remove(i);
                            }
                        }
                    }
                }
                else{
                    System.out.println("Wrong Command!");
                }
            }
            catch(Exception ex){
                System.out.println("Somthing went wrong!");
            }
        }
    }

    public static Directory changeDirectory(Directory chiledDirectory, String chiled){
        for (Directory dir : chiledDirectory.getChiledDirectory()) {
            if(dir.getName().equals(chiled)){
                return dir;
            }
        }
        return null;
    }

    public static void searchForDirectory(Directory root, String pathWanted){
        if(root.getPath().equals(pathWanted)){
            currentDir = root;
        }
        else{
            for (Directory directory : root.getChiledDirectory()) {
                searchForDirectory(directory, pathWanted);
            }
        }
    }       
}
