package br.com.ufs.observatorio.util;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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