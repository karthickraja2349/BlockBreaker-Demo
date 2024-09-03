import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

             
public class BlockBreaker{
      private char[][] gameArea;
      private Scanner input = new Scanner(System.in);
      private Queue<Node> queue = new LinkedList();
      private Stack<Node> stack = new Stack();
      private static  int ballCount = 5;
      private static  int wallLife = 2;
      private static  int  scoreCount = 0;  
      private static  final int startRow = 9;
      private static  final int startColumn = 5; 
      
      
      public BlockBreaker(){
            gameArea = new char[10][10];
            for(int i=0;i<gameArea.length;i++){
                 for(int j=0;j<gameArea[0].length;j++){
                       gameArea[i][j]= ' ' ;
                 }
            }
            placeBricks();
            initializeBall();
            printGameArea();
      }
      
      private void printGameArea(){
             printBorder();
             for(int i=0;i<gameArea.length;i++){
                 System.out.print("|");
                 for(int j=0;j<gameArea[0].length;j++){
                      System.out.print(gameArea[i][j] + " ");
                 }
                 System.out.print("|");
                 System.out.println();
            }
            printBorder();           
      }
      
      private void printBorder(){
            System.out.print("*");
            for(int i=0;i<gameArea[0].length;i++){
                System.out.print("--");
            }
            System.out.print("*");
            System.out.println();
     }   
     
     public static void main(String[]args){
          new BlockBreaker().moveBall(startRow,startColumn);
     }
     
     private void placeBricks(){
          gameArea[4][3] = '|';
          gameArea[4][4] = '|';
          gameArea[4][5] = '|';
          gameArea[4][6] = '|';
          gameArea[4][7] = '|';
          gameArea[5][3] = '|';
          gameArea[5][4] = '|';
          gameArea[5][5] = '|';
          gameArea[5][6] = '|';
          gameArea[5][7] = '|'; 
     }     
     
     private void initializeBall(){
         gameArea[startRow][startColumn] = 'o';
         queue.add(new Node(9,4));
     }    
     
     private void printBall(int row, int column){
          if(gameArea[row][column] != 'o' || gameArea[row][column] !='|'){
              gameArea[row][column] = 'o';
              queue.add(new Node(row,column));
              Node node = queue.poll();
              int previousRow = node.getRow();
              int previousColumn = node.getColumn();
              gameArea[previousRow][previousColumn] =' ';
         } 
         
         else if(gameArea[row][column] == '|'){
             wallLife++;
             ballReturnBack();
         }    
     }    
     
     private void ballReturnBack(){
        while(!stack.isEmpty()){
            Node node = stack.pop();
            int previousRow = node.getRow();
            int previousColumn = node.getColumn();
            //gameArea[previousRow][previousColumn] ='o';
            moveBall(startRow,startColumn);
       }      
           
     
     } 

     private void moveBall(int row, int column){
          stack.push(new Node(row,column));
          printBall(row,column);
          printGameArea();
          System.out.println("Enter the directon R/r -> Right, L/l -> left , U/u -> up , D/d -> down");
          char movement = input.next().charAt(0);
          switch(movement){
              case 'U':
              case 'u':
                  moveBall(row-1,column);
                  break;
              case 'D':
              case 'd':
                  moveBall(row+1,column);
                  break;
              case 'R':
              case 'r':
                  moveBall(row,column+1);
                  break;
              case 'L':
              case 'l':
                  moveBall(row,column-1);
                  break;
              default:
                   System.out.println("Please select R/L/U/D");
        }
   }                                
  }                                       
