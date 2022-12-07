import java.util.ArrayList;

public class App {

    public static void main(String[] args) {

        System.out.println("ArrayList Demo");

        //create an ArrayList object
        ArrayList<String> mylist = new ArrayList<>();


        //Add items to it
        mylist.add("apples");
        mylist.add("oranges");


        //Loop over and print the items
        for (String item : mylist) {
            System.out.println("Item -> " + item);
        }

        //Remove apples from the list
        mylist.remove("apples");

        //Print the no of item ionside the list
        int count = mylist.size();
        System.out.println(count);


    }


}