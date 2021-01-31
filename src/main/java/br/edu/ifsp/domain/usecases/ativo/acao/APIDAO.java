package br.edu.ifsp.domain.usecases.ativo.acao;

import br.edu.ifsp.domain.entities.ativo.Acao;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class APIDAO {
    private final String API_KEY = "VPG6K3O2QHXZEWPG";

    public float getNewPrice(String codigo) {
        float price = -1;
        try {
            String link = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + codigo + "&apikey=" + API_KEY;
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");


            try (Scanner scanner = new Scanner(url.openStream())) {
                String inline = "";
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                JSONParser parser = new JSONParser();
                JSONObject data_obj = (JSONObject) parser.parse(inline);
                JSONObject obj = (JSONObject) data_obj.get("Global Quote");
                price = Float.parseFloat((String) obj.get("05. price"));
            }
        } catch (IOException | ParseException e) {
            System.out.println(e);
        }
        return price;
    }

    public void getOne(String codigo) {
        try {
            String link = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + codigo + "&apikey=" + API_KEY;
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");


            try (Scanner scanner = new Scanner(url.openStream())) {
                String inline = "";
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                JSONParser parser = new JSONParser();
                JSONObject data_obj = (JSONObject) parser.parse(inline);
                JSONObject obj = (JSONObject) data_obj.get("Global Quote");
            }
        } catch (IOException | ParseException e) {
            System.out.println(e);
        }
    }

    public List<Acao> search(String keyword) {
        List<Acao> acoes = new ArrayList<>();
        try {
            String link = "https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=" + keyword + "&apikey=" + API_KEY;
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");


            try (Scanner scanner = new Scanner(url.openStream())) {
                String inline = "";
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }
                JSONParser parser = new JSONParser();
                JSONObject data_obj = (JSONObject) parser.parse(inline);
                JSONArray arr = (JSONArray) data_obj.get("bestMatches");

                for (Object o : arr) {
                    JSONObject new_obj = (JSONObject) o;
                    String codigo = (String) new_obj.get("1. symbol");
                    String nome = (String) new_obj.get("2. name");
                    String pais = (String) new_obj.get("4. region");
                    Acao acao = new Acao(codigo, pais, nome);

                    acoes.add(acao);
                }
            }
            return acoes;
        } catch (IOException | ParseException e) {
            System.out.println(e);
        }
        return acoes;
    }
}
