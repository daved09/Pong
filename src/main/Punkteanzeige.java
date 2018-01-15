package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;

public class Punkteanzeige extends JComponent{
	private final int posx = 447,posy = 15;
	private int pt1,pt2;
	
	public Punkteanzeige()
	{
		pt1 = 0;
		pt2 = 0;
		setLocation(posx, posy);
		setSize(150,150);
	}
	
	public void punkte()
	{
		repaint();
	}
	
	public void reset()
	{
		pt1 = 0;
		pt2 = 0;
		punkte();
	}
	
	
	public int getPt1() {
		return pt1;
	}

	public int getPt2() {
		return pt2;
	}

	public void addPt1()
	{
		pt1++;
	}
	
	public void addPt2()
	{
		pt2++;
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Pain", Font.BOLD, 80));
		g.drawString(pt1+" "+pt2, 0, 60);
	}

}
