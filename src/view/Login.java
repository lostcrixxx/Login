package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ConexaoDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;

	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void logar() {

		String sql = "SELECT * FROM login WHERE usuario = ? AND senha = ?";
		try {
			// prepara a consulta ao banco e função do que foi digitado
			pst = con.prepareStatement(sql);
			pst.setString(1, txtUsuario.getText());
			pst.setString(2, txtSenha.getText());

			// Executa a Query
			rs = pst.executeQuery();

		
			// se existir usuário e senha correspondente
			if (rs.next()) {
				TelaPrincipal principal = new TelaPrincipal();
				principal.setVisible(true);
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválido(s)");
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			
		}
	}

	public Login() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 430, 240);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setTitle("Login");

		txtUsuario = new JTextField();
		txtUsuario.setBounds(138, 35, 191, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblLogin = new JLabel("Usu\u00E1rio:");
		lblLogin.setBounds(48, 38, 55, 14);
		contentPane.add(lblLogin);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(48, 81, 55, 14);
		contentPane.add(lblSenha);

		JButton btnAcessar = new JButton("Acessar");
		btnAcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
				
			}
		});
		btnAcessar.setBounds(138, 136, 89, 23);
		contentPane.add(btnAcessar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(240, 136, 89, 23);
		contentPane.add(btnCancelar);

		JLabel lblStatus = new JLabel("status");

		lblStatus.setBounds(57, 123, 46, 48);
		contentPane.add(lblStatus);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(138, 78, 191, 20);
		contentPane.add(txtSenha);

		con = ConexaoDAO.getConexao();
		// serve de apoio ao status da conexão
		// System.out.println(con);

		if (con != null) {
			lblStatus.setIcon(new ImageIcon(getClass().getResource("/icones/dbok.png")));
			// lblStatus.setText("Conectado");
		} else {
			lblStatus.setIcon(new ImageIcon(getClass().getResource("/icones/dberro.png")));
			// lblStatus.setText("Nao Conectado");
		}

	}

}
