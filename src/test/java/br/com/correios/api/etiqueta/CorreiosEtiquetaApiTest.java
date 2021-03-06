package br.com.correios.api.etiqueta;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.correios.api.CorreiosApi;
import br.com.correios.api.postagem.cliente.ContratoEmpresa;
import br.com.correios.credentials.CorreiosCredenciais;

public class CorreiosEtiquetaApiTest {

	private CorreiosCredenciais credenciais;
	private String cnpj = "123456789";
	private String numeroDoContrato = "321564987";
	private String numeroDoCartaoDePostagem = "654789123";
	private String codigoDoServico = "41068";

	@Before
	public void startUp() {
		credenciais = new CorreiosCredenciais("seu-usuario", "sua-senha");
	}

	@Test
	public void deveriaSolicitarEtiquetasAosCorreios() throws Exception {
		CorreiosApi api = new CorreiosApi();

		ContratoEmpresa contrato = new ContratoEmpresa(cnpj, numeroDoContrato, numeroDoCartaoDePostagem);

		List<Etiqueta> etiquetas = api
			.etiquetas(credenciais)
			.solicita(2)
			.usandoCodigoDoServicoDeEntrega(codigoDoServico)
			.comContrato(contrato);

		assertThat(etiquetas.size()).isGreaterThan(1);
	}

}
