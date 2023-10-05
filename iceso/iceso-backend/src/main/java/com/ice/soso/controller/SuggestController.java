package com.ice.soso.controller;

/**
 * User: hallen
 * Date: 2023/10/3
 * Time: 19:07
 */


import com.ice.soso.common.BaseResponse;
import com.ice.soso.common.ResultUtils;
import com.ice.soso.model.dto.post.PostEsDTO;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class SuggestController {
    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @GetMapping("/search/getSearchPrompt")
    public BaseResponse<List<String>> getSearchPrompt(@RequestParam String keyword) {
        SuggestBuilder suggestBuilder = new SuggestBuilder()
                .addSuggestion("suggestionTitle", new CompletionSuggestionBuilder("suggestion")
                        .skipDuplicates(true)
                        .size(5)
                        .prefix(keyword));

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withSuggestBuilder(suggestBuilder)
                .build();

        SearchHits<PostEsDTO> searchHits = elasticsearchRestTemplate.search(searchQuery, PostEsDTO.class);

        List<String> suggestions = new ArrayList<>();
        searchHits.getSearchHits().forEach(hit -> {
            suggestions.add(hit.getContent().getSuggestion().toString());
        });

        return ResultUtils.success(suggestions);
    }
}
