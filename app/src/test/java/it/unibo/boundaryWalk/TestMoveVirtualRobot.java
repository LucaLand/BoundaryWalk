package it.unibo.boundaryWalk;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestMoveVirtualRobot {
    private MoveVirtualRobot appl;

    @Before
    public void systemSetUp() {
        System.out.println("TestMoveVirtualRobot | setUp: robot should be at HOME-DOWN ");
        appl = new MoveVirtualRobot();
    }

    @After
    public void  terminate() {
        System.out.println("%%%  TestMoveVirtualRobot |  terminates ");
    }

    //@Test
    public void testMovesNoFailing() {
        System.out.println("TestMoveVirtualRobot | testWork ");
        boolean moveFailed = appl.moveLeft(300);
        assertTrue( ! moveFailed  );
        moveFailed = appl.moveRight(1000);    //back to DOWN
        assertTrue( ! moveFailed  );
        moveFailed = appl.moveStop(100);
        assertTrue( ! moveFailed  );
    }

    //@Test
    public void testMoveForwardNoHit() {
        System.out.println("TestMoveVirtualRobot | testMoveForward ");
        boolean moveFailed = appl.moveForward(600);
        assertTrue( ! moveFailed  );
        moveFailed = appl.moveBackward(600);  //back to home
        assertTrue( ! moveFailed  );
    }

    //@Test
    public void testMoveForwardHit() {
        System.out.println("TestMoveVirtualRobot | testMoveForward ");
        boolean moveFailed = appl.moveForward(1600);
        assertTrue( moveFailed  );
        moveFailed = appl.moveBackward(1600);       //back to home
        assertTrue( moveFailed  );
    }



    //@Test
    public void testPlan3BoundaryWalk(){
        int room[][];
        int time = 300;
        room = boundaryWalk(time, appl);

    }

    private static final int SUD = 0;
    private static final int EST = 1;
    private static final int NORD = 2;
    private static final int OVEST = 3;

    public int[][] boundaryWalk(int time, MoveVirtualRobot robot){
        int room[][] = new int[200][200];

        int direction = 0;
        int r=1,c=1;
        boolean moveFailed;
        for(int i=0; i<4; i++) {
            do {
                moveFailed = robot.moveForward(time);
                if(!moveFailed) {
                    switch (direction) {
                        case SUD:
                            r++;
                            break;
                        case NORD:
                            r--;
                            break;
                        case EST:
                            c++;
                            break;
                        case OVEST:
                            c--;
                    }
                    room[r][c] = 1;
                }
            }while(!moveFailed);
            moveFailed = robot.moveLeft(200);
            if(!moveFailed){
                direction++;
            }else
                assertTrue(!moveFailed);
        }
        for(int i =0;i<10;i++){
           for(int j=0; j<10; j++){
               System.out.print(room[i][j]);
           }
           System.out.print("\n");
        }

        return room;
    }

}