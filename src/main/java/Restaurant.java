import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private HashMap<String, Item> menus = new HashMap<>(); // Better using hashmap, less time complexity

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        LocalTime currentTime = getCurrentTime();

        return (closingTime.isAfter(currentTime) || closingTime.equals(currentTime)) &&
        (openingTime.isBefore(currentTime) || openingTime.equals(currentTime));
    }

    public LocalTime getCurrentTime(){ return LocalTime.now(); }

    public List<Item> getMenu() {
        Collection<Item> values = menus.values();

        ArrayList<Item> listOfValues = new ArrayList<>(values);
        return listOfValues;
    }

    private Item findItemByName(String itemName){
        return menus.get(itemName);
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menus.put(name, newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menus.remove(itemName);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

}
