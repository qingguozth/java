package qrcode;

public class Test {

	public static void main(String[] args)  throws Exception{
		// TODO Auto-generated method stub
			
			//���ɴ�logo �Ķ�ά�� 
			String text = "https://qingguozth.github.io/";
			Qrcode1.encode(text, "D:\\WPS\\11.JPG", "d:/WPS", true);
			 
			//���ɲ���logo �Ķ�ά��
			String textt = "https://qingguozth.github.io/";
			Qrcode1.encode(textt,"","d:/WPS",true);
			
			//ָ����ά��ͼƬ��������������
			System.out.println("�����Ľ��Ϊ��");
			System.out.println(Qrcode1.decode("D:/WPS/74152956.jpg"));
	}

}
