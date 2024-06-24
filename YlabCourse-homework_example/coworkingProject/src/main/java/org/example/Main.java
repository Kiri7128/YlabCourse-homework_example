package org.example;


import org.example.manage.ManageUsers;
import org.example.manage.ResourceManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main  {
    public static void main(String[] args) throws IOException, ParseException {


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ManageUsers manageUsers = new ManageUsers();
        ResourceManager resourceManager = new ResourceManager();
        resourceManager.WorkspaceAdd("1","table");
        resourceManager.WorkspaceAdd("2","table");
        resourceManager.ConferenceRoomAdd("1","conference room №1");

        boolean exit = false;
        boolean logout = true;
        manageUsers.useradd("admin","admin","12345");

        User currentuser = new User("test","test","test");//
        while (!exit) {
            System.out.println("1. Вход");
            System.out.println("2. Регистрация");
            System.out.println("3. Выход");
            int option = Integer.parseInt(reader.readLine());
            switch (option) {
                case 1:
                    System.out.println("Введите ваш ник");
                    String username = reader.readLine();
                    System.out.println("Введите ваш пароль");
                    String password = reader.readLine();
                    if (manageUsers.Login(username,password))
                    {
                        currentuser = manageUsers.getUser(username);
                        logout = false;
                    } else {
                        System.out.println("error");
                    }
                    break;
                case 2:
                    System.out.print("Имя: ");
                    String name = reader.readLine();
                    System.out.print("Ник: ");
                    String username1 = reader.readLine();
                    System.out.print("пароль: ");
                    String password1 = reader.readLine();
                    manageUsers.useradd(username1,name,password1);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    break;
            }



            while (!logout)
            {
                String username = currentuser.getusername();
                System.out.println("welcome " + username);
                System.out.println("1. Просмотр списка всех доступных рабочих мест и конференц-залов");
                System.out.println("2. Просмотр доступных слотов для бронирования рабочих мест на конкретную дату");
                System.out.println("3. Просмотр доступных слотов для бронирования конференц-залов на конкретную дату");
                System.out.println("4. Бронирование рабочего места на определённое время и дату");
                System.out.println("5. Бронирование конференц-зала на определённое время и дату");
                System.out.println("6. Отмена бронирования рабочего места");
                System.out.println("7. Отмена бронирования конференц-зала");
                if (!username.equals("admin")){System.out.println("8. Выход");}

                if (username.equals("admin"))
                {
                    System.out.println("8. добавление новых рабочих мест");
                    System.out.println("9. добавление новых конференц-залов");
                    System.out.println("10. удаление рабочего места");
                    System.out.println("11. удаление конференц-зала");
                    System.out.println("12. Выход");
                }
                option = Integer.parseInt(reader.readLine());

                switch (option) {
                        case 1:
                            resourceManager.ShowWorkspaces();
                            break;
                        case 2:
                            System.out.println("Введите дату в формате год-месяц-число");
                            String form2 = reader.readLine();
                            System.out.println("Введите номер рабочего места");
                            String id2 = reader.readLine();
                            try {
                                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(form2);
                                resourceManager.ShowBookingcurrentDate(date,id2);
                                break;
                            }
                            catch (Exception e)
                            {
                                System.out.println("ERROR");
                                break;
                            }
                        case 3:
                            System.out.println("Введите дату в формате год-месяц-число");
                            String form3 = reader.readLine();
                            System.out.println("Введите номер конференц-зала");
                            String id3 = reader.readLine();
                            try {
                                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(form3);
                                resourceManager.ShowBookingConferencecurrentDate(date,id3);
                                break;
                            }
                            catch (Exception e)
                            {
                                System.out.println("ERROR");
                                break;
                            }

                        case 4:
                            System.out.println("Введите номер рабочего места для брони");
                            String id4 = reader.readLine();
                            System.out.println("Введите дату начало брони в формате год-месяц-число-часы");
                            String num1 = reader.readLine();
                            System.out.println("Введите дату завершения брони в формате год-месяц-число-часы");
                            String num2 = reader.readLine();
                            try{
                                Date datestart = new SimpleDateFormat("yyyy-MM-dd-HH").parse(num1);
                                Date dateend = new SimpleDateFormat("yyyy-MM-dd-HH").parse(num2);
                                resourceManager.Bookingworkspace(id4,datestart,dateend,currentuser.getusername());

                            }catch (Exception e){}
                            break;
                        case 5:
                            System.out.println("Введите номер конференц-зала для брони");
                            String id5 = reader.readLine();
                            System.out.println("Введите дату начало брони в формате год-месяц-число-часы");
                            String num15 = reader.readLine();
                            System.out.println("Введите дату завершения брони в формате год-месяц-число-часы");
                            String num25 = reader.readLine();
                            try{
                                Date datestart = new SimpleDateFormat("yyyy-MM-dd-HH").parse(num15);
                                Date dateend = new SimpleDateFormat("yyyy-MM-dd-HH").parse(num25);
                                resourceManager.BookingConferenceRoom(id5,datestart,dateend,currentuser.getusername());
                            }catch (Exception e){}
                            break;
                        case 6:
                            System.out.println("Введите номер рабочего места для отмены брони");
                            String id6 = reader.readLine();
                            System.out.println("Введите дату начало брони в формате год-месяц-число-часы");
                            String num156 = reader.readLine();
                            System.out.println("Введите дату завершения брони в формате год-месяц-число-часы");
                            String num256 = reader.readLine();
                            try{
                                Date datestart = new SimpleDateFormat("yyyy-MM-dd-HH").parse(num156);
                                Date dateend = new SimpleDateFormat("yyyy-MM-dd-HH").parse(num256);
                                resourceManager.CancelBookingworkspace(id6,datestart,dateend);
                            }catch (Exception e){}
                            break;
                        case 7:
                            System.out.println("Введите номер конференц-зала для отмены брони");
                            String id7 = reader.readLine();
                            System.out.println("Введите дату начало брони в формате год-месяц-число-часы");
                            String num157 = reader.readLine();
                            System.out.println("Введите дату завершения брони в формате год-месяц-число-часы");
                            String num257 = reader.readLine();
                            try{
                                Date datestart = new SimpleDateFormat("yyyy-MM-dd-HH").parse(num157);
                                Date dateend = new SimpleDateFormat("yyyy-MM-dd-HH").parse(num257);
                                resourceManager.CancelBookingConferenceRoom(id7,datestart,dateend);
                            }catch (Exception e){}
                            break;
                        case 8:
                            if (username.equals("admin"))
                            {
                                System.out.println("id нового рабочего места :");
                                String id = reader.readLine();
                                System.out.println("имя рабочего места :");
                                String name = reader.readLine();
                                resourceManager.WorkspaceAdd(id,name);
                                break;
                            }
                            else {
                                logout = true;
                                break;
                            }
                        default:
                            break;
                    }

                if (username.equals("admin"))
                {
                    if (option == 9) {
                        System.out.println("id нового конференц-зала :");
                        String id = reader.readLine();
                        System.out.println("имя конференц-зала :");
                        String name = reader.readLine();
                        resourceManager.ConferenceRoomAdd(id,name);
                    }
                    if (option == 10) {
                        System.out.println("id рабочего места для удаления:");
                        String id = reader.readLine();
                        resourceManager.WorkspaceRemove(id);
                    }
                    if (option == 11) {
                        System.out.println("id конференц-зала для удаления:");
                        String id = reader.readLine();
                        resourceManager.ConferenceRemove(id);
                    }
                    if (option == 12) {logout = true;}
                }



            }
        }







    }
}