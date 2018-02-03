package components;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class Ball extends JComponent{
	private int posx, posy, speed;
	private final int breite = 20 , hoehe = 20;
	private boolean rechts = true, round = true, oben, unten, pause, newGame = true;
	
	public Ball(int x, int y){
		setPosx(x);
		setPosy(y);
		setSize(breite, hoehe);
		setLocation(x, y);
	}
	
	public boolean isPauseNewGame()	{
		if(pause || newGame)
			return true;
		else return false;
	}

	
	public int getPosx() 
	{
		return posx;
	}




	public void setPosx(int posx) 
	{
		this.posx = posx;
	}

	public void reset(){
		defaultPos();
		defSpeed();
		stopGame();
		mitte();
		rechts = true;
	}


	public int getPosy() 
	{
		return posy;
	}
	
	
	public void oben()
	{
		unten = false;
		oben = true;
	}
	
	public void unten()
	{
		oben = false;
		unten = true;
	}
	
	public void mitte()
	{
		unten = false;
		oben = false;
	}



	public void setPosy(int posy) 
	{
		this.posy = posy;
	}
	
	public void pause()
	{
		if(pause)
			pause = false;
		else 
			pause = true;
	}
	
	public void startGame()
	{
		newGame = false;
		round = false;
	}
	
	public void stopGame()
	{
		newGame = true;
	}
	
	public void defaultPos()
	{
		posx = 490;
		posy = 350;
	}

	public void collision(Collision coll){
		switch (coll){
			case HOCH: oben(); break;
			case MITTE: mitte(); break;
			case RUNTER: unten(); break;
			default: System.err.println("Gibt es nicht Bitch!");
		}
		andereRichtung();
	}
	
	public void addSpeed()
	{
		speed++;
	}
	
	public void defSpeed()
	{
		speed = 5;
	}
	
	public void bewegen()
	{
		if(!pause && !newGame)
		{
			if(round)
			{
				round = false;
				pause(1000);
			}
			if(rechts)
			{
				posx += speed;
			}
			else
			{
				posx -= speed;
			}
			if(oben)
			{
				if(posy <= 0)
					unten();
				posy -= speed;
			}
			if(unten)
			{
				if(posy >= 800-50)
					oben();
				posy += speed;
			}			
		}
		setLocation(getPosx(), getPosy());
	}
	
	public void tor()
	{
		setPosx(490);
		setPosy(350);
		setLocation(getPosx(), getPosy());
		round = true;
	}
	
	public void andereRichtung()
	{
		if(rechts)
			rechts = false;
		else
			rechts = true;
	}
	
	

	public boolean isRechts() {
		return rechts;
	}

	private void pause(int time)
	{
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(Color.WHITE);
//		g.drawRect(0, 0, breite, hoehe);
//		g.fillRect(0, 0, breite, hoehe);
		g.fillRect(0, 0, breite, hoehe);
	}
}
