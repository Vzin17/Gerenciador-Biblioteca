package br.edu.unisep.bibliotecagerenciar.view;

public class LivroFisico extends Livro {
    private String codigoBarras;

    public LivroFisico(String titulo, String autor, String genero, String codigoBarras, int anoPublicacao, String editora, String barras) {
        super(titulo, autor, genero);
        this.codigoBarras = codigoBarras;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }
}
