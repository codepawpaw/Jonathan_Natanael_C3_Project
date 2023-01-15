import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantTest {
    Restaurant restaurant;
    LocalTime openingTime = LocalTime.parse("10:30:00");
    LocalTime closingTime = LocalTime.parse("22:00:00");

    @BeforeEach
    public void setup() {
        restaurant = new Restaurant("Amelie's cafe","Chennai", openingTime, closingTime);
    }

    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        assertTrue(restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        Restaurant spiedRestaurant = spy(restaurant);
        doReturn(LocalTime.parse("23:30:00")).when(spiedRestaurant).getCurrentTime();

        assertFalse(spiedRestaurant.isRestaurantOpen());
    }

    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }

    @Test
    public void calculate_total_order_item_should_return_expected_number() {
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        List<String> items = new ArrayList();
        items.add("Sweet corn soup");
        items.add("Vegetable lasagne");

        assertEquals(388, restaurant.calculateTotalOrderItem(items));
    }

    @Test
    public void calculate_total_order_item_should_return_0_when_selected_menu_not_exists() {
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        List<String> items = new ArrayList();
        items.add("Chicken");

        assertEquals(0, restaurant.calculateTotalOrderItem(items));
    }
}