class foodFactory extends HelloWorld{
    Food ff = new Food();
    public Food getFood(String s){
        if(s.equals("Fastfood"))
            return new FastFood();
        else 
            return new Fruits();

    }
    public void servesFood(){

    }
}
class Food extends HelloWorld{
public void servesFood(){
        System.out.println("I am serving"+this.getClass().getName());
    }
}
class Fruits extends Food{}
class FastFood extends Food{}
public class HelloWorld{


    public static void main (String[] args) throws java.lang.Exception
    {
        // your code goes here
        foodFactory myFoods = new foodFactory();
        Food food1 = myFoods.getFood("Fastfood");
        Food food2 = myFoods.getFood("Fruits");
        System.out.println("My name is: " + food1.getClass().getName());
        System.out.println("My name is: " + food2.getClass().getName());
        System.out.println("Our superclass is: " + food1.getClass().getSuperclass().getName());
        food1.servesFood();
        food2.servesFood();

    }

}