package stickman.view;

import stickman.model.Enemy;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/***
 * 背景
 */
public class BackGround {
	//当前场景图片
	private BufferedImage bgImage = null;
	//场景顺序
	private int sort;
	//是否为最后的场景
	private boolean flag;
	//游戏结束标记
	private boolean isOver = false;
	//定义降旗结束
	private boolean isDown = false;
	//用集合保存敌人
	private List<Enemy> allEnemy = new ArrayList<Enemy>();
	//用集合保存障碍物
	private List<Obstruction> allObstruction = new ArrayList<Obstruction>();
	//被消灭的敌人
	private List<Enemy> removeEnemy = new ArrayList<Enemy>();
	//被消灭的障碍物
	private List<Obstruction> removeObstruction = new ArrayList<Obstruction>();

	//敌人开始移动
	public void enemyStartMove(){
		for(int i=0;i<this.allEnemy.size();i++){
			this.allEnemy.get(i).startMove();
		}
	}

	//构造方法
	public BackGround(int sort,boolean flag){
		this.sort = sort;
		this.flag = flag;
		if(flag){
			bgImage = StaticValue.endImage;
		}else{
			bgImage = StaticValue.bgImage;
		}
		//第一个场景
		if(sort==1){
			for(int i=0;i<15;i++){
				this.allObstruction.add(new Obstruction(i*60, 540, 9,this));
			}
			//绘制砖块和问号
			this.allObstruction.add(new Obstruction(120, 360, 4,this));
			this.allObstruction.add(new Obstruction(300, 360, 0,this));
			this.allObstruction.add(new Obstruction(360, 360, 4,this));
			this.allObstruction.add(new Obstruction(420, 360, 0,this));
			this.allObstruction.add(new Obstruction(480, 360, 4,this));
			this.allObstruction.add(new Obstruction(540, 360, 0,this));
			this.allObstruction.add(new Obstruction(420, 180, 4,this));
			//绘制水管
			this.allObstruction.add(new Obstruction(660, 540, 6,this));
			this.allObstruction.add(new Obstruction(720, 540, 5,this));
			this.allObstruction.add(new Obstruction(660, 480, 8,this));
			this.allObstruction.add(new Obstruction(720, 480, 7,this));
			//隐藏砖块
			this.allObstruction.add(new Obstruction(660, 300, 3,this));
			//绘制怪物
			this.allEnemy.add(new Enemy(600, 480, true, 1,this));
			this.allEnemy.add(new Enemy(690, 540, true, 2, 420, 540,this));
		}
		//第二个场景
		if(sort==2){
			for(int i=0;i<15;i++){
				if(i != 9 && i != 10 && i != 11 ){
					this.allObstruction.add(new Obstruction(i*60, 540, 9,this));
				}
			}
			//绘制水管
			this.allObstruction.add(new Obstruction(60, 540, 6,this));
			this.allObstruction.add(new Obstruction(120, 540, 5,this));
			this.allObstruction.add(new Obstruction(60, 480, 6,this));
			this.allObstruction.add(new Obstruction(120, 480, 5,this));
			this.allObstruction.add(new Obstruction(60, 420, 8,this));
			this.allObstruction.add(new Obstruction(120, 420, 7,this));
			this.allObstruction.add(new Obstruction(300, 540, 6,this));
			this.allObstruction.add(new Obstruction(360, 540, 5,this));
			this.allObstruction.add(new Obstruction(300, 480, 6,this));
			this.allObstruction.add(new Obstruction(360, 480, 5,this));
			this.allObstruction.add(new Obstruction(300, 420, 6,this));
			this.allObstruction.add(new Obstruction(360, 420, 5,this));
			this.allObstruction.add(new Obstruction(300, 360, 8,this));
			this.allObstruction.add(new Obstruction(360, 360, 7,this));
			//绘制怪物
			this.allEnemy.add(new Enemy(330, 360, true, 2, 300, 420,this));
		}
		//第三个场景
		if(sort==3){
			for(int i=0;i<15;i++){
				this.allObstruction.add(new Obstruction(i*60, 540, 9,this));
			}
			//绘制砖块和问号
			this.allObstruction.add(new Obstruction(180, 360, 4,this));
			this.allObstruction.add(new Obstruction(420, 360, 4,this));
			this.allObstruction.add(new Obstruction(660, 360, 4,this));
			this.allObstruction.add(new Obstruction(420, 180, 4,this));
		}
		//第四个场景
		if(sort==4){
			for(int i=0;i<15;i++){
				if(i<2||i>12){
					this.allObstruction.add(new Obstruction(i*60, 540, 9,this));
				}
			}
			this.allObstruction.add(new Obstruction(120, 360, 0,this));
			this.allObstruction.add(new Obstruction(180, 360, 0,this));
			this.allObstruction.add(new Obstruction(300, 180, 0,this));
			this.allObstruction.add(new Obstruction(360, 180, 0,this));
			this.allObstruction.add(new Obstruction(420, 180, 0,this));
			this.allObstruction.add(new Obstruction(480, 180, 0,this));
			this.allObstruction.add(new Obstruction(540, 180, 0,this));
			this.allObstruction.add(new Obstruction(660, 360, 0,this));
			this.allObstruction.add(new Obstruction(720, 360, 0,this));
		}
		//第五个场景
		if(sort==5){
			int z = 2;
			for(int i=0;i<15;i++){
				if(i%2==0 && i<7){
					this.allObstruction.add(new Obstruction(i*60, 540-(i*60), 9,this));
					for(int x=i;x>0;x--){
						this.allObstruction.add(new Obstruction(i*60, 540-(x*60)+60, 10,this));
					}
				}
				if(i%2==0 && i>7){
					this.allObstruction.add(new Obstruction(i*60, 540-((i-z)*60), 9,this));
					for(int x=i-z;x>0;x--){
						this.allObstruction.add(new Obstruction(i*60, 540-(x*60)+60, 10,this));
					}
					z+=4;
				}
			}
		}
		//第六个场景
		if(sort==6){
			for(int i=0;i<15;i++){
				this.allObstruction.add(new Obstruction(i*60, 540, 9,this));
			}
			this.allObstruction.add(new Obstruction(480, 480, 1,this));
			this.allObstruction.add(new Obstruction(480, 420, 1,this));
			this.allObstruction.add(new Obstruction(480, 360, 1,this));
			this.allObstruction.add(new Obstruction(480, 300, 1,this));
			this.allObstruction.add(new Obstruction(480, 240, 1,this));
			this.allObstruction.add(new Obstruction(480, 180, 1,this));
			this.allObstruction.add(new Obstruction(540, 240, 1,this));
			this.allObstruction.add(new Obstruction(540, 300, 1,this));
			this.allObstruction.add(new Obstruction(540, 360, 1,this));
			this.allObstruction.add(new Obstruction(540, 420, 1,this));
			this.allObstruction.add(new Obstruction(540, 480, 1,this));
			this.allObstruction.add(new Obstruction(600, 300, 1,this));
			this.allObstruction.add(new Obstruction(600, 360, 1,this));
			this.allObstruction.add(new Obstruction(600, 420, 1,this));
			this.allObstruction.add(new Obstruction(600, 480, 1,this));
			this.allObstruction.add(new Obstruction(660, 360, 1,this));
			this.allObstruction.add(new Obstruction(660, 420, 1,this));
			this.allObstruction.add(new Obstruction(660, 480, 1,this));
			this.allObstruction.add(new Obstruction(720, 420, 1,this));
			this.allObstruction.add(new Obstruction(720, 480, 1,this));
			this.allObstruction.add(new Obstruction(780, 480, 1,this));
			//通关要点，隐形砖块
			this.allObstruction.add(new Obstruction(300, 360, 3,this));
		}
		//第七个场景
		if(sort==7){
			for(int i=0;i<15;i++){
				this.allObstruction.add(new Obstruction(i*60, 540, 9,this));
			}
			this.allObstruction.add(new Obstruction(490, 180, 11,this));
			this.allObstruction.add(new Obstruction(520, 480, 2,this));
			//地上障碍物
			this.allObstruction.add(new Obstruction(240, 360, 1,this));
			this.allObstruction.add(new Obstruction(240, 420, 1,this));
			this.allObstruction.add(new Obstruction(240, 480, 1,this));
			this.allObstruction.add(new Obstruction(180, 420, 1,this));
			this.allObstruction.add(new Obstruction(180, 480, 1,this));
			this.allObstruction.add(new Obstruction(120, 480, 1,this));
		}
	}

	//重置方法,重置障碍物和敌人
	public void reset(){
		//将移除的障碍物和敌人还原
		this.allEnemy.addAll(this.removeEnemy);
		this.allObstruction.addAll(this.removeObstruction);
		//调用障碍物和敌人的重置方法
		for(int i=0;i<this.allEnemy.size();i++){
			this.allEnemy.get(i).reset();
		}
		for(int i=0;i<this.allObstruction.size();i++){
			this.allObstruction.get(i).reset();
		}
	}

	public BufferedImage getBgImage() {
		return bgImage;
	}

	public List<Obstruction> getAllObstruction() {
		return allObstruction;
	}

	public List<Obstruction> getRemoveObstruction() {
		return removeObstruction;
	}

	public int getSort() {
		return sort;
	}

	public List<Enemy> getAllEnemy() {
		return allEnemy;
	}

	public List<Enemy> getRemoveEnemy() {
		return removeEnemy;
	}

	public boolean isFlag() {
		return flag;
	}

	public boolean isOver() {
		return isOver;
	}

	public void setOver(boolean isOver) {
		this.isOver = isOver;
	}

	public boolean isDown() {
		return isDown;
	}

	public void setDown(boolean isDown) {
		this.isDown = isDown;
	}

}
