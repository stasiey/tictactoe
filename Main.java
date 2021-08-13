package tictactoe;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Table table = new Table();
        table.loopGUI();

    }
}

class Table{
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    //private String userEnterCells; stage 1
    private String coordinate;
    int xNum = 0;
    int oNum = 0;
    int x;
    int y;
    boolean start = true;
    boolean state = true;
    String[][] table = new String[3][3];
    String primary = " ";
    private String command;
    boolean mediumStart;
    int score;
    int depth;
    int moveX = -1;
    int moveY = -1;
    int bestScore;

    void loopGUI(){
        while(start){
            System.out.print("Input command:");
            userChoice();
        }
    }

    void userChoice(){
        setCommand(scanner.nextLine());
        getCommand();
        if(command.equals("exit")){
            gameOver();
        }else {
            String[] x = command.split(" ");
            if(x.length < 3){
                System.out.println("Bad parameters!");
            }else if(!"start".equals(x[0]) || !("easy".equals(x[1]) || "user".equals(x[1]) || "medium".equals(x[1]) ||
                    "hard".equals(x[1])) || !("easy".equals(x[2]) || "user".equals(x[2]) || "medium".equals(x[2]) ||
                    "hard".equals(x[2]))) {
                System.out.println("mismatched");
            }else{
                stage3Display();
                perGameStart();
            }
        }
    }

    void stage3Display(){
        getCommand();
        String[] x = command.split(" ");
        tableGenerator();
        if(x[1].equals("user") && x[2].equals("user")){
            while(state){
                System.out.print("Enter the coordinates:");
                inPutNumber();
                playerPut1();
                runtimeTable();
                if(!detectSame().equals("")){
                    System.out.println(detectSame());
                    break;
                }
                System.out.print("Enter the coordinates:");
                inPutNumber();
                playerPut2();
                runtimeTable();
                if(!detectSame().equals("")){
                    System.out.println(detectSame());
                    break;
                }
            }
        } else if(x[1].equals("easy") && x[2].equals("easy")){
            while(state) {
                System.out.println("Making move level \"easy1\"");
                easyComCoordinate1();
                runtimeTable();
                gameFinish();
                if (!detectSame().equals("")) {
                    System.out.println(detectSame());
                    break;
                }
                System.out.println("Making move level \"easy2\"");
                easyComCoordinate2();
                runtimeTable();
                if (!detectSame().equals("")) {
                    System.out.println(detectSame());
                    break;
                }
            }
        }else if(x[1].equals("user") && x[2].equals("easy")){
            while(state){
                System.out.print("Enter the coordinates:");
                inPutNumber();
                playerPut1();
                runtimeTable();
                if(!detectSame().equals("")){
                    System.out.println(detectSame());
                    break;
                }
                System.out.println("Making move level \"easy\"");
                easyComCoordinate2();
                runtimeTable();
                if (!detectSame().equals("")) {
                    System.out.println(detectSame());
                    break;
                }
            }
        }else if(x[1].equals("easy") && x[2].equals("user")){
            while(state) {
                System.out.println("Making move level \"easy\"");
                easyComCoordinate1();
                runtimeTable();
                if (!detectSame().equals("")) {
                    System.out.println(detectSame());
                    break;
                }
                System.out.print("Enter the coordinates:");
                inPutNumber();
                playerPut2();
                runtimeTable();
                if(!detectSame().equals("")){
                    System.out.println(detectSame());
                    break;
                }
            }
        } else if(x[1].equals("user") && x[2].equals("medium")){
            while(state){
                System.out.print("Enter the coordinates:");
                inPutNumber();
                playerPut1();
                runtimeTable();
                if(!detectSame().equals("")){
                    System.out.println(detectSame());
                    break;
                }
                System.out.println("Making move level \"medium\"");
                mediumComCoordinate2();
                runtimeTable();
                if (!detectSame().equals("")) {
                    System.out.println(detectSame());
                    break;
                }
            }
        }
        else if(x[1].equals("medium") && x[2].equals("user")){
            while(state){
                System.out.println("Making move level \"medium\"");
                mediumComCoordinate1();
                runtimeTable();
                if (!detectSame().equals("")) {
                    System.out.println(detectSame());
                    break;
                }
                System.out.print("Enter the coordinates:");
                inPutNumber();
                playerPut2();
                runtimeTable();
                if(!detectSame().equals("")){
                    System.out.println(detectSame());
                    break;
                }
            }
        }else if(x[1].equals("medium") && x[2].equals("medium")){
            while(state){
                System.out.println("Making move level \"medium1\"");
                mediumComCoordinate1();
                runtimeTable();
                if (!detectSame().equals("")) {
                    System.out.println(detectSame());
                    break;
                }
                System.out.println("Making move level \"medium2\"");
                mediumComCoordinate2();
                runtimeTable();
                if (!detectSame().equals("")) {
                    System.out.println(detectSame());
                    break;
                }
            }
        }else if(x[1].equals("hard") && x[2].equals("user")){
            while(state){
                System.out.println("Making move level \"hard\"");
                hardPlayer1();
                runtimeTable();
                if (!detectSame().equals("")) {
                    System.out.println(detectSame());
                    break;
                }
                System.out.print("Enter the coordinates:");
                inPutNumber();
                playerPut2();
                runtimeTable();
                if(!detectSame().equals("")){
                    System.out.println(detectSame());
                    break;
                }
            }
        }else if(x[1].equals("user") && x[2].equals("hard")){
            while(state){
                System.out.print("Enter the coordinates:");
                inPutNumber();
                playerPut1();
                runtimeTable();
                if(!detectSame().equals("")){
                    System.out.println(detectSame());
                    break;
                }
                System.out.println("Making move level \"hard\"");
                hardPlayer2();
                runtimeTable();
                if (!detectSame().equals("")) {
                    System.out.println(detectSame());
                    break;
                }
            }
        }else if(x[1].equals("hard") && x[2].equals("hard")){
            while(state){
                System.out.println("Making move level \"hard\"");
                hardPlayer1();
                runtimeTable();
                if (!detectSame().equals("")) {
                    System.out.println(detectSame());
                    break;
                }
                System.out.println("Making move level \"hard\"");
                hardPlayer2();
                runtimeTable();
                if (!detectSame().equals("")) {
                    System.out.println(detectSame());
                    break;
                }
            }
        }
    }

