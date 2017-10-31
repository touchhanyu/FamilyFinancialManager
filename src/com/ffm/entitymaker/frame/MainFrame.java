package com.ffm.entitymaker.frame;

import java.awt.Button;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author Hanyu
 * 
 */
@SuppressWarnings("serial")
public class MainFrame extends Frame {
	private Panel panel1;
	private Panel panel2;
	private Button button;
	private TextField srcText;
//	private Button generateButton;

	public MainFrame() {
		this.setVisible(true);
		this.setSize(500, 300);
		this.srcText = new TextField(32);
		this.srcText.setSize(2, TextField.HEIGHT);
		this.srcText.setVisible(true);
		this.panel1 = new Panel();
		this.panel1.add(this.srcText);
		this.button = new Button("打开");
		ClickActionListener listener = new ClickActionListener();
		listener.setParentFrame(this);
		this.button.addActionListener(listener);
		this.panel1.add(this.button);
		this.add(this.panel1);
		this.panel2 = new Panel();
		this.add(this.panel2);
	}
}

class ClickActionListener implements ActionListener {
	private Frame parentFrame;
	private FileDialog fileDia;
//	private TextField srcText;
//	private File file;

	public Frame getParentFrame() {
		return parentFrame;
	}

	public void setParentFrame(Frame parentFrame) {
		this.parentFrame = parentFrame;
	}

	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.fileDia = new FileDialog(this.parentFrame, "File", FileDialog.LOAD);
		this.fileDia.setVisible(true);
		File[] files = this.fileDia.getFiles();
//		this.parentFrame.dispose();
	}
}