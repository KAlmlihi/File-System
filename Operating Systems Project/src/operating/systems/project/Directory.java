/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operating.systems.project;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Khaled
 */
public class Directory {
    private Directory parent;
    private String name;
    private ArrayList<File> file;
    private ArrayList<Directory> chiledDirectory;
    private String path;

    public Directory() {
        this.parent = null;
        this.name = null;
        this.file = new ArrayList<File>();
        this.chiledDirectory = new ArrayList<Directory>();
        this.path = null;
    }
    
    

    public Directory(Directory parent, String name, String path) {
        this.parent = parent;
        this.name = name;
        this.file = new ArrayList<File>();
        this.chiledDirectory = new ArrayList<Directory>();
        this.path = path;
        // I guess i can make both file and chiledDirectory arraylists as only one arraylist but no time to think about it.
    }    
    
    
    public void printPermition(){
        for (File file1 : file) {
            System.out.println(file1.getName() + ": ( -read: " +file1.canWrite() + " -write: " + file1.canRead() + " -execure: " + file1.canExecute() + " )");
        }
    }
    
    public void printContent(){
        for (Directory dir : chiledDirectory) {
            System.out.print(dir.name + "     ");
        }
        for (File file : file) {
            System.out.print(file.getName() + "     ");
        }
        System.out.println("");
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    public void setParent(Directory parent) {
        this.parent = parent;
    }

    public void setChiledDirectory(ArrayList<Directory> chiledDirectory) {
        this.chiledDirectory = chiledDirectory;
    }

    public void setFile(ArrayList<File> file) {
        this.file = file;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public Directory getParent() {
        return parent;
    }

    public ArrayList<File> getFile() {
        return file;
    }

    public ArrayList<Directory> getChiledDirectory() {
        return chiledDirectory;
    }

    
    
    
    
}
