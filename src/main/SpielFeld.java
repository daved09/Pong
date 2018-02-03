package main;

import components.Ball;
import components.Collision;
import components.Paddle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SpielFeld extends JPanel implements KeyListener{

	public SpielFeld()
	{
		super();
		setBackground(Color.black);
		setPaddles();
		setLayout(null);
		addKeyListener(this);
		new Thread(new Steuer1Thread()).start();
		new Thread(new Steuer2Thread()).start();
		new Thread(new Ballbewegung()).start();
		new Thread(new Renderer()).start();
	}
	
	private Paddle pad1, pad2;
	private Punkteanzeige pt;
	private Ball ball;
	
	private boolean w,s,hoch,runter;
	
	private void setPaddles()
	{
		pad1 = new Paddle(0, 250, 20, 200);
		pad2 = new Paddle(1000-25, 250, 20, 200);
		ball = new Ball(490, 350);
		pt = new Punkteanzeige();
		add(pad1);
		add(pad2);
		add(ball);
		add(pt);
		ball.defSpeed();
	}
	
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.drawRect(500, 0, 5, 800);
		g.fillRect(500, 0, 5, 800);
	}


	@Override
	public void keyPressed(KeyEvent e) {
		ball.startGame();
		switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
				w = true;
				break;
			case KeyEvent.VK_S:
				s = true;
				break;
			case KeyEvent.VK_UP:
				hoch = true;
				break;
			case KeyEvent.VK_DOWN:
				runter = true;
				break;
		}
		if((e.getKeyCode() == KeyEvent.VK_P)&&(e.isControlDown()))
			ball.stopGame();
		if((e.getKeyCode() == KeyEvent.VK_R)&&(e.isControlDown()))
			reset();
	}
	
	private void reset()
	{
		ball.reset();
		pt.reset();
		pad1.defaultPos(0, 250);
		pad2.defaultPos(1000-25, 250);
	}


	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
				w = false;
				break;
			case KeyEvent.VK_S:
				s = false;
				break;
			case KeyEvent.VK_UP:
				hoch = false;
				break;
			case KeyEvent.VK_DOWN:
				runter = false;
				break;
		}
	}


	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	private class Steuer1Thread implements Runnable
	{
		@Override
		public void run() {
			try
			{
				while(true)
				{
					if(hoch)
						pad2.hoch();
					else if(runter)
						pad2.runter();
					Thread.sleep(10);
				}				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	private class Steuer2Thread implements Runnable
	{
		@Override
		public void run() {
			try
			{
				while(true)
				{
					if(w)
						pad1.hoch();
					else if(s)
						pad1.runter();
					Thread.sleep(10);
				}				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	
	
	
	private void kollPad1()
	{
        if((ball.getPosx() <= pad1.getPosx()+20) && ((ball.getPosy() <= pad1.getPosy()+200)&&(ball.getPosy()+20 > pad1.getPosy()))){
            if(w)
                ball.collision(Collision.HOCH);
            else if(s)
                ball.collision(Collision.RUNTER);
            else
                ball.collision(Collision.MITTE);
        }
	}

	private void kollPad2()
	{
		if((ball.getPosx() >= pad2.getPosx()-20) && ((ball.getPosy() <= pad2.getPosy()+200)&&(ball.getPosy()+20 > pad2.getPosy()))){
			if(hoch)
				ball.collision(Collision.HOCH);
			else if(runter)
				ball.collision(Collision.RUNTER);
			else
				ball.collision(Collision.MITTE);
		}				
	}
	
	private boolean toor()
	{
		if(ball.isRechts())
		{
			if((ball.getPosx() >= pad2.getPosx()-20) && ((ball.getPosy() > pad2.getPosy()+200) || ball.getPosy() < pad2.getPosy()))
			{
				pt.addPt1();
				return true;
			}
		}
		if(!ball.isRechts())
		{
			if((ball.getPosx() <= pad1.getPosx()+20) && ((ball.getPosy() > pad1.getPosy()+200) || ball.getPosy() < pad1.getPosy()))
			{
				pt.addPt2();
				return true;
			}
		}
		return false;
	}
	
	private void win()
	{
		if(pt.getPt1() == 9)
		{
			JOptionPane.showMessageDialog(null, "Spieler 1 hat gewonnen!");
			reset();
		}
		if(pt.getPt2() == 9)
		{
			JOptionPane.showMessageDialog(null, "Spieler 2 hat gewonnen!");
			reset();
		}
	}
	
	private class Ballbewegung implements Runnable
	{
		@Override
		public void run() {
			try{
				int z = 0;
				while(true)
				{
					if(toor())
					{
						ball.mitte();
						ball.tor();
						pt.punkte();
						ball.andereRichtung();
						ball.defSpeed();
						win();
					}
					else{
						ball.bewegen();
					}
					if(!ball.isPauseNewGame())
					{
						z++;
						if(z == 1000)
						{
							ball.addSpeed();
							z = 0;
						}						
					}
					kollPad1();
					kollPad2();
					Thread.sleep(10);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	private class Renderer implements Runnable{
        public void run() {
            try{
                while (true){
                    ball.repaint();
                    pad1.repaint();
                    pad2.repaint();
                    Thread.sleep(1);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}