    void playerPut1(){
        getCoordinate();
        String[] x = coordinate.split(" ");
        table[Integer.parseInt(x[0])-1][Integer.parseInt(x[1])-1] = "X";
    }

    void playerPut2(){
        getCoordinate();
        String[] x = coordinate.split(" ");
        table[Integer.parseInt(x[0])-1][Integer.parseInt(x[1])-1] = "O";
    }

    void easyComCoordinate1(){
        x = random.nextInt(3);
        y = random.nextInt(3);
        while(!table[x][y].equals(" ")) {
            x = random.nextInt(3);
            y = random.nextInt(3);
        }
        table[x][y] = "X";
    }

    void easyComCoordinate2(){
        x = random.nextInt(3);
        y = random.nextInt(3);
        while(!table[x][y].equals(" ")) {
            x = random.nextInt(3);
            y = random.nextInt(3);
        }
        table[x][y] = "O";
    }

    void mediumComCoordinate1() {
        int countX = 0;
        int countO = 0;
        generateMedium();
        //行
        while(mediumStart){
            for(int i = 0; i < table.length; i++){
                for(int j = 0; j < table.length; j++){
                    if(table[i][j].equals("X")){
                        countX++;
                    } else if(table[i][j].equals("O")){
                        countO++;
                    } else if(table[i][j].equals(" ")){
                        x = i;
                        y = j;
                    }
                }if(countX == 2 && countO == 0){
                    table[x][y] = "X";
                    break;
                }else if(countO == 2 && countX == 0){
                    table[x][y] = "X";
                    break;
                }else{
                    countO = 0;
                    countX = 0;
                }
            }
            if(table[x][y].equals("X")){
                mediumStart = false;
                break;
            }
            //列
            for(int i = 0; i < table.length; i++){
                for(int j = 0; j < table.length; j++){
                    if(table[j][i].equals("X")){
                        countX++;
                    } else if(table[j][i].equals("O")){
                        countO++;
                    } else if(table[j][i].equals(" ")){
                        x = j;
                        y = i;
                    }
                }if(countX == 2 && countO == 0){
                    table[x][y] = "X";
                    break;
                }else if(countO == 2 && countX == 0){
                    table[x][y] = "X";
                    break;
                }else{
                    countO = 0;
                    countX = 0;
                }
            }
            if(table[x][y].equals("X")){
                mediumStart = false;
                break;
            }

            //正对角
            for(int i = 0; i < table.length; i++){
                for(int j = 0; j < table.length; j++){
                    if (i == j){
                        if(table[i][j].equals("X")){
                            countX++;
                        } else if(table[i][j].equals("O")){
                            countO++;
                        } else if(table[i][j].equals(" ")){
                            x = i;
                            y = j;
                        }
                    }
                }
            }
            if(countX == 2 && countO == 0){
                table[x][y] = "X";
                break;
            }else if(countO == 2 && countX == 0){
                table[x][y] = "X";
                break;
            }else{
                countO = 0;
                countX = 0;
            }
            //反对角
            for(int i = 0; i < table.length; i++){
                for(int j = 0; j < table.length; j++){
                    if(i + j == table.length - 1){
                        if(table[i][j].equals("X")){
                            countX++;
                        } else if(table[i][j].equals("O")){
                            countO++;
                        } else if(table[i][j].equals(" ")){
                            x = i;
                            y = j;
                        }
                    }
                }
            }
            if(countX == 2 && countO == 0){
                table[x][y] = "X";
                break;
            }else if(countO == 2 && countX == 0){
                table[x][y] = "X";
                break;
            }else{
                countO = 0;
                countX = 0;
            }
            if(!table[x][y].equals("X")){
                x = random.nextInt(3);
                y = random.nextInt(3);
                while(!table[x][y].equals(" ")) {
                    x = random.nextInt(3);
                    y = random.nextInt(3);
                }
                table[x][y] = "X";
                break;
            }
        }

    }

