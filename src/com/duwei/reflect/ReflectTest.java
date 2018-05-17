package com.duwei.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectTest {

    public static void main(String[] args) throws Exception {

        Class<UserBean> myclass = UserBean.class;

        Field[] fields = myclass.getFields();
        Method[] methods = myclass.getMethods();

        for (Field field : fields) {
            System.out.println(field.getName());
        }
        System.out.println("--------------------------");
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        System.out.println("--------------------------");

        UserBean userBean = myclass.newInstance();

        Method setMethod = myclass.getMethod("setUsername", String.class);
        setMethod.invoke(userBean, "伟大的大伟");
//			userBean.setUsername("伟大的大伟");
        Method getMethod = myclass.getMethod("getUsername");
        Class<?> returnType = getMethod.getReturnType();
        System.out.println(returnType);
        String name = (String) getMethod.invoke(userBean);
        System.out.println(name);
        Method loginMethod = myclass.getMethod("userLogin");
        loginMethod.invoke(userBean);
    }

}

class UserBean {

    private String userid;
    private String username;
    private String password;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void userLogin() {
        System.out.println(getUsername() + ": 登陆成功");
    }

}