package ui;

import euromillions.CuponEuromillions;
import euromillions.Dip;
import euromillions.EuromillionsDraw;

import java.util.logging.Logger;

public class DemoMain {

    private static final Logger LOGGER = Logger.getLogger( DemoMain.class.getName() );

    /**
     * demonstrates a client for ramdom euromillions bets
     */
    public static void main(String[] args) {

        // played sheet
        CuponEuromillions thisWeek = new CuponEuromillions();
        LOGGER.info("Betting with three random bets...");
        thisWeek.addDipToCuppon(Dip.generateRandomDip());
        thisWeek.addDipToCuppon(Dip.generateRandomDip());
        thisWeek.addDipToCuppon(Dip.generateRandomDip());

        // simulate a random draw
        EuromillionsDraw draw = EuromillionsDraw.generateRandomDraw();

        //report results
        LOGGER.info("You played:");
        String played = thisWeek.format();
        LOGGER.info(played);

        LOGGER.info("Draw results:");

        String results = draw.getDrawResults().format();
        LOGGER.info(results);

        LOGGER.info("Your score:");
        for (Dip dip : draw.findMatches(thisWeek)) {
            String score = dip.format();
            LOGGER.info(score);

        }
    }
}
