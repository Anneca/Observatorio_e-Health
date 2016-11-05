package br.com.ufs.observatorio.util;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import br.com.ufs.observatorio.model.Cidade;
import br.com.ufs.observatorio.model.Hospital;
import br.com.ufs.observatorio.model.Pais;
import br.com.ufs.observatorio.model.RedeSocial;
import br.com.ufs.observatorio.model.Tecnologia;

public class JsonWrite {

	List<String> listaCores = new ArrayList<String>();
	JSONArray jsonArray = new JSONArray();

	@SuppressWarnings("unchecked")
	public JSONArray gerarArquivoJson(ArrayList<Tecnologia> list) {

		// Preenchendo vetor com as cores
		listaCores.add("#f56954");
		listaCores.add("#00a65a");
		listaCores.add("#f39c12");
		listaCores.add("#00c0ef");
		listaCores.add("#3c8dbc");

		// Cria um Objeto JSON
		int i = 0;
		for (Tecnologia tec : list) {

			JSONObject jsonObject = new JSONObject();
			// Armazena dados em um Objeto JSON
			jsonObject.put("value", Integer.toString(tec.getQuantidade()));
			jsonObject.put("color", listaCores.get(i));
			jsonObject.put("highlight", listaCores.get(i));
			jsonObject.put("label", tec.getDescricao());
			jsonArray.add(jsonObject);
			i++;
		}

		for (int j = 0; j < jsonArray.size(); j++) {
			System.out.println("(" + j + ") " + jsonArray.get(j));
		}

		return jsonArray;
	}

