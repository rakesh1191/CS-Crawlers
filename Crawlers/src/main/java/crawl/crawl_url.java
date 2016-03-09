package crawl;

import java.io.BufferedWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

import javax.annotation.Generated;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
//
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.bouncycastle.math.ec.MontgomeryLadderMultiplier;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.JsonNodeCreator;
import com.fasterxml.jackson.databind.util.RootNameLookup;

import opennlp.tools.coref.sim.SimilarityModel;

	public class crawl_url implements Runnable{
	static String inputLink="http://www.calstatela.edu";
	static String title;
	static ArrayList<String> urls=new ArrayList<String>();  	
    
	public void run(String link)
	{
		
		Document doc = null;
		try {
			doc = Jsoup.connect(link).timeout(10*1000).get();		    
			Elements elts = doc.select("a[href]");
			if(urls.isEmpty())
				{
					for (Element link1 : elts) 
						{                
            // 
			///System.out.println("\nlink : " + link.attr("href"));
			//System.out.println("text : " + link.text());
			//absolute ********URL
			
			System.out.println(">>>>>"+link1.attr("abs:href"));
			String b=link1.attr("abs:href");
			urls.add(b);			
						}
				}
			else
			    {
					for (Element link1 : elts) 
				    	{
					String b=link1.attr("abs:href");					
						if(!urls.contains(b)){
							urls.add(b);
						}									
				    }
			    }
			} catch (IOException e) 
				{
			// TODO Auto-generated catch block
			//e.printStackTrace();
				}
		}	
	
		public void G()
		{							
				//get filename from url
				for (String st : urls) 
				{
					try {	
					
					String link=st;	
					Document doc = null;
					doc = Jsoup.connect(link).get();
					title=doc.html();			
					String inputUrl = link;
					String file = null;
					try 
					{
						file = inputUrl.substring(inputUrl.lastIndexOf('/')+1, inputUrl.lastIndexOf('.'));	
					} catch (Exception e) {
						//file = inputUrl.substring(inputUrl.lastIndexOf('/'));
						/*
						URI uri = new URI(inputUrl); 
					    URL url = uri.toURL();
					    String fil = url.getFile();
					    System.out.println(fil);
					    String ss=fil.replace("/","");
					    file=ss;
					    file = fil.substring(fil.lastIndexOf('/')+1);
					    */
					}
				if(file==null)
				{
					Random r=new Random();					/// giving files random name
					int nu=r.nextInt(100000)+1;
					//file = "file"+Integer.toString(nu);
					//uuid
					UUID uuid = UUID.randomUUID();
	                String randomUUIDString = uuid.toString();
	                file=randomUUIDString;
				}
				System.out.println(file);
				//Download file
				//new PrintWriter("C://tika//file.html", title);
				Writer writer = new BufferedWriter(new OutputStreamWriter(
					    new FileOutputStream("C://tika//"+file+".html"), "UTF-8"));
				writer.write(title);
				writer.close();
				}
					catch (Exception e) 
					{
						// TODO: handle exception
						//System.out.println(e);
					}
				} 			

		}	 
		
		static JSONObject json = new JSONObject();
		@SuppressWarnings({ "resource", "null" })
		public static void tikaExtractor() throws IOException, SAXException, TikaException{
			
			File folder = new File("C://tika//");
			File[] listOfFiles = folder.listFiles();
			String path;
			for (File fl : listOfFiles) 
			{
			    if (fl.isFile()) 
			    {
			        System.out.println(fl.getPath());
			        path=fl.getPath();
			    
				  File file = new File(path);
			      //Parser method parameters
			      Parser parser = (Parser) new AutoDetectParser();
			      BodyContentHandler handler = new BodyContentHandler();
			      Metadata metadata = new Metadata();
			      FileInputStream inputstream = new FileInputStream(file);
			      ParseContext context = new ParseContext();
			      parser.parse(inputstream, handler, metadata, context);
			      //.out.println(handler.toString());			      
			      String fileJson;
			      
			      //fileJson="{url:[ { ";
			      
			     
			      JSONArray list = new JSONArray();
			      list.put("keywords :"+metadata.get("keywords"));
			      list.put("description :"+metadata.get("description"));
			      list.put("hash code :"+file.hashCode());
			  		json.put(metadata.get("title"),list );			      
			      //fileJson="},]}";		      
			      
			      //json.getJSONArray("hash").getJSONArray(1);
			     // json.append("hash", file.hashCode());
			      //JSONObject jo = new JSONObject();
			      //Collection<JSONObject> items = new ArrayList<JSONObject>();
			      //JSONObject item1 = new JSONObject();
			      //getting the list of all meta data elements 
			      
			      String[] metadataNames = metadata.names();
		      
		      for(String name : metadataNames) {		        
		         System.out.println(name + ": " + metadata.get(name));
		       //to json
			      //		         
		        // item1.put("aDataSort", new JSONArray(0));
		         
		         //json.append(name, metadata.get(name));
			      
			      //JsonNode jn=mapper
			      
		         //
			      FileWriter file2 = null;
			      file2 = new FileWriter("C:\\tika\\test.json");
			      file2.write(json.toString());
			      file2.flush();			         
		      }	
		     // jo.put(path, new JSONArray(items));		      
		      
		      
			    }
			}

		}		

	public static void main(String[] args) throws IOException, SAXException, TikaException 
	{
			
		 crawl_url crw=new crawl_url();		
	    // check	
		if (!args[2].isEmpty()) 
			{
			inputLink="http://"+args[2];
			}		 
		 crw.run(inputLink);		
		 int size=urls.size();
		 // Scanner sc=new Scanner(System.in);
		 //System.out.println("Enter the depth : ");		 
		 String d=args[1];
		 int depth=Integer.parseInt(d);
		 //sc.nextInt();
		 if(depth>1)
		 	{
			     for(int i=0;i<size;i++)
			     {			
			    	 crw.run(urls.get(i));			
			     }		
		 	}
	 	try 
		 	{		
			 	if(args[3].equals("-e"))
			 	{
			 		crw.G();			 
			 		tikaExtractor();	
			 	}	 
		 	}
	 	catch (Exception e) 
		 		{
				// TODO: handle exception
		 		}
			Thread t=new Thread(crw);
			t.start();
			Thread t1=new Thread(crw);
			t1.start();		
	}

	public void run() {
		// TODO Auto-generated method stub
		
	}
	

}
