package charstream;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class EventHandler implements ActionListener{
	//다른 클래스 객체에 대한 참조
	CharStreamWindow frame;
	
	//frame을 주입받기 위한 생성자
	//생성자를 직접 만들면 기본적으로 제공되는 생성자는 없어집니다.
	//이제는 new EventHandler()는 안됩니다.
	public EventHandler(CharStreamWindow frame) 
	{
		super();
		this.frame = frame;
	}
	
	//버튼을 누르거나 메뉴를 누를 때 호출되는 메소드
	//ActionListener 인터페이스가 소유
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == frame.btnWrite)
		{
			//JFileChooser : 파일 대화상자
			//파일 열기나 저장을 위한 대화상자
			JFileChooser fc = new JFileChooser();
			int r = fc.showSaveDialog(frame);
			if(r == JFileChooser.APPROVE_OPTION) 
			{
				//입력한 파일명을 가져오기
				File f = fc.getSelectedFile();
				try(FileWriter fw = new FileWriter(f))
				{
					String msg = frame.display.getText();
					//버퍼에 기록
					fw.write(msg);
					//버퍼의 내용 내보내기
					fw.flush();
					//성공했다는 메시지 출력
					JOptionPane.showMessageDialog(null, "저장 성공");
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "저장 실패");
				}
			}
		}
		else if(e.getSource() == frame.btnRead)
		{
			JFileChooser fc = new JFileChooser();
			int r = fc.showOpenDialog(frame);
			if(r == fc.APPROVE_OPTION)
			{
				File f = fc.getSelectedFile();
				//파일에서 문자단위로 읽어주는 스트림을 생성
				try(FileReader fr = new FileReader(f))
				{
					//문자열을 연산할 StringBuilder를 생성
					StringBuilder sb = new StringBuilder();
					while(true)
					{
						//10글자를 저장할 배열을 생성
						char[] buf = new char[10];
						//배열의 크기만큼 읽고 개수를 r1에 저장
						int r1 = fr.read(buf);
						//읽은 데이터가 없으면 읽기를 중단
						if(r1<=0)
						{
							break;
						}
						//읽은 데이터가 있으면 sb에 추가
						sb.append(buf);
					}
					//읽은 내용을 display에 출력
					frame.display.setText(sb.toString());
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "저장 실패");
				}
			}
		}
		else if(e.getSource() == frame.bufWrite)
		{
			//window.display에 입력한 내용을 파일에 기록
			
			//기록할 파일을 생성
			JFileChooser fc = new JFileChooser();
			int result = fc.showSaveDialog(null);
			if(result == JFileChooser.APPROVE_OPTION)
			{
				File f = fc.getSelectedFile();
				
				//기록은 PrintWriter를 사용
				try(PrintWriter pw = new PrintWriter(f))
				{
					//기록할 내용을 가져오기
					String msg = frame.display.getText();
					//msg를 기록하기
					pw.print(msg);
					pw.flush();
					
				}
				catch(Exception e1)
				{
					frame.display.setText(e1.getMessage());
				}
			}
		}
		else if(e.getSource() == frame.bufRead)
		{
			//읽을 파일을 파일 대화상자에서 선택
			//파일 대화상자를 생성
			JFileChooser fc = new JFileChooser();
			//대화상자를 열기 대화상자로 출력
			int result = fc.showOpenDialog(null);
			//파일을 선택하고 열기 버튼을 눌렀다면
			if(result == JFileChooser.APPROVE_OPTION)
			{
				//선택한 파일 가져오기
				File f = fc.getSelectedFile();
				//f를 이용해서 BufferedReader 만들기
				//스트림 클래스의 객체는 생성할 때 예외처리를 해야하고
				//사용이 끝나면 close를 호출해야 합니다.
				try(BufferedReader br = new BufferedReader(new FileReader(f)))
				{
					StringBuilder sb = new StringBuilder();
					while(true)
					{
						//한줄을 읽기
						String line = br.readLine();
						//읽은 내용이 없으면 그만 읽기
						if(line == null)
						{
							break;
						}
						//읽은 내용이 있으면 sb에 추가
						sb.append(line+"\n");
					}
					//전부 읽은 내용을 활용
					frame.display.setText(sb.toString());
				}
				catch(Exception e1) 
				{
					frame.display.setText(e1.getMessage());
				}
			}
		}
	}
}
