package hello;

import java.io.*;
import java.net.*;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebSpider {

	public static void main(String[] args) {
		URL url = null;
		URLConnection urlconn = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		String regex = "http://[\\w+\\.?/?]+\\.[A-Za-z]+";
		
		Pattern p = Pattern.compile(regex);
		TreeMap<String, String> treemap =new TreeMap<String, String>();
		
		try {
			url = new URL("http://www.sina.com.cn/");
			urlconn = url.openConnection();
			pw = new PrintWriter(new FileWriter("url.txt"), true);
			br = new BufferedReader(new InputStreamReader(urlconn.getInputStream()));
			String buf = null;

			while ((buf = br.readLine()) != null) {    //buf 爬取的网页源码
				Matcher buf_m = p.matcher(buf);      //进行模式匹配   其中包含重复
				
				while (buf_m.find()) {
					treemap.put(buf_m.group(),"");
//					System.out.println(buf_m.group());
//					pw.println(buf_n.group());
				}
			}
			
			Iterator<String> its = treemap.keySet().iterator();    //迭代器生成位置注意
			
			while(its.hasNext()){
				pw.println(its.next());
			}
			
			System.out.println("获取结束！");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			pw.close();
		}
	}
}
