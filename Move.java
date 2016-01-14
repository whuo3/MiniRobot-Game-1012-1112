/**
 * Project name:RobotGames
 * Discription: player will find himself in a field of many vicious robots of two different kinds. He will be to trick the robots into crashing into one another and into the debris pile left from previous collisions
 * Author name: Weijie Huo
 * login name: huow
 * recitation section: RM2
 */
import javax.swing.*;
public class Move{
  /* 
   * The first repetition is to allow all the robots to move
   * The second repetition is to check if the robots are dead after the robots move
   * 
   */
  public static void gameMove(int moveDirection){
    for(int i=0; i<RobotGUI.gameRobots.length; i++){
      if(RobotGUI.gameRobots[i].ifBroken() == true)
        RobotGUI.gameRobots[i].borkenRobotMove(RobotGUI.player.getPlayerCoordinateX(), RobotGUI.player.getPlayerCoordinateY(), RobotGUI.player);
      else
        RobotGUI.gameRobots[i].robotMove(RobotGUI.player.getPlayerCoordinateX(), RobotGUI.player.getPlayerCoordinateY(), RobotGUI.player);}
    for(int i=0; i<RobotGUI.gameRobots.length; i++){
      if(RobotGUI.gameRobots[i].robotState()==false)
        continue;
      for(int j=0; j<RobotGUI.gameRobots.length; j++){
        if(i==j)
          continue;
        if(RobotGUI.gameRobots[i].getRobotCoordinateX()==RobotGUI.player.getPlayerCoordinateX()&&RobotGUI.gameRobots[i].getRobotCoordinateY()==RobotGUI.player.getPlayerCoordinateY()){
          RobotGUI.player.setPlayerDead();
          RobotGUI.boardLabel[RobotGUI.player.getPlayerCoordinateX()][RobotGUI.player.getPlayerCoordinateY()].setIcon(RobotGUI.deadIcon);
        }
        else if(RobotGUI.gameRobots[i].getRobotCoordinateX()==RobotGUI.gameRobots[j].getRobotCoordinateX()&&RobotGUI.gameRobots[i].getRobotCoordinateY()==RobotGUI.gameRobots[j].getRobotCoordinateY()){
          RobotGUI.gameRobots[i].setRobotDead();
          RobotGUI.gameRobots[j].setRobotDead();
          RobotGUI.boardLabel[RobotGUI.gameRobots[i].getRobotCoordinateX()][RobotGUI.gameRobots[i].getRobotCoordinateY()].setIcon(RobotGUI.debris);
          break;
          // this break can improve the efficiency of the game by trying to avoid some repetition which make no sense to the game
        }
        else
          RobotGUI.gameRobots[i].setRobotLocation();}
    }
    //Check how many robots are still alive on the board, and then update the statusBar
    RobotGUI.robotaliveNum=0;
    for(int i =0; i<RobotGUI.gameRobots.length; i++){
      if(RobotGUI.gameRobots[i].robotState()==true){
        RobotGUI.robotaliveNum++;}   
    }
    
    RobotGUI.statusBar.setText("GameLevel: "+RobotGUI.gameLevel+"   "+"Num of Robots: "+ RobotGUI.robotaliveNum);
    /*
     * There are two situations to determine if the plyer win or lose 
     * 1. if the robots are all dead, then the player win. Otherwise, game over
     * 2. Whether the playerState is false or not. if false game over. Otherwise, Win
     * 3. if Game win, A pane would show up to tell you that you win, and there are 10 more robots waiting for you than the previous level
     * 4. if Game lose, A option pane would show up to ask if you want to continute, if continute,the game restart, otherwise, exit.
     */
    if(RobotGUI.robotaliveNum ==0){
      JOptionPane.showMessageDialog(null,"You win!");
      RobotGUI.setGameOver();
      RobotGUI.gameLevel++;
      RobotGUI.gameRobots = null;
      RobotGUI.gameRobots = RobotGUI.initializeRobot();
      RobotGUI.player = null;
      RobotGUI.player = new Projectplayer3();
      RobotGUI.buNum = 100; // avoid the when game restart, the robot keep runing.
    }
    else if(RobotGUI.player.playerState()==false){
      RobotGUI.timer.stop();
      if(JOptionPane.showConfirmDialog(null,"GameOver!!\nWould you want to play again?", "GameOver", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
        RobotGUI.setGameOver();
        RobotGUI.gameLevel=1;
        RobotGUI.gameRobots = null;
        RobotGUI.gameRobots = RobotGUI.initializeRobot();
        RobotGUI.player = null;
        RobotGUI.player = new Projectplayer3();       
      }
      else
        System.exit(0);
      RobotGUI.buNum = 100; // avoid the when game restart, the robot keep runing.
    }
    // if gameover, clean the gameBoard
    if(RobotGUI.gameOver == true){
      for(int i = 0; i<30; i++){
        for(int j = 0; j<45; j++){
          RobotGUI.boardLabel[i][j].setIcon(null);
        }
      }
      RobotGUI.robotaliveNum = 0;
      for(int z =0;z<RobotGUI.gameRobots.length; z++){
        RobotGUI.gameRobots[z].createRobot();
        RobotGUI.robotaliveNum++;}
      RobotGUI.player.createPlayer();
      RobotGUI.gameOver = false;
      RobotGUI.timer.restart();
    }
    //this especially aim to update the data on substatus if the game restart.
    RobotGUI.statusBar.setText("GameLevel: "+RobotGUI.gameLevel+"   "+"Num of Robots: "+ RobotGUI.robotaliveNum);
  }
}