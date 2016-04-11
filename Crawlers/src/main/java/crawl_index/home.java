package crawlerWeb;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.lucene.queryparser.classic.ParseException;

/**
 * Servlet implementation class home
 */
@WebServlet("/home")
public class home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public home() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("hiiiiiiiiiiiiiiii");
		//getIndex getI=new getIndex();
		//getIndex.getCosineSimilarity();
		//get terms
		ArrayList<String> term=null;
		HttpSession se=request.getSession();
		 AllTerms at=new AllTerms();
		 at.initAllTerms();
		 term=at.getAllTermsarray();
		// String d="["+"\"rrrrrrr\""+","+"\"lavdyaghe\""+","+"]";
		 String zx = "[";
		 StringBuffer buffer = new StringBuffer();
		for(int i=0;i<10000;i++)
		{
			//sb.append()
			System.out.println(term.get(i));
			buffer.append("\""+term.get(i)+"\""+",");
			//zx += "\""+term.get(i)+"\""+",";
			 
		}
		zx+=buffer.toString();
		 zx += "]";
		System.out.println("hiiiiiiiii"+zx);
		 se.setAttribute("term", zx);
		response.sendRedirect("Home.jsp");
	}

	
	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache");
		ArrayList<GetUrl> links=null;
		ArrayList<Double> score=null;
		ArrayList<Double> rScore=null;
		
		HttpSession se=request.getSession();
		se.setAttribute("links", null);
		
		String que=request.getParameter("getQue");
		search_lucene sl=new search_lucene();
		try {
			sl.getSearch(que);
			//links.clear();
			links=sl.getLinks();
			score=sl.getScore();
			rScore=sl.getrScore();
			for (GetUrl l : links){
				System.out.println("Retrieved links are : "+l.getId());
				System.out.println("Retrieved links are : "+l.getUrl());
			}		
			int size=links.size();				
			//
			se.setAttribute("links", links);
			se.setAttribute("size", size);
			se.setAttribute("score", score);
			se.setAttribute("rScore", rScore);
		
			//response.sendRedirect("Display.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("Display.jsp");
			rd.forward(request, response);
		} catch (ParseException e) {			
			e.printStackTrace();
		}		
	}

}
