package ff14Res.Util;

public class ff14shanghaijisuan {	
		//���������˺�
		public double fun_1(int a,int b,int c,int d,int j) {//a����b����cֱ��d������j�����ٶ�
			double x=1;
			double e=(((b-340)/3300.0*0.13)+1)*d;//��������˺�
			double f=0.05+(a-380)/3300.0*0.2;//��������
			double g=1.4+(a-380)/3300.0*0.2;//�����˺�
			double h=(c-380)/3300.0*0.55;//ֱ������
			double i=1.25;//ֱ���˺�
			double k=2.5-((j-380)/100+1)*0.01;
			//��������˺�(b-292)/2170*1.13*d
			//��������0.05+(a-364)/2170*0.2  �����˺�1.4+(a-364)/2170*0.2 ֱ������ (c-364)/2170*0.55 ֱ���˺�1.25
			x=(e*(f*h*g*i+(f-f*h)*g+(h-h*f)*i+(1-f*h-(f-f*h)-(h-h*f))*1))/k;
			return x;	
		}
		//���������޸������˺�ƫ��
		public double fun_2(int a,int b,int c,int d,int i,int e,int f,int g,int h,int j) {//a����b����cֱ��d������ efgh�޸�ֵ
			return fun_1(a+e,b+f,c+g,d+h,i+j)-fun_1(a,b,c,d,i);
		}
		//��Ҫ��������ħ��ʯ��ô��
		public int []fun_3(int a,int b,int c,int d,int e) {
			int f=b+c+e;
			double max1=fun_1(a,0,0,d,f);
			int []max2={0,0};
			for(int i=0;i<=f;i++){
				for(int j=0;j<=f-i;j++){
					if(fun_1(a,i,j,d,f-i-j)>max1&&f-i-j>380){
						max1=fun_1(a,i,j,d,f-i-j);
						max2[0]=i;
						max2[1]=j;
					}
				}
			}
			return max2;
		}
		public double fun_4(int a,int b,int c,int d,int m,int e,int f,int g,int h,int o,int i,int j,int k,int l,int q) {//a����b����cֱ��d������ efgh�޸�ֵ
			return fun_2(a,b,c,d,m,i-e,j-f,k-g,l-h,q-o);
		}
}
