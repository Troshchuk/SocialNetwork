package com.bionic.socialNetwork.logic;

import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @version 1.0 on 30.07.2014.
 * @autor Bish_UA
 */
public class UserAvatarLogic {

    final String AVATAR_DIR = "/avatar/";
    final String NO_AVATAR = "/avatar/noavatar.png";

    public void saveAvatar(InputStream uploadedInputStream,
                           String uploadedFileLocation,
                           String uploadedFileName,
                           long userId) {
        String str = "";
        try {
            str = new String(uploadedFileName.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String filePath = uploadedFileLocation +
                AVATAR_DIR + userId + str;


        try {
            OutputStream out = new FileOutputStream(new File(filePath));
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(filePath));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

        UserDao userDao = new UserDaoImpl();
        User user = null;

        try {
            user = userDao.selectById(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //deleting previous avatar from fileSystem
        if (!user.getPathToAvatar().equals(NO_AVATAR)) {
            File file = new File(uploadedFileLocation +
                    user.getPathToAvatar());
            file.delete();
        }

        user.setPathToAvatar(AVATAR_DIR + userId + str);

        try {
            userDao.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public File getAvatar(String realPath, long userId) {

        UserDao userDao = new UserDaoImpl();
        User user = null;

        try {
            user = userDao.selectById(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        File file = new File(realPath + user.getPathToAvatar());

        return file;
    }

    public void delAvatar(long userId){

        try {
            UserDao userDao = new UserDaoImpl();
            User user = userDao.selectById(userId);
            user.setPathToAvatar(NO_AVATAR);
            userDao.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
