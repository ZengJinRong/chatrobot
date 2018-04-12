package pers.zengjinrong.chatrobot;

import pers.zengjinrong.chatrobot.util.PostUtil;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String message = PostUtil.sendMessage("你好");
        System.out.println(message);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String msg = scanner.next();
            message = PostUtil.sendMessage(msg);
            System.out.println(message);
        }

    }
}