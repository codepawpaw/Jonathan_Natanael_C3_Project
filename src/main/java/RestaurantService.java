import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class RestaurantService {
    private static HashMap<String, Restaurant> restaurants2 = new HashMap<>(); // more faster, with time complexity O(1)

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurant = restaurants2.get(restaurantName);

        if (restaurant == null) {
            throw new restaurantNotFoundException(restaurantName);
        }

        return restaurant;
    }

    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants2.put(name, newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);

        restaurants2.remove(restaurantName);

        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        Collection<Restaurant> values = restaurants2.values();

        ArrayList<Restaurant> listOfValues = new ArrayList<>(values);
        return listOfValues;
    }
}
