package stc.utils;

import java.io.*;

/**
 * @author : hoangtq
 * @since : 13:48 15/09/2020, Tue
 **/
public class Test {

    public static void main(String[] args) {
        Test main = new Test();
        UserProfile writeUser = new UserProfile(1001, "hoangtq", "Tran Quang Hoang");

        System.out.println("Before write: ");
        System.out.println(writeUser.toString());

        String pathToFile = "logs/log.txt";
        main.writeObjectToFile(pathToFile, writeUser);

        UserProfile readUser = (UserProfile) main.readObjectToFile(pathToFile);
        System.out.println("After write: ");
        System.out.println(readUser.toString());
    }

    public void writeObjectToFile(String pathToFile, Object object) {
        try (FileOutputStream fileOut = new FileOutputStream(pathToFile);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        ) {
            objectOut.writeObject(object);
            objectOut.close();
            System.out.println("Write done!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Object readObjectToFile(String pathToFile) {
        Object obj = null;
        try (FileInputStream fis = new FileInputStream(pathToFile);
             ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            obj = ois.readObject();
            System.out.println("Read done!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return obj;
    }

    static class UserProfile implements Serializable {
        private int id;
        private String userName;
        private String fullName;

        public UserProfile(int id, String userName, String fullName) {
            this.id = id;
            this.userName = userName;
            this.fullName = fullName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        @Override
        public String toString() {
            return "UserProfile{" +
                    "id=" + id +
                    ", userName='" + userName + '\'' +
                    ", fullName='" + fullName + '\'' +
                    '}';
        }
    }

}
