import java.awt.Component;

/**
 * class that tests the game
 * 
 * @author Cyrine Ben Ayed and Jessica Fend
 * @version Spring 2022
*/
public class Tests {

    /** Tests whether the components in windows show correctly */
    public static void checkButton(Component c) {
        assert c.isShowing(): "failed";
        System.out.println("passed");
    }

    /** Keep track of scores per day to make sure they change reasonably */
    public static void checkScore(int day, int mental, int physical, int academics, int social) {
        if (day==1) {
            assert mental >=50 && mental <= 60: "failed";
            assert physical >=40 && physical <= 60: "failed";
            assert academics >=50 && academics <= 60: "failed";
            assert social >=40 && social <= 60: "failed";
        }
        else if (day==2) {
            assert mental >=40 && mental <= 70: "failed";
            assert physical >=40 && physical <= 60: "failed";
            assert academics >=50 && academics <= 70: "failed";
            assert social >=40 && social <= 70: "failed";
        }
        else if (day==3) {
            assert mental >=30 && mental <= 80: "failed";
            assert physical >=40 && physical <= 70: "failed";
            assert academics >=50 && academics <= 80: "failed";
            assert social >=40 && social <= 80: "failed";
        }
        else if (day==4) {
            assert mental >=20 && mental <= 90: "failed";
            assert physical >=30 && physical <= 80: "failed";
            assert academics >=50 && academics <= 90: "failed";
            assert social >=30 && social <= 90: "failed";
        }
        else if (day==5) {
            assert mental >=20 && mental <= 90: "failed";
            assert physical >=30 && physical <= 80: "failed";
            assert academics >=50 && academics <= 100: "failed";
            assert social >=30 && social <= 100: "failed";
        }
        else if (day==6) {
            assert mental >=20 && mental <= 90: "failed";
            assert physical >=20 && physical <= 90: "failed";
            assert academics >=50 && academics <= 110: "failed";
            assert social >=30 && social <= 110: "failed";
        }
        else if (day==7) {
            assert mental >=20 && mental <= 90: "failed";
            assert physical >=10 && physical <= 100: "failed";
            assert academics >=50 && academics <= 120: "failed";
            assert social >=30 && social <= 120: "failed";
        }
        else {
            assert mental >=20 && mental <= 100: "failed";
            assert physical >=0 && physical <= 110: "failed";
            assert academics >=50 && academics <= 120: "failed";
            assert social >=30 && social <= 130: "failed";
        }
        System.out.println("Score check test passed");
    }

}