    void generateMedium(){
        mediumStart = true;
    }

    void finishMedium(){
        mediumStart = false;
    }

    void mediumComCoordinate2(){
        int countX = 0;
        int countO = 0;
        generateMedium();
        //行
        while(mediumStart){
            for(int i = 0; i < table.length; i++){
                for(int j = 0; j < table.length; j++){
                    if(table[i][j].equals("X")){
                        countX++;
                    } else if(table[i][j].equals("O")){
                        countO++;
                    } else if(table[i][j].equals(" ")){
                        x = i;
                        y = j;
                    }
                }if(countO == 2 && countX == 0){
                    table[x][y] = "O";
                    break;
                }else if(countX == 2 && countO == 0){
                    table[x][y] = "O";
                    break;
                }else{
                    countO = 0;
                    countX = 0;
                }
            }if(table[x][y].equals("O")){
                mediumStart = false;
                break;
            }
            //列
            for(int i = 0; i < table.length; i++){
                for(int j = 0; j < table.length; j++){
                    if(table[j][i].equals("X")){
                        countX++;
                    } else if(table[j][i].equals("O")){
                        countO++;
                    } else if(table[j][i].equals(" ")){
                        x = j;
                        y = i;
                    }
                }if(countO == 2 && countX == 0){
                    table[x][y] = "O";
                    break;
                }else if(countX == 2 && countO == 0){
                    table[x][y] = "O";
                    break;
                }else{
                    countO = 0;
                    countX = 0;
                }
            }if(table[x][y].equals("O")){
                mediumStart = false;
                break;
            }
            //正对角
            for(int i = 0; i < table.length; i++){
                for(int j = 0; j < table.length; j++){
                    if (i == j){
                        if(table[i][j].equals("X")){
                            countX++;
                        } else if(table[i][j].equals("O")){
                            countO++;
                        } else if(table[i][j].equals(" ")){
                            x = i;
                            y = j;
                        }
                    }
                }
            }
            if(countO == 2 && countX == 0){
                table[x][y] = "O";
                break;
            }else if(countX == 2 && countO == 0){
                table[x][y] = "O";
                break;
            }else{
                countO = 0;
                countX = 0;
            }
            //反对角
            for(int i = 0; i < table.length; i++){
                for(int j = 0; j < table.length; j++){
                    if(i + j == table.length - 1){
                        if(table[i][j].equals("X")){
                            countX++;
                        } else if(table[i][j].equals("O")){
                            countO++;
                        } else if(table[i][j].equals(" ")){
                            x = i;
                            y = j;
                        }
                    }
                }
            }
            if(countO == 2 && countX == 0){
                table[x][y] = "O";
                break;
            }else if(countX == 2 && countO == 0){
                table[x][y] = "O";
                break;
            }else{
                countO = 0;
                countX = 0;
            }
            if(!table[x][y].equals("O")){
                x = random.nextInt(3);
                y = random.nextInt(3);
                while(!table[x][y].equals(" ")) {
                    x = random.nextInt(3);
                    y = random.nextInt(3);
                }
                table[x][y] = "O";
                break;
            }
        }
    }

