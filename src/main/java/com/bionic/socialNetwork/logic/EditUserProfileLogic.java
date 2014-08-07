package com.bionic.socialNetwork.logic;

import com.bionic.socialNetwork.dao.InterestDao;
import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.dao.impl.InterestDaoImpl;
import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.Interest;
import com.bionic.socialNetwork.models.User;


import java.sql.Date;
import java.util.*;


/**
 * @version 1.0 on 28.07.2014.
 * @autor Bish_UA
 */
public class EditUserProfileLogic {


    public boolean edit(long userId, String name, String surname,
                     String position, String interests, String day,
                     String month, String year) {
        try {
            if(logic(userId, name, surname, position, interests, day, month, year)){
                return true;
            }else{
                return false;
            }

        }
        catch (Exception e) {
            return false;
        }


    }

    private boolean logic(long userId, String name, String surname,
                       String position, String interests, String day,
                       String month, String year) throws Exception {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.selectById(userId);

        if (!name.equals("")&& !surname.equals("") && !position.equals("")) {
            user.setName(name);
            user.setSurname(surname);
            user.setPosition(position);
        }else{
            return false;
        }

        Date date = null;
        date = parseBirthday(day, month, year);

        if (date != null) {
            user.setBirthday(date);
        }else{
            return false;
        }

        userDao.update(user);

        editInterests(userId, interests);

        return true;
    }


    private void editInterests(long userId, String interest) throws Exception {

        //parsing income line to request array
        String[] requestArray = interest.split(",");
        int length = requestArray.length;
        for (int i = 0; i < length; i++) {
            requestArray[i] = requestArray[i].trim();
        }

        //convert request array to request list
        List<String> requestList = new ArrayList<String>(Arrays.asList(requestArray));

        UserDao userDao = new UserDaoImpl();

        //getting current interests from DB
        List<Interest> interestsFromDb = userDao.selectAllInterests(userId);

        //convert interest lists from DB to 2 String lists (for deleting and adding users interests)
        List<String> interestForDelete = new ArrayList<String>(interestsFromDb.size());
        List<String> interestsForSubtraction = new ArrayList<String>(interestsFromDb.size());
        for (Interest current : interestsFromDb) {
            interestForDelete.add(current.getInterest());
            interestsForSubtraction.add(current.getInterest());

        }

        //getting list of interests what we must to delete
        interestForDelete.removeAll(requestList);

        //getting current user from DB
        User user = userDao.selectById(userId);

        //deleting old interests from DB
        InterestDao interestDao = new InterestDaoImpl();
        for (String current : interestForDelete) {
            Interest delInterest = interestDao.selectByInterest(current);
            userDao.deleteInterests(delInterest, user);
        }

        //searching interests for insert into DB
        if (requestList.size() != 0) {
            requestList.removeAll(interestsForSubtraction);
        }

        //inserting new interest to DB
        if (requestList.size() != 0) {
            Interest inter = null;
            for (String current : requestList) {
                inter = null;
                inter = interestDao.selectByInterest(current);
                if (inter == null) {
                    inter = new Interest();
                    inter.setInterest(current);
                    Set<User> set = new HashSet<User>();
                    set.add(user);
                    inter.setUsers(set);
                    interestDao.insert(inter);
                } else {
                    Set<User> set = inter.getUsers();
                    set.add(user);
                    inter.setUsers(set);
                    interestDao.update(inter);

                }
            }
        }
    }

    public java.sql.Date parseBirthday(String day, String month, String year) {
        int intDay;
        int intMonth;
        int intYear;
        try {
            intDay = Integer.parseInt(day);
            intMonth = Integer.parseInt(month);
            intYear = Integer.parseInt(year);
        } catch (Exception e) {
            return null;
        }


        if (intDay < 32 && intDay > 0 &&
                intMonth <= 12 && intMonth > 0 &&
                intYear > 1920 && intYear <2020) {
            //month -- because int sqlDate first month is 0
            intMonth--;
            Calendar calendar = new GregorianCalendar(intYear, intMonth, intDay);
            Date date = new Date(calendar.getTimeInMillis());
            return date;
        } else {
            return null;
        }
    }
}
