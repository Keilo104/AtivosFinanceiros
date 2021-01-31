package br.edu.ifsp;

import br.edu.ifsp.application.main.repository.sqlite.DatabaseBuilder;

public class Main {
    public static void main(String[] args) {
        //DatabaseBuilder.dropAll();
        DatabaseBuilder.buildDatabaseIfMissing();

        App.main(args);
    }
}
