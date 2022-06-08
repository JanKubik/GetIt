
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class BlockBrakerPanel extends JPanel implements KeyListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ArrayList<Block> blocks = new ArrayList<Block>();
	Block ball = new Block(237, 435, 35,25,"ball.png");
	Block paddle = new Block(175, 480, 150, 25, "paddle.png");

	
	public BlockBrakerPanel() {
		
		for(int i=0; i<8; i++) 
			blocks.add(new Block((i*60+2),0,60,25,"blue.png"));
		for(int i=0; i<8; i++) 
			blocks.add(new Block((i*60+2),25,60,25,"green.png"));
		for(int i=0; i<8; i++) 
			blocks.add(new Block((i*60+2),50,60,25,"yellow.png"));
		for(int i=0; i<8; i++) 
			blocks.add(new Block((i*60+2),75,60,25,"red.png"));

		
	addKeyListener(this);
	setFocusable(true);
	
	}
	
	public void paintComponent(Graphics g) {
		blocks.forEach(block ->{
			block.draw(g, this);
		});
		ball.draw(g,this);
		paddle.draw(g, this);
	}
	
	public void update() {
		ball.x += ball.movX;
		if(ball.x > (getWidth() - 25) || ball.x < 0) {
			ball.movX *=-1; }
	
		
		if(ball.y <0 || ball.intersects(paddle)) {
			ball.movY *=-1; }
		
		ball.y += ball.movY;
		
		blocks.forEach(block ->{
			if (ball.intersects(block) && !block.destroyed) {
				ball.movY *=-1;
				block.destroyed = true;
				
			}
		});
		
		repaint();
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			new Thread(() -> {
				while(true) {
					update();
					try {
						Thread.sleep(10);
					}catch (InterruptedException err) {
						err.printStackTrace();
					}
				}
			}).start();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x < (getWidth() - paddle.width)) {
			paddle.x += 15;
			System.out.println("pressed RIGHT "+getWidth());
		}
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x > 0) {
			paddle.x -= 15;	
		
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



}
