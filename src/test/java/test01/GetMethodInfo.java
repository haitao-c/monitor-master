package test01;

import io.haitaoc.model.SysDeviceItems;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GetMethodInfo {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        /*SysDeviceItems sysDeviceItem = new SysDeviceItems();
        Method m = sysDeviceItem.getClass().getDeclaredMethod("setCpuStatus", boolean.class);
        m.invoke(sysDeviceItem,true);
        System.out.println(sysDeviceItem.isCpuStatus());*/

        try {
            Class<?> clazz = Class.forName("io.haitaoc.model.SysDeviceItems");
            //获取本类的所有方法，存放入数组
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println("方法名："+method.getName());
                //获取本方法所有参数类型，存入数组
                Class<?>[] getTypeParameters = method.getParameterTypes();
                if(getTypeParameters.length==0){
                    System.out.println("此方法无参数");
                }
                for (Class<?> class1 : getTypeParameters) {
                    String parameterName = class1.getName();
                    System.out.println("参数类型："+parameterName);
                }
                System.out.println("****************************");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
class test01 {
    public test01(){
        System.out.println("我是tset01");
    }
    public void t1(){}
    public void t2(String sss){}
    public void t3(Integer integer,Boolean boo,test02 t){}
}

class test02{}


