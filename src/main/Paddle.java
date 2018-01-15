package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class Paddle extends JComponent {
	private int x, y, breite, hoehe, punkte;
	
	public Paddle(int x,int y, int width, int height)
	{
		this.setPosx(x);
		this.setPosy(y);
		this.setBreite(width);
		this.setHoehe(height);
		this.setLocation(this.x, this.y);
		this.setSize(getBreite(), getHoehe());
	}
	
	

	public int getPunkte() {
		return punkte;
	}
	
	public void tor()
	{
		punkte++;
	}

	public int getPosx() {
		return x;
	}

	public void setPosx(int x) {
		this.x = x;
	}

	public int getPosy() {
		return y;
	}

	public void setPosy(int y) {
		this.y = y;
	}
	
	public int getBreite() {
		return breite;
	}

	public void setBreite(int breite) {
		this.breite = breite;
	}

	public int getHoehe() {
		return hoehe;
	}

	public void setHoehe(int hoehe) {
		this.hoehe = hoehe;
	}
	
	public void runter()
	{
		if(!endeUnten())
		{
			setPosy(getPosy()+6);
			this.setLocation(getPosx(), getPosy());			
		}
	}
	
	public void hoch()
	{
		if(!endeOben())
		{
			setPosy(getPosy()-6);
			this.setLocation(getPosx(), getPosy());			
		}
	}
	public void rechts()
	{
		setPosx(getPosx()+10);
		this.setLocation(getPosx(), getPosy());
	}
	
	private boolean endeUnten()
	{
		if((getPosy() >= 800-220))
		{
			return true;
		}
		else
			return false;
	}
	
	private boolean endeOben()
	{
		if((getPosy() <= 0))
		{
			return true;
		}
		else
			return false;
	}
	
	public void defaultPos(int x, int y)
	{
		setPosx(x);
		setPosy(y);
		setLocation(getPosx(), getPosy());
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
		g.fillRect(0, 0, getBreite()-1, getHoehe()-1);
	}
	
}
