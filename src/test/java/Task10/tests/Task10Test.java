package Task10.tests;

/**
 * Created by andrii.omelchuk on 4/21/2017.
 */
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Task10Test extends GenericTest{


    @Test
    public void myTest(){

        for(int i = 0; i<3; i++){
            app.addFirstItem();
        }
            app.removeAllFromCart();
        assertEquals(app.getQuantityOfItemsInCart(), "0");
    }
}
