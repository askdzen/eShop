package com.epam.ad.testjpa.util;

import com.epam.ad.testjpa.entity.User;





    public class Runner {
//        JPAService service = new JPAService();
//
//        public void testSaveRecord() throws Exception {
//            UserEntity user = new UserEntity();
//            user.setUsername("TestUser");
//            user.setPassword("TestPass");
//            service.add(user);
//            System.out.println(user);
//        }
//
//        public void updateRecord() {
//
//        }
//
//        public List<UserEntity> getAllTest() {
//            return service.getAll();
//
//
//        }
//
//
//        public static void main(String[] args) throws Exception {
//            Runner runner = new Runner();
//            runner.testSaveRecord();
//            List<UserEntity> userEntities = runner.getAllTest();
//
//
//            for (UserEntity u : userEntities) {
//                System.out.println(u);
//            }
//            System.out.println();
//        }

public Class<User> getTestClass(){
    Class<User> testClass1 = User.class;
    return testClass1;
}
        public static void main(String[] args) {
//            System.out.println("Hello");
           Runner runner=new Runner();
            System.out.println(runner.getTestClass().getName());
            System.out.println(runner.getTestClass().getCanonicalName());
            System.out.println(runner.getTestClass().getSimpleName());
            System.out.println(runner.getTestClass().getTypeName());
        }
    }

