package stickman;

import stickman.model.*;
import stickman.view.BackGround;
import stickman.view.Obstruction;
import stickman.view.StaticValue;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class App extends JFrame implements KeyListener,Runnable{

	private List<BackGround> allBG = new ArrayList<BackGround>();

	private Mario mario = null;

	private BackGround nowBG = null;

	private Thread t = null;

	//是否已经开始游戏
	private boolean isStart = false;

	public static void main(String[] args){
		new App();
	}

	public App(){
		this.setTitle("Stickman");
		this.setSize(900, 600);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((width-900)/2, (height-600)/2);
		this.setResizable(false);
		//初始化图片
		StaticValue.init();
		//创建全部场景
		for(int i=1;i<=7;i++){
			this.allBG.add(new BackGround(i, i==7?true:false));
		}
		//将第一个场景设置为当前场景
		this.nowBG = this.allBG.get(0);
		//初始化
		this.mario = new Mario(0, 480);
		//放入场景中
		this.mario.setBg(nowBG);
		this.repaint();
		this.addKeyListener(this);
		this.t = new Thread(this);
		t.start();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void paint(Graphics g) {

		//双缓存
		BufferedImage image = new BufferedImage(900, 600, BufferedImage.TYPE_3BYTE_BGR);
		Graphics g2 = image.getGraphics();

		if(this.isStart){
			//绘制背景
			g2.drawImage(this.nowBG.getBgImage(), 0, 0, this);
			//绘制生命
			g2.drawString("生命:"+this.mario.getLife(), 720, 50);
			//绘制分数
			g2.drawString("分数:"+this.mario.getScore(), 800, 50);
			//绘制怪物敌人
			Iterator<Enemy> iterEnemy = this.nowBG.getAllEnemy().iterator();
			while(iterEnemy.hasNext()){
				Enemy e = iterEnemy.next();
				g2.drawImage(e.getShowImage(), e.getX(), e.getY(), this);
			}
			//绘制障碍物
			Iterator<Obstruction> iter = this.nowBG.getAllObstruction().iterator();
			while(iter.hasNext()){
				Obstruction ob = iter.next();
				g2.drawImage(ob.getShowImage(), ob.getX(), ob.getY(), this);
			}
			//绘制
			g2.drawImage(this.mario.getShowImage(), this.mario.getX(), this.mario.getY(), this);
		}else{
			g2.drawImage(StaticValue.startImage, 0, 0, this);
		}


		//把缓存图片绘制进去
		g.drawImage(image, 0, 0, this);

	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		if(this.isStart){
			//移动控制
			if(e.getKeyCode()==39){
				this.mario.rightMove();
			}
			if(e.getKeyCode()==37){
				this.mario.leftMove();
			}
			//跳跃控制
			if(e.getKeyCode()==38){
				this.mario.jump();
			}
		}else if(e.getKeyCode()==32){
			this.isStart = true;
			this.nowBG.enemyStartMove();
			this.mario.setScore(0);
			this.mario.setLife(3);
		}
	}

	public void keyReleased(KeyEvent e) {
		if(this.isStart){
			//控制停止
			if(e.getKeyCode()==39){
				this.mario.rightStop();;
			}
			if(e.getKeyCode()==37){
				this.mario.leftStop();;
			}
		}
	}

	public void run() {
		while(true){
			this.repaint();
			try {
				Thread.sleep(50);
				if(this.mario.getX() >= 840){
					//切换场景
					this.nowBG = this.allBG.get(this.nowBG.getSort());
					this.mario.setBg(nowBG);
					this.nowBG.enemyStartMove();
					//修改坐标
					this.mario.setX(0);
				}
				if(this.mario.isDead()){
					JOptionPane.showMessageDialog(this, "游戏结束");
					System.exit(0);
				}
				if(this.mario.isClear()){
					JOptionPane.showMessageDialog(this, "恭喜游戏通关！");
					System.exit(0);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
