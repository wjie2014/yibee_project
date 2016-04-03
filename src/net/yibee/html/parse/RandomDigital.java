package net.yibee.html.parse;

import java.util.Random;

public class RandomDigital {
	 static Random r = new Random();
	    static String ssource = "123456789";
	    static char[] src = ssource.toCharArray();
	    //��������ַ�
	    private static String randString (int length)
	    {
	            char[] buf = new char[length];
	            int rnd;
	            for(int i=1;i<length;i++)
	            {
	                    rnd = Math.abs(r.nextInt()) % src.length;

	                    buf[i] = src[rnd];
	            }
	            return new String(buf);
	    }

	    //���ø÷�������������ַ�,
	    //����i: Ϊ�ַ�ĳ���
	    public static String runVerifyCode(int i)
	    {
	            String VerifyCode = randString(i).toString();
	            return VerifyCode;
	    }
	    
}
