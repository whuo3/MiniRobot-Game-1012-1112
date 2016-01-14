/**
 * Project name:RobotGames
 * Discription: player will find himself in a field of many vicious robots of two different kinds. He will be to trick the robots into crashing into one another and into the debris pile left from previous collisions
 * Author name: Weijie Huo
 * login name: huow
 * recitation section: RM2
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.Timer;
/*Thi class is used to create the GUI of the robot game
 * 1.create the user interface by adding buttons object and boardlabel into different panel, and properly set layout of the panels. create Menu, and substatus
 * 2.add actionlistener to buttons, and allow buttons to control the movement of player
 * 3.making real time/ one per tick(200ms)
 * 4.Set holding down the buttons resulting in a continuous stream of player movements by using MouseListerner
 */ 
public class RobotGUI extends JFrame implements ActionListener, MouseListener{
  
  public static Timer timer;
  int times=0;
  boolean loopCondition;
  static int buNum;
  
  public static boolean gameOver = false;
  public static int totalRobotNum,moveDirection, robotaliveNum=10;
  public static int gameLevel=1;
  
  public static Icon debris = new ImageIcon("debris.png");
  public static Icon robot2 = new ImageIcon("robot2.png");
  public static Icon robot = new ImageIcon("robot.png");
  public static Icon playerIcon = new ImageIcon("player.png");
  public static Icon deadIcon = new ImageIcon("dead.png");
  
  private final String buttonName[] ={"Up Left", "Up", "Up Right", "Left", "Wait", "Right", "Down Left", "Down", "Down Right", "Teleport"};
  private JButton buttons[] = new JButton[buttonName.length];
  public static JLabel boardLabel[][] = new JLabel[30][45];
  private Container container;
  private JPanel buttonPanel;
  private JPanel boardPanel;
  public static JLabel statusBar = new JLabel();
  private JMenuBar bar = new JMenuBar();
  private JMenu gameMenu = new JMenu("Game");
  private JMenuItem restart = new JMenuItem("New Game");   
  private Color c1 = new Color(10, 50, 150);
  private Color c2 = new Color(10, 50, 200);
  
  static Projectplayer3 player = new Projectplayer3();
  static Projectrobot4[] gameRobots = initializeRobot();
  
  /**
   * Default constructor
   */
  
  public RobotGUI(){
    setVisible(true);
    buttonPanel = new JPanel();
    boardPanel = new JPanel();
    
    //initialize button[i], addActionListener to buttons, add buttons to the ButtonPanel
    for(int i = 0; i<buttonName.length; i++){
      buttons[i]=new JButton(buttonName[i]);
      buttons[i].addMouseListener(this);
      buttons[i].addActionListener(this);
      buttonPanel.add(buttons[i]);}
    //initialize boardLabels, set its colour, size and add them to boardPanel
    for(int i = 0; i<30; i++){
      for(int j = 0; j<45; j++){
        boardLabel[i][j] = new JLabel();
        boardLabel[i][j].setOpaque(true);
        boardLabel[i][j].setSize(24, 24);
        if((i+j)%2==0)
          boardLabel[i][j].setBackground(c1);
        else
          boardLabel[i][j].setBackground(c2);
        boardPanel.add(boardLabel[i][j]);        
      }
    }
    container = getContentPane();
    buttonPanel.setLayout(new GridLayout(4, 3));
    boardPanel.setLayout(new GridLayout(30, 45));   
    container.setLayout(new BorderLayout(0,0));
    container.add(boardPanel, BorderLayout.CENTER);
    container.add(buttonPanel, BorderLayout.EAST);
    container.add(statusBar, BorderLayout.SOUTH);
    statusBar.setText("GameLevel: "+gameLevel+"   "+"Num of Robots: "+ robotaliveNum);
    setJMenuBar(bar);
    bar.add(gameMenu);
    gameMenu.add(restart);
    restart.addActionListener(this);   
    timer = new Timer(200, this);
    timer.setInitialDelay(500);    
    double width = Toolkit.getDefaultToolkit().getScreenSize().width;
    double height = Toolkit.getDefaultToolkit().getScreenSize().height;
    this.setPreferredSize(new Dimension((int)width,(int)height-20));
    pack();
    timer.start();
  }
  //Reflect what the mouse's behavior it is by marking down the sinal buNum through setting the mousePressed and mouseReleased
  public void mouseEntered(MouseEvent e){}   
  public void mouseExited(MouseEvent e){}
  public void mousePressed(MouseEvent e){
      loopCondition = true;
    //buNum is a signal indicating what kinds of buttons the player is pressing
    if(e.getSource().equals(buttons[0])){
      buNum =7;}
    else if(e.getSource().equals(buttons[1])){
      buNum =8;}
    else if(e.getSource().equals(buttons[2])){
      buNum =9;}
    else if(e.getSource().equals(buttons[3])){
      buNum =4;}
    else if(e.getSource().equals(buttons[4])){
      buNum =5;}
    else if(e.getSource().equals(buttons[5])){
      buNum =6;}
    else if(e.getSource().equals(buttons[6])){
      buNum =1;}
    else if(e.getSource().equals(buttons[7])){        
      buNum =2;}
    else if(e.getSource().equals(buttons[8])){
      buNum =3;}
    else if(e.getSource().equals(buttons[9])){
      buNum =0;}
  }
  public void mouseReleased(MouseEvent e){
    timer.stop();
    loopCondition = false;
    timer.start();}
  public void mouseClicked(MouseEvent e){}
  
