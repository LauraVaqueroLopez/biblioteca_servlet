package org.example.gestion_biblio_servlet;

import jakarta.persistence.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/libro")
public class LibroServlet extends HttpServlet {
    private static final String PERSISTENCE_UNIT_NAME = "bibliotecaPU";
    private EntityManagerFactory emf;

    @Override
    public void init() {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    @Override
    public void destroy() {
        if (emf != null) {
            emf.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        String isbn = request.getParameter("isbn");

        try {
            if (isbn == null) {
                // Leer todos los libros
                List<Libro> libros = em.createQuery("SELECT l FROM Libro l", Libro.class).getResultList();
                request.setAttribute("libros", libros);
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            } else {
                // Leer un libro por ISBN
                Libro libro = em.find(Libro.class, isbn);
                request.setAttribute("libro", libro);
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
        } finally {
            em.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");

        try {
            Libro libro = new Libro(isbn, titulo, autor);
            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        response.sendRedirect("libro");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        EntityManager em = emf.createEntityManager();
        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");

        try {
            Libro libro = em.find(Libro.class, isbn);
            if (libro != null) {
                em.getTransaction().begin();
                libro.setTitulo(titulo);
                libro.setAutor(autor);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }

        response.sendRedirect("libro");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        EntityManager em = emf.createEntityManager();
        String isbn = request.getParameter("isbn");

        try {
            Libro libro = em.find(Libro.class, isbn);
            if (libro != null) {
                em.getTransaction().begin();
                em.remove(libro);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }

        response.sendRedirect("libro");
    }
}
