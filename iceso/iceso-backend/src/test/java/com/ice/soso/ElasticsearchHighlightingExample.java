package com.ice.soso;

/**
 * User: hallen
 * Date: 2023/9/28
 * Time: 21:42
 */

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;

import java.io.IOException;

public class ElasticsearchHighlightingExample {
    public static void main(String[] args) throws IOException {
        // 创建 Elasticsearch 客户端
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder("YourElasticsearchServerURL"));

        // 构建查询
        SearchRequest searchRequest = new SearchRequest("your_index_name");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(/* Your Query Here */);

        // 设置高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("field_to_highlight"); // 要高亮的字段
        highlightBuilder.preTags("<em>"); // 高亮起始标签
        highlightBuilder.postTags("</em>"); // 高亮结束标签
        searchSourceBuilder.highlighter(highlightBuilder);

        searchRequest.source(searchSourceBuilder);

        // 执行查询
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        // 处理查询结果，包括高亮部分
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            String documentId = hit.getId();
            // 处理普通字段
            // ...
            // 处理高亮字段
            HighlightField highlightField = hit.getHighlightFields().get("field_to_highlight");
            if (highlightField != null) {
                Text[] fragments = highlightField.fragments();
                String highlightedText = "";
                for (Text fragment : fragments) {
                    highlightedText += fragment;
                }
                System.out.println("Highlighted Text: " + highlightedText);
            }
        }

        // 关闭 Elasticsearch 客户端
        client.close();
    }
}

