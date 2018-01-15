package main;


import javax.swing.*;

public class GuiPong extends JFrame{
	private static final long serialVersionUID = 1L;


	public static void main(String[] args) {
		GuiPong gui = new GuiPong();
		gui.gui();
		gui.setVisible(true);
	}
	
	private void gui()
	{
		setSize(1000,800);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Pong");
		setResizable(false);
		SpielFeld feld = new SpielFeld();
		add(feld);
		addKeyListener(feld);
	}

}