    int score(String x){
        if("X wins".equals(x)){
            score = 10;
        } else if ("O wins".equals(x)){
            score = -10;
        } else {
            score = 0;
        }
        return score;
    }


    void hardPlayer1(){
        int bestScore = -10000;
        for(int i = 0; i < table.length; i++){
            for(int j = 0; j < table.length; j++){
                if(table[i][j].equals(" ")){
                    table[i][j] = "X";
                    score = minimax(table,0,false);
                    table[i][j] = " ";
                    if(score > bestScore){
                        moveX = i;
                        moveY = j;
                        bestScore = score;
                    }
                }
            }
        }
        table[moveX][moveY] = "X";
    }

    void hardPlayer2(){
        int bestScore = 10000;
        for(int i = 0; i < table.length; i++){
            for(int j = 0; j < table.length; j++){
                if(table[i][j].equals(" ")){
                    table[i][j] = "O";
                    score = minimax(table,0,true);
                    table[i][j] = " ";
                    if(score < bestScore){
                        moveX = i;
                        moveY = j;
                        bestScore = score;
                    }
                }
            }
        }
        table[moveX][moveY] = "O";
    }


    void perGameStart(){
        state = true;
    }

    void perGameEnd(){
        state = false;
    }



    void gameOver(){
        start = false;
    }




    //stage 1
    /*public String getUserEnterCells() {
        return userEnterCells;
    }

    public void setUserEnterCells(String userEnterCells) {
        this.userEnterCells = userEnterCells;
    }*/
    public String getCommand(){
        return command;
    }

    public void setCommand(String command){
        this.command = command;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate){
        this.coordinate = coordinate;
    }





    public void display(){
        //System.out.print("Enter the cells:");
        //setUserEnterCells(scanner.nextLine()); stage 1
        tableGenerator();
        while(state){
            System.out.println("Enter the coordinates:");
            inPutNumber();
            playerPut();
            runtimeTable();
            detectSame();
            System.out.println("Making move level \"easy\"");
            easyComCoordinate();
            runtimeTable();
            detectSame();
        }

        //tableChanged();stage 1
        //detectSame();
    }

