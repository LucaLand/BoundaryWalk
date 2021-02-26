package it.unibo.boundaryWalk;

import org.apache.commons.codec.language.bm.Rule;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TestPlan1BoundaryWalk {

    private MoveVirtualRobot appl;

    @Before
    public void systemSetUp() {
        System.out.println("TestMoveVirtualRobot | setUp: robot should be at HOME-DOWN ");
        appl = new MoveVirtualRobot();
    }

    @After
    public void  terminate() {
        System.out.println("%%%  TestPlan1 |  terminates ");
    }

    @Test
    public void TestPlan1(){
        String moves = "";

        boolean moveFailed;
        for(int i =0; i<4; i++){
            do {
                moveFailed = appl.moveForward(300);
                moves = moves.concat("w");
            }while(!moveFailed);
            moveFailed = appl.moveLeft(350);
            moves = moves.concat("l");
        }
        System.out.println("Stringa: " + moves);
        assertTrue(moves.matches("w*lw*lw*lw*l"));

    }

}
