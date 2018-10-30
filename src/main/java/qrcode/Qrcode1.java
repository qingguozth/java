package qrcode;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.imageio.ImageIO;
 
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
/**
 * ��ά�����ɹ���
 * @author 
 *
 */
public class Qrcode1 {
 
	private static final String CHARSET = "utf-8";
	private static final String FORMAT_NAME = "JPG";
	// ��ά��ߴ�
	private static final int QRCODE_SIZE = 300;
	// LOGO���
	private static final int WIDTH = 60;
	// LOGO�߶�
	private static final int HEIGHT = 60; 
	
 
	/**
	 * ���ɶ�ά��
	 * @param content	Դ����
	 * @param imgPath	logoͼƬ��·��
	 * @param needCompress	�Ƿ�Ҫѹ��
	 * @return		���ض�ά��ͼƬ
	 * @throws Exception
	 */
	private static BufferedImage createImage(String content, String imgPath, boolean needCompress) throws Exception {
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();//
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);//�ݴ�ȼ�ΪH��30%�Ľ�����
		hints.put(EncodeHintType.CHARACTER_SET, CHARSET);//utf-8����
		hints.put(EncodeHintType.MARGIN, 1);
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE,
				hints);
		int width = bitMatrix.getWidth(); 
		int height = bitMatrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
			}
		}
		if (imgPath == null || "".equals(imgPath)) {
			return image;
		}
		// ����ͼƬ
		Qrcode1.insertImage(image, imgPath, needCompress);
		return image;
	}
	/*������������������������������������ֱ��ʾ���ɶ�ά����ı����ݣ���Ҫ����һ���ı��ö�ά��ͼƬ��ʾ���������ڶ����͵���������
	 *�ֱ��ʾ���ɵĶ�ά�뱣���·���Ͷ�ά���logo�Ƿ�Ҫѹ����	 
	 * 
	 * MultiFormatWriter()����һ����������encode��ͨ���÷������ı����ݽ��б��룬�÷������������������һ��������ʾ���ɶ�ά����ı����ݣ�
	 * �ڶ���������ʾ�����ʽ��������������ʾ���ɵĶ�ά��Ŀ�ȣ����ĸ�������ʾ���ɵĶ�ά��ĸ߶ȣ������������ѡ���������������ı��ı��룬
	 *encode�����ķ���ֵ��һ��BitMatrix������԰�BitMatrix����һ����ά���飬�����ά�����ÿһ��Ԫ�ض���ʾһ�����ص��Ƿ�������
	 *
	 *
	 *��������BitMatrixת�������ڴ���ͼƬ�Ĵ�����ʽBufferedImage������image�С�
	 *
	 *imgPathΪ�ջ���imgPath��Ϊ�յ�·����û�������Ƭlogo����������logo�Ķ�ά�롣
	 *�������logo
	 *
	 */
 
	/**
	 * �����ɵĶ�ά���в���ͼƬ
	 * @param source ���ɵĶ�ά��
	 * @param imgPath ·��
	 * @param needCompress
	 * @throws Exception
	 */
	private static void insertImage(BufferedImage source, String imgPath, boolean needCompress) throws Exception {
		File file = new File(imgPath);
		if (!file.exists()) {
			System.err.println("" + imgPath + "   ���ļ������ڣ�");
			return;
		}
		Image src = ImageIO.read(file);//
		int width = src.getWidth(null); 
		int height = src.getHeight(null);
		if (needCompress) { // ѹ��LOGO
			if (width > WIDTH) {//�ж�logo�Ŀ��Ƿ���ڹ涨��logo�Ŀ��
				width = WIDTH;
			}
			if (height > HEIGHT) {
				height = HEIGHT;
			}
			Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);//������ͼ������Ű汾
			BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // ������С���ͼ
			g.dispose();
			src = image;
		}
		// ����LOGO
		Graphics2D graph = source.createGraphics();
		int x = (QRCODE_SIZE - width) / 2;
		int y = (QRCODE_SIZE - height) / 2;
		graph.drawImage(src, x, y, width, height, null);
		Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
		graph.setStroke(new BasicStroke(3f));
		graph.draw(shape);
		graph.dispose();
	}
 
	/**
	 * ���ɴ�logo��ά�룬�����浽����
	 * @param content
	 * @param imgPath	logoͼƬ
	 * @param destPath
	 * @param needCompress
	 * @throws Exception
	 */
	public static void encode(String content, String imgPath, String destPath, boolean needCompress) throws Exception {
		BufferedImage image = Qrcode1.createImage(content, imgPath, needCompress);
		mkdirs(destPath);
		String file = new Random().nextInt(99999999) + ".jpg";//��������ļ���
		ImageIO.write(image, FORMAT_NAME, new File(destPath + "/" + file));
	}
 
	public static void mkdirs(String destPath) { 
		File file = new File(destPath);
		// ���ļ��в�����ʱ��mkdirs���Զ��������Ŀ¼��������mkdir��(mkdir�����Ŀ¼����������׳��쳣)
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
	}
 
	public static void encode(String content, String imgPath, String destPath) throws Exception {
		Qrcode1.encode(content, imgPath, destPath, false);
	}//logo·����Ϊ�գ�������������logo�Ķ�ά��
 
	public static void encode(String content, String destPath, boolean needCompress) throws Exception {
		Qrcode1.encode(content, null, destPath, needCompress);
	}//logo·��Ϊ�գ�������logo�Ķ�ά��
 
	public static void encode(String content, String destPath) throws Exception {
		Qrcode1.encode(content, null, destPath, false);
	}//logo·��Ϊ�գ�����������logo�Ķ�ά��
 
	public static void encode(String content, String imgPath, OutputStream output, boolean needCompress)
			throws Exception {
		BufferedImage image = Qrcode1.createImage(content, imgPath, needCompress);
		ImageIO.write(image, FORMAT_NAME, output);
	}//
 
	public static void encode(String content, OutputStream output) throws Exception {
		Qrcode1.encode(content, null, output, false);
	}//
 
	
	/**
	 * �Ӷ�ά���У���������
	 * @param file	��ά��ͼƬ�ļ�
	 * @return	 ���شӶ�ά���н�����������ֵ
	 * @throws Exception
	 */
	public static String decode(File file) throws Exception {
		BufferedImage image;
		image = ImageIO.read(file);
		if (image == null) {
			return null;
		}
		BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Result result;
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();////
		hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
		result = new MultiFormatReader().decode(bitmap);
		String resultStr = result.getText();
		return resultStr;
	}
 
	public static String decode(String path) throws Exception {
		return Qrcode1.decode(new File(path));
	}
}