     void tableGenerator(){
        System.out.println("---------");
        //changeSymbols();stage1
        gameTable();
        System.out.println("---------");
    }

    void gameTable(){
        //int count = 0;                          stage1
        //while(count < changeSymbols().length()){stage1
            for (int i = 0; i < table.length; i++){
                System.out.print("| ");
                for (int j = 0; j < table.length; j++){
                    //table[i][j] = String.valueOf(changeSymbols().charAt(count)); stage1
                    //count ++;                                                    stage1
                    table[i][j]= " ";
                    System.out.print(table[i][j]+ " ");
                }System.out.println("|");
            }
        }
    //}
    //stage 1
    /*String changeSymbols(){
        StringBuilder s = new StringBuilder();
        s.append(getUserEnterCells());
        for(int i = 0; i < s.length(); i++){
            if(String.valueOf(s.charAt(i)).equals("_")) {
                s.deleteCharAt(i);
                s.insert(i, " ");
            }
        }return s.toString();
    }*/

    void inPutNumber(){
        setCoordinate(scanner.nextLine());
        getCoordinate();
        String[] x = coordinate.trim().split(" ");
        while(!"0123456789".contains(String.valueOf(x[0]))){
            System.out.println("You should enter numbers!");
            System.out.print("Enter the coordinates:");
            setCoordinate(scanner.nextLine());
            getCoordinate();
            x = coordinate.split(" ");
        }

        while( Integer.parseInt(x[0]) - 1 >= table.length || Integer.parseInt(x[1]) - 1 >= table.length){
            System.out.println("Coordinates should be from 1 to 3!");
            System.out.print("Enter the coordinates:");
            setCoordinate(scanner.nextLine());
            getCoordinate();
            x = coordinate.split(" ");
        }

        while(!table[Integer.parseInt(x[0])-1][Integer.parseInt(x[1])-1].equals(" ")){
            System.out.println("This cell is occupied! Choose another one!");
            System.out.print("Enter the coordinates:");
            setCoordinate(scanner.nextLine());
            getCoordinate();
            x = coordinate.split(" ");
        }
    }

    void playerPut(){
        getCoordinate();
        String[] x = coordinate.split(" ");
        table[Integer.parseInt(x[0])-1][Integer.parseInt(x[1])-1] = "X";
    }

    void easyComCoordinate(){
        x = random.nextInt(3);
        y = random.nextInt(3);
        while(!table[x][y].equals(" ")) {
            x = random.nextInt(3);
            y = random.nextInt(3);
        }
        table[x][y] = "O";
    }

    void runtimeTable(){
        System.out.println("---------");
        for (int i = 0; i < table.length; i++){
            System.out.print("| ");
            for (int j = 0; j < table.length; j++){
                System.out.print(table[i][j]+ " ");
            }System.out.println("|");
        }
        System.out.println("---------");
    }


    //stage 1
    /*void tableChanged(){
        getCoordinate();
        //detectNumOfSymbol();
        String[] x = coordinate.split(" ");
        if(xNum>oNum){
            table[Integer.parseInt(x[0])-1][Integer.parseInt(x[1])-1] = "O";
        } else {
            table[Integer.parseInt(x[0])-1][Integer.parseInt(x[1])-1] = "X";
        }
        System.out.println("---------");
        for (int i = 0; i < table.length; i++){
            System.out.print("| ");
            for (int j = 0; j < table.length; j++){
                System.out.print(table[i][j]+ " ");
            }System.out.println("|");
        }
        System.out.println("---------");
    }*/
    //stage 1
    /*void detectNumOfSymbol(){
        for (int i = 0; i < changeSymbols().length(); i++){
            if (String.valueOf(changeSymbols().charAt(i)).equals("X")){
                xNum ++;
            }
        }
        for (int i = 0; i < changeSymbols().length(); i++){
            if (String.valueOf(changeSymbols().charAt(i)).equals("O")){
                oNum ++;
            }
        }
    }*/

