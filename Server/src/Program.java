
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sachin
 */
public class Program implements Serializable{
    String id,name;
    int sem,maxseats;
    static final long serialVersionUID = -7588980448693010399L;

    public Program(String id, String name, int sem, int maxseats) {
        this.id = id;
        this.name = name;
        this.sem = sem;
        this.maxseats = maxseats;
    }
     public  Program()
     {
         
     }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSem() {
        return sem;
    }

    public void setSem(int sem) {
        this.sem = sem;
    }

    public int getMaxseats() {
        return maxseats;
    }

    public void setMaxseats(int maxseats) {
        this.maxseats = maxseats;
    }
}
