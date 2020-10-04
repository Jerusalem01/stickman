package stickman.model;

import stickman.view.BackGround;
import stickman.view.Obstruction;
import stickman.view.StaticValue;

import java.awt.image.BufferedImage;

public class Mario implements Runnable{
	//坐标
	private int x;
	private int y;
	//定义所在场景
	private BackGround bg;
	//加入线程
	private Thread t = null;
	//移动速度e
	private int xmove = 0;
	//跳跃速度
	private int ymove = 0;
	//状态
	private String status;
	//显示图片
	private BufferedImage showImage;
	//生命和分数
	private int score;
	private int life;
	//当前移动中的图片
	private int moving = 0;
	//跳跃时间
	private int upTime = 0;
	//标记玛丽奥是否死亡
	private boolean isDead = false;
	//完成游戏，游戏结束
	private boolean isClear = false;
	//构造方法
	public Mario(int x,int y){
		this.x = x;
		this.y = y;
		//初始图片
		this.showImage = StaticValue.allMarioImage.get(0);
		this.score = 0;
		this.life = 3;

		this.t = new Thread(this);
		t.start();
		this.status = "right-standing";
	}

	public void leftMove(){
		//移动速度
		xmove = -5;
		//改变状态
		//如果当前已经是跳跃，应该保持原有状态，不能再改变
		if(this.status.indexOf("jumping") != -1){
			this.status = "left-jumping";
		}else{
			this.status = "left-moving";
		}
	}

	public void rightMove(){
		xmove = 5;
		if(this.status.indexOf("jumping") != -1){
			this.status = "right-jumping";
		}else{
			this.status = "right-moving";
		}
	}

	public void leftStop(){
		this.xmove = 0;
		if(this.status.indexOf("jumping") != -1){
			this.status = "left-jumping";
		}else{
			this.status = "left-standing";
		}
	}

	public void rightStop(){
		this.xmove = 0;
		if(this.status.indexOf("jumping") != -1){
			this.status = "right-jumping";
		}else{
			this.status = "right-standing";
		}
	}

	public void jump(){
		if(this.status.indexOf("jumping") == -1){
			if(this.status.indexOf("left") != -1){
				this.status = "left-jumping";
			}else{
				this.status = "right-jumping";
			}
			ymove = -10;
			upTime = 18;
		}
	}

	//下落方法
	public void down(){
		if(this.status.indexOf("left") != -1){
			this.status = "left-jumping";
		}else{
			this.status = "right-jumping";
		}
		ymove = 10;
	}
	//死亡方法
	public void dead(){
		this.life--;
		if(this.life == 0){
			this.isDead = true;
		}else{
			this.bg.reset();
			this.x = 0;
			this.y = 480;
		}
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


	public void run() {
		while(true){
			//判断是否与障碍物碰撞
			//定义标记
			if(this.bg.isFlag() && this.x >= 520){
				this.bg.setOver(true);
				if(this.bg.isDown()){
					//降旗后玛丽奥开始移
					this.status = "right-moving";
					if(this.x < 580){
						//向右
						this.x += 5;
					}else{
						if(this.y < 480){
							//向下
							this.y += 5;
						}
						this.x += 5;
						if(this.x >= 780){
							//游戏结束
							this.setClear(true);
						}
					}
				}else{
					//如果为最后一个场景，x坐标到了550，游戏结束
					//自动控制
					if(this.y < 420){
						this.y += 5;
					}
					if(this.y >= 420){
						this.y = 420;
						this.status = "right-standing";
					}
				}
			}else{
				boolean canLeft = true;
				boolean canRight = true;
				//能否跳跃标记
				boolean onLand = false;
				for(int i=0;i<this.bg.getAllObstruction().size();i++){
					Obstruction ob = this.bg.getAllObstruction().get(i);
					//不能向右移动
					if(ob.getX()==this.x+60 && (ob.getY()+50>this.y && ob.getY()-50<this.y)){
						if(ob.getType() != 3){
							canRight = false;
						}
					}
					//不能向左移动
					if(ob.getX()==this.x-60 && (ob.getY()+50>this.y && ob.getY()-50<this.y)){
						if(ob.getType() != 3){
							canLeft = false;
						}
					}
					//判断能否跳跃
					if(ob.getY()==this.y+60 && (ob.getX()+60>this.x && ob.getX()-60<this.x)){
						if(ob.getType() != 3){
							onLand = true;
						}
					}
					//判断跳跃时是否撞到障碍物
					if(ob.getY()==this.y-60 && (ob.getX()+50>this.x && ob.getX()-50<this.x)){
						//如果是砖块
						if(ob.getType()==0){
							//移除砖块
							this.bg.getAllObstruction().remove(ob);
							//保存到移除的障碍物中
							this.bg.getRemoveObstruction().add(ob);
						}
						//如果是问号||隐藏的砖块
						if((ob.getType()==4 || ob.getType()==3) && upTime > 0){
							//增加分数
							score += 10;
							ob.setType(2);
							ob.setImage();
						}
						upTime = 0;
					}
				}

				//对敌人的判断
				for(int i=0;i<this.bg.getAllEnemy().size();i++){
					Enemy e = this.bg.getAllEnemy().get(i);
					if((e.getX()+50>this.x && e.getX()-50<this.x) && (e.getY()+60>this.y && e.getY()-60<this.y)){
						//死亡
						this.dead();
					}
					if(e.getY()==this.y+60 && (e.getX()+60>this.x && e.getX()-60<this.x)){
						if(e.getType() == 1){
							e.dead();
							this.upTime = 5;
							this.ymove = -10;
							//敌人死亡，增加分数
							score += 10;
						}else if(e.getType() == 2){
							this.dead();
						}
					}
				}



				if(onLand && upTime == 0){
					if(this.status.indexOf("left") != -1){
						if(xmove != 0){
							this.status = "left-moving";
						}else{
							this.status = "left-standing";
						}
					}else{
						if(xmove != 0){
							this.status = "right-moving";
						}else{
							this.status = "right-standing";
						}
					}
				}else{
					//上升状态
					if(upTime != 0){
						upTime--;
					}else{
						this.down();
					}
					y += ymove;
				}

				if(this.y>600){
					this.dead();
				}


				if(canLeft && xmove<0 || canRight && xmove>0){
					x += xmove;
					if(x<0){
						x = 0;
					}
				}
			}

			//默认向右
			int temp = 0;
			//向左状态
			if(this.status.indexOf("left") != -1){
				temp += 5;
			}

			//判断是否移动
			if(this.status.indexOf("moving") != -1){
				temp += this.moving;
				moving++;
				if(moving==4){
					this.moving = 0;
				}
			}

			if(this.status.indexOf("jumping") != -1){
				temp += 4;
			}

			//改变显示图片
			this.showImage = StaticValue.allMarioImage.get(temp);

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void setBg(BackGround bg) {
		this.bg = bg;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isDead() {
		return isDead;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public int getLife() {
		return life;
	}


	public void setLife(int life) {
		this.life = life;
	}

	public boolean isClear() {
		return isClear;
	}

	public void setClear(boolean isClear) {
		this.isClear = isClear;
	}

}
