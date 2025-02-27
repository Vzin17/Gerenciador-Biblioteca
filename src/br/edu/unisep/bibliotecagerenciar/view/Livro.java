package br.edu.unisep.bibliotecagerenciar.view;

public abstract class Livro {
    private String titulo;
    private String autor;
    private String genero;
    private String isbn;
    private int anoPublicacao;
    private String editora;

    public Livro(String titulo, String autor, String genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.isbn = isbn;
        this.anoPublicacao = anoPublicacao;
        this.editora = editora;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public String getEditora() {
        return editora;
    }

    @Override
    public String toString() {
        return titulo + " - " + autor + " (" + genero + "), ISBN: " + isbn + ", Ano: " + anoPublicacao + ", Editora: " + editora;
    }
}
