
package main_pkg;

import javafx.collections.ObservableList;


public class Course {
  protected String coursename;
  protected int price;
  protected String time;
  protected String courseday;

    public Course(String coursename, int price, String time, String courseday) {
        this.coursename = coursename;
        this.price = price;
        this.time = time;
        this.courseday = courseday;
    }

    public String getCoursename() {
        return coursename;
    }

    public int getPrice() {
        return price;
    }

    public String getTime() {
        return time;
    }

    public String getCourseday() {
        return courseday;
    }

 
    public static int priceCourse(ObservableList<Course> list){
        int sum=0;
        for(Course c: list){
            sum=sum+c.getPrice();
        }
        return sum;
    }
public static String toString(ObservableList<Course> list,int sum){
    String bill="";
    for(Course c: list){
       bill+=c.getCoursename()+"= "+c.getPrice()+"\n";      
    }
    bill+=" "+"Your Total= "+sum+"\n\n\n";
    bill+=" "+"PAY THE BILL VIA"+"\n"+"   MUTUAL TRUST BANK L.T.D";
    return bill;
}  
}
