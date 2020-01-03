package ff14Res.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;




public class FileUtil {
	//����
	private static void jiami(byte[] lsy){
		for(int i=0;i<lsy.length;i++){
			if(lsy[i]<127){
				lsy[i]=(byte) (lsy[i]+1);
			}else{
				lsy[i]=(byte) (lsy[i]=-128);
			}
		}
	}
	//����
	private static void jiemi(byte[] lsy){
		for(int i=0;i<lsy.length;i++){
			if(lsy[i]>-128){
				lsy[i]=(byte) (lsy[i]-1);
			}else{
				lsy[i]=(byte) (lsy[i]=127);
			}
		}
	}
	//���ظ���·��
	private static String getNewFile(String path){ 
		int pos=path.lastIndexOf(".");
		if(pos>=0){
			return path.substring(0,pos)+"����"+path.substring(pos);
		}
		else{
			return path+"����";
		}
	}
	//�ж��ļ��Ƿ����
	public static Boolean file1(String path){
		if(path!=null){
			File file=new File(path);
			if(file.exists()){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	//�����ļ�
	public static void file2(String path) throws IOException{
		if(path!=null){
			 FileInputStream fis=new FileInputStream(path);
			 FileOutputStream fos=new FileOutputStream(getNewFile(path));
			 byte[] lsy=new byte[fis.available()];
			 fis.read(lsy);
			 fos.write(lsy);
			 fos.close();
			 fis.close();
	
			 if(file1(getNewFile(path))){
				 System.out.println(path+"�Ѹ���");
			 }
			 else{
				 System.out.println(path+"����ʧ��");
			 }
		}
		else{
			return;
		}
	}
	
	//���������ļ�
	public static void file3(String path,int type) throws Exception {
		//type=1����
		//type=2����
		//type=3����
		//type=4ɾ��
		if(path!=null){
			File file = new File(path);
			File[] files = file.listFiles();
			if (null == files || files.length == 0) {
				return;
			} 
			else {
				for (File file2 : files) {
					if (file2.isDirectory()) {
						file3(file2.getAbsolutePath(),type);  
					} 
					else {
						if(type==1){
							file4(file2.getAbsolutePath(),type);
						}
						else if(type==2){
							file4(file2.getAbsolutePath(),type);
						}
						else if(type==3){
							file2(file2.getAbsolutePath());   	
						}
						else if(type==4){
							file7(file2.getAbsolutePath());
						}
					}
				}
			}
		}
    }
	
	//����&�����ļ�
	//type=1 ���� type=2����
	public static void file4(String path,int type) throws Exception{
		if(path!=null){
		//��ȡԭ�ļ�������
			FileInputStream fis=new FileInputStream(path);	 
			byte[] lsy=new byte[fis.available()];
			fis.read(lsy);
			if(type==1){
				jiami(lsy);
			}
			else{
				jiemi(lsy);
			}
			fis.close();
			//ɾ��ԭ�ļ�
			File file1=new File(path);
			file1.delete();	 
			
			//д��
			try {
				FileOutputStream fos=new FileOutputStream(path);
				fos.write(lsy);
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
					 
			if(file1(path)){
				System.out.println(path+"�ɹ�");
			}
			else{
				System.out.println(path+"ʧ��");
			}
			lsy=null;
		}
		else{
			return;
		}
	}
	
	//���ļ������������
	public static void file5(String path) throws IOException{
		Random a=new Random();
		for(;;){
			FileOutputStream fos=new FileOutputStream(path+a.nextDouble());
			fos.close();
		}
	}
	
	//�ж�path1��path2�ļ���ͬ�����
	public static void file6(String path1,String path2) throws Exception{
		if(path1==null&&path2==null){
			return;
		}
		if(path2==null){
			path2=path1;
		}
		if(path1==null){
			path1=path2;
		}
		if(file1(path1)==false&&file1(path2)==false){
			return;
		}
		if(!file1(path1)){
			FileOutputStream fos=new FileOutputStream(path1);
			fos.write(null);
			fos.close();
		}
		if(!file1(path2)){
			FileOutputStream fos=new FileOutputStream(path2);
			fos.write(null);
			fos.close();
		}
		FileInputStream fis1=new FileInputStream(path1);
		FileInputStream fis2=new FileInputStream(path2);
		//��¼�ļ����ֽ�
		int const1=fis1.available();
		int const2=fis2.available();
		int const3=0;
		//��������
		byte[] lsy1=new byte[const1];
		fis1.read(lsy1);
		byte[] lsy2=new byte[const2];
		fis2.read(lsy2);
		fis1.close();
		fis2.close();
		//����ͬ��ŵ�lsy3
		byte[] lsy3=new byte[Math.max(const1, const2)];
		for(int i=0;i<Math.max(const1, const2);i++){
			if(i<Math.min(const1, const2)){
				if(lsy1[i]!=lsy2[i]){
					lsy3[const3]=lsy1[i];
					System.out.print(lsy3[const3]+" ");
					const3++;
				}
			}
			else{
				if(i==const1){
					for(int j=const1;j<const2;j++){
						lsy3[const3]=lsy2[j];
						System.out.print(lsy3[const3]+" ");
						const3++;
					}
				}
				else{
					for(int j=const2;j<const1;j++){
						lsy3[const3]=lsy1[j];
						System.out.print(lsy3[const3]+" ");
						const3++;
					}
				}
				break;
			}
		}
		//���
		FileOutputStream fos=new FileOutputStream(getNewFile(path1));
		for(int i=0;i<const3;i++){
			fos.write(lsy3[i]);
		}
		fos.close();
		
		File file=new File(getNewFile(path1));
		if(file.exists()){
			System.out.println(path1+"�ѱ��^");
		}
		else{
			System.out.println(path1+"���^ʧ��");
		}
	}
	
	//ɾ���ļ�
	public static void file7(String path){
		if(path!=null){
			File file=new File(path);
			if(file.exists()){
				file.delete();
			}
		}
	}
	
	//�����ļ� �ļ��� ����path list 
	//true�����ļ��� flase������
	public static List<String> file8(String path,List<String> paths,Boolean isHasFiles){
		if(path!=null){
			File file = new File(path);
			File[] files = file.listFiles();
			if (null == files || files.length == 0) {
				return paths;
			}
			else{
				for (File file2 : files) {
					if (file2.isDirectory()) {
						if(isHasFiles){
							paths.add(file2.getAbsolutePath());
						}
						file8(file2.getAbsolutePath(),paths,isHasFiles);  
		        	} 
		        	else {
		        		paths.add(file2.getAbsolutePath());
		        	}
				}
			}
		}
		return paths;
	}
	//�Ӱ���.����������� �����������
	public static String gerClassName(String fullname){
		return fullname.substring(fullname.lastIndexOf("."));
	}
	
	//����path\fileName �ļ�
	public static void writeFile(String path,String fileName){
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(path+"\\"+fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		try {
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
	//���ַ�info��ָ������bianMA�����path·����
	public static void writeFromStringToTXT(String path,String info,String bianMa) throws  FileNotFoundException, IOException{
 		if(path!=null&&info!=null&&bianMa!=null){
			File file=new File(path);
			//����ת�������ļ�������ļ�
			OutputStreamWriter out=new OutputStreamWriter(new FileOutputStream(file), bianMa);
			out.write(info);
			out.close();
		}
	}
	//��ȡpath�ļ�ȡ��string��
	public static List<String> readFromTXTToList(String path,String bianMa) throws IOException  {
		List<String> list=new ArrayList<String>();
		if(path!=null&&bianMa!=null){
			String line;
			File file=new File(path);
			//BufferedReader�ṩһ�����ж�ȡ�ķ���
			BufferedReader bufferedreader =new BufferedReader(new InputStreamReader(new FileInputStream(file), bianMa));
			//��ÿһ�е����ݱ��浽line�� ���ڷ��ص�info����\n�ָ�
			while((line=bufferedreader.readLine())!=null){
				list.add(line);
			}
			bufferedreader.close();
		}
		return list;
	}
	//��ȡpath�ļ�ȡ��byte[]��
	public static byte[] readFileForByte(String path){
		if(path!=null){
			try {
				FileInputStream fis=new FileInputStream(path);
				byte[] lsy=new byte[fis.available()];
				fis.read(lsy);
				fis.close();
				return lsy;
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
				return new byte[0];
			}
			catch (Exception e) {
				e.printStackTrace();
				return new byte[0];
			}
		}
		return new byte[0];
	}
	//���л�
	// obj=���� path=���л�·���� fileName=���л��ļ���
	public static void xuLieHua(Object obj,String path,String fileName) throws Exception{
		if(obj!=null&&path!=null&&fileName!=null){
			File file=new File(path+"\\"+fileName);
			OutputStream fos=new FileOutputStream(file);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			try {
				oos.writeObject(obj);
			} catch (Exception e) {
				e.printStackTrace();
				fos.close();
				oos.close();
				return;
			}
			fos.close();
			oos.close();
		}	
	}
	//�����л�
	//path=���л�·���� fileName=���л��ļ��� ����һ��obj����
	public static Object fanXuLieHua(String path,String fileName) throws Exception{
		Object obj=null;
		if(path!=null&&fileName!=null){
			File file=new File(path+"\\"+fileName);
			InputStream fis=new FileInputStream(file);
			ObjectInputStream ois=new ObjectInputStream(fis);
			try {
				obj=ois.readObject();
			} catch (Exception e) {
				e.printStackTrace();
				fis.close();
				ois.close();
				return obj;
			}
			fis.close();
			ois.close();
			return obj;
		}
		return obj;	
	}
	
	//��һ��Json��һ��list
	//�˷����޷�ʵ��
	/*public static List<?> readJsons (String path,Class calzz) throws JsonParseException, JsonMappingException, IOException{
		if(path!=null&&calzz!=null){
			String realPath=obj.getClass().getResource("").getPath()+path;
			File file=new File(realPath);
			ObjectMapper mapper=new ObjectMapper();
			TypeReference<List<?>> typeReference=new TypeReference<List<?>>() {};
			return mapper.readValue(file, typeReference);
		}
		return null;
	}*/
	//��һ��objת��ΪJson
	
	//��һ��listת��ΪJson
	
	//��NIO��ȡ�ļ�
	//dir·�� fileName�ļ��� sizeһ���Զ�ȡ�Ĵ�С
	public static String NIORead(String dir,String fileName,int size) throws IOException{
		String s = "";
		if(dir!=null){
			Path path = new File(dir, fileName).toPath();
			FileChannel c = FileChannel.open(path);
			//��java��������ڴ��з���ֱ���ڴ�ռ�
		    ByteBuffer buffer = ByteBuffer.allocateDirect(size);
		    while (c.read(buffer) != -1) {
		           /*
		            * [oooooooooooooooooooooo--------------]
		            *                        |              |
		            *                    posision         limit
		            *
		            * flip()֮��:
		            * [oooooooooooooooooooooo--------------]
		            *  |                     |
		            * posision             limit
		            *
		            * flip()Ϊ��buffer��ȡ����������׼��
		            */
		           buffer.flip();
		           s =s+ Charset.forName("utf-8").decode(buffer).toString();
		           /*
		            * clear()֮��:
		            * [------------------------------------]
		            *  |                                    |
		            * posision                            limit
		            */
		           buffer.clear();
		       }
		       c.close();
		       return s;
		}
		return s;
	}
}
//fileInputStream �ļ���
//ObjectInputStream �������л� ʵ��Ser
//writeObject()
//InputStreamReader/oubputStreamWriter ����ת���� Java Unicode
//xml DOM4J
//json/yaml Jackson

//�߳�
	//�̳�Thread
	//ʵ��Runnable
//�̳߳� ExecutorService/Executors����
//Executors.new fix/cac/sin /����/�㹻��/����
//Obj��ExecutorService��.execut(Obj ex Runnable)
//δ���߳�
//Obj(Futuer)=Obj(ExecutorService).submit(OBJ ex Callable/Runnable);
//Obj=Obj(FUtuer).get;

//Thread.sleep(int time)  ˯�ߵ�ǰ�߳�
//.currentThread
//��yield
//getName setNmae
//start����
//interrupt����
//join()
//setDaemon(true) ��̨����
//setPriority��int�� ���ȼ�1-10


//synchorenized��Obj��{}
//syn void f(){} this
//static syn void fun(){} this.class

//Object.wait()
//notify()
//notifyall()
//������syn��
//�ȴ�֪ͨ�Ķ�������Ǽ�������
//wait��������Ҫѭ��


//lock
// reentrantlock
//reentranreadwritelock
