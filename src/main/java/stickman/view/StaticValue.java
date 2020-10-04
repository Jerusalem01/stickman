package stickman.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/***
 * 状态图
 */
public class StaticValue {

	public static List<BufferedImage> allMarioImage = new ArrayList<BufferedImage>();

	public static BufferedImage startImage = null;

	public static BufferedImage endImage = null;

	public static BufferedImage bgImage = null;

	public static List<BufferedImage> allFlowerImage = new ArrayList<BufferedImage>();

	public static List<BufferedImage> allTriangleImage = new ArrayList<BufferedImage>();

	public static List<BufferedImage> allTurtleImage = new ArrayList<BufferedImage>();

	public static List<BufferedImage> allObstructionImage = new ArrayList<BufferedImage>();

	public static BufferedImage mariDeadImage = null;

	public static String ImagePath = System.getProperty("user.dir")+"/src/main/resources/";

	//将图片初始化
	public static void init(){
		//图片初始化
		for(int i=1;i<=10;i++){
			try {
				allMarioImage.add(ImageIO.read(new File(ImagePath+"walk"+i+".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//导入背景图片
		try {
			startImage = ImageIO.read(new File(ImagePath+"start.jpg"));
			bgImage = ImageIO.read(new File(ImagePath+"firststage.jpg"));
			endImage = ImageIO.read(new File(ImagePath+"firststageend.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//导入敌人图片
		for(int i=1;i<=5;i++){
			try {
				if(i<=2){
					allFlowerImage.add(ImageIO.read(new File(ImagePath+"flower"+i+".png")));
				}
				if(i<=3){
					allTriangleImage.add(ImageIO.read(new File(ImagePath+"triangle"+i+".png")));
				}
				allTurtleImage.add(ImageIO.read(new File(ImagePath+"Turtle"+i+".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//导入障碍物图片
		for(int i=1;i<=12;i++){
			try {
				allObstructionImage.add(ImageIO.read(new File(ImagePath+"ob"+i+".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//导入死亡图片
		try {
			mariDeadImage = ImageIO.read(new File(ImagePath+"over.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
