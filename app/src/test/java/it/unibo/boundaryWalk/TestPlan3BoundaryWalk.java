package it.unibo.boundaryWalk;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestPlan3BoundaryWalk {
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
    public void testPlan3BoundaryWalk(){
        int perimetro = 10;
        double RobotLen = 1.5;
        double RobotSpeed = 0.2;
        int time = (int) (100 * RobotLen/RobotSpeed);

        int room[][];

        room = boundaryWalk(time, appl);

        int i =0, j=0;
        while (room[i][j] == 1){
            i++;
        }
        if(room[--i][++j]==0)
            fail();
        while(room[i][j]==1)
                    j++;
        if(room[++i][--j]==0)
            fail();
        while (room[i][j] == 1 && i>=0)
            i--;
        if(room[++i][--j]==0)
            fail();
        while (room[i][j] == 1 && j>=0)
            j--;

    }

    private static final int SUD = 0;
    private static final int EST = 1;
    private static final int NORD = 2;
    private static final int OVEST = 3;

    public int[][] boundaryWalk(int time, MoveVirtualRobot robot){
        int room[][] = new int[200][200];

        int direction = 0;
        int r=0,c=0;
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