    String detectSame(){
        String result ="";
        String winner = "";
        int count = 0;
        boolean win = false;
        boolean result1 = false;
        boolean result2 = false;
        boolean result3 = false;
        boolean result4 = false;
        boolean draw1 = false;
        boolean draw2 = false;
        boolean draw3 = false;
        boolean draw4 = false;
        String x ="";
        //行
        for(int i = 0; i < table.length; i++){
            for(int j = 1; j < table.length - 1; j++){
                if(table[i][j].equals(table[i][j+1]) && table[i][j].equals(table[i][j-1]) &&
                        !table[i][j].equals(" ")){
                    win = true;
                    winner = table[i][j];
                    break;
                }
                /*else {
                    result1 = true;
                }*/
            }if(win){
                x = winner +" wins";
                break;
            }
        }
        //列
        for(int i = 1; i < table.length - 1; i++){
            for(int j = 0; j < table.length; j++){
                if(table[i][j].equals(table[i+1][j]) && table[i][j].equals(table[i-1][j]) &&
                        !table[i][j].equals(" ")){
                    win = true;
                    winner = table[i][j];
                    break;
                }
                /*else {
                    result2 = true;
                }*/
            }
            if(win){
                x = winner +" wins";
                break;
            }
        }
        //左上角
        for(int i = 1; i < table.length - 1; i++){
            for(int j = 1; j < table.length - 1; j++){
                if(table[i][j].equals(table[i+1][j+1]) && table[i][j].equals(table[i-1][j-1]) &&
                        !table[i][j].equals(" ")){
                    win = true;
                    winner = table[i][j];
                    break;
                }  /*else {
                    result3 = true;
                }*/
            }if(win){
                x = winner +" wins";
                break;
            }
        }
        //右下角
        for(int i = 1; i < table.length - 1; i++){
            for(int j = 1; j < table.length -1; j++){
                if(table[i][j].equals(table[i+1][j-1]) && table[i][j].equals(table[i-1][j+1])
                        && !table[i][j].equals(" ")){
                    win = true;
                    winner = table[i][j];
                    break;
                } /*else{
                    result4 = true;
                }*/
            }if(win){
                x = winner +" wins";
                break;
            }
        }

        for(int i = 0; i < table.length; i++){
            for(int j = 0; j < table.length; j++){
                if(table[i][j] != " "){
                    count++;
                }
            }
        }

        if(!x.equals("")){
            result = x;
        }else if(count == 9){
            result = "draw";
        }
        //else if (result1 || result2 || result3 || result4){
            //System.out.println("Game not finished");
        //}
        return result;
    }

    void gameFinish(){
        if(!detectSame().equals("")){
            System.out.println(detectSame());
            state = false;
        }
    }



    int minimax(String table[][],int depth, boolean isMiniMax){
        String x = detectSame();
        /*if(!x.equals("")){
            return score(x);
        }*/
        if(x.equals("X wins")){
            return  10 - depth;
        } else if(x.equals("O wins")){
            return  -10 + depth;
        } else if(x.equals("draw")){
            return  0;
        }
        int bestScore;
        if (isMiniMax){
            bestScore = -10000;
            for(int i = 0; i < table.length; i++){
                for(int j = 0; j < table.length; j++){
                    if(table[i][j].equals(" ")){
                        table[i][j] = "X";
                        score = minimax(table,depth + 1,false);
                        table[i][j] = " ";
                        bestScore = Math.max(score,bestScore);
                    }
                }
            }
        } else {
            bestScore = 10000;
            for(int i = 0; i < table.length; i++){
                for(int j = 0; j < table.length; j++){
                    if(table[i][j].equals(" ")){
                        table[i][j] = "O";
                        score = minimax(table,depth + 1,true);
                        table[i][j] = " ";
                        bestScore = Math.min(score,bestScore);
                    }
                }
            }
        }
        return bestScore;
    }
}
