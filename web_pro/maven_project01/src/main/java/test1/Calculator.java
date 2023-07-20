package test1;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener{
    JTextField input;//文本框，显示计算结果
    JButton []button=new JButton[20];//按钮数组，存放20个按钮组件
    JPanel pan=new JPanel();
    String name[]={"C","÷", "×", "←","7", "8", "9", "-", "4", "5", "6", "+", "1", "2", "3", "√", "%", "0", ".", "="};//按钮标签数组
    double result;//存储双目运算的结果
    public Calculator(){
        setTitle("计算器-szl");
        pan.setLayout(new GridLayout(5,4));//设置面板pan的布局格式为5行4列的网格布局，存放20个按钮
        input=new JTextField(20);
        input.setText("");
        input.setFont(new Font("宋体",Font.BOLD,25));
        input.setForeground(Color.orange);
        for(int i=0;i<button.length;i++){
            button[i]=new JButton(name[i]);
            button[i].setFont(new Font("黑体",Font.BOLD,30));
            button[i].addActionListener(this);//给每一个按钮注册事件监听器
            pan.add(button[i]);
        }
        add(input,BorderLayout.NORTH);
        add(pan,BorderLayout.CENTER);
        setSize(600,400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗体
    }
    //动作响应
    public void actionPerformed(ActionEvent e){
        //如果点击按钮 "C"则触发清空文本框的操作
        if(e.getSource()==button[0]){
            input.setText("");
        }
        //如果点击按钮"="则调用计算方法并在文本框显示结果
        else if(e.getSource()==button[19]){
            calculate();
            input.setText(""+result);//输出计算结果
        }
        //如果点击"<--"按钮则删去文本框内字符串的末尾字符
        else if(e.getSource()==button[3]){
            String str=input.getText();
            if(str.length()>1){
                str=str.substring(0,str.length()-1);
                input.setText(str);
            }
            else{
                input.setText("");
            }

        }
        //以字符串拼接的方式将点击的按钮的标签拼接起来，成为一个运算表达式字符串
        else{
            input.setText(input.getText()+e.getActionCommand());
        }
    }
    public void calculate(){
        char[]arr=input.getText().toCharArray();//将输入的计算表达式字符串存储在字符数组中便于查找运算符的位置
        int i=0;//移动标志变量
        while('0'<=arr[i]&&arr[i]<='9'||arr[i]=='.')//去除数字与小数点以确定双目运算符的位置
            i++;
        char operator=arr[i];//将该运算符存储起来
        //双目运算
        if(operator!='%'&&operator!='√'){
            String s=input.getText(),s1,s2;//s1,s2分别存储运算符前后的数值字符串
            s1=s.substring(0,i);
            s2=s.substring(i+1,s.length());
            Double left=Double.parseDouble(s1);//将运算符左边的数值字符串转换为浮点数
            Double right=Double.parseDouble(s2);//将运算符右边的数值字符串转换为浮点数
            //根据不同的运算符进行相应的计算
            if(operator=='+')result=left+right;
            else if(operator=='-')result=left-right;
            else if(operator=='×')result=left*right;
            else if(operator=='÷')result=left/right;
        }
        //单目运算
        else{
            String s=input.getText(),s1;
            s1=s.substring(0,s.length()-1);
            Double a=Double.parseDouble(s1);
            if(operator=='%'){
                result=0.01*a;
            }
            else if(operator=='√'){
                result=Math.sqrt(a);
            }

        }
    }
    public static void main(String[]args){
        new Calculator();
    }
}