/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.erwinh.th;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Scanner;
import java.util.Random;

class Position
{
    // position Left = Column; Top = Row
    public int Column;
    public int Row;
    public int Start;
    public int End;
}

/**
 *
 * @author erwin_h
 */
public class MainForm extends javax.swing.JFrame {
    // user char
    String UserChar = "X";    
    String Obstacle = "#";
    String ValidPath = ".";
    String TreasureSymbol = "$";
    String TreasureSymbolValid = "V";
    String TreasureSymbolInvalid = "I";
    String BoardContents = "";
    String PrevContent = ValidPath;
    String NextContent = "";
    
    Position InitialPos;
    Position CurrentPos;

    // array length
    int ColLen = 8;
    int RowLen = 6;

    //array 7x9 to hold board content
    String[][] aBoardContent;

    //static ConsoleKeyInfo inputKey;
    String NewLine = "\n";
    int Decreaser = 9;

    int TreasurePosCount = 3;
    Position TreasurePos;
    
    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
                
        InitValue();
        BuildLayout();
        SetTreasurePos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextArea1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextArea1MousePressed(evt);
            }
        });
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextArea1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(0, 11, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextArea1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea1MousePressed
        SelectText(CurrentPos);
    }//GEN-LAST:event_jTextArea1MousePressed

    private void jTextArea1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyReleased
        String strCode = "";
        var nextPos = new Position(); 
        int code = evt.getKeyCode();
        switch( code ) {
            case KeyEvent.VK_UP:
                // handle up
                strCode = "Key Up";
                nextPos.Start = CurrentPos.Start - Decreaser;
                nextPos.End = CurrentPos.End - Decreaser;
                ValidateNextMove(nextPos);               
                
                break;
            case KeyEvent.VK_DOWN:
                // handle down
                strCode = "Key Down";
                nextPos.Start = CurrentPos.Start + Decreaser;
                nextPos.End = CurrentPos.End + Decreaser;
                ValidateNextMove(nextPos);               
                
                break;
            case KeyEvent.VK_LEFT:
                // handle left
                strCode = "Key Left";
                nextPos.Start = CurrentPos.Start - 1;
                nextPos.End = CurrentPos.End - 1;
                ValidateNextMove(nextPos);               
                
                break;
            case KeyEvent.VK_RIGHT :
                // handle right
                strCode = "Key Right";
                nextPos.Start = CurrentPos.Start + 1;
                nextPos.End = CurrentPos.End + 1;
                ValidateNextMove(nextPos);               
                
                break;
        }
        
        jTextArea2.append("["+GetSelectedText()+";S"+CurrentPos.Start+";E"+CurrentPos.End+"], ");
        //JOptionPane.showMessageDialog(null, "KeyCode: " + code + ", strCode: " + strCode);
    }//GEN-LAST:event_jTextArea1KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        InitValue();
        BuildLayout();
        SetTreasurePos();
        jTextArea1.requestFocus();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }
    
    void print(String text)
    {
        jTextArea1.append(text);
    }
    
    void println(String text)
    {
        jTextArea1.append(text + "\n");
    }

    private void InitValue()
    {
        //initial cursor position
        InitialPos = new Position();
        InitialPos.Start = 37;
        InitialPos.End = InitialPos.Start + 1;
        CurrentPos = InitialPos;
        //jTextArea1.setText("test from code");
    }
    
    private void BuildLayout()
    {
        SetText("");
        // the board content
        var strBoardContent = "########" + NewLine +
            "#......#" + NewLine +
            "#.###..#" + NewLine +
            "#...#.##" + NewLine +
            "#.#....#" + NewLine +
            "########" + NewLine;
        
        // process the board content line by line
        Scanner scanner = new Scanner(strBoardContent);
        while (scanner.hasNextLine())
        {
            var line = scanner.nextLine();
            println(line);
        }
        scanner.close();
        
//        println("########");
//        println("#......#");
//        println("#.###..#");
//        println("#...#.##");
//        println("#X#....#");
//        println("########");

        ReplaceText(InitialPos, UserChar);
        
        // store the board content
        BoardContents = GetAllText();
    }
    
    private void SelectText(Position pos)
    {
        jTextArea1.select(pos.Start, pos.End);
    }
    
    private String GetSelectedText()
    {
        return jTextArea1.getSelectedText();
    }
    
    private String Substring(Position pos)
    {
        var str = jTextArea1.getText();
        return str.substring(pos.Start, pos.End);
    }
    
    private String GetAllText()
    {
        return jTextArea1.getText();
    }
    
    private void SetText(String text)
    {
        jTextArea1.setText(text);
    }
    
    private void ReplaceText(Position pos, String newText)
    {
        StringBuilder strBuilder = new StringBuilder(GetAllText());
        strBuilder.replace(pos.Start, pos.End, newText);
        SetText(strBuilder.toString());
    }
    
    private void ValidateNextMove(Position nextPos)
    {
        NextContent = Substring(nextPos);
        var isObstacle = NextContent.equals(Obstacle);

        if (!isObstacle)
        {
            if (NextContent.equals(TreasureSymbol))
            {
                // set current user pos with prev content
                ReplaceText(CurrentPos, PrevContent);
                
                if (TreasurePos.Start == nextPos.Start && TreasurePos.End == nextPos.End)
                {
                    // set prev with next content
                    PrevContent = TreasureSymbolValid;
                    // replace next position with valid treasure symbol
                    ReplaceText(nextPos, TreasureSymbolValid);
                }
                else
                {
                    // set prev with next content
                    PrevContent = TreasureSymbolInvalid;
                    // replace next position with invalid treasure symbol
                    ReplaceText(nextPos, TreasureSymbolInvalid);
                }
                
                // select next position
                SelectText(nextPos);
                CurrentPos = nextPos;
            }
            else
            {
                // set current user pos with prev content
                ReplaceText(CurrentPos, PrevContent);
                // set prev with next content
                PrevContent = NextContent;
                // replace next position with user character
                ReplaceText(nextPos, UserChar);
                // select next position/user character
                SelectText(nextPos);
                CurrentPos = nextPos;
            }
        }
        else
        {
            SelectText(CurrentPos);
        }
    }
    
    private void SetTreasurePos()
    {
        jTextArea2.setText("");
        // init vars
        Random random = new Random();
        Position[] treasurePositions = new Position[TreasurePosCount];
        // get valid positions
        int validPos = 0;
        int minPos = 0;
        int maxPos = 53;
        while (validPos <= TreasurePosCount - 1)
        {
            // get random number
            int start = randInt(minPos, maxPos);
            int end = start + 1;
            // assign position value
            var pos = new Position();
            pos.Start = start;
            pos.End = end;
            // get string from position given
            var strInputPos = BoardContents.substring(pos.Start, pos.End);
            // position is valid if the string in that position not equal to Obstacle, user char and new line character
            var isValidPos = !strInputPos.equals(Obstacle) && !strInputPos.equals(UserChar) && !strInputPos.equals(NewLine);
            
            // if the position valid mark it with TreasureSymbol and store the position into array
            if (isValidPos)
            {
                // check if treasure position already stored in the array
                var isExists = IsTreasurePosExists(treasurePositions, pos);
                if (!isExists){
                    ReplaceText(pos, TreasureSymbol);
                    treasurePositions[validPos] = pos;

                    validPos++;
                    jTextArea2.append("th["+pos.Start+", "+pos.End+"]\n");
                }
            }
        }

        // get random number to select treasure position
        int treasurePos = randInt(0, TreasurePosCount - 1);
        // set treasure position
        TreasurePos = treasurePositions[treasurePos];
        // select char in the initial position
        SelectText(InitialPos);
    }
    
    private Boolean IsTreasurePosExists(Position[] positions, Position position)
    {
        var result = false;
        for (Position pos : positions)
        {
            if(pos != null && pos.Start == position.Start && pos.End == position.End)
            {
                result = true;
                break;
            }
        }
        
        return result;
    }
    
    private int randInt(int min, int max) 
    {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    
/*
    private void InitValue1()
    {
        //initial cursor position
        InitialPos = new Position();
        InitialPos.Column = 1;
        InitialPos.Row = 4;
        CurrentPos = InitialPos;
        //jTextArea1.setText("test from code");
    }
    
    private void InitBoardContent1()
    {
        // the board content
        String strBoardContent = "########" + NewLine +
                "#......#" + "\n" +
                "#.###..#" + NewLine +
                "#...#.##" + NewLine +
                "#.#....#" + NewLine +
                "########" + NewLine;

        // init array to hold board content
        aBoardContent = new String[RowLen][ColLen];

        // init index variables
        int iRow = 0;
        int iColumn = 0;
        int i = 0;

        // process the board content line by line
        Scanner scanner = new Scanner(strBoardContent);
        while (scanner.hasNextLine())
        {
            //i++;
            String line = scanner.nextLine();
            char[] chars = line.toCharArray();
            for (char c : chars)
            {
                aBoardContent[iRow][iColumn] = String.valueOf(c);
                iColumn++;
            }

            iRow++;
            iColumn = 0;
        }
        scanner.close();

        aBoardContent[InitialPos.Row][InitialPos.Column] = UserChar;
    }
    
    private void BuildLayout1()
    {
        for (int i = 0; i < RowLen; i++)
        {
            for (int j = 0; j < ColLen; j++)
            {
                print(aBoardContent[i][j]);
            }
            println("");
        }

        //Console.SetCursorPosition(InitialPos.Column, InitialPos.Row);
    }
*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}
