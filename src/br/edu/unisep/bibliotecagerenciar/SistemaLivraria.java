package br.edu.unisep.bibliotecagerenciar;

import br.edu.unisep.bibliotecagerenciar.view.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SistemaLivraria extends JFrame {
    private ArrayList<Livro> livros;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Emprestimo> emprestimos;

    public SistemaLivraria() {
        livros = new ArrayList<>();
        usuarios = new ArrayList<>();
        emprestimos = new ArrayList<>();
        initUI();
    }

    private void initUI() {
        setTitle("Sistema de Livraria");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Botão para cadastro de livros
        JButton botaoCadastrarLivro = new JButton("Cadastrar Livro");
        botaoCadastrarLivro.addActionListener(e -> mostrarCadastroLivro());

        // Botão para listar livros
        JButton botaoListarLivros = new JButton("Listar Livros");
        botaoListarLivros.addActionListener(e -> listarLivros());

        // Botão para consultar livros disponíveis
        JButton botaoConsultarDisponiveis = new JButton("Consultar Livros Disponíveis");
        botaoConsultarDisponiveis.addActionListener(e -> consultarLivrosDisponiveis());

        // Botão para listar livros no empréstimo
        JButton botaoListarEmprestimos = new JButton("Listar Livros no Empréstimo");
        botaoListarEmprestimos.addActionListener(e -> listarLivrosEmprestados());

        // Botão para adicionar usuário
        JButton botaoAdicionarUsuario = new JButton("Adicionar Usuário");
        botaoAdicionarUsuario.addActionListener(e -> mostrarCadastroUsuario());

        // Botão para empréstimo de livro
        JButton botaoPegarEmprestado = new JButton("Pegar Livro Emprestado");
        botaoPegarEmprestado.addActionListener(e -> mostrarEmprestimoLivro());

        // Adicionando botões na tela
        add(botaoCadastrarLivro);
        add(botaoListarLivros);
        add(botaoConsultarDisponiveis);
        add(botaoListarEmprestimos);
        add(botaoAdicionarUsuario);
        add(botaoPegarEmprestado);
    }

    // Método para listar todos os livros cadastrados
    private void listarLivros() {
        StringBuilder listaLivros = new StringBuilder("Livros cadastrados:\n");
        for (Livro livro : livros) {
            listaLivros.append(livro.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, listaLivros.toString());
    }

    // Método para consultar livros disponíveis (não emprestados)
    private void consultarLivrosDisponiveis() {
        StringBuilder livrosDisponiveis = new StringBuilder("Livros Disponíveis:\n");
        for (Livro livro : livros) {
            boolean emprestado = false;
            for (Emprestimo emp : emprestimos) {
                if (emp.getLivro().equals(livro) && emp.getStatus().equals("Em Empréstimo")) {
                    emprestado = true;
                    break;
                }
            }
            if (!emprestado) {
                livrosDisponiveis.append(livro.toString()).append("\n");
            }
        }
        JOptionPane.showMessageDialog(this, livrosDisponiveis.toString());
    }

    // Método para listar livros que estão em empréstimo
    private void listarLivrosEmprestados() {
        StringBuilder livrosEmprestados = new StringBuilder("Livros no Empréstimo:\n");
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getStatus().equals("Em Empréstimo")) {
                livrosEmprestados.append(emprestimo.getLivro().toString()).append(" - Emprestado para: ")
                        .append(emprestimo.getUsuario().getNome()).append("\n");
            }
        }
        JOptionPane.showMessageDialog(this, livrosEmprestados.toString());
    }

    // Método para mostrar o cadastro de livro com opção Físico ou Digital
    private void mostrarCadastroLivro() {
        JFrame frameCadastroLivro = new JFrame("Cadastro de Livro");
        frameCadastroLivro.setSize(400, 400);
        frameCadastroLivro.setLayout(new GridLayout(9, 2));

        JTextField campoTitulo = new JTextField();
        JTextField campoAutor = new JTextField();
        JTextField campoGenero = new JTextField();
        JTextField campoIsbn = new JTextField();
        JTextField campoAnoPublicacao = new JTextField();
        JTextField campoEditora = new JTextField();

        // Campos para Físico ou Digital
        JButton botaoFisico = new JButton("Livro Físico");
        JButton botaoDigital = new JButton("Livro Digital");

        // Tipo de Livro selecionado
        String[] tipoLivro = new String[1];

        botaoFisico.addActionListener(e -> tipoLivro[0] = "Físico");
        botaoDigital.addActionListener(e -> tipoLivro[0] = "Digital");

        // Botão para salvar o livro
        JButton botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(e -> {
            try {
                String titulo = campoTitulo.getText();
                String autor = campoAutor.getText();
                String genero = campoGenero.getText();
                String isbn = campoIsbn.getText();
                int anoPublicacao = Integer.parseInt(campoAnoPublicacao.getText());
                String editora = campoEditora.getText();

                if ("Físico".equals(tipoLivro[0])) {
                    livros.add(new LivroFisico(titulo, autor, genero, isbn, anoPublicacao, editora, "Codigo123"));
                } else if ("Digital".equals(tipoLivro[0])) {
                    livros.add(new LivroDigital(titulo, autor, genero, isbn, anoPublicacao, editora, "http://livro.com"));
                }

                JOptionPane.showMessageDialog(frameCadastroLivro, "Livro cadastrado com sucesso!");
                frameCadastroLivro.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frameCadastroLivro, "Ano de publicação deve ser um número válido!");
            }
        });

        // Adicionando componentes no frame
        frameCadastroLivro.add(new JLabel("Título:"));
        frameCadastroLivro.add(campoTitulo);
        frameCadastroLivro.add(new JLabel("Autor:"));
        frameCadastroLivro.add(campoAutor);
        frameCadastroLivro.add(new JLabel("Gênero:"));
        frameCadastroLivro.add(campoGenero);
        frameCadastroLivro.add(new JLabel("ISBN:"));
        frameCadastroLivro.add(campoIsbn);
        frameCadastroLivro.add(new JLabel("Ano de Publicação:"));
        frameCadastroLivro.add(campoAnoPublicacao);
        frameCadastroLivro.add(new JLabel("Editora:"));
        frameCadastroLivro.add(campoEditora);

        frameCadastroLivro.add(botaoFisico);
        frameCadastroLivro.add(botaoDigital);
        frameCadastroLivro.add(botaoSalvar);

        frameCadastroLivro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameCadastroLivro.setVisible(true);
    }

    // Método para cadastrar um usuário
    private void mostrarCadastroUsuario() {
        JTextField campoNomeUsuario = new JTextField();
        int opcao = JOptionPane.showConfirmDialog(this, campoNomeUsuario, "Digite o nome do usuário", JOptionPane.OK_CANCEL_OPTION);
        if (opcao == JOptionPane.OK_OPTION) {
            String nome = campoNomeUsuario.getText();
            usuarios.add(new Usuario(nome));
            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
        }
    }

    // Método para pegar um livro emprestado
    private void mostrarEmprestimoLivro() {
        String nomeUsuario = JOptionPane.showInputDialog(this, "Digite o nome do usuário que pegará o livro:");
        Usuario usuario = usuarios.stream().filter(u -> u.getNome().equals(nomeUsuario)).findFirst().orElse(null);

        if (usuario != null) {
            String tituloLivro = JOptionPane.showInputDialog(this, "Digite o título do livro que será emprestado:");
            Livro livro = livros.stream().filter(l -> l.getTitulo().equals(tituloLivro) && !isLivroEmprestado(l)).findFirst().orElse(null);

            if (livro != null) {
                emprestimos.add(new Emprestimo(livro, usuario, "Em Empréstimo"));
                JOptionPane.showMessageDialog(this, "Empréstimo realizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Livro não disponível para empréstimo.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Usuário não encontrado.");
        }
    }

    // Método para verificar se o livro está emprestado
    private boolean isLivroEmprestado(Livro livro) {
        return emprestimos.stream().anyMatch(e -> e.getLivro().equals(livro) && e.getStatus().equals("Em Empréstimo"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SistemaLivraria().setVisible(true));
    }
}
