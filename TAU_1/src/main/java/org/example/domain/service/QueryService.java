package org.example.domain.service;

public class QueryService {
    public String createQuery(String column, String table, String condition){

        if(column.isEmpty() || table.isEmpty()){
            throw new RuntimeException("Brak kolumny albo tablicy w zapytaniu");
        }

        String query = String.format("SELECT %s FROM %s", column, table);

        if(query.contains(";") && !query.endsWith(";")){
            throw new RuntimeException("Wykryto ; w środku zapytania");
        }

        return condition.isEmpty() ? query : String.format(query + " WHERE %s", condition);
    }
}
