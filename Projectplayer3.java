/**
 * Project name:RobotGames
 * Discription: player will find himself in a field of many vicious robots of two different kinds. He will be to trick the robots into crashing into one another and into the debris pile left from previous collisions
 * Author name: Weijie Huo
 * login name: huow
 * recitation section: RM2
 */
import java.util.*;
//The Projectplayer3 class is used to randomly create player and handle player's move
public class Projectplayer3{
  private int Mov, moveVert, moveHor;
  Random random = new Random();
  private int playerCoordinateX;
  private int playerCoordinateY;
  private boolean playerState = true;
  /*This method is created to randomly create a number to determine where the player is without getting the location same as other robots
   * check whether the player location is reduplicate with robots by using getIcon()!=null
   * display player by using setIcon()
  */
  public void createPlayer(){
    while(true){
      playerCoordinateX = random.nextInt(30);
      playerCoordinateY = random.nextInt(45);
      if(RobotGUI.boardLabel[playerCoordinateX][playerCoordinateY].getIcon()!=null)
        continue;
      RobotGUI.boardLabel[playerCoordinateX][playerCoordinateY].setIcon(RobotGUI.playerIcon);
      break;
    }
  }
  /*As its name, this method is setted to handle player movement
   * Judge if gamer is inputing command to teleport or not
   * If not, update the player's location by computing moveVertical and moveHorizon
   * including boundry check using conditional command.
   */
  public void playerMove(int input){
    if(input==0){
      RobotGUI.boardLabel[playerCoordinateX][playerCoordinateY].setIcon(null);
      Teleport();
    }
    else{
      Mov = input-1;
      moveVert = -(Mov/3-1);
      moveHor = (Mov%3)-1;
      if(playerCoordinateX+moveVert<0){moveVert = 0;}
      if(playerCoordinateX+moveVert>29){moveVert = 0;}
      if(playerCoordinateY+moveHor<0){moveHor = 0;}
      if(playerCoordinateY+moveHor>44){moveHor = 0;}
      RobotGUI.boardLabel[playerCoordinateX][playerCoordinateY].setIcon(null);
      playerCoordinateX+=moveVert;
      playerCoordinateY+=moveHor;
      if(RobotGUI.boardLabel[playerCoordinateX][playerCoordinateY].getIcon()!=null){
        RobotGUI.boardLabel[playerCoordinateX][playerCoordinateY].setIcon(RobotGUI.playerIcon);
        setPlayerDead();
      RobotGUI.timer.stop();
      }
      else
        RobotGUI.boardLabel[playerCoordinateX][playerCoordinateY].setIcon(RobotGUI.playerIcon);
    }
  }
  // this method is creating the new location of player using the same way like create player's location
  public void Teleport(){
    while(true){
      playerCoordinateX = random.nextInt(30);
      playerCoordinateY = random.nextInt(45);
      if(RobotGUI.boardLabel[playerCoordinateX][playerCoordinateY].getIcon()!=null)
        continue;
      RobotGUI.boardLabel[playerCoordinateX][playerCoordinateY].setIcon(RobotGUI.playerIcon);
      break;
    }
  }
  public void setPlayerDead(){
    playerState = false;
    RobotGUI.boardLabel[RobotGUI.player.getPlayerCoordinateX()][RobotGUI.player.getPlayerCoordinateY()].setIcon(RobotGUI.deadIcon);
    RobotGUI.timer.stop();
  }
  public boolean playerState(){
    if(playerState==false)
      return false;
    return true;  }
  public int getPlayerCoordinateX(){
    return playerCoordinateX;  }
  public int getPlayerCoordinateY(){
    return playerCoordinateY;  }
  public boolean getPlayerState(){
    return playerState;}
}