	@SuppressWarnings("unchecked")
	public JSONArray gerarArquivoJsonRedeSocial(ArrayList<RedeSocial> list) {

		// Preenchendo vetor com as cores
		listaCores.add("#f56954");
		listaCores.add("#00a65a");
		listaCores.add("#f39c12");
		listaCores.add("#00c0ef");
		listaCores.add("#3c8dbc");

		// Cria um Objeto JSON
		int i = 0;
		for (RedeSocial rede : list) {

			JSONObject jsonObject = new JSONObject();
			// Armazena dados em um Objeto JSON
			jsonObject.put("value", Integer.toString(rede.getQuantidade()));
			jsonObject.put("color", listaCores.get(i));
			jsonObject.put("highlight", listaCores.get(i));
			jsonObject.put("label", rede.getDescricao());
			jsonArray.add(jsonObject);
			i++;
		}

		for (int j = 0; j < jsonArray.size(); j++) {
			System.out.println("(" + j + ") " + jsonArray.get(j));
		}

		return jsonArray;
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray gerarArquivoJsonHospital(ArrayList<Hospital> list) {

		for (Hospital h : list) {

			JSONObject jsonObject = new JSONObject();
			// Armazena dados em um Objeto JSON
			jsonObject.put("codigo", h.getCodigo());
			jsonObject.put("nome", h.getNome());
			jsonObject.put("site", h.getSite());
			jsonObject.put("cidade", h.getCidade().getDescricao());
			jsonObject.put("pais", h.getPais().getDescricao());
			jsonObject.put("possui_site", h.getPossuiSite());
			jsonObject.put("dt_cadastro", h.getDataAlteracao());
			jsonObject.put("natureza", h.getTipoOrganizacao());

			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray gerarArquivoJsonPais(ArrayList<Pais> list) {

		for (Pais p : list) {

			JSONObject jsonObject = new JSONObject();
			// Armazena dados em um Objeto JSON
			jsonObject.put("codigo", p.getCodigo());
			jsonObject.put("descricao", p.getDescricao());
			jsonObject.put("capital", p.getCapital());
			jsonObject.put("populacao", p.getPopulacao());
			jsonObject.put("idh", p.getIDH());
			jsonObject.put("idi", p.getIDI());
			jsonObject.put("pib", p.getPIB());
			jsonObject.put("ultima_alteracao", p.getUltimaAlteracao());
			
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray gerarArquivoJsonCidade(ArrayList<Cidade> list) {

		for (Cidade c : list) {

			JSONObject jsonObject = new JSONObject();
			// Armazena dados em um Objeto JSON
			jsonObject.put("codigo", c.getCodigo());
			jsonObject.put("descricao", c.getDescricao());
			jsonObject.put("capital", c.getCapital());
			jsonObject.put("ultima_alteracao", c.getUltimaAlteracao());
			
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

	@SuppressWarnings("unchecked")
	public JSONArray gerarArquivoJsonTecnologias(ArrayList<Tecnologia> list) {

		for (Tecnologia c : list) {

			JSONObject jsonObject = new JSONObject();
			// Armazena dados em um Objeto JSON
			jsonObject.put("colocacao", c.getColocacao());
			jsonObject.put("descricao", c.getDescricao());
			jsonObject.put("quantidade", c.getQuantidade());
			
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

	@SuppressWarnings("unchecked")
	public JSONArray gerarArquivoJsonRedesSociais(ArrayList<RedeSocial> list) {

		for (RedeSocial rede : list) {

			JSONObject jsonObject = new JSONObject();
			// Armazena dados em um Objeto JSON
			jsonObject.put("colocacao", rede.getColocacao());
			jsonObject.put("descricao", rede.getDescricao());
			jsonObject.put("quantidade", rede.getQuantidade());
			
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	public List<String> getListaCores() {
		return listaCores;
	}

	public void setListaCores(List<String> listaCores) {
		this.listaCores = listaCores;
	}

}

// package br.com.ufs.observatorio.util;
//
// import java.io.FileWriter;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;
//
// import org.json.simple.JSONArray;
// import org.json.simple.JSONObject;
//
// import br.com.ufs.observatorio.model.Tecnologia;
//
// public class JsonWrite {
//
// static List<String> listaCores = new ArrayList<String>();
// static ArrayList<Tecnologia> listinha = new ArrayList<Tecnologia>();
// static Tecnologia obj1 = new Tecnologia();
// static Tecnologia obj2 = new Tecnologia();
//
// // public void gerarArquivoJson(ArrayList<Tecnologia> list) {
//
// @SuppressWarnings("unchecked")
// public static void main(String[] args) {
// obj1.setQuantidade(32);
// obj1.setDescricao("Jquery");
// obj2.setQuantidade(34);
// obj2.setDescricao("Apache");
//
// listinha.add(obj1);
// listinha.add(obj2);
//
// // Preenchendo vetor com as cores
// listaCores.add("#f56954");
// listaCores.add("#00a65a");
// listaCores.add("#f39c12");
// listaCores.add("#00c0ef");
// listaCores.add("#3c8dbc");
//
// // Cria um Objeto JSON
// JSONArray jsonArray = new JSONArray();
// int i = 0;
// for (Tecnologia tec : listinha) {
//
// FileWriter writeFile = null;
// JSONObject jsonObject = new JSONObject();
// // Armazena dados em um Objeto JSON
// jsonObject.put("value", Integer.toString(tec.getQuantidade()));
// jsonObject.put("color", listaCores.get(i));
// jsonObject.put("highlight", listaCores.get(i));
// jsonObject.put("label", tec.getDescricao());
// jsonArray.add(jsonObject);
// i++;
// }
//
// for (int j = 0; j < jsonArray.size(); j++) {
// System.out.println("(" + j + ") " + jsonArray.get(j));
// }
// }
//
//// @SuppressWarnings("unchecked")
//// public static void main(String[] args) {
////
//// // Cria um Objeto JSON
//// JSONObject jsonObject = new JSONObject();
////
//// FileWriter writeFile = null;
////
//// // Armazena dados em um Objeto JSON
//// jsonObject.put("nome", "Allan");
//// jsonObject.put("sobrenome", "Romanato");
//// jsonObject.put("pais", "Brasil");
//// jsonObject.put("estado", "SP");
////
//// try {
//// writeFile = new FileWriter("saida.json");
//// // Escreve no arquivo conteudo do Objeto JSON
//// writeFile.write(jsonObject.toJSONString());
//// writeFile.close();
//// } catch (IOException e) {
//// e.printStackTrace();
//// }
////
//// // Imprimne na Tela o Objeto JSON para vizualização
//// System.out.println(jsonObject);
////
//// }
//
// public List<String> getListaCores() {
// return listaCores;
// }
//
// public void setListaCores(List<String> listaCores) {
// this.listaCores = listaCores;
// }
//
// }
