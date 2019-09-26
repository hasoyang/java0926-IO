package charstream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CharStreamWindow extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton btnWrite, btnRead, bufRead, bufWrite;
	JTextArea display;
	public CharStreamWindow() {
		btnWrite = new JButton("문자스트림 기록");
		btnRead = new JButton("문자스트림 읽기");
		
		display = new JTextArea(30,30);
		
		JPanel pan = new JPanel();
		pan.add(btnWrite);
		pan.add(btnRead);
		add("North", pan);
		
		add(display);
		
		
		EventHandler handler = new EventHandler(this);
		btnWrite.addActionListener(handler);
		btnRead.addActionListener(handler);
		
		bufWrite = new JButton("버퍼 문자 스트림 기록");
		bufRead = new JButton("버퍼 문자 스트림 읽기");
		
		pan.add(bufWrite);
		pan.add(bufRead);
		
		
		bufRead.addActionListener(handler);
		bufWrite.addActionListener(handler);
		setBounds(100,100,300,300);
		pack();
		setTitle("문자 스트림 사용");
		setVisible(true);
		//닫기 버튼을 누를 때 종료해주는 기능 추가
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}
}
