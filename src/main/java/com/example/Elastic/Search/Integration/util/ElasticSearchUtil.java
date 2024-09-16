package com.example.Elastic.Search.Integration.util;

import co.elastic.clients.elasticsearch._types.query_dsl.*;
import lombok.val;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by Chathura Oshada
 *
 * @author : Chathura Oshada
 * @data : 8/9/2024
 * @project : Elastic-Search-Integration
 */
public class ElasticSearchUtil {
    public static Supplier<Query> createSupplierQuery(String approximateProductName){
        Supplier<Query> supplier = ()-> Query.of(q->q.fuzzy(createFuzzyQuery(approximateProductName)));
        return  supplier;
    }


    public static FuzzyQuery createFuzzyQuery(String approximateProductName){
        val fuzzyQuery  = new FuzzyQuery.Builder();
        return  fuzzyQuery.field("name").value(approximateProductName).build();

    }

    public static Supplier<Query> createSupplierAutoSuggest(String name, Long id) {
        Supplier<Query> supplier = () -> Query.of(q -> q.bool(createAutoSuggestBoolQuery(name, id)));
        return supplier;
    }

    public static BoolQuery createAutoSuggestBoolQuery(String name, Long id) {
        val boolQuery = new BoolQuery.Builder();

        // MatchPhrasePrefix query for name
        MatchPhrasePrefixQuery nameQuery = new MatchPhrasePrefixQuery.Builder()
                .field("name")
                .query(name)
                .build();

        // Term query for id
        TermQuery idQuery = new TermQuery.Builder()
                .field("price")
                .value(id)
                .build();

        return boolQuery
                .must(Query.of(q -> q.matchPhrasePrefix(nameQuery)))  // Must match the name
                .filter(Query.of(q -> q.term(idQuery)))               // Filter by id
                .build();
    }
    public static Supplier<Query> supplierQueryForBoolQuery(String productName, Integer price){
        Supplier<Query> supplier = ()->Query.of(q->q.bool(boolQuery(productName,price)));
        return supplier;
    }

    public static BoolQuery boolQuery(String productName, Integer price){

        val boolQuery  = new BoolQuery.Builder();
        return boolQuery.filter(termQuery(productName)).must(matchQuery(price)).build();
    }

    public static List<Query> termQuery(String productName){
        final List<Query> terms = new ArrayList<>();
        val termQuery = new TermQuery.Builder();
        terms.add(Query.of(q->q.term(termQuery.field("name").value(productName).build())));
        return terms;
    }

    public static List<Query> matchQuery(Integer price){
        final List<Query> matches = new ArrayList<>();
        val matchQuery = new MatchQuery.Builder();
        matches.add(Query.of(q->q.match(matchQuery.field("price").query(price).build())));
        return matches;
    }

}
