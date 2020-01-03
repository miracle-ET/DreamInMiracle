package ff14Res.Util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class FSUtil {//String className,className!=null&& Class<?> clazz=Class.forName(className);
	//调用无参的obj的methodName方法
	//methodName:方法名 obj:实例对象
	public static Object doMethod(String methodName,Object obj) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
		if(methodName!=null&&obj!=null){
			Class<?> clazz=obj.getClass();
			Method m=clazz.getMethod(methodName);
			return m.invoke(obj);
		}
		else{
			Exception e=new Exception("参数为空");
			e.printStackTrace();
			return null;
		}
	}
	//调用有参的obj的methodName方法
	//methodName:方法名 obj:实例对象 classList:方法中每一个参数的类型的集合 infoList:方法中每个参数的实参值的集合 最大6个参数
	public static Object doMethod(String methodName,Object obj,@SuppressWarnings("rawtypes") List<Class> classList,List<?> infoList) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
		if(methodName!=null&&obj!=null&&classList!=null&&classList.size()>=1&&infoList!=null&&infoList.size()>=1&&classList.size()==infoList.size()){
			Class<?> clazz=obj.getClass();
			Method m = null;
			if(classList.size()==1){
				m=clazz.getMethod(methodName,classList.get(0));
				return m.invoke(obj,infoList.get(0));
			}else if(classList.size()==2){
				m=clazz.getMethod(methodName,classList.get(0),classList.get(1));
				return m.invoke(obj,infoList.get(0),infoList.get(1));
			}else if(classList.size()==3){
				m=clazz.getMethod(methodName,classList.get(0),classList.get(1),classList.get(2));
				return m.invoke(obj,infoList.get(0),infoList.get(1),infoList.get(2));
			}else if(classList.size()==4){
				m=clazz.getMethod(methodName,classList.get(0),classList.get(1),classList.get(2),classList.get(3));
				return m.invoke(obj,infoList.get(0),infoList.get(1),infoList.get(2),infoList.get(3));
			}else if(classList.size()==5){
				m=clazz.getMethod(methodName,classList.get(0),classList.get(1),classList.get(2),classList.get(3),classList.get(4));
				return m.invoke(obj,infoList.get(0),infoList.get(1),infoList.get(2),infoList.get(3),infoList.get(4));
			}
			else{
				Exception e=new Exception("参数不正确");
				e.printStackTrace();
				return null;
			}
		}
		else{
			Exception e=new Exception("参数太多");
			e.printStackTrace();
			return null;
		}
	}
	//得到对象的infoName属性的值
	//obj:实例对象 infoName:属性名字
	//返回一个该实例对象 infoName属性的值obj
	public static Object getPriviteField(Object obj,String infoName) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		Field field=obj.getClass().getDeclaredField(infoName);
		field.setAccessible(true);
		return field.get(obj);	
	}
	//修改对象的infoName属性的值
	//obj:实例对象 infoName:属性名字 setObj:修改的值
	public static void setPriviteField(Object obj,String infoName,Object setObj) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		Field field=obj.getClass().getDeclaredField(infoName);
		field.setAccessible(true);
		field.set(obj,setObj);
		return;	
	}
	//得到一个字符串对应的类的实例
	//classname:包名.类名
	//返回一个该类的实例obj 须有无参的构造函数
	public static Object getObjFromString(String className){
		try {
			Class<?> clazz=Class.forName(className);
			Object obj=clazz.newInstance();
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//得到一个字符串对应的类的实例 并设置属性
	//classname:包名.类名
	//返回一个该类的实例obj 须有无参的构造函数
	public static Object getObjFromStringSet(String className,List<String> nameList,List<?> infoList){
		try {

			Class<?> clazz=Class.forName(className);
			Object obj=clazz.newInstance();
			if(nameList!=null&&infoList!=null&&nameList.size()==infoList.size()){
				for(int i=0;i<nameList.size();i++){
					setPriviteField(obj,nameList.get(i),infoList.get(i));
				}
				return obj;
			}
			else{
				System.out.println("参数不正确");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//��������
	//Class.forName(string)  Class�ķ��� string=����.����
	//obj.getClass()          ���ö����getClass����
	//=����.class
	//��ȡ����
	//obj.getPackage().getName() return string ����
	//obj.getSimpleName() return string ����
	//obj.getName() return string ����.����
	//���������
	//(Class)obj.get/*D*/Fields() ���б���
	//(Class)obj.get/*D*/getSimpleName() ���з���
	//(Class)obj.get/*D*/Methods() ���з���
	//
	//(Class)obj.newInstance ���޲ι��촴������
}
