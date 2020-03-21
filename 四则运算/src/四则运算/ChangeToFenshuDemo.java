package ��������;

/**
 * С��ת����
 * @author Happy_Girl2012
 */
public class ChangeToFenshuDemo {  
	/**
	 * ȡ�ù�Լ��
	 * @param a
	 * @param b
	 * @return int
	 */
    public static int getGongYueShu(int a, int b) {//�������������Լ��  
        int t = 0;  
        if(a < b){  
        t = a;  
        a = b;  
        b = t;  
        }  
        int c = a % b;  
        if(c == 0){  
            return b;  
        }else{  
            return getGongYueShu(b, c);  
        }  
    }  
    /**
     * ת���ɷ���
     * @param xiaoshu
     * @return String
     */
    public static String toFenshu(String xiaoshu) {   
  
        String[] array = new String[2];  
        array = xiaoshu.split("\\.");  
        int a = Integer.parseInt(array[0]);//��ȡ��������  
        int b = Integer.parseInt(array[1]);//��ȡС������  
        int length = array[1].length();  
        int FenZi = (int) (a * Math.pow(10, length) + b);  
        int FenMu = (int) Math.pow(10, length);  
        int MaxYueShu = getGongYueShu(FenZi, FenMu);  
        return new String(FenZi / MaxYueShu + "/" + FenMu / MaxYueShu);  
    }  
  
}  