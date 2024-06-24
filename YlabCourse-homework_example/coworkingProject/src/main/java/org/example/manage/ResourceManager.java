package org.example.manage;


import org.example.conferenceroom.ConferenceBooking;
import org.example.conferenceroom.ConferenceRoom;
import org.example.workspace.Workspace;
import org.example.workspace.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ResourceManager {
    private Map<String, Workspace> workspaces = new HashMap<>();// id -> Workspace
    private Map<String, ConferenceRoom> conferenceRooms = new HashMap<>();



    private Map<String, ArrayList<WorkspaceBooking>> workspacesbooking = new HashMap<>();// id -> Workspace
    private Map<String, ArrayList<ConferenceBooking>> conferencebooking = new HashMap<>();


    public Map<String, Workspace> getWorkspaces() {
        return workspaces;
    }
    public Map<String, ArrayList<WorkspaceBooking>> getWorkspacesbooking()
    {
        return workspacesbooking;
    }

    //добавление новых рабочих мест
    public void WorkspaceAdd(String id,String name) {
        workspaces.put(id,new Workspace(id,name));
        workspacesbooking.put(id,new ArrayList<>());
    }
    //добавление новых конференц-залов
    public void ConferenceRoomAdd(String id,String name)
    {
        conferenceRooms.put(id,new ConferenceRoom(id,name));
        conferencebooking.put(id,new ArrayList<>());
    }

    //просмотр списка всех доступных рабочих мест
    public void ShowWorkspaces() {
        for (Workspace workspace : workspaces.values())
        {
            System.out.println("Рабочее место #" + workspace.getid() + " - " + workspace.getName());
        }
        for (ConferenceRoom conferenceRoom : conferenceRooms.values())
        {
            System.out.println("конференц-зал #" + conferenceRoom.getid() + " - " + conferenceRoom.getName());
        }
    }
    //Удаление рабочих мест
    public void WorkspaceRemove(String id)
    {
        workspaces.remove(id);
    }
    public void ConferenceRemove(String id)
    {
        conferenceRooms.remove(id);
    }


    //просмотр доступных слотов для бронирования на конкретную дату
    public void ShowBookingcurrentDate(Date date,String id) {//дата, номер стола
        int currentyear = date.getYear();
        int currentmonth = date.getMonth();
        int currentday = date.getDay();

        ArrayList<WorkspaceBooking> currenBooking = workspacesbooking.get(id);
        ArrayList<WorkspaceBooking> currenBooking1 = new ArrayList<>();// список всех бронирований на текущую дату

        for (WorkspaceBooking workspase : currenBooking)
        {
            int year = workspase.getStartTime().getYear();
            int month = workspase.getStartTime().getMonth();
            int day = workspase.getStartTime().getDay();
            if (currentyear == year && month == currentmonth && currentday == day) {currenBooking1.add(workspase);}
        }

        if (currenBooking1.size() == 0){
            System.out.println("Доступно любое время с 9:00 до 21:00");
        } else {
            System.out.println("Рабочии часы с 9:00 до 21:00");
            System.out.println("Занятые слоты на текущую дату");
            for (WorkspaceBooking workspase : currenBooking1)
            {
                System.out.println("Слот с " + workspase.getStartTime().getHours() + " до "
                        + workspase.getendTime().getHours() + " занят - " + workspase.getUserId());
            }
        }

    }
    public void ShowBookingConferencecurrentDate(Date date,String id)
    {
        int currentyear = date.getYear();
        int currentmonth = date.getMonth();
        int currentday = date.getDay();

        ArrayList<ConferenceBooking> currenBooking = conferencebooking.get(id);
        ArrayList<ConferenceBooking> currenBooking1 = new ArrayList<>();// список всех бронирований на текущую дату

        for (ConferenceBooking conferenceBooking : currenBooking)
        {
            int year = conferenceBooking.getStartTime().getYear();
            int month = conferenceBooking.getStartTime().getMonth();
            int day = conferenceBooking.getStartTime().getDay();
            if (currentyear == year && month == currentmonth && currentday == day) {currenBooking1.add(conferenceBooking);}
        }
        if (currenBooking1.size() == 0){
            System.out.println("Доступно любое время с 9:00 до 21:00");
        } else {
            System.out.println("Рабочии часы с 9:00 до 21:00");
            System.out.println("Занятые слоты на текущую дату");
            for (ConferenceBooking conferenceBooking : currenBooking1)
            {
                System.out.println("Слот с " + conferenceBooking.getStartTime().getHours() + " до "
                        + conferenceBooking.getendTime().getHours() + " занят - " + conferenceBooking.getUserId());
            }
        }
    }



    // бронирование рабочего места
    public void Bookingworkspace(String id,Date start,Date end,String userid) {//номер стола,дата с какого по какого,userid
        WorkspaceBooking workspaceBooking = new WorkspaceBooking(userid,start,end);
        // должны совпадать даты начало и конец
        // проверка доступны ли даты бронирования
        // проверка есть ли такое рабочее место
        if (workspaces.get(id) != null)
        {
            if (CheckBooking(id,start,end))
            {
                workspacesbooking.get(id).add(workspaceBooking);
                System.out.println("Ok");
            }
            else {
                System.out.println("Ошибка брони");
            }
        }


    }
    public void BookingConferenceRoom(String id,Date start,Date end,String userid) {
        ConferenceBooking conferenceBooking = new ConferenceBooking(userid,start,end);
        if (CheckBooking(id,start,end))
        {
            System.out.println("Ok");
            conferencebooking.get(id).add(conferenceBooking);
        }
    }



    //Отмена бронирования рабочего места
    public void CancelBookingworkspace(String id,Date start,Date end)
    {
        int index = 0;
        //Пройтись по всем датам по имени
        for (WorkspaceBooking workspace : workspacesbooking.get(id))
        {
            if (workspace.getStartTime().getTime() == start.getTime() && workspace.getendTime().getTime() == end.getTime())
            {
                workspacesbooking.get(id).remove(index);
                break;
            }
            index++;
        }
    }
    public void CancelBookingConferenceRoom(String id,Date start,Date end)
    {
        int index = 0;
        for (ConferenceBooking cnferencerom : conferencebooking.get(id))
        {
            conferencebooking.get(id).remove(index);
            break;
        }
    }



    // функция для проверки доступны ли даты бронирования
    public boolean CheckBooking(String id,Date start,Date end) {
        ArrayList<WorkspaceBooking> currentBooking = new ArrayList<>();
        currentBooking = workspacesbooking.get(id);

        for (WorkspaceBooking workspaceBooking : currentBooking)
        {
            Date curStart = workspaceBooking.getStartTime();
            Date cureEnd = workspaceBooking.getendTime();
            if (start.getTime() >= cureEnd.getTime()){return true;}
            if (curStart.getTime() <= start.getTime() && cureEnd.getTime() > start.getTime()){return false;}
            if (end.getTime() <= curStart.getTime()){return true;}
        }
        return true;
    }


}
