
/******************************************************************************
 Approach:
 We run one million simulations. In each simulation, we give the user one million "tries". Each try represents the user
 randomly selecting between 1-4 buttons to press. These buttons must be adjacent to each other. If the buttons are all off at any point,
 that simulation ends. If the user doesn't find a solution within one million random tries, the simulation fails.

 In version 2, we replace the random selection of buttons with alternating between pressing 2 and 3 buttons each time. The average number of tries
 was 14.5.

 In version 1, on average, the number of tries until success was about 15.7. This is not a proof.
 *******************************************************************************/

public class Main {
    public static void main(String[] args) {
        boolean[] buttons = new boolean[4];
        int tries = 1000000;
        double sumOfTries = 0.0;
        int maxTries = 0;

        for (int x = 0; x < tries; x++) {
            buttons = new boolean[4];

            boolean allOff = true;

            boolean success = false;
            for (int i = 0; i < buttons.length; i++) {
                if (Math.random() < 0.5) { //randomly set each button to on/off at start
                    buttons[i] = true;
                    allOff = false;
                }
            }

            int numPress;
            int start;
            for (int i = 0; i < 1000000; i++) { //give it a very large number of tries

                //version 2: alternate between pressing 2 and 3 buttons
                if (i % 2 == 0) {
                    numPress = 2;
                } else {
                    numPress = 3;
                }

                //version 1: randomly selected a number of buttons to press
                // numPress = (int) (Math.random() * 4) + 1; //randomly press between 1 - 4 buttons

                start = (int) (Math.random() * 4); // start at a random index (to simulate the spinning/uncertain start of the wheel)

                for (int n = 0; n < numPress; n++) {
                    buttons[start] = !buttons[start]; //flip the button
                    start = (start + 1) % 4; //increase the index, going to index 0 if we go too far
                }

                allOff = true;
                for (int n = 0; n < buttons.length; n++) { //determine if all the buttons are off
                    if (buttons[n]) {
                        allOff = false;
                    }
                }

                if (allOff) { //if the buttons are all off, we have won
                    success = true;
                    sumOfTries += (i + 1);
                    maxTries = Math.max(maxTries, (i + 1));
                    //System.out.println("SUCCESS!! Num tries: " + (i + 1));
                    break;
                }
            }
            if (success == false) {
                System.out.println("FAILURE");
                return;
            }

        }

        System.out.println("Average num tries per success: " + (sumOfTries / tries));
        System.out.println("Largest number of tries: " + maxTries);
    }
}

