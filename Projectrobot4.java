/**
 * Project name:RobotGames
 * Discription: player will find himself in a field of many vicious robots of two different kinds. He will be to trick the robots into crashing into one another and into the debris pile left from previous collisions
 * Author name: Weijie Huo
 * login name: huow
 * recitation section: RM2
 */
import java.util.*;
/*Projectrobot4 class is created to handle the creation of robots and broken robots
 *Besides it handle how the robots move and include some method determine robot's dead and return the location of robot 
 */
public class Projectrobot4{
  Random random = new Random();
  private int robotCoordinateX, robotCoordinateY, robotMoveCounter = 1;
  private boolean robotState = true, brokenRobot = false;
  // This method is to randomly create the robot on the board, and set the Icon depending on whether the robot is broken or not
  public void createRobot(){
    while(true){
      robotCoordinateX = random.nextInt(30);
      robotCoordinateY = random.nextInt(45);
      robotState = true;
      if(RobotGUI.boardLabel[robotCoordinateX][robotCoordinateY].getIcon()!=null)
        continue;
      if(brokenRobot == true)
        RobotGUI.boardLabel[robotCoordinateX][robotCoordinateY].setIcon(RobotGUI.robot2);
      else
        RobotGUI.boardLabel[robotCoordinateX][robotCoordinateY].setIcon(RobotGUI.robot);
      break;
    }
  }
  //robotMove method is used to get the player's location and allow the normal robot to move depending on player's location
  public void robotMove(int playerCoordinateX, int playerCoordinateY, Projectplayer3 player){
    if(robotState == false){}
    else{
      if(robotCoordinateX<playerCoordinateX&&robotCoordinateY<playerCoordinateY){
        RobotGUI.boardLabel[robotCoordinateX][robotCoordinateY].setIcon(null);
        robotCoordinateX=robotCoordinateX+1;
        robotCoordinateY=robotCoordinateY+1;
      }
      else if(robotCoordinateX>playerCoordinateX&&robotCoordinateY>playerCoordinateY){
        RobotGUI.boardLabel[robotCoordinateX][robotCoordinateY].setIcon(null);
        robotCoordinateX=robotCoordinateX-1;
        robotCoordinateY=robotCoordinateY-1;
      }
      else if(robotCoordinateX<playerCoordinateX&&robotCoordinateY>playerCoordinateY){
        RobotGUI.boardLabel[robotCoordinateX][robotCoordinateY].setIcon(null);
        robotCoordinateX=robotCoordinateX+1;
        robotCoordinateY=robotCoordinateY-1;
      }
      else if(robotCoordinateX>playerCoordinateX&&robotCoordinateY<playerCoordinateY){
        RobotGUI.boardLabel[robotCoordinateX][robotCoordinateY].setIcon(null);
        robotCoordinateX=robotCoordinateX-1;
        robotCoordinateY=robotCoordinateY+1;
      }
      else if(robotCoordinateX==playerCoordinateX&&robotCoordinateY>playerCoordinateY){
        RobotGUI.boardLabel[robotCoordinateX][robotCoordinateY].setIcon(null);
        robotCoordinateY=robotCoordinateY-1;
      }
      else if(robotCoordinateX>playerCoordinateX&&robotCoordinateY==playerCoordinateY){
        RobotGUI.boardLabel[robotCoordinateX][robotCoordinateY].setIcon(null);
        robotCoordinateX=robotCoordinateX-1;
      }
      else if(robotCoordinateX<playerCoordinateX&robotCoordinateY==playerCoordinateY){
        RobotGUI.boardLabel[robotCoordinateX][robotCoordinateY].setIcon(null);
        robotCoordinateX=robotCoordinateX+1;
      }
      else if(robotCoordinateX==playerCoordinateX&robotCoordinateY<playerCoordinateY){
        RobotGUI.boardLabel[robotCoordinateX][robotCoordinateY].setIcon(null);
        robotCoordinateY=robotCoordinateY+1;
      }
    }
  }
  //robotMove method is used to get the player's location and allow the broken robot to move depending on player's location
  public void borkenRobotMove(int playerCoordinateX, int playerCoordinateY, Projectplayer3 player){
    if(robotState == false){}
    else{
      int ranDir;
      if(robotMoveCounter%4==0){
        while(true){
          ranDir = random.nextInt(9)+1;
          if(ranDir==5)
            continue;
          break;}
        ranDir = ranDir-1;
        int broMoveVert = -(ranDir/3-1);
        int broMoveHor = (ranDir%3)-1;
        RobotGUI.boardLabel[robotCoordinateX][robotCoordinateY].setIcon(null);
        if(robotCoordinateX+broMoveVert<0){broMoveVert = 0;}
        if(robotCoordinateX+broMoveVert>29){broMoveVert = 0;}
        if(robotCoordinateY+broMoveHor<0){broMoveHor = 0;}
        if(robotCoordinateY+broMoveHor>44){broMoveHor = 0;}
        robotCoordinateX+=broMoveVert;
        robotCoordinateY+=broMoveHor;
        robotMoveCounter = 0;        
      }
      else{this.robotMove(player.getPlayerCoordinateX(), player.getPlayerCoordinateY(), player);}
    }
    robotMoveCounter++;
  }
  public int getRobotCoordinateX(){
    return robotCoordinateX;
  }
  public int getRobotCoordinateY(){
    return robotCoordinateY;
  }
  public boolean robotState(){
    if(robotState==false)
      return false;
    return true;
  }
  public void setRobotType(){
    brokenRobot = true;
  }
  public boolean ifBroken(){
    if(brokenRobot == true)
      return  true;
    else
      return false;
  }
  public void setRobotDead(){
    robotState = false;
  }
  public void setRobotLocation(){
    if(RobotGUI.boardLabel[robotCoordinateX][robotCoordinateY].getIcon()!=RobotGUI.debris&&robotState == true){
      if(brokenRobot == true)
        RobotGUI.boardLabel[robotCoordinateX][robotCoordinateY].setIcon(RobotGUI.robot2);
      else
        RobotGUI.boardLabel[robotCoordinateX][robotCoordinateY].setIcon(RobotGUI.robot);}
  }
}

