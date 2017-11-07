package cucumber.appium;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import cucumber.appium.OCR;
import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;
import org.sikuli.basics.Settings;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ImageHandler {
	private AppiumDriver<?> driver;
	private OCR ocr = new OCR(driver);
	String imgPath = "src/img/";
	double DEFAULT_MIN_SIMILARITY = 0.7;
	private static Logger Log = Logger.getLogger(OCR.class);

	/**
	 * Image가 존재하는지 확인 한다.
	 * 
	 * @return void
	 * @author hhh3h
	 */
	public void validateImage(String image) throws Exception {
		System.out.printf("당신의 이미지는 %s 입니다.", image);
		String imageName = image;
		String imageLocation = imgPath + imageName;
		
		BufferedImage testImg = convertImgFileToBufferedImage(imageLocation);
        assertThat("default minSimilarity should be used", Settings.MinSimilarity, is(DEFAULT_MIN_SIMILARITY));
        ocr.getCoords(testImg, imageLocation, 0.9, null);
        assertThat("minSimilarity should revert back to default", Settings.MinSimilarity, is(DEFAULT_MIN_SIMILARITY));
	}
	
	/**
	 * 이미지를 선택 한다.
	 * 
	 * @return void
	 * @author hhh3h
	 */
	public void selectImage(String btn_image) throws Exception {
		System.out.printf("당신이 클릭할 이미지는 %s 입니다.", btn_image);
		String imageName = btn_image;
		String imageLocation = imgPath + imageName;
		ocr.clickImage(imageLocation, 10);
	}	

	/**
	 * 이미지를 읽어서 Buffer에 저장 한다.
	 * 
	 * @return BufferedImage
	 * @author hhh3h
	 */
	private BufferedImage convertImgFileToBufferedImage(String imagePath) {
		BufferedImage in = null;
		try {
			in = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return in;
	}

}
