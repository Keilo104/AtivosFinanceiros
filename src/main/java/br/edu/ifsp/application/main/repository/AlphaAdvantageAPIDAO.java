package br.edu.ifsp.application.main.repository;

import br.edu.ifsp.domain.DAOs.APIDAO;
import br.edu.ifsp.domain.entities.ativo.Acao;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.mail.Flags;

public class AlphaAdvantageAPIDAO implements APIDAO {
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

    public Acao getOne(String codigo) {
        try {
            String link = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + codigo + "&apikey=" + API_KEY;
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");

            Acao acao;
            try (Scanner scanner = new Scanner(url.openStream())) {
                String inline = "";
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                JSONParser parser = new JSONParser();
                JSONObject data_obj = (JSONObject) parser.parse(inline);
                JSONObject obj = (JSONObject) data_obj.get("Global Quote");

                acao = new Acao( Float.parseFloat( obj.get( "05. price" ).toString() ) );
            }
            return acao;
        } catch (IOException | ParseException e) {
            System.out.println(e);
            return null;
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

    public  Map<String, Float> getHistoryOfOne(String keyword) {
        Map<String, Float> historico = new LinkedHashMap<>();
        try {
            String link = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + keyword + "&apikey=" + API_KEY;
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
                JSONObject arr = (JSONObject) data_obj.get("Time Series (Daily)");
                Set keys = arr.keySet();

                for (Object k : keys) {
                    JSONObject new_obj = (JSONObject) arr.get(k);
                    String data = k.toString();
                    Float preco = Float.parseFloat((String) new_obj.get("4. close"));
                    historico.put(data, preco);
                }
            }
        } catch (IOException | ParseException e) {
            System.out.println(e);
        }
        return historico;
    }
}
