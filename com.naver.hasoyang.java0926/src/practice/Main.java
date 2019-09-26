package practice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		//log.txt파일을 읽어서 트래픽의 합계 출력해보기
		//1.log.txt파일으ㅢ 내용을 줄 단위로 읽을 수 있는 객체를 생성
		
		try(BufferedReader br = new BufferedReader(new FileReader("/Users/jihyunsub/Downloads/log.txt")))
		{
			
			int sum =0;
			TreeSet<String> set = new TreeSet<>();
			
			//문자열을 전부 읽어서 출력
			while(true)
			{
				boolean b = false;
				String line = br.readLine();
				//더 이상 읽은 것이 없으면 종료
				if(line == null)
				{
					break;
				}
				//System.out.printf("%s\n", line);
				//읽어온 줄을 공백을 기준으로 분할해서
				//마지막 데이터 출력
				
				String [] data = line.split(" ");
				
				//System.out.printf("%s\n", data[data.length-1]);
				//마지막 데이터를 정수로 변환
				sum += (int)Integer.parseInt(data[data.length-1]);
				
				set.add(data[0]);
				
			}
			System.out.printf("접근한 ip들의 개수 : %d\n", set.size());
			for(String ip : set)
			{
				System.out.printf("%s\n", ip);
			}
			System.out.printf("마지막 데이터들의 합은 : %d",sum);
		}
		catch(Exception e)
		{
			System.out.printf("%s\n", e.getMessage());
		}
	}

}
