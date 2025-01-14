<%@ page import="org.example.gestion_biblio_servlet.Libro" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Libros</title>
</head>
<body>
<h1>Gestión de Libros</h1>

<h2>Añadir un Libro</h2>
<form action="libro" method="post">
    <label for="isbn">ISBN:</label>
    <input type="text" id="isbn" name="isbn" required>
    <br>
    <label for="titulo">Título:</label>
    <input type="text" id="titulo" name="titulo" required>
    <br>
    <label for="autor">Autor:</label>
    <input type="text" id="autor" name="autor" required>
    <br>
    <button type="submit">Añadir Libro</button>
</form>

<h2>Lista de Libros</h2>
<table border="1">
    <tr>
        <th>ISBN</th>
        <th>Título</th>
        <th>Autor</th>
    </tr>
    <%
        List<Libro> libros = (List<org.example.gestion_biblio_servlet.Libro>) request.getAttribute("libros");
        if (libros != null) {
            for (org.example.gestion_biblio_servlet.Libro libro : libros) {
    %>
    <tr>
        <td><%= libro.getIsbn() %></td>
        <td><%= libro.getTitulo() %></td>
        <td><%= libro.getAutor() %></td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>