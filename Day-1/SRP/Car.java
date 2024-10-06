class Car{
    String model;
    String brand;
    int year;
    int price;
    
    public Car(String model,String brand, int year, int price){
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.price = price;
    }
}

class Insurance{
    private Car car;
    private int val = 5000;
    
    public Insurance(Car car){
        this.car = car;
    }
    
    public void CalculateInsurance(){
     if(this.car.year<=2020){
         System.out.println("The insurance of this car of ths model " + this.car.year+" is: "+ this.val*2);
     }   
     else{
         System.out.println("The insurance of the car model is: "+ this.val);
     }
    }
}

public class Main{
    public static void main(String[] args){
        Car obj = new Car("City", "Honda", 2013,170000);
        
        Insurance obj2 =  new Insurance(obj);

        
        obj2.CalculateInsurance();
    }
}
