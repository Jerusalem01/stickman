package stickman.model;

import stickman.view.BackGround;
import stickman.view.Obstruction;
import stickman.view.StaticValue;

import java.awt.image.BufferedImage;

/***
 * 敌人
 */
public class Enemy implements Runnable{
	//坐标
	private int x;
	private int y;
	//初始坐标
	private int startx;
	private int starty;
	//怪物类型
	private int type;
	//显示图片
	private BufferedImage showImage;
	//移动方向
	private boolean isLeftOrUp = true;
	//移动范围
	private int upMax = 0;
	private int downMax = 0;
	//加入线程
	private Thread t = null;
	//定义图片变化
	private int imageType = 0;
	//定义所在场景
	private BackGround bg ;

	//蘑菇怪
	public Enemy(int x,int y,boolean isLeft,int type,BackGround bg){
		this.x = x;
		this.y = y;
		this.startx = x;
		this.starty = y;
		this.isLeftOrUp = isLeft;
		this.type = type;
		this.bg = bg;
		if(type==1){
			this.showImage = StaticValue.allTriangleImage.get(0);
		}
		this.t = new Thread(this);
		t.start();
		t.suspend();
	}
	//食人花
	public Enemy(int x,int y,boolean isUp,int type,int upMax,int downMax,BackGround bg){
		this.x = x;
		this.y = y;
		this.startx = x;
		this.starty = y;
		this.isLeftOrUp = isUp;
		this.type = type;
		this.upMax = upMax;
		this.downMax = downMax;
		this.bg = bg;
		if(type==2){
			this.showImage = StaticValue.allFlowerImage.get(0);
		}
		this.t = new Thread(this);
		t.start();
		t.suspend();
	}
	public void run() {
		while(true){
			//判断怪物类型
			if(type==1){
				if(this.isLeftOrUp){
					this.x -= 5;
				}else{
					this.x += 5;
				}
				if(imageType==0){
					imageType = 1;
				}else{
					imageType = 0;
				}
				//定义标记
				boolean canLeft = true;
				boolean canRight = true;
				for(int i=0;i<this.bg.getAllObstruction().size();i++){
					Obstruction ob = this.bg.getAllObstruction().get(i);
					//不能向右移动
					if(ob.getX()==this.x+60 && (ob.getY()+50>this.y && ob.getY()-50<this.y)){
						canRight = false;
					}
					//不能向左移动
					if(ob.getX()==this.x-60 && (ob.getY()+50>this.y && ob.getY()-50<this.y)){
						canLeft = false;
					}
				}
				if(!canLeft && this.isLeftOrUp || this.x == 0){
					this.isLeftOrUp = false;
				}else if(!canRight && !this.isLeftOrUp || this.x == 840){
					this.isLeftOrUp = true;
				}
				this.showImage = StaticValue.allTriangleImage.get(imageType);
			}
			if(type==2){
				if(this.isLeftOrUp){
					this.y -=5;
				}else{
					this.y +=5;
				}
				if(imageType==0){
					imageType = 1;
				}else{
					imageType = 0;
				}
				if(this.isLeftOrUp && this.y == this.upMax){
					this.isLeftOrUp = false;
				}
				if(!this.isLeftOrUp && this.y == this.downMax){
					this.isLeftOrUp = true;
				}
				this.showImage = StaticValue.allFlowerImage.get(imageType);
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void reset(){
		//还原坐标
		this.x = this.startx;
		this.y = this.starty;
		//还原图片
		if(this.type == 1){
			this.showImage = StaticValue.allTriangleImage.get(0);
		}else if(this.type == 2){
			this.showImage = StaticValue.allFlowerImage.get(0);
		}
	}

	public void dead(){
		//死亡图片
		this.showImage = StaticValue.allTriangleImage.get(2);
		this.bg.getAllEnemy().remove(this);
		this.bg.getRemoveEnemy().add(this);
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public BufferedImage getShowImage() {
		return showImage;
	}
	public void setBg(BackGround bg) {
		this.bg = bg;
	}
	public int getType() {
		return type;
	}

	public void startMove(){
		t.resume();
	}

}
