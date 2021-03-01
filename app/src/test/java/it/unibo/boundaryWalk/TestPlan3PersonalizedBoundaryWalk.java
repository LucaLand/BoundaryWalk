package it.unibo.boundaryWalk;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestPlan3PersonalizedBoundaryWalk {
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
        //Dati del committente in metri
        double lR = 2.4, lC = 2.4;
        double perimetro = (lR*2)+(lC*2);
        double RobotLen = 0.6;
        double RobotSpeed = 0.2;
        int time = (int) (100 * RobotLen/RobotSpeed);
        int homePosR = 1, homePosC = 1; //Cella di partenza


        //Start Test
        int room[][];

        room = boundaryWalk(time, appl, homePosR, homePosC);

        for(int i =0;i<=lR/RobotLen+1+homePosR;i++){
            for(int j=0; j<=lC/RobotLen+1+homePosC; j++){
                System.out.print(room[i][j]);
            }
            System.out.print("\n");
        }


        int i=homePosR, j=homePosC, count=0;
        while (room[i][j] == 1){
            i++;
            count++;
        }
        if(room[--i][++j]==0)
            fail();
        while(room[i][j]==1) {
            j++;
            count++;
        }
        if(room[--i][--j]==0)
            fail();
        while (room[i][j] == 1 && i>0) {
            i--;
            count++;
        }
        if (++i==homePosR) {
            if (room[i][--j] == 0)
                fail("Errore in angolo nord-est");
        }else
            fail("Robot oltre la riga di partenza!");

        while (room[i][j] == 1 && j>0) {
            j--;
            count++;
        }
        if (++j!=homePosC)
            fail("Errore Robot non tornato nel punto di partenza: Room["+i+","+j+"]");
        else
            count--;

        System.out.print("D: "+ count*RobotLen +"m\tL: "+ perimetro+"m\n");
        if((count*RobotLen) != perimetro)
            fail("d!=p -> La distanza percorsa dal robot non corrisponde alla lunghezza perimetro!");
    }

    private static final int SUD = 0;
    private static final int EST = 1;
    private static final int NORD = 2;
    private static final int OVEST = 3;

    public int[][] boundaryWalk(int time, MoveVirtualRobot robot, int homeR, int homeC){
        int room[][] = new int[200][200];

        room[homeR][homeC] = 2;
        int direction = 0;
        int r=homeR,c=homeC;
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

        return room;
    }
}
