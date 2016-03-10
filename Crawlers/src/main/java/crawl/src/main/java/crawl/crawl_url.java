package crawl.src.main.java.crawl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.json.JSONObject;
import org.xml.sax.SAXException;
import javax.xml.bind.DatatypeConverter;

public class crawl_url implements Runnable {
	static String inputLink="http://www.calstatela.edu/student";
	static String title;
	static ArrayList<String> urls=new ArrayList<String>();  
	Thread thread1=new Thread();
	
	public void runCrawl(String link)
	{
		Document doc = null;
		try {
			doc = Jsoup.connect(link).timeout(10*1000).get();		    
		Elements elts = doc.select("a[href]");
		if(urls.isEmpty()){
		for (Element link1 : elts) {                
            // 
			///System.out.println("\nlink : " + link.attr("href"));
			//System.out.println("text : " + link.text());
			//absolute ********URL
			
			System.out.println(">>>>>"+link1.attr("abs:href"));
			String b=link1.attr("abs:href");
			urls.add(b);
			
		}
		}
			else{
				for (Element link1 : elts) {
					String b=link1.attr("abs:href");
					
					
						if(!urls.contains(b)){
							urls.add(b);
						}
				
									
						}
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		public void G()
		{
							
				//get filename from url
				for (String st : urls) {
					try {	
					
					String link=st;	
				Document doc = null;
				doc = Jsoup.connect(link).get();
				title=doc.html();			
				String inputUrl = link;
				String file = null;
				File f1 = null;
					try {
						file = inputUrl.substring(inputUrl.lastIndexOf('/')+1, inputUrl.lastIndexOf('.'));	
						
					} catch (Exception e) {
						//file = inputUrl.substring(inputUrl.lastIndexOf('/'));						
					}
				if(file==null)
				{
					Random r=new Random();					/// giving files random name
					int nu=r.nextInt(100)+1;
					file = "file"+Integer.toString(nu);							
				}
				System.out.println(file);
				//Download file
				//new PrintWriter("C://tika//file.html", title);
				Writer writer = new BufferedWriter(new OutputStreamWriter(
					    new FileOutputStream("C:/Users/vallabh/Desktop/extract/"+file+".html"), "UTF-8"));
				writer.write(title);
				writer.close();
				//
				File folder = new File("C:/Users/vallabh/Desktop/extract");
				File[] listOfFiles = folder.listFiles();
				for (File fl : listOfFiles) {
				    if (fl.isFile()) {
				        System.out.println(fl.getPath());
				        tikaExtractor(fl.getPath());
				    }
				}
				
				//
					
				}
					catch (Exception e) {
						// TODO: handle exception
					}
				} 			
			
		}
		static JSONObject json = new JSONObject();
		@SuppressWarnings({ "resource"})
		public static void tikaExtractor(String path) throws IOException, SAXException, TikaException{
			File file = new File(path);
		      //Parser method parameters
		      Parser parser = (Parser) new AutoDetectParser();
		      BodyContentHandler handler = new BodyContentHandler();
		      Metadata metadata = new Metadata();
		      FileInputStream inputstream = new FileInputStream(file);
		      ParseContext context = new ParseContext();
		      
		      parser.parse(inputstream, handler, metadata, context);
		      System.out.println(handler.toString());

		      //getting the list of all meta data elements 
		      String[] metadataNames = metadata.names();
		      
		      
		      
		      for(String name : metadataNames) {		        
		    	  System.out.println(name + ": " + metadata.get(name));
		      //to json     
			      json.append(name, metadata.get(name));			      
		      //
			      FileWriter file2 = null;
			      file2 = new FileWriter("C:\\Users\\vallabh\\Desktop\\extract\\test.json");
			      file2.write(json.toString());
			      file2.flush();			         
		      }		      

		}
				
		@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		crawl_url crw=new crawl_url();
		
	    crw.runCrawl(inputLink);
		 int size=urls.size();
		 
		Scanner sc=new Scanner(System.in);
		 System.out.println("Enter the depth : ");
		 int depth=sc.nextInt();
		 if(depth>1){
			 for(int i=0;i<size;i++){
				 crw.runCrawl(urls.get(i));
			 }
		
	}
		Thread thread = new Thread(); {
			crw.G();	
		}
		thread.start();
		
	}

	public void run() {
		// TODO Auto-generated method stub
		System.out.println("This is currently running on a separate thread, " +  
	            "the id is: " + Thread.currentThread().getId());  
		
	}
	
	 private static String toHex(byte[] bytes) {
	        return DatatypeConverter.printHexBinary(bytes);
	    }

}