  /* This method:
   * 1. loopCondition to determine whether the mouse is pressing or not
   * 2. passing the move direction to play object and allow player move by calling playerMove method
   * 3. Call the static method on Move class, allow robots move and check if the game is over.
   * 4. Setting Menu action and allow it restart the game
   */
  public void actionPerformed(ActionEvent e){
    times++;
    if(loopCondition == true&&player.getPlayerState()==true){
      if(buNum==7){
        player.playerMove(7);}
      else if(buNum==8){
        player.playerMove(8);}
      else if(buNum==9){
        player.playerMove(9);}
      else if(buNum==4){
        player.playerMove(4);}
      else if(buNum==5){
        player.playerMove(5);}
      else if(buNum==6){
        player.playerMove(6);}
      else if(buNum==1){
        player.playerMove(1);}
      else if(buNum==2){
        player.playerMove(2);}
      else if(buNum==3){
        player.playerMove(3);}
      else if(buNum==0){
        player.playerMove(0);}
    }
    //set evevy 5 ticks, the robots move using counter.
    if(times%5==0){
      Move.gameMove(5);
      times = 0;}
    //set menuItem restart function
    if(e.getSource().equals(restart)){
      timer.stop();
      gameLevel=1;
      gameRobots = null;
      gameRobots = RobotGUI.initializeRobot();
      player = null;
      player = new Projectplayer3();
      for(int i = 0; i<30; i++){
        for(int j = 0; j<45; j++){
          boardLabel[i][j].setIcon(null);
        }
      }
      robotaliveNum = 0;
      for(int z =0;z<RobotGUI.gameRobots.length; z++){
        gameRobots[z].createRobot();
        robotaliveNum++;}
      player.createPlayer();
      RobotGUI.statusBar.setText("GameLevel: "+RobotGUI.gameLevel+"   "+"Num of Robots: "+ robotaliveNum);
    }
  }  
  public static void main(String args[]){
    RobotGUI robot = new RobotGUI(); 
    robot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //create robots and players
    for(int i =0; i<gameRobots.length; i++)
      gameRobots[i].createRobot();
    player.createPlayer();        
  }
  /* This method is to initialize Robot
   * determine whether the robots is broken or not
   * setting 15% chances of creating a broken robot through randomly create 100 numbers. if the number picked by computer is in the domain of 1 to 15 then the robots is broken
   */
  public static Projectrobot4[] initializeRobot(){
    Random random = new Random();
    Projectrobot4[] ro;
    int decideBroken;
    totalRobotNum = gameLevel*10;
    ro = new Projectrobot4[totalRobotNum];
    for(int i=0; i<ro.length; i++){
      decideBroken = random.nextInt(100)+1;
      ro[i] = new Projectrobot4();
      if( decideBroken <= 15&&decideBroken>=1)
        ro[i].setRobotType();}
    return ro;}  
  
  public static void setGameOver(){
    gameOver = true;}
}

