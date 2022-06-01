/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minermasterriant;

/**
 *
 * @author ibrahim
 */
public class SensorData {
    public float t,x,y,z;
    public SensorData(float t,float x,float y,float z){
        this.t = t;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public String getX(){
        return ""+x;
    }
    public String getY(){
        return ""+y;
    }
    public String getZ(){
        return ""+z;
    }
    public String getT(){
        return ""+t;
    }
}
