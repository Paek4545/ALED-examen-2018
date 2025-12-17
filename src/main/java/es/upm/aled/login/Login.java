package es.upm.aled.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InputStream file = getServletContext().getResourceAsStream("login.html");
		InputStreamReader reader1 = new InputStreamReader(file);
		BufferedReader html = new BufferedReader(reader1);
		String pagina="", linea;
		while((linea = html.readLine()) != null) {
			pagina += linea;
		}
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println(pagina);
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtenemos los parámetros del HTML:
		String usuario = request.getParameter("usuario");
		String contraseñaEnviada = request.getParameter("contraseña");
		HttpSession sesion = request.getSession(); 
		String contraseñaGuardada = (String) sesion.getAttribute(usuario);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if(contraseñaGuardada == null || contraseñaEnviada == null) {
			 out.println("<html><body>Usuario no registrado o faltan datos.</body></html>");
			 sesion.setAttribute(usuario, contraseñaEnviada); //user es el nombre del usuario y pass su contraseña
		 } 
		 else if(contraseñaEnviada.equals(contraseñaGuardada)){ 
		   out.println("<html><body>Contraseña correcta</body></html>"); 
		 } 
		 else {
			out.println("Contraseña incorrecta.");
		 }
		out.close();
		}
	}
