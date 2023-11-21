import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String str = br.readLine();
			if(str == null || str.isEmpty()) {
				break;
			}
			sb.append(str).append("\n");
		}
		br.close();
		System.out.println(sb.toString());
	}